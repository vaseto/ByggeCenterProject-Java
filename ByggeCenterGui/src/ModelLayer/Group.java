/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelLayer;

import java.io.Serializable;

/**
 *
 * @author viva
 */
public class Group implements Serializable{
    private String name;
    private int discount;
    public Group(String name,int discount){
        setName(name);
        setDiscount(discount);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
    
    public String toString(){
        return name;
    }
  }
    