/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.persistencia;

import espotify.logica.Cliente;
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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author usuario
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    // 1 de Singleton
    public ClienteJpaController(){
        emf = Persistence.createEntityManagerFactory("EspotifyPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    // 2 de Singleton
    private static ClienteJpaController instancia = null;
    
    // 3 de Singleton
    public static ClienteJpaController getInstance() {
        if (ClienteJpaController.instancia == null)
            ClienteJpaController.instancia = new ClienteJpaController();
        
        return (ClienteJpaController.instancia);
    }

    public void create(Cliente cliente) throws PreexistingEntityException, Exception {
        if (cliente.getMisSeguidores() == null) {
            cliente.setMisSeguidores(new ArrayList<Usuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Usuario> attachedMisSeguidores = new ArrayList<Usuario>();
            for (Usuario misSeguidoresUsuarioToAttach : cliente.getMisSeguidores()) {
                misSeguidoresUsuarioToAttach = em.getReference(misSeguidoresUsuarioToAttach.getClass(), misSeguidoresUsuarioToAttach.getNickname());
                attachedMisSeguidores.add(misSeguidoresUsuarioToAttach);
            }
            cliente.setMisSeguidores(attachedMisSeguidores);
            em.persist(cliente);
            for (Usuario misSeguidoresUsuario : cliente.getMisSeguidores()) {
                misSeguidoresUsuario.getMisSeguidores().add(cliente);
                misSeguidoresUsuario = em.merge(misSeguidoresUsuario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCliente(cliente.getNickname()) != null) {
                throw new PreexistingEntityException("Cliente " + cliente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getNickname());
            List<Usuario> misSeguidoresOld = persistentCliente.getMisSeguidores();
            List<Usuario> misSeguidoresNew = cliente.getMisSeguidores();
            List<Usuario> attachedMisSeguidoresNew = new ArrayList<Usuario>();
            for (Usuario misSeguidoresNewUsuarioToAttach : misSeguidoresNew) {
                misSeguidoresNewUsuarioToAttach = em.getReference(misSeguidoresNewUsuarioToAttach.getClass(), misSeguidoresNewUsuarioToAttach.getNickname());
                attachedMisSeguidoresNew.add(misSeguidoresNewUsuarioToAttach);
            }
            misSeguidoresNew = attachedMisSeguidoresNew;
            cliente.setMisSeguidores(misSeguidoresNew);
            cliente = em.merge(cliente);
            for (Usuario misSeguidoresOldUsuario : misSeguidoresOld) {
                if (!misSeguidoresNew.contains(misSeguidoresOldUsuario)) {
                    misSeguidoresOldUsuario.getMisSeguidores().remove(cliente);
                    misSeguidoresOldUsuario = em.merge(misSeguidoresOldUsuario);
                }
            }
            for (Usuario misSeguidoresNewUsuario : misSeguidoresNew) {
                if (!misSeguidoresOld.contains(misSeguidoresNewUsuario)) {
                    misSeguidoresNewUsuario.getMisSeguidores().add(cliente);
                    misSeguidoresNewUsuario = em.merge(misSeguidoresNewUsuario);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = cliente.getNickname();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getNickname();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<Usuario> misSeguidores = cliente.getMisSeguidores();
            for (Usuario misSeguidoresUsuario : misSeguidores) {
                misSeguidoresUsuario.getMisSeguidores().remove(cliente);
                misSeguidoresUsuario = em.merge(misSeguidoresUsuario);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
