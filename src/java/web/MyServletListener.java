/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import com.objectdb.Enhancer;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author juans
 */
@WebListener()
public class MyServletListener implements ServletContextListener {

    // Prepare the EntityManagerFactory & Enhance:
    @Override
    public void contextInitialized(ServletContextEvent e) {
        
        Enhancer.enhance("web.*");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb://localhost:6136/CarDealership.odb;user=admin;password=admin");
        e.getServletContext().setAttribute("emf", emf);
        
    }

    // Release the EntityManagerFactory:
    @Override
    public void contextDestroyed(ServletContextEvent e) {
        EntityManagerFactory emf = (EntityManagerFactory)e.getServletContext().getAttribute("emf");
        emf.close();
    }
}
