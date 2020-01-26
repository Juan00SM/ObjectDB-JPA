/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.servlets;

import dao.MyDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.model.Car;
import web.model.Car_Dealership;
import web.model.Customer;

/**
 *
 * @author juans
 */
@WebServlet(name = "Cars", urlPatterns = {"/Cars"})
public class Car_Servlet extends HttpServlet {

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
            case "listcustomers":
                listCustomers(request, response);
                break;
                case "removecar":
                removeCust(request, response);
                break;
            case "listcust":
                listCust(request, response);
                break;
            case "newcar":
                newCust(request, response);
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
        Car car = new Car(request.getParameter("carmake"), request.getParameter("model"), request.getParameter("seats").isEmpty() ? 0 : Integer.valueOf(request.getParameter("seats")),
                request.getParameter("depositCapacity").isEmpty() ? 0 : Float.parseFloat(request.getParameter("depositCapacity")));

        if (request.getParameter("cardealership") != null) {
            car.setCarDealership((Car_Dealership) this.dao.getFindObject(Car_Dealership.class, Long.parseLong(request.getParameter("cardealership"))));
        }

        this.dao.addPersistObject(car);

        toList(request, response);
    }

    private void newInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Car/new.jsp");
        dispatcher.forward(request, response);
    }

    private void toList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Car/list.jsp");

        List<Car> list = this.dao.getAllObject(Car.class);
        request.setAttribute("list", list);
        dispatcher.forward(request, response);
    }

    private void listCustomers(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Car/customers.jsp");

        List<Customer> list = this.dao.getAllObjectbyIdClass(Customer.class, Car.class, Long.parseLong(request.getParameter("id")), true);
        request.setAttribute("list", list);
        request.setAttribute("id", Long.parseLong(request.getParameter("id")));

        dispatcher.forward(request, response);
    }

    private void showedit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Car car = (Car) this.dao.getFindObject(Car.class, Long.parseLong(request.getParameter("id")));
        request.setAttribute("car", car);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Car/edit.jsp");
        dispatcher.forward(request, response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Car car = new Car(request.getParameter("carmake"), request.getParameter("model"), request.getParameter("seats").isEmpty() ? 0 : Integer.valueOf(request.getParameter("seats")),
                request.getParameter("depositCapacity").isEmpty() ? 0 : Float.parseFloat(request.getParameter("depositCapacity")));
        car.setId(Long.parseLong(request.getParameter("id")));
        this.dao.modifyUpdateObject(Car.class, car);
        toList(request, response);
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!request.getParameter("id").equals("all")) {
            this.dao.removeDeleteObjectByClass(Car.class, Long.parseLong(request.getParameter("id")));
        } else {
            this.dao.removeDeleteObjectAll(Car.class);
        }
        toList(request, response);

    }
    private void removeCust(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!request.getParameter("idcu").equals("all")) {
            this.dao.removeObjectOfColletion(Long.parseLong(request.getParameter("idcu")),Car_Dealership.class,Long.parseLong(request.getParameter("idc")));
        }else{
            this.dao.removeObjectOfColletion(Long.parseLong("-1"),Car_Dealership.class,Long.parseLong(request.getParameter("idc")));
        }
        request.setAttribute("id", Long.parseLong(request.getParameter("idc")));
        listCust(request, response);

    }
    private void listCust(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Car/customers.jsp");
        Long id = request.getParameter("id")==null?(Long)request.getAttribute("id"):Long.parseLong(request.getParameter("id"));
        List<Car> list = this.dao.getAllObjectbyIdClass(Customer.class, Car.class, id,true);
        request.setAttribute("list", list);
        List<Car> cust = this.dao.getAllObjectbyIdClass(Customer.class, Car.class, id,false);
        request.setAttribute("cust", cust);
        request.setAttribute("id", id);
        dispatcher.forward(request, response);
    }
    private void newCust(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long idc = Long.parseLong(request.getParameter("custselect"));
        Long id = Long.parseLong(request.getParameter("id"));
        Car car = (Car)this.dao.getFindObject(Car.class, id);
        Customer cust = (Customer)this.dao.getFindObject(Customer.class, idc);
        car.addCustomer(cust);
        this.dao.modifyUpdateObject(Car.class, car);
        request.setAttribute("id", id);
        listCust(request, response);
    }
}
