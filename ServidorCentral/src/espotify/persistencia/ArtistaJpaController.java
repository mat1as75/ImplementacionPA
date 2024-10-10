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
import espotify.logica.Artista;
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
public class ArtistaJpaController implements Serializable {

    public ArtistaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public ArtistaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("EspotifyPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Artista artista) throws PreexistingEntityException, Exception {
        if (artista.getMisAlbumesPublicados() == null) {
            artista.setMisAlbumesPublicados(new ArrayList<Album>());
        }
        if (artista.getMisSeguidores() == null) {
            artista.setMisSeguidoresCompletos(new ArrayList<Usuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Album> attachedMisAlbumesPublicados = new ArrayList<Album>();
            for (Album misAlbumesPublicadosAlbumToAttach : artista.getMisAlbumesPublicados()) {
                misAlbumesPublicadosAlbumToAttach = em.getReference(misAlbumesPublicadosAlbumToAttach.getClass(), misAlbumesPublicadosAlbumToAttach.getIdAlbum());
                attachedMisAlbumesPublicados.add(misAlbumesPublicadosAlbumToAttach);
            }
            artista.setMisAlbumesPublicados(attachedMisAlbumesPublicados);
            List<Usuario> attachedMisSeguidores = new ArrayList<Usuario>();
            for (Usuario misSeguidoresUsuarioToAttach : artista.getMisSeguidores()) {
                misSeguidoresUsuarioToAttach = em.getReference(misSeguidoresUsuarioToAttach.getClass(), misSeguidoresUsuarioToAttach.getNickname());
                attachedMisSeguidores.add(misSeguidoresUsuarioToAttach);
            }
            artista.setMisSeguidoresCompletos(attachedMisSeguidores);
            em.persist(artista);
            for (Album misAlbumesPublicadosAlbum : artista.getMisAlbumesPublicados()) {
                espotify.logica.Artista oldMiArtistaOfMisAlbumesPublicadosAlbum = (espotify.logica.Artista) misAlbumesPublicadosAlbum.getMiArtista();
                misAlbumesPublicadosAlbum.setMiArtista(artista);
                misAlbumesPublicadosAlbum = em.merge(misAlbumesPublicadosAlbum);
                if (oldMiArtistaOfMisAlbumesPublicadosAlbum != null) {
                    oldMiArtistaOfMisAlbumesPublicadosAlbum.getMisAlbumesPublicados().remove(misAlbumesPublicadosAlbum);
                    oldMiArtistaOfMisAlbumesPublicadosAlbum = em.merge(oldMiArtistaOfMisAlbumesPublicadosAlbum);
                }
            }
            for (Usuario misSeguidoresUsuario : artista.getMisSeguidores()) {
                misSeguidoresUsuario.getMisSeguidores().add(artista);
                misSeguidoresUsuario = em.merge(misSeguidoresUsuario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findArtista(artista.getNickname()) != null) {
                throw new PreexistingEntityException("Artista " + artista + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Artista artista) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Artista persistentArtista = em.find(Artista.class, artista.getNickname());
            List<Album> misAlbumesPublicadosOld = persistentArtista.getMisAlbumesPublicados();
            List<Album> misAlbumesPublicadosNew = artista.getMisAlbumesPublicados();
            List<Usuario> misSeguidoresOld = persistentArtista.getMisSeguidores();
            List<Usuario> misSeguidoresNew = artista.getMisSeguidores();
            List<Album> attachedMisAlbumesPublicadosNew = new ArrayList<Album>();
            for (Album misAlbumesPublicadosNewAlbumToAttach : misAlbumesPublicadosNew) {
                misAlbumesPublicadosNewAlbumToAttach = em.getReference(misAlbumesPublicadosNewAlbumToAttach.getClass(), misAlbumesPublicadosNewAlbumToAttach.getIdAlbum());
                attachedMisAlbumesPublicadosNew.add(misAlbumesPublicadosNewAlbumToAttach);
            }
            misAlbumesPublicadosNew = attachedMisAlbumesPublicadosNew;
            artista.setMisAlbumesPublicados(misAlbumesPublicadosNew);
            List<Usuario> attachedMisSeguidoresNew = new ArrayList<Usuario>();
            for (Usuario misSeguidoresNewUsuarioToAttach : misSeguidoresNew) {
                misSeguidoresNewUsuarioToAttach = em.getReference(misSeguidoresNewUsuarioToAttach.getClass(), misSeguidoresNewUsuarioToAttach.getNickname());
                attachedMisSeguidoresNew.add(misSeguidoresNewUsuarioToAttach);
            }
            misSeguidoresNew = attachedMisSeguidoresNew;
            artista.setMisSeguidoresCompletos(misSeguidoresNew);
            artista = em.merge(artista);
            for (Album misAlbumesPublicadosOldAlbum : misAlbumesPublicadosOld) {
                if (!misAlbumesPublicadosNew.contains(misAlbumesPublicadosOldAlbum)) {
                    misAlbumesPublicadosOldAlbum.setMiArtista(null);
                    misAlbumesPublicadosOldAlbum = em.merge(misAlbumesPublicadosOldAlbum);
                }
            }
            for (Album misAlbumesPublicadosNewAlbum : misAlbumesPublicadosNew) {
                if (!misAlbumesPublicadosOld.contains(misAlbumesPublicadosNewAlbum)) {
                    Artista oldMiArtistaOfMisAlbumesPublicadosNewAlbum = (Artista) misAlbumesPublicadosNewAlbum.getMiArtista();
                    misAlbumesPublicadosNewAlbum.setMiArtista(artista);
                    misAlbumesPublicadosNewAlbum = em.merge(misAlbumesPublicadosNewAlbum);
                    if (oldMiArtistaOfMisAlbumesPublicadosNewAlbum != null && !oldMiArtistaOfMisAlbumesPublicadosNewAlbum.equals(artista)) {
                        oldMiArtistaOfMisAlbumesPublicadosNewAlbum.getMisAlbumesPublicados().remove(misAlbumesPublicadosNewAlbum);
                        oldMiArtistaOfMisAlbumesPublicadosNewAlbum = em.merge(oldMiArtistaOfMisAlbumesPublicadosNewAlbum);
                    }
                }
            }
            for (Usuario misSeguidoresOldUsuario : misSeguidoresOld) {
                if (!misSeguidoresNew.contains(misSeguidoresOldUsuario)) {
                    misSeguidoresOldUsuario.getMisSeguidores().remove(artista);
                    misSeguidoresOldUsuario = em.merge(misSeguidoresOldUsuario);
                }
            }
            for (Usuario misSeguidoresNewUsuario : misSeguidoresNew) {
                if (!misSeguidoresOld.contains(misSeguidoresNewUsuario)) {
                    misSeguidoresNewUsuario.getMisSeguidores().add(artista);
                    misSeguidoresNewUsuario = em.merge(misSeguidoresNewUsuario);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = artista.getNickname();
                if (findArtista(id) == null) {
                    throw new NonexistentEntityException("The artista with id " + id + " no longer exists.");
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
            Artista artista;
            try {
                artista = em.getReference(Artista.class, id);
                artista.getNickname();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The artista with id " + id + " no longer exists.", enfe);
            }
            List<Album> misAlbumesPublicados = artista.getMisAlbumesPublicados();
            for (Album misAlbumesPublicadosAlbum : misAlbumesPublicados) {
                misAlbumesPublicadosAlbum.setMiArtista(null);
                misAlbumesPublicadosAlbum = em.merge(misAlbumesPublicadosAlbum);
            }
            List<Usuario> misSeguidores = artista.getMisSeguidores();
            for (Usuario misSeguidoresUsuario : misSeguidores) {
                misSeguidoresUsuario.getMisSeguidores().remove(artista);
                misSeguidoresUsuario = em.merge(misSeguidoresUsuario);
            }
            em.remove(artista);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Artista> findArtistaEntities() {
        return findArtistaEntities(true, -1, -1);
    }

    public List<Artista> findArtistaEntities(int maxResults, int firstResult) {
        return findArtistaEntities(false, maxResults, firstResult);
    }

    private List<Artista> findArtistaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Artista.class));
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

    public Artista findArtista(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Artista.class, id);
        } finally {
            em.close();
        }
    }

    public int getArtistaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Artista> rt = cq.from(Artista.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
