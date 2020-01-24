/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.servlets;

import dao.MyDao;
import web.model.Car_Dealership;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.model.Car;
import web.model.Parent;

/**
 *
 * @author juans
 */
@WebServlet(name = "CarDealerships", urlPatterns = {"/CarDealerships"})
public class CarDealership_Servlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private EntityManagerFactory emf;
    private MyDao dao;

    @Override
    public void init() {
        this.emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        this.dao = new MyDao(emf);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        switch (action != null ? action : "list") {
            case "index":
                index(request, response);
                break;
            case "new":
                newInsert(request, response);
                break;
            case "register":
                register(request, response);
                break;
            case "list":
                toList(request, response);
                break;
            case "showedit":
                showedit(request, response);
                break;
            case "edit":
                edit(request, response);
                break;
            case "remove":
                remove(request, response);
                break;
            case "listcars":
                listCars(request, response);
                break;
            default:
                break;
        }

        

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Car_Dealership carD = new Car_Dealership(request.getParameter("name"), request.getParameter("ceo"),
                request.getParameter("annualProfit").isEmpty()?0:Double.parseDouble(request.getParameter("annualProfit")));

        this.dao.addPersistObject(carD);

        toList(request, response);
    }

    private void newInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CarDealership/new.jsp");
        dispatcher.forward(request, response);
    }

    private void toList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CarDealership/list.jsp");

        List<Car_Dealership> list = this.dao.getAllObject(Car_Dealership.class);
        request.setAttribute("list", list);
        dispatcher.forward(request, response);
    }
    private void listCars(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/CarDealership/cars.jsp");

        List<Car> list = this.dao.getAllObjectbyClass(Car.class, Car_Dealership.class, Long.parseLong(request.getParameter("id")),true);
        request.setAttribute("list", list);
        request.setAttribute("id", request.getParameter("id"));
        dispatcher.forward(request, response);
    }
    

    private void showedit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Car_Dealership carD = (Car_Dealership) this.dao.getFindObject(Car_Dealership.class, Long.parseLong(request.getParameter("id")));
        request.setAttribute("carD", carD);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/CarDealership/edit.jsp");
        dispatcher.forward(request, response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Car_Dealership carD = new Car_Dealership(request.getParameter("name"), request.getParameter("ceo"),
                request.getParameter("annualProfit").isEmpty()?0:Double.parseDouble(request.getParameter("annualProfit")));
        carD.setId(Long.parseLong(request.getParameter("id")));
        this.dao.modifyUpdateObject(Car_Dealership.class, carD);
        toList(request, response);
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!request.getParameter("id").equals("all")) {
            this.dao.removeDeleteObject(Car_Dealership.class, Long.parseLong(request.getParameter("id")));
        }else{
            this.dao.removeDeleteObjectAll(Car_Dealership.class);
        }
        toList(request, response);

    }
}
