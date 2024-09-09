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
import espotify.logica.Album;
import espotify.logica.TemaConURL;
import espotify.logica.TemaID;
import espotify.persistencia.exceptions.NonexistentEntityException;
import espotify.persistencia.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author mat
 */
public class TemaConURLJpaController implements Serializable {

    public TemaConURLJpaController() {
        emf = Persistence.createEntityManagerFactory("EspotifyPU");
    }
    
    public TemaConURLJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TemaConURL temaConURL) throws PreexistingEntityException, Exception {
        if (temaConURL.getIdTema() == null) {
            temaConURL.setIdTema(new TemaID());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Album miAlbum = temaConURL.getMiAlbum();
            if (miAlbum != null) {
                miAlbum = em.getReference(miAlbum.getClass(), miAlbum.getAlbumID());
                temaConURL.setMiAlbum(miAlbum);
            }
            em.persist(temaConURL);
            if (miAlbum != null) {
                miAlbum.getMisTemas().add(temaConURL);
                miAlbum = em.merge(miAlbum);
            }
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
            TemaConURL persistentTemaConURL = em.find(TemaConURL.class, temaConURL.getIdTema());
            Album miAlbumOld = persistentTemaConURL.getMiAlbum();
            Album miAlbumNew = temaConURL.getMiAlbum();
            if (miAlbumNew != null) {
                miAlbumNew = em.getReference(miAlbumNew.getClass(), miAlbumNew.getAlbumID());
                temaConURL.setMiAlbum(miAlbumNew);
            }
            temaConURL = em.merge(temaConURL);
            if (miAlbumOld != null && !miAlbumOld.equals(miAlbumNew)) {
                miAlbumOld.getMisTemas().remove(temaConURL);
                miAlbumOld = em.merge(miAlbumOld);
            }
            if (miAlbumNew != null && !miAlbumNew.equals(miAlbumOld)) {
                miAlbumNew.getMisTemas().add(temaConURL);
                miAlbumNew = em.merge(miAlbumNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                TemaID id = temaConURL.getIdTema();
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

    public void destroy(TemaID id) throws NonexistentEntityException {
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
            Album miAlbum = temaConURL.getMiAlbum();
            if (miAlbum != null) {
                miAlbum.getMisTemas().remove(temaConURL);
                miAlbum = em.merge(miAlbum);
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

    public TemaConURL findTemaConURL(TemaID id) {
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
