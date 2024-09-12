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
import espotify.logica.Genero;
import espotify.logica.Album;
import espotify.persistencia.exceptions.NonexistentEntityException;
import espotify.persistencia.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author tecnologo
 */
public class GeneroJpaController implements Serializable {

    public GeneroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public GeneroJpaController() {
        this.emf = Persistence.createEntityManagerFactory("EspotifyPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Genero genero) throws PreexistingEntityException, Exception {
        if (genero.getMisAlbumes() == null) {
            genero.setListaMisAlbumes(new ArrayList<Album>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Genero miPadre = genero.getMiPadre();
            if (miPadre != null) {
                miPadre = em.getReference(miPadre.getClass(), miPadre.getNombreGenero());
                genero.setMiPadre(miPadre);
            }
            List<Album> attachedMisAlbumes = new ArrayList<Album>();
            for (Album misAlbumesAlbumToAttach : genero.getMisAlbumes()) {
                misAlbumesAlbumToAttach = em.getReference(misAlbumesAlbumToAttach.getClass(), misAlbumesAlbumToAttach.getIdAlbum());
                attachedMisAlbumes.add(misAlbumesAlbumToAttach);
            }
            genero.setListaMisAlbumes(attachedMisAlbumes);
            em.persist(genero);
            if (miPadre != null) {
                Genero oldMiPadreOfMiPadre = miPadre.getMiPadre();
                if (oldMiPadreOfMiPadre != null) {
                    oldMiPadreOfMiPadre.setMiPadre(null);
                    oldMiPadreOfMiPadre = em.merge(oldMiPadreOfMiPadre);
                }
                miPadre.setMiPadre(genero);
                miPadre = em.merge(miPadre);
            }
            for (Album misAlbumesAlbum : genero.getMisAlbumes()) {
                misAlbumesAlbum.getMisGeneros().add(genero);
                misAlbumesAlbum = em.merge(misAlbumesAlbum);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findGenero(genero.getNombreGenero()) != null) {
                throw new PreexistingEntityException("Genero " + genero + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Genero genero) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Genero persistentGenero = em.find(Genero.class, genero.getNombreGenero());
            Genero miPadreOld = persistentGenero.getMiPadre();
            Genero miPadreNew = genero.getMiPadre();
            List<Album> misAlbumesOld = persistentGenero.getMisAlbumes();
            List<Album> misAlbumesNew = genero.getMisAlbumes();
            if (miPadreNew != null) {
                miPadreNew = em.getReference(miPadreNew.getClass(), miPadreNew.getNombreGenero());
                genero.setMiPadre(miPadreNew);
            }
            List<Album> attachedMisAlbumesNew = new ArrayList<Album>();
            for (Album misAlbumesNewAlbumToAttach : misAlbumesNew) {
                misAlbumesNewAlbumToAttach = em.getReference(misAlbumesNewAlbumToAttach.getClass(), misAlbumesNewAlbumToAttach.getIdAlbum());
                attachedMisAlbumesNew.add(misAlbumesNewAlbumToAttach);
            }
            misAlbumesNew = attachedMisAlbumesNew;
            genero.setListaMisAlbumes(misAlbumesNew);
            genero = em.merge(genero);
            if (miPadreOld != null && !miPadreOld.equals(miPadreNew)) {
                miPadreOld.setMiPadre(null);
                miPadreOld = em.merge(miPadreOld);
            }
            if (miPadreNew != null && !miPadreNew.equals(miPadreOld)) {
                Genero oldMiPadreOfMiPadre = miPadreNew.getMiPadre();
                if (oldMiPadreOfMiPadre != null) {
                    oldMiPadreOfMiPadre.setMiPadre(null);
                    oldMiPadreOfMiPadre = em.merge(oldMiPadreOfMiPadre);
                }
                miPadreNew.setMiPadre(genero);
                miPadreNew = em.merge(miPadreNew);
            }
            for (Album misAlbumesOldAlbum : misAlbumesOld) {
                if (!misAlbumesNew.contains(misAlbumesOldAlbum)) {
                    misAlbumesOldAlbum.getMisGeneros().remove(genero);
                    misAlbumesOldAlbum = em.merge(misAlbumesOldAlbum);
                }
            }
            for (Album misAlbumesNewAlbum : misAlbumesNew) {
                if (!misAlbumesOld.contains(misAlbumesNewAlbum)) {
                    misAlbumesNewAlbum.getMisGeneros().add(genero);
                    misAlbumesNewAlbum = em.merge(misAlbumesNewAlbum);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = genero.getNombreGenero();
                if (findGenero(id) == null) {
                    throw new NonexistentEntityException("The genero with id " + id + " no longer exists.");
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
            Genero genero;
            try {
                genero = em.getReference(Genero.class, id);
                genero.getNombreGenero();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The genero with id " + id + " no longer exists.", enfe);
            }
            Genero miPadre = genero.getMiPadre();
            if (miPadre != null) {
                miPadre.setMiPadre(null);
                miPadre = em.merge(miPadre);
            }
            List<Album> misAlbumes = genero.getMisAlbumes();
            for (Album misAlbumesAlbum : misAlbumes) {
                misAlbumesAlbum.getMisGeneros().remove(genero);
                misAlbumesAlbum = em.merge(misAlbumesAlbum);
            }
            em.remove(genero);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Genero> findGeneroEntities() {
        return findGeneroEntities(true, -1, -1);
    }

    public List<Genero> findGeneroEntities(int maxResults, int firstResult) {
        return findGeneroEntities(false, maxResults, firstResult);
    }

    private List<Genero> findGeneroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Genero.class));
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

    public Genero findGenero(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Genero.class, id);
        } finally {
            em.close();
        }
    }

    public int getGeneroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Genero> rt = cq.from(Genero.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
