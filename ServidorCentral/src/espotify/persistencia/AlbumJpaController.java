/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.persistencia;

import espotify.logica.Album;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import espotify.logica.Tema;
import java.util.ArrayList;
import java.util.List;
import espotify.logica.Genero;
import espotify.persistencia.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author tecnologo
 */
public class AlbumJpaController implements Serializable {

    public AlbumJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public AlbumJpaController() {
        this.emf = Persistence.createEntityManagerFactory("EspotifyPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Album album) {
        if (album.getMisTemas() == null) {
            album.setMisTemas(new ArrayList<Tema>());
        }
        if (album.getMisGeneros() == null) {
            album.setMisGeneros(new ArrayList<Genero>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Tema> attachedMisTemas = new ArrayList<Tema>();
            for (Tema misTemasTemaToAttach : album.getMisTemas()) {
                misTemasTemaToAttach = em.getReference(misTemasTemaToAttach.getClass(), misTemasTemaToAttach.getIdTema());
                attachedMisTemas.add(misTemasTemaToAttach);
            }
            album.setMisTemas(attachedMisTemas);
            List<Genero> attachedMisGeneros = new ArrayList<Genero>();
            for (Genero misGenerosGeneroToAttach : album.getMisGeneros()) {
                misGenerosGeneroToAttach = em.getReference(misGenerosGeneroToAttach.getClass(), misGenerosGeneroToAttach.getNombreGenero());
                attachedMisGeneros.add(misGenerosGeneroToAttach);
            }
            album.setMisGeneros(attachedMisGeneros);
            em.persist(album);
            for (Tema misTemasTema : album.getMisTemas()) {
                Album oldMiAlbumOfMisTemasTema = misTemasTema.getMiAlbum();
                misTemasTema.setMiAlbum(album);
                misTemasTema = em.merge(misTemasTema);
                if (oldMiAlbumOfMisTemasTema != null) {
                    oldMiAlbumOfMisTemasTema.getMisTemas().remove(misTemasTema);
                    oldMiAlbumOfMisTemasTema = em.merge(oldMiAlbumOfMisTemasTema);
                }
            }
            for (Genero misGenerosGenero : album.getMisGeneros()) {
                misGenerosGenero.getMisAlbumes().add(album);
                misGenerosGenero = em.merge(misGenerosGenero);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Album album) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Album persistentAlbum = em.find(Album.class, album.getIdAlbum());
            List<Tema> misTemasOld = persistentAlbum.getMisTemas();
            List<Tema> misTemasNew = album.getMisTemas();
            List<Genero> misGenerosOld = persistentAlbum.getMisGeneros();
            List<Genero> misGenerosNew = album.getMisGeneros();
            List<Tema> attachedMisTemasNew = new ArrayList<Tema>();
            for (Tema misTemasNewTemaToAttach : misTemasNew) {
                misTemasNewTemaToAttach = em.getReference(misTemasNewTemaToAttach.getClass(), misTemasNewTemaToAttach.getIdTema());
                attachedMisTemasNew.add(misTemasNewTemaToAttach);
            }
            misTemasNew = attachedMisTemasNew;
            album.setMisTemas(misTemasNew);
            List<Genero> attachedMisGenerosNew = new ArrayList<Genero>();
            for (Genero misGenerosNewGeneroToAttach : misGenerosNew) {
                misGenerosNewGeneroToAttach = em.getReference(misGenerosNewGeneroToAttach.getClass(), misGenerosNewGeneroToAttach.getNombreGenero());
                attachedMisGenerosNew.add(misGenerosNewGeneroToAttach);
            }
            misGenerosNew = attachedMisGenerosNew;
            album.setMisGeneros(misGenerosNew);
            album = em.merge(album);
            for (Tema misTemasOldTema : misTemasOld) {
                if (!misTemasNew.contains(misTemasOldTema)) {
                    misTemasOldTema.setMiAlbum(null);
                    misTemasOldTema = em.merge(misTemasOldTema);
                }
            }
            for (Tema misTemasNewTema : misTemasNew) {
                if (!misTemasOld.contains(misTemasNewTema)) {
                    Album oldMiAlbumOfMisTemasNewTema = misTemasNewTema.getMiAlbum();
                    misTemasNewTema.setMiAlbum(album);
                    misTemasNewTema = em.merge(misTemasNewTema);
                    if (oldMiAlbumOfMisTemasNewTema != null && !oldMiAlbumOfMisTemasNewTema.equals(album)) {
                        oldMiAlbumOfMisTemasNewTema.getMisTemas().remove(misTemasNewTema);
                        oldMiAlbumOfMisTemasNewTema = em.merge(oldMiAlbumOfMisTemasNewTema);
                    }
                }
            }
            for (Genero misGenerosOldGenero : misGenerosOld) {
                if (!misGenerosNew.contains(misGenerosOldGenero)) {
                    misGenerosOldGenero.getMisAlbumes().remove(album);
                    misGenerosOldGenero = em.merge(misGenerosOldGenero);
                }
            }
            for (Genero misGenerosNewGenero : misGenerosNew) {
                if (!misGenerosOld.contains(misGenerosNewGenero)) {
                    misGenerosNewGenero.getMisAlbumes().add(album);
                    misGenerosNewGenero = em.merge(misGenerosNewGenero);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = album.getIdAlbum();
                if (findAlbum(id) == null) {
                    throw new NonexistentEntityException("The album with id " + id + " no longer exists.");
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
            Album album;
            try {
                album = em.getReference(Album.class, id);
                album.getIdAlbum();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The album with id " + id + " no longer exists.", enfe);
            }
            List<Tema> misTemas = album.getMisTemas();
            for (Tema misTemasTema : misTemas) {
                misTemasTema.setMiAlbum(null);
                misTemasTema = em.merge(misTemasTema);
            }
            List<Genero> misGeneros = album.getMisGeneros();
            for (Genero misGenerosGenero : misGeneros) {
                misGenerosGenero.getMisAlbumes().remove(album);
                misGenerosGenero = em.merge(misGenerosGenero);
            }
            em.remove(album);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Album> findAlbumEntities() {
        return findAlbumEntities(true, -1, -1);
    }

    public List<Album> findAlbumEntities(int maxResults, int firstResult) {
        return findAlbumEntities(false, maxResults, firstResult);
    }

    private List<Album> findAlbumEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Album.class));
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

    public Album findAlbum(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Album.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlbumCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Album> rt = cq.from(Album.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
