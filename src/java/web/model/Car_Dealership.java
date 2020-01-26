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
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author juans
 */
@Entity
public class Car_Dealership extends Parent implements Serializable {

    private static final long serialVersionUID = 1L;

    // Persistent Fields:
    @Id
    @GeneratedValue
    Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date signingDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "car_dealerships", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Car> cars;

    private String name;
    private String ceo;
    private double annualProfit;

    // Constructors:
    public Car_Dealership(String name, String ceo, double annualProfit) {
        this.signingDate = new Date(System.currentTimeMillis());
        this.name = name;
        this.ceo = ceo;
        this.annualProfit = annualProfit;
        this.cars = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getSigningDate() {
        return signingDate;
    }

    public void setSigningDate(Date signingDate) {
        this.signingDate = signingDate;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public double getAnnualProfit() {
        return annualProfit;
    }

    public void setAnnualProfit(double annualProfit) {
        this.annualProfit = annualProfit;
    }

    public boolean addCar(Car car) {
        return this.cars.add(car);
    }

    public boolean removeCar(Long id) {
        if (id != -1) {
            int count = 0;
            for (Car car : this.cars) {
                if (car.getId() == id) {
                    this.cars.remove(count);
                    return true;
                }
                count++;
            }
            return false;
        } else {
            int count = 0;
            for (Car car : this.cars) {
                this.cars.remove(count);
                count++;
            }
            return true;
        }
    }

    @Override
    public String toString() {
        return "Car_Dealership{" + "id=" + id + ", signingDate=" + signingDate + ", cars=" + cars.size() + ", name=" + name + ", ceo=" + ceo + ", annualProfit=" + annualProfit + '}';
    }

    @Override
    public boolean selfUpdate(Parent ob) {
        Car_Dealership v = Car_Dealership.class.cast(ob);
        ArrayList<Long> ids = new ArrayList<>();

        this.setName(v.getName());
        this.setCeo(v.getCeo());
        this.setAnnualProfit(v.getAnnualProfit());
        
        for (Car car : this.cars) {
            ids.add(car.getId());
        }
        for (Car car2 : v.getCars()) {
            if (!ids.contains(car2.getId())) {
                this.addCar(car2);
            }
        }
        return true;
    }

}
