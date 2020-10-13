package com.company.AnimalSubClasses;
import com.company.Animal;


public class Mammoth extends Animal {
    protected int buyPrice = 250;

    public Mammoth(String name, String gender) {
        super(name, gender);

    }
    public int getBuyPrice(){
        //this.buyPrice = buyPrice;
        return this.buyPrice;
    }
    public int getHealth(){
        return this.health;
    }
    public String getName() {
        return this.name;
    }

    public void eat() {
        System.out.println("");
    }
}
