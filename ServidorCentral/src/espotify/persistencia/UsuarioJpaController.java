/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import espotify.logica.Usuario;
import espotify.persistencia.exceptions.NonexistentEntityException;
import espotify.persistencia.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

/**
 *
 * @author tecnologo
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public UsuarioJpaController() {
        this.emf = Persistence.createEntityManagerFactory("EspotifyPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws PreexistingEntityException, Exception {
        if (usuario.getMisSeguidores() == null) {
            usuario.setMisSeguidoresCompletos(new ArrayList<Usuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Usuario> attachedMisSeguidores = new ArrayList<Usuario>();
            for (Usuario misSeguidoresUsuarioToAttach : usuario.getMisSeguidores()) {
                misSeguidoresUsuarioToAttach = em.getReference(misSeguidoresUsuarioToAttach.getClass(), misSeguidoresUsuarioToAttach.getNickname());
                attachedMisSeguidores.add(misSeguidoresUsuarioToAttach);
            }
            usuario.setMisSeguidoresCompletos(attachedMisSeguidores);
            em.persist(usuario);
            for (Usuario misSeguidoresUsuario : usuario.getMisSeguidores()) {
                misSeguidoresUsuario.getMisSeguidores().add(usuario);
                misSeguidoresUsuario = em.merge(misSeguidoresUsuario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuario(usuario.getNickname()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getNickname());
            List<Usuario> misSeguidoresOld = persistentUsuario.getMisSeguidores();
            List<Usuario> misSeguidoresNew = usuario.getMisSeguidores();
            List<Usuario> attachedMisSeguidoresNew = new ArrayList<Usuario>();
            for (Usuario misSeguidoresNewUsuarioToAttach : misSeguidoresNew) {
                misSeguidoresNewUsuarioToAttach = em.getReference(misSeguidoresNewUsuarioToAttach.getClass(), misSeguidoresNewUsuarioToAttach.getNickname());
                attachedMisSeguidoresNew.add(misSeguidoresNewUsuarioToAttach);
            }
            misSeguidoresNew = attachedMisSeguidoresNew;
            usuario.setMisSeguidoresCompletos(misSeguidoresNew);
            usuario = em.merge(usuario);
            for (Usuario misSeguidoresOldUsuario : misSeguidoresOld) {
                if (!misSeguidoresNew.contains(misSeguidoresOldUsuario)) {
                    misSeguidoresOldUsuario.getMisSeguidores().remove(usuario);
                    misSeguidoresOldUsuario = em.merge(misSeguidoresOldUsuario);
                }
            }
            for (Usuario misSeguidoresNewUsuario : misSeguidoresNew) {
                if (!misSeguidoresOld.contains(misSeguidoresNewUsuario)) {
                    misSeguidoresNewUsuario.getMisSeguidores().add(usuario);
                    misSeguidoresNewUsuario = em.merge(misSeguidoresNewUsuario);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = usuario.getNickname();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getNickname();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<Usuario> misSeguidores = usuario.getMisSeguidores();
            for (Usuario misSeguidoresUsuario : misSeguidores) {
                misSeguidoresUsuario.getMisSeguidores().remove(usuario);
                misSeguidoresUsuario = em.merge(misSeguidoresUsuario);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Usuario findUsuario(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public Usuario findUsuarioByEmail(String email) {
        EntityManager em = getEntityManager();
        try {
            return (Usuario) em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class)
                    .setParameter("email", email)
                    .getSingleResult(); /* Aunq el sistema esta diseña para que no se repitan Emails, 
                                            este atributo no es parte de la clase primaria */
        } catch (NoResultException e) {
            return null; /* Retorna null si no se encuentra */
        } finally {
            em.close();
        }
    }
    
    public Usuario findUsuarioByIdentifier(String identificador) { 
        EntityManager em = getEntityManager();
        try {
            if (identificador == null)
                return null;

            if (esEmail(identificador))
                return findUsuarioByEmail(identificador);
            else 
                return findUsuario(identificador);
        } finally {
            em.close();
        }
    }

    public boolean esEmail(String identificador) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        
        return pattern.matcher(identificador).matches();
    }
    
    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
