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
@WebServlet(name = "Customers", urlPatterns = {"/Customers"})
public class Customer_Servlet extends HttpServlet {

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
                case "listrented":
                listRented(request, response);
                break;
                case "removecar":
                removeCar(request, response);
                break;
            case "listcars":
                listCars(request, response);
                break;
            case "newcar":
                newCar(request, response);
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
    private void listCars(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Customer/rented.jsp");
        Long id = request.getParameter("id")==null?(Long)request.getAttribute("id"):Long.parseLong(request.getParameter("id"));
        List<Car> list = this.dao.getAllObjectbyIdClass(Car.class, Customer.class, id,true);
        request.setAttribute("list", list);
        List<Car> cars = this.dao.getAllObjectbyIdClass(Car.class, Customer.class, id,false);
        request.setAttribute("cars", cars);
        request.setAttribute("id", id);
        dispatcher.forward(request, response);
    }

    private void newCar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long idc = Long.parseLong(request.getParameter("carselect"));
        Long id = Long.parseLong(request.getParameter("id"));
        Customer cust = (Customer)this.dao.getFindObject(Customer.class, id);
        Car car = (Car)this.dao.getFindObject(Car.class, idc);
        cust.addRentedCar(car);
        this.dao.modifyUpdateObject(Customer.class, cust);
        request.setAttribute("id", id);
        listCars(request, response);
    }
    private void removeCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!request.getParameter("idc").equals("all")) {
            this.dao.removeObjectOfColletion(Long.parseLong(request.getParameter("idc")),Customer.class,Long.parseLong(request.getParameter("idcu")));
        }else{
            this.dao.removeObjectOfColletion(Long.parseLong("-1"),Customer.class,Long.parseLong(request.getParameter("idcu")));
        }
        request.setAttribute("id", Long.parseLong(request.getParameter("idcu")));
        listCars(request, response);

    }
    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Customer cust = new Customer(request.getParameter("name"), request.getParameter("lastname"), request.getParameter("dni"),
                request.getParameter("age").isEmpty() ? 0 :Integer.valueOf(request.getParameter("age")));
        
        this.dao.addPersistObject(cust);

        toList(request, response);
    }

    private void newInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Customer/new.jsp");
        dispatcher.forward(request, response);
    }

    private void toList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Customer/list.jsp");

        List<Customer> list = this.dao.getAllObject(Customer.class);
        request.setAttribute("list", list);
        dispatcher.forward(request, response);
    }
    private void listRented(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Customer/rented.jsp");

        List<Car> list = this.dao.getAllObjectbyIdClass(Car.class, Customer.class, Long.parseLong(request.getParameter("id")), true);
        request.setAttribute("list", list);
        request.setAttribute("id", Long.parseLong(request.getParameter("id")));
        dispatcher.forward(request, response);
    }

    private void showedit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Customer cust = (Customer) this.dao.getFindObject(Customer.class, Long.parseLong(request.getParameter("id")));
        request.setAttribute("customer", cust);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Customer/edit.jsp");
        dispatcher.forward(request, response);
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Customer cust = new Customer(request.getParameter("name"), request.getParameter("lastname"), request.getParameter("dni"),
                request.getParameter("age").isEmpty() ? 0 :Integer.valueOf(request.getParameter("age")));
        cust.setId(Long.parseLong(request.getParameter("id")));
        this.dao.modifyUpdateObject(Customer.class, cust);
        toList(request, response);
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!request.getParameter("id").equals("all")) {
            this.dao.removeDeleteObjectByClass(Customer.class, Long.parseLong(request.getParameter("id")));
        } else {
            this.dao.removeDeleteObjectAll(Customer.class);
        }
        toList(request, response);

    }

}
