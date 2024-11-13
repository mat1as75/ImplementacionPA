/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.persistencia;

import espotify.logica.RegistroAcceso;
import espotify.persistencia.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author mat1as
 */
public class RegistroAccesoJpaController implements Serializable {

    public RegistroAccesoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public RegistroAccesoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("EspotifyPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RegistroAcceso registroAcceso) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(registroAcceso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RegistroAcceso registroAcceso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            registroAcceso = em.merge(registroAcceso);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = registroAcceso.getIdRegistro();
                if (findRegistroAcceso(id) == null) {
                    throw new NonexistentEntityException("The registroAcceso with id " + id + " no longer exists.");
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
            RegistroAcceso registroAcceso;
            try {
                registroAcceso = em.getReference(RegistroAcceso.class, id);
                registroAcceso.getIdRegistro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registroAcceso with id " + id + " no longer exists.", enfe);
            }
            em.remove(registroAcceso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RegistroAcceso> findRegistroAccesoEntities() {
        return findRegistroAccesoEntities(true, -1, -1);
    }

    public List<RegistroAcceso> findRegistroAccesoEntities(int maxResults, int firstResult) {
        return findRegistroAccesoEntities(false, maxResults, firstResult);
    }

    private List<RegistroAcceso> findRegistroAccesoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RegistroAcceso.class));
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

    public RegistroAcceso findRegistroAcceso(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RegistroAcceso.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistroAccesoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RegistroAcceso> rt = cq.from(RegistroAcceso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
