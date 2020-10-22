package com.company.AnimalSubClasses;
import com.company.Animal;


public class Mammoth extends Animal {
     static protected int buyPrice = 250;

    public Mammoth(String name, String gender) {
        super(name, gender);
    }
    public int getBuyPrice(){
        this.buyPrice = buyPrice;
        return this.buyPrice;
    }
    public void eat() {
        System.out.println("");
    }
}
