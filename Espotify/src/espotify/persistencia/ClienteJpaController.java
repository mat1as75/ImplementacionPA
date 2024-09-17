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
import espotify.logica.ListaParticular;
import java.util.ArrayList;
import java.util.List;
import espotify.logica.Usuario;
import espotify.persistencia.exceptions.NonexistentEntityException;
import espotify.persistencia.exceptions.PreexistingEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author tecnologo
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public ClienteJpaController() {
        this.emf = Persistence.createEntityManagerFactory("EspotifyPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) throws PreexistingEntityException, Exception {
        if (cliente.getMisListasReproduccionCreadas() == null) {
            cliente.setMisListasReproduccionCreadasCompleta(new ArrayList<ListaParticular>());
        }
        if (cliente.getMisSeguidores() == null) {
            cliente.setMisSeguidoresCompletos(new ArrayList<Usuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<ListaParticular> attachedMisListasReproduccionCreadas = new ArrayList<ListaParticular>();
            for (ListaParticular misListasReproduccionCreadasListaParticularToAttach : cliente.getMisListasReproduccionCreadas()) {
                misListasReproduccionCreadasListaParticularToAttach = em.getReference(misListasReproduccionCreadasListaParticularToAttach.getClass(), misListasReproduccionCreadasListaParticularToAttach.getNombreLista());
                attachedMisListasReproduccionCreadas.add(misListasReproduccionCreadasListaParticularToAttach);
            }
            cliente.setMisListasReproduccionCreadasCompleta(attachedMisListasReproduccionCreadas);
            List<Usuario> attachedMisSeguidores = new ArrayList<Usuario>();
            for (Usuario misSeguidoresUsuarioToAttach : cliente.getMisSeguidores()) {
                misSeguidoresUsuarioToAttach = em.getReference(misSeguidoresUsuarioToAttach.getClass(), misSeguidoresUsuarioToAttach.getNickname());
                attachedMisSeguidores.add(misSeguidoresUsuarioToAttach);
            }
            cliente.setMisSeguidoresCompletos(attachedMisSeguidores);
            em.persist(cliente);
            for (ListaParticular misListasReproduccionCreadasListaParticular : cliente.getMisListasReproduccionCreadas()) {
                misListasReproduccionCreadasListaParticular.getCliente().add(cliente);
                misListasReproduccionCreadasListaParticular = em.merge(misListasReproduccionCreadasListaParticular);
            }
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
            List<ListaParticular> misListasReproduccionCreadasOld = persistentCliente.getMisListasReproduccionCreadas();
            List<ListaParticular> misListasReproduccionCreadasNew = cliente.getMisListasReproduccionCreadas();
            List<Usuario> misSeguidoresOld = persistentCliente.getMisSeguidores();
            List<Usuario> misSeguidoresNew = cliente.getMisSeguidores();
            List<ListaParticular> attachedMisListasReproduccionCreadasNew = new ArrayList<ListaParticular>();
            for (ListaParticular misListasReproduccionCreadasNewListaParticularToAttach : misListasReproduccionCreadasNew) {
                misListasReproduccionCreadasNewListaParticularToAttach = em.getReference(misListasReproduccionCreadasNewListaParticularToAttach.getClass(), misListasReproduccionCreadasNewListaParticularToAttach.getNombreLista());
                attachedMisListasReproduccionCreadasNew.add(misListasReproduccionCreadasNewListaParticularToAttach);
            }
            misListasReproduccionCreadasNew = attachedMisListasReproduccionCreadasNew;
            cliente.setMisListasReproduccionCreadasCompleta(misListasReproduccionCreadasNew);
            List<Usuario> attachedMisSeguidoresNew = new ArrayList<Usuario>();
            for (Usuario misSeguidoresNewUsuarioToAttach : misSeguidoresNew) {
                misSeguidoresNewUsuarioToAttach = em.getReference(misSeguidoresNewUsuarioToAttach.getClass(), misSeguidoresNewUsuarioToAttach.getNickname());
                attachedMisSeguidoresNew.add(misSeguidoresNewUsuarioToAttach);
            }
            misSeguidoresNew = attachedMisSeguidoresNew;
            cliente.setMisSeguidoresCompletos(misSeguidoresNew);
            cliente = em.merge(cliente);
            for (ListaParticular misListasReproduccionCreadasOldListaParticular : misListasReproduccionCreadasOld) {
                if (!misListasReproduccionCreadasNew.contains(misListasReproduccionCreadasOldListaParticular)) {
                    misListasReproduccionCreadasOldListaParticular.getCliente().remove(cliente);
                    misListasReproduccionCreadasOldListaParticular = em.merge(misListasReproduccionCreadasOldListaParticular);
                }
            }
            for (ListaParticular misListasReproduccionCreadasNewListaParticular : misListasReproduccionCreadasNew) {
                if (!misListasReproduccionCreadasOld.contains(misListasReproduccionCreadasNewListaParticular)) {
                    misListasReproduccionCreadasNewListaParticular.getCliente().add(cliente);
                    misListasReproduccionCreadasNewListaParticular = em.merge(misListasReproduccionCreadasNewListaParticular);
                }
            }
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
            List<ListaParticular> misListasReproduccionCreadas = cliente.getMisListasReproduccionCreadas();
            for (ListaParticular misListasReproduccionCreadasListaParticular : misListasReproduccionCreadas) {
                misListasReproduccionCreadasListaParticular.getCliente().remove(cliente);
                misListasReproduccionCreadasListaParticular = em.merge(misListasReproduccionCreadasListaParticular);
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
