package com.company.AnimalSubClasses;
import com.company.Animal;


public class GiantRat extends Animal {
    protected int buyPrice = 100;

    public GiantRat(String name, String gender) {
        super(name, gender);
        feedsOn = 3;

    }
    public double getSellPrice(){
        double healthModifier = (getHealth() *0.01);
        this.buyPrice = (int) (buyPrice * healthModifier);
        return this.buyPrice;
    }
    public void eat() {
        System.out.println("");
    }
}