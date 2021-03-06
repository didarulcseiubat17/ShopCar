/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shopcar.repository;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import shopcar.util.Transacional;


/**
 *
 * @author vFreitas
 * @param <T> The type T 
 */
public class JpaDAO<T> implements DAO<T>, Serializable
{
    /* The EntityManager of my connection */
    private final EntityManager em;
    /* The class to be persist */
    private final Class<T> classe;
    
    private ThreadLocal<EntityManager> threadLocal;
   
    /* Builder */
    /**
    *
    * @author info1
    * @param classe The class to that will represent T
    * @param em A new instance of EntityManager 
    */
    public JpaDAO(Class<T> classe, EntityManager em)
    {
        this.classe = classe;
        this.em = em;
        threadLocal = new ThreadLocal<>();
        threadLocal.set(em);
    }
    
    public Class<T> getClasse()
    {
        return this.classe;
    }
    
    public EntityManager getEntityManager()
    {
        return this.em;
    }
    
    public void getFlush()
    {
        em.flush();
    }
    
    @Override
    @Transacional
    public void save(T entity)
    {
        //em.getTransaction().begin();
        em.persist(entity);
        //em.getTransaction().commit();
    }

    @Override
    public void update(T entity)
    {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }

    @Override
    public void remove(T entity)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T getById(Object pk)
    {
        return em.find(this.classe, pk);
    }

    @Override
    public List<T> getAll()
    {
        List<T> resultList = (List<T>) em.createQuery("select e from " + classe.getSimpleName() + " e").getResultList();
        return resultList;
    }

    @Override
    public T getByRestriction(String attribute, String filter)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<T> getByRestriction(String namedQuery, String parameter, Object value)
    {
        List <T> resultList = (List<T>) em.createNamedQuery(namedQuery)
                .setParameter(parameter, value)
                .getResultList();
        return resultList;
    }
}
