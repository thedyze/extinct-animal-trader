package com.company.AnimalSubClasses;
import com.company.Animal;


public class Mammoth extends Animal {
     static protected int buyPrice = 250;
     static protected int feedsOn = 1;

    public Mammoth(String name, String gender) {
        super(name, gender);
    }
    public double getSellPrice(){
        double healthModifier = (getHealth() *0.01);
        this.buyPrice = (int) (buyPrice * healthModifier);
        return this.buyPrice;
    }
    public int getFeedsOn() {
        return this.feedsOn;

    }
}
