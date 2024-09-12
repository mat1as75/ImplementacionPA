/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.persistencia;

import espotify.DataTypes.exceptions.NonexistentEntityException;
import espotify.DataTypes.exceptions.PreexistingEntityException;
import espotify.logica.ListaParticular;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import espotify.logica.Tema;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ms
 */
public class ListaParticularJpaController implements Serializable {

    public ListaParticularJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
     public ListaParticularJpaController() {
        this.emf = Persistence.createEntityManagerFactory("EspotifyPU");
    } 
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ListaParticular listaParticular) throws PreexistingEntityException, Exception {
        if (listaParticular.getMisTemas() == null) {
            listaParticular.setMisTemas(new ArrayList<Tema>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Tema> attachedMisTemas = new ArrayList<Tema>();
            for (Tema misTemasTemaToAttach : listaParticular.getMisTemas()) {
                misTemasTemaToAttach = em.getReference(misTemasTemaToAttach.getClass(), misTemasTemaToAttach.getIdTema());
                attachedMisTemas.add(misTemasTemaToAttach);
            }
            listaParticular.setMisTemas(attachedMisTemas);
            em.persist(listaParticular);
            for (Tema misTemasTema : listaParticular.getMisTemas()) {
                misTemasTema.getMisReproducciones().add(listaParticular);
                misTemasTema = em.merge(misTemasTema);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findListaParticular(listaParticular.getNombreLista()) != null) {
                throw new PreexistingEntityException("ListaParticular " + listaParticular + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ListaParticular listaParticular) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ListaParticular persistentListaParticular = em.find(ListaParticular.class, listaParticular.getNombreLista());
            List<Tema> misTemasOld = persistentListaParticular.getMisTemas();
            List<Tema> misTemasNew = listaParticular.getMisTemas();
            List<Tema> attachedMisTemasNew = new ArrayList<Tema>();
            for (Tema misTemasNewTemaToAttach : misTemasNew) {
                misTemasNewTemaToAttach = em.getReference(misTemasNewTemaToAttach.getClass(), misTemasNewTemaToAttach.getIdTema());
                attachedMisTemasNew.add(misTemasNewTemaToAttach);
            }
            misTemasNew = attachedMisTemasNew;
            listaParticular.setMisTemas(misTemasNew);
            listaParticular = em.merge(listaParticular);
            for (Tema misTemasOldTema : misTemasOld) {
                if (!misTemasNew.contains(misTemasOldTema)) {
                    misTemasOldTema.getMisReproducciones().remove(listaParticular);
                    misTemasOldTema = em.merge(misTemasOldTema);
                }
            }
            for (Tema misTemasNewTema : misTemasNew) {
                if (!misTemasOld.contains(misTemasNewTema)) {
                    misTemasNewTema.getMisReproducciones().add(listaParticular);
                    misTemasNewTema = em.merge(misTemasNewTema);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = listaParticular.getNombreLista();
                if (findListaParticular(id) == null) {
                    throw new NonexistentEntityException("The listaParticular with id " + id + " no longer exists.");
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
            ListaParticular listaParticular;
            try {
                listaParticular = em.getReference(ListaParticular.class, id);
                listaParticular.getNombreLista();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The listaParticular with id " + id + " no longer exists.", enfe);
            }
            List<Tema> misTemas = listaParticular.getMisTemas();
            for (Tema misTemasTema : misTemas) {
                misTemasTema.getMisReproducciones().remove(listaParticular);
                misTemasTema = em.merge(misTemasTema);
            }
            em.remove(listaParticular);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ListaParticular> findListaParticularEntities() {
        return findListaParticularEntities(true, -1, -1);
    }

    public List<ListaParticular> findListaParticularEntities(int maxResults, int firstResult) {
        return findListaParticularEntities(false, maxResults, firstResult);
    }

    private List<ListaParticular> findListaParticularEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ListaParticular.class));
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

    public ListaParticular findListaParticular(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ListaParticular.class, id);
        } finally {
            em.close();
        }
    }

    public int getListaParticularCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ListaParticular> rt = cq.from(ListaParticular.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
