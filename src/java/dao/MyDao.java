/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import web.model.Car;
import web.model.Car_Dealership;
import web.model.Parent;

/**
 *
 * @author juans
 */
public class MyDao {

    private EntityManagerFactory emf;
    private EntityManager em;

    public MyDao(EntityManagerFactory emf) {
        this.emf = emf;
        this.em = this.emf.createEntityManager();
    }

    public boolean addPersistObject(Object ob) {

        try {
            this.em = this.emf.createEntityManager();
            this.em.getTransaction().begin();
            this.em.persist(ob);
            this.em.getTransaction().commit();

        } finally {
            // Close the database connection:
            if (this.em.getTransaction().isActive()) {
                this.em.getTransaction().rollback();
                this.em.close();
                return false;
            }
            this.em.close();
        }
        return true;
    }

    public Parent getFindObject(Class cl, Long id) {

        try {
            this.em = this.emf.createEntityManager();

            Parent ob = (Parent) this.em.find(cl, id);
            return ob;

        } catch (Exception e) {
            this.em.close();
            return null;
        }
    }

    public boolean removeDeleteObjectAll(Class cl) {
        try {
            this.em = this.emf.createEntityManager();

            Query query = this.em.createQuery("SELECT ob FROM " + cl.getSimpleName() + " ob", cl);
            List list = query.getResultList();

            this.em.getTransaction().begin();
            for (Object object : list) {
                this.em.remove(object);
            }
            this.em.getTransaction().commit();

        } finally {
            // Close the database connection:
            if (this.em.getTransaction().isActive()) {
                this.em.getTransaction().rollback();
                this.em.close();
                return false;
            }
            this.em.close();
        }
        return true;
    }

    public boolean removeDeleteObject(Object ob) {

        try {
            this.em = this.emf.createEntityManager();
            this.em.getTransaction().begin();
            this.em.remove(ob);
            this.em.getTransaction().commit();

        } finally {
            // Close the database connection:
            if (this.em.getTransaction().isActive()) {
                this.em.getTransaction().rollback();
                this.em.close();
                return false;
            }
            this.em.close();
        }
        return true;
    }

    public boolean removeDeleteObject(Class cl, Long id) {

        try {
            Parent ob = this.getFindObject(cl, id);
            this.em.getTransaction().begin();
            this.em.remove(ob);
            this.em.getTransaction().commit();

        } finally {
            // Close the database connection:
            if (this.em.getTransaction().isActive()) {
                this.em.getTransaction().rollback();
                this.em.close();
                return false;
            }
            this.em.close();
        }
        return true;
    }

    public boolean modifyUpdateObject(Class cl, Parent ob) {
        try {
            this.em = this.emf.createEntityManager();

            Parent o = em.find(cl, ob.getId());
            if (o != null) {
                this.em.getTransaction().begin();
                o.selfUpdate(ob);
                this.em.getTransaction().commit();
            }
        } finally {
            // Close the database connection:
            if (this.em.getTransaction().isActive()) {
                this.em.getTransaction().rollback();
                this.em.close();
                return false;
            }
            this.em.close();
        }
        return true;
    }

    public List getAllObject(Class cl) {
        try {
            this.em = this.emf.createEntityManager();
            Query query = this.em.createQuery("SELECT ob FROM " + cl.getSimpleName() + " ob", cl);
            return query.getResultList();

        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    public List getAllObjectbyClass(Class cl, Class cls, Long id, boolean opt) {
        try {
            this.em = this.emf.createEntityManager();
            Query query = this.em.createQuery("SELECT ob FROM " + cl.getSimpleName() + " ob", cl);
            return query.getResultList();

        } catch (Exception e) {
            em.close();
            return null;
        }
    }

}
