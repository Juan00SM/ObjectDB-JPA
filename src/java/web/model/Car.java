/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author juans
 */
@Entity
public class Car extends Parent implements Serializable {

    private static final long serialVersionUID = 1L;

    // Persistent Fields:
    @Id
    @GeneratedValue
    Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date signingDate;

    private String carmake;
    private String model;
    private int seats;
    private float depositCapacity;

    @ManyToOne
    @JoinColumn(name = "carDealershipID", nullable = false)
    private Car_Dealership car_dealership;

    @ManyToMany(targetEntity = web.model.Customer.class)
    private List<Customer> customers;

    public Car(String carmake, String model, int seats, float depositCapacity) {
        this.signingDate = new Date(System.currentTimeMillis());
        this.carmake = carmake;
        this.model = model;
        this.seats = seats;
        this.depositCapacity = depositCapacity;
        this.customers = new ArrayList<>();
    }

    public boolean addCustomer(Customer customer) {
        return this.customers.add(customer);
    }

    public boolean removeCustomer(Long id) {
        int count = 0;
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                this.customers.remove(count);
                return true;
            }
            count++;
        }
        return false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public Date getSigningDate() {
        return signingDate;
    }

    public void setSigningDate(Date signingDate) {
        this.signingDate = signingDate;
    }

    public Car_Dealership getCarDealership() {
        return car_dealership;
    }

    public void setCarDealership(Car_Dealership carDealership) {
        this.car_dealership = carDealership;
    }

    public String getCarmake() {
        return carmake;
    }

    public void setCarmake(String carmake) {
        this.carmake = carmake;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public float getDepositCapacity() {
        return depositCapacity;
    }

    public void setDepositCapacity(float depositCapacity) {
        this.depositCapacity = depositCapacity;
    }

    @Override
    public String toString() {
        return "Car{" + "id=" + id + ", signingDate=" + signingDate + ", carmake=" + carmake + ", model=" + model + ", seats=" + seats + ", depositCapacity="
                + depositCapacity + ", carDealership=" + car_dealership.getId() + "_" + car_dealership.getName() + ", customer=" + customers.size() + '}';
    }

    @Override
    public boolean selfUpdate(Parent ob) {
        Car v = (Car)ob; 
        ArrayList<Long> ids = new ArrayList<>();
        
        this.setCarDealership(v.getCarDealership());
        this.setDepositCapacity(v.getDepositCapacity());
        this.setCarmake(v.getCarmake());
        this.setModel(v.getModel());
        this.setSeats(v.getSeats());
        
        for (Customer cust : this.customers) {
            ids.add(cust.getId());
        }
        for (Customer cust2 : v.getCustomers()) {
            if (!ids.contains(cust2.getId())) {
                this.addCustomer(cust2);
            }
        }
        return true;
    }

}
