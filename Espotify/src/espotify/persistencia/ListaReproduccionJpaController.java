/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.persistencia;

import espotify.logica.ListaReproduccion;
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
public class ListaReproduccionJpaController implements Serializable {

    public ListaReproduccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ListaReproduccion listaReproduccion) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(listaReproduccion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findListaReproduccion(listaReproduccion.getNombreLista()) != null) {
                throw new PreexistingEntityException("ListaReproduccion " + listaReproduccion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ListaReproduccion listaReproduccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            listaReproduccion = em.merge(listaReproduccion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = listaReproduccion.getNombreLista();
                if (findListaReproduccion(id) == null) {
                    throw new NonexistentEntityException("The listaReproduccion with id " + id + " no longer exists.");
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
            ListaReproduccion listaReproduccion;
            try {
                listaReproduccion = em.getReference(ListaReproduccion.class, id);
                listaReproduccion.getNombreLista();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The listaReproduccion with id " + id + " no longer exists.", enfe);
            }
            em.remove(listaReproduccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ListaReproduccion> findListaReproduccionEntities() {
        return findListaReproduccionEntities(true, -1, -1);
    }

    public List<ListaReproduccion> findListaReproduccionEntities(int maxResults, int firstResult) {
        return findListaReproduccionEntities(false, maxResults, firstResult);
    }

    private List<ListaReproduccion> findListaReproduccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ListaReproduccion.class));
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

    public ListaReproduccion findListaReproduccion(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ListaReproduccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getListaReproduccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ListaReproduccion> rt = cq.from(ListaReproduccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
