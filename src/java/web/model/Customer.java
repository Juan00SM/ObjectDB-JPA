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
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author juans
 */
@Entity
public class Customer extends Parent implements Serializable{

    // Persistent Fields:
    @Id @GeneratedValue
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date signingDate;
    
    private String name;
    private String lastName;
    private String dni;
    private int age;
    
    @ManyToMany(targetEntity = web.model.Car.class, mappedBy = "customers")
    private List<Car> rentedCars;

    public Customer(String name, String lastName, String dni, int age) {
        this.signingDate = new Date(System.currentTimeMillis());
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.age = age;
        this.rentedCars = new ArrayList<>();
    }

    public boolean addRentedCar(Car car){
        return this.rentedCars.add(car);
    }
    
    public boolean removeRentedCar(Long id){
        int count = 0;
        for (Car rentedCar : rentedCars) {
            if (rentedCar.getId() == id) {
                this.rentedCars.remove(count);
                return true;
            }
            count++;
        }
        return false;
    }
    public Date getSigningDate() {
        return signingDate;
    }

    public void setSigningDate(Date signingDate) {
        this.signingDate = signingDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Car> getRentedCars() {
        return rentedCars;
    }

    public void setRentedCars(List<Car> rentedCars) {
        this.rentedCars = rentedCars;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", signingDate=" + signingDate + ", name=" + name + ", lastName=" + lastName 
                + ", dni=" + dni + ", age=" + age + ", rentedCars=" + rentedCars.size() + '}';
    }

    @Override
    public boolean selfUpdate(Parent ob) {
        Customer v = (Customer)ob; 
        ArrayList<Long> ids = new ArrayList<>();
        
        this.setName(v.getName());
        this.setAge(v.getAge());
        this.setDni(v.getDni());
        this.setLastName(v.getLastName());
        
        for (Car car : this.rentedCars) {
            ids.add(car.getId());
        }
        for (Car car2 : v.getRentedCars()) {
            if (!ids.contains(car2.getId())) {
                this.addRentedCar(car2);
            }
        }
        return true;
    }
    
    
}
