package com.company.AnimalSubClasses;
import com.company.Animal;


public class Mammoth extends Animal {
     static protected int buyPrice = 250;

    public Mammoth(String name, String gender) {
        super(name, gender);
        feedsOn = 1;
    }

    public double getSellPrice(){
        double healthModifier = (getHealth() *0.01);
        buyPrice = (int) (buyPrice * healthModifier);
        return buyPrice;
    }
}
