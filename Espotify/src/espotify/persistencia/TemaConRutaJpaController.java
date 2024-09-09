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
import espotify.logica.TemaConRuta;
import espotify.logica.TemaID;
import espotify.persistencia.exceptions.NonexistentEntityException;
import espotify.persistencia.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TemaConRutaJpaController implements Serializable {
    
    public TemaConRutaJpaController() {
        emf = Persistence.createEntityManagerFactory("EspotifyPU");
    }
    
    public TemaConRutaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TemaConRuta temaConRuta) throws PreexistingEntityException, Exception {
        if (temaConRuta.getIdTema() == null) {
            temaConRuta.setIdTema(new TemaID());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Album miAlbum = temaConRuta.getMiAlbum();
            if (miAlbum != null) {
                miAlbum = em.getReference(miAlbum.getClass(), miAlbum.getAlbumID());
                temaConRuta.setMiAlbum(miAlbum);
            }
            em.persist(temaConRuta);
            if (miAlbum != null) {
                miAlbum.getMisTemas().add(temaConRuta);
                miAlbum = em.merge(miAlbum);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTemaConRuta(temaConRuta.getIdTema()) != null) {
                throw new PreexistingEntityException("TemaConRuta " + temaConRuta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TemaConRuta temaConRuta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TemaConRuta persistentTemaConRuta = em.find(TemaConRuta.class, temaConRuta.getIdTema());
            Album miAlbumOld = persistentTemaConRuta.getMiAlbum();
            Album miAlbumNew = temaConRuta.getMiAlbum();
            if (miAlbumNew != null) {
                miAlbumNew = em.getReference(miAlbumNew.getClass(), miAlbumNew.getAlbumID());
                temaConRuta.setMiAlbum(miAlbumNew);
            }
            temaConRuta = em.merge(temaConRuta);
            if (miAlbumOld != null && !miAlbumOld.equals(miAlbumNew)) {
                miAlbumOld.getMisTemas().remove(temaConRuta);
                miAlbumOld = em.merge(miAlbumOld);
            }
            if (miAlbumNew != null && !miAlbumNew.equals(miAlbumOld)) {
                miAlbumNew.getMisTemas().add(temaConRuta);
                miAlbumNew = em.merge(miAlbumNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                TemaID id = temaConRuta.getIdTema();
                if (findTemaConRuta(id) == null) {
                    throw new NonexistentEntityException("The temaConRuta with id " + id + " no longer exists.");
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
            TemaConRuta temaConRuta;
            try {
                temaConRuta = em.getReference(TemaConRuta.class, id);
                temaConRuta.getIdTema();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The temaConRuta with id " + id + " no longer exists.", enfe);
            }
            Album miAlbum = temaConRuta.getMiAlbum();
            if (miAlbum != null) {
                miAlbum.getMisTemas().remove(temaConRuta);
                miAlbum = em.merge(miAlbum);
            }
            em.remove(temaConRuta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TemaConRuta> findTemaConRutaEntities() {
        return findTemaConRutaEntities(true, -1, -1);
    }

    public List<TemaConRuta> findTemaConRutaEntities(int maxResults, int firstResult) {
        return findTemaConRutaEntities(false, maxResults, firstResult);
    }

    private List<TemaConRuta> findTemaConRutaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TemaConRuta.class));
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

    public TemaConRuta findTemaConRuta(TemaID id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TemaConRuta.class, id);
        } finally {
            em.close();
        }
    }

    public int getTemaConRutaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TemaConRuta> rt = cq.from(TemaConRuta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
