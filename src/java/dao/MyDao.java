/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import javax.jdo.JDOHelper;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import web.model.Car;
import web.model.Car_Dealership;
import web.model.Customer;
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
    //Clase a borrar, id a borrar(-1 todos), clase de donde borrar, id de donde borrar
    public boolean removeObjectOfColletion(Long idcl, Class cls, Long idcls) {
        try {
            this.em = this.emf.createEntityManager();
            
            this.em.getTransaction().begin();
            String option = cls.getSimpleName();
            switch (option) {
                case "Car_Dealership":
                    Car_Dealership ob = (Car_Dealership) this.em.find(cls, idcls);
                    ob.removeCar(idcl);
                    break;
                    
                case "Car":
                    Car ob2 = (Car) this.em.find(cls, idcls);
                    ob2.removeCustomer(idcl);
                    break;
                    
                case "Customer":
                    Customer ob3 = (Customer) this.em.find(cls, idcls);
                    ob3.removeRentedCar(idcl);
                    break;
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

    public boolean removeDeleteObjectByClass(Class cl, Long id) {

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
    //buscar cl para cls con id, true si los tiene, false los que no

    public List getAllObjectbyIdClass(Class cl, Class cls, Long id, boolean opt) {
        try {
            if (opt) {
                this.em = this.emf.createEntityManager();
                String sql = "SELECT obj FROM " + cls.getSimpleName() + " ob JOIN ob." + cl.getSimpleName().toLowerCase() + "s obj where ob.id=" + id;
                TypedQuery<Parent> query = em.createQuery(sql, cl);
                List<Parent> resultList = query.getResultList();
                return resultList;
            } else {
                this.em = this.emf.createEntityManager();
                String sql = "SELECT obj FROM " + cl.getSimpleName() + " obj where obj." + cls.getSimpleName().toLowerCase() + "s is null";
                TypedQuery<Parent> query = em.createQuery(sql, cl);
                List<Parent> resultList = query.getResultList();
                return resultList;
            }

        } catch (Exception e) {
            e.printStackTrace();
            em.close();
            return new ArrayList();
        }
    }

}
