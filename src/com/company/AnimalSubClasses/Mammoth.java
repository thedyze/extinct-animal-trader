package com.company.AnimalSubClasses;
import com.company.Animal;


public class Mammoth extends Animal {
    private int buyPrice = 250;

    public Mammoth(String name, String gender) {
        super(name, gender);

    }
    public void getBuyPrice(){
        this.buyPrice = buyPrice;
    }
    public int getHealth(){
        return this.health;
    }
    public void eat() {
        System.out.println("");
    }
}
