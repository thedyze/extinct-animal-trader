package com.company.AnimalSubClasses;
import com.company.Animal;


public class FlyingFox extends Animal {
    protected int buyPrice = 150;

    public FlyingFox(String name, String gender) {
        super(name, gender);
        feedsOn = 1;

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