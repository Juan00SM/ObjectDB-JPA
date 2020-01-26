/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.model;

/**
 *
 * @author juans
 */
public abstract class Parent {
    protected Long id;

    public Parent() {
    }
    public abstract boolean selfUpdate(Parent ob);
    
    public abstract Long getId();

    public abstract void setId(Long id);
}
