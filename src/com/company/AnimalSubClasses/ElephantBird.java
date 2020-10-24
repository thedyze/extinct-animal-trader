package com.company.AnimalSubClasses;
import com.company.Animal;


public class ElephantBird extends Animal {
    static protected int buyPrice = 250;

    public ElephantBird(String name, String gender) {
        super(name, gender);
        feedsOn = 2;
    }
    public double getSellPrice(){
        double healthModifier = (getHealth() *0.01);
        this.buyPrice = (int) (buyPrice * healthModifier);
        return this.buyPrice;
    }

}