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
import espotify.logica.Cliente;
import espotify.logica.Suscripcion;
import espotify.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author tecnologo
 */
public class SuscripcionJpaController implements Serializable {

    public SuscripcionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public SuscripcionJpaController() {
        this.emf = Persistence.createEntityManagerFactory("EspotifyPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Suscripcion suscripcion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente miCliente = suscripcion.getMiCliente();
            if (miCliente != null) {
                miCliente = em.getReference(miCliente.getClass(), miCliente.getNickname());
                suscripcion.setMiCliente(miCliente);
            }
            em.persist(suscripcion);
            if (miCliente != null) {
                Suscripcion oldMiSuscripcionOfMiCliente = miCliente.getMiSuscripcion();
                if (oldMiSuscripcionOfMiCliente != null) {
                    oldMiSuscripcionOfMiCliente.setMiCliente(null);
                    oldMiSuscripcionOfMiCliente = em.merge(oldMiSuscripcionOfMiCliente);
                }
                miCliente.setMiSuscripcion(suscripcion);
                miCliente = em.merge(miCliente);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Suscripcion suscripcion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Suscripcion persistentSuscripcion = em.find(Suscripcion.class, suscripcion.getIdSuscripcion());
            Cliente miClienteOld = persistentSuscripcion.getMiCliente();
            Cliente miClienteNew = suscripcion.getMiCliente();
            if (miClienteNew != null) {
                miClienteNew = em.getReference(miClienteNew.getClass(), miClienteNew.getNickname());
                suscripcion.setMiCliente(miClienteNew);
            }
            suscripcion = em.merge(suscripcion);
            if (miClienteOld != null && !miClienteOld.equals(miClienteNew)) {
                miClienteOld.setMiSuscripcion(null);
                miClienteOld = em.merge(miClienteOld);
            }
            if (miClienteNew != null && !miClienteNew.equals(miClienteOld)) {
                Suscripcion oldMiSuscripcionOfMiCliente = miClienteNew.getMiSuscripcion();
                if (oldMiSuscripcionOfMiCliente != null) {
                    oldMiSuscripcionOfMiCliente.setMiCliente(null);
                    oldMiSuscripcionOfMiCliente = em.merge(oldMiSuscripcionOfMiCliente);
                }
                miClienteNew.setMiSuscripcion(suscripcion);
                miClienteNew = em.merge(miClienteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = suscripcion.getIdSuscripcion();
                if (findSuscripcion(id) == null) {
                    throw new NonexistentEntityException("The suscripcion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Suscripcion suscripcion;
            try {
                suscripcion = em.getReference(Suscripcion.class, id);
                suscripcion.getIdSuscripcion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The suscripcion with id " + id + " no longer exists.", enfe);
            }
            Cliente miCliente = suscripcion.getMiCliente();
            if (miCliente != null) {
                miCliente.setMiSuscripcion(null);
                miCliente = em.merge(miCliente);
            }
            em.remove(suscripcion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Suscripcion> findSuscripcionEntities() {
        return findSuscripcionEntities(true, -1, -1);
    }

    public List<Suscripcion> findSuscripcionEntities(int maxResults, int firstResult) {
        return findSuscripcionEntities(false, maxResults, firstResult);
    }

    private List<Suscripcion> findSuscripcionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Suscripcion.class));
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

    public Suscripcion findSuscripcion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Suscripcion.class, id);
        } finally {
            em.close();
        }
    }

    public int getSuscripcionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Suscripcion> rt = cq.from(Suscripcion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
