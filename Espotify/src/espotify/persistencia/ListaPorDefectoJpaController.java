/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.persistencia;

import espotify.logica.ListaPorDefecto;
import espotify.persistencia.exceptions.NonexistentEntityException;
import espotify.persistencia.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author ms
 */
public class ListaPorDefectoJpaController implements Serializable {

    public ListaPorDefectoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ListaPorDefecto listaPorDefecto) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(listaPorDefecto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findListaPorDefecto(listaPorDefecto.getNombreLista()) != null) {
                throw new PreexistingEntityException("ListaPorDefecto " + listaPorDefecto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ListaPorDefecto listaPorDefecto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            listaPorDefecto = em.merge(listaPorDefecto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = listaPorDefecto.getNombreLista();
                if (findListaPorDefecto(id) == null) {
                    throw new NonexistentEntityException("The listaPorDefecto with id " + id + " no longer exists.");
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
            ListaPorDefecto listaPorDefecto;
            try {
                listaPorDefecto = em.getReference(ListaPorDefecto.class, id);
                listaPorDefecto.getNombreLista();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The listaPorDefecto with id " + id + " no longer exists.", enfe);
            }
            em.remove(listaPorDefecto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ListaPorDefecto> findListaPorDefectoEntities() {
        return findListaPorDefectoEntities(true, -1, -1);
    }

    public List<ListaPorDefecto> findListaPorDefectoEntities(int maxResults, int firstResult) {
        return findListaPorDefectoEntities(false, maxResults, firstResult);
    }

    private List<ListaPorDefecto> findListaPorDefectoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ListaPorDefecto.class));
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

    public ListaPorDefecto findListaPorDefecto(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ListaPorDefecto.class, id);
        } finally {
            em.close();
        }
    }

    public int getListaPorDefectoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ListaPorDefecto> rt = cq.from(ListaPorDefecto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
