/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.persistencia;

import espotify.logica.TemaConURL;
import espotify.persistencia.exceptions.NonexistentEntityException;
import espotify.persistencia.exceptions.PreexistingEntityException;
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
 * @author mat
 */
public class TemaConURLJpaController implements Serializable {

    public TemaConURLJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public TemaConURLJpaController() {
        this.emf = Persistence.createEntityManagerFactory("EspotifyPU");
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TemaConURL temaConURL) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(temaConURL);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTemaConURL(temaConURL.getIdTema()) != null) {
                throw new PreexistingEntityException("TemaConURL " + temaConURL + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TemaConURL temaConURL) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            temaConURL = em.merge(temaConURL);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = temaConURL.getIdTema();
                if (findTemaConURL(id) == null) {
                    throw new NonexistentEntityException("The temaConURL with id " + id + " no longer exists.");
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
            TemaConURL temaConURL;
            try {
                temaConURL = em.getReference(TemaConURL.class, id);
                temaConURL.getIdTema();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The temaConURL with id " + id + " no longer exists.", enfe);
            }
            em.remove(temaConURL);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TemaConURL> findTemaConURLEntities() {
        return findTemaConURLEntities(true, -1, -1);
    }

    public List<TemaConURL> findTemaConURLEntities(int maxResults, int firstResult) {
        return findTemaConURLEntities(false, maxResults, firstResult);
    }

    private List<TemaConURL> findTemaConURLEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TemaConURL.class));
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

    public TemaConURL findTemaConURL(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TemaConURL.class, id);
        } finally {
            em.close();
        }
    }

    public int getTemaConURLCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TemaConURL> rt = cq.from(TemaConURL.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
