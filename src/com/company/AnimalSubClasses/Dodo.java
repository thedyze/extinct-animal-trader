package com.company.AnimalSubClasses;
import com.company.Animal;


public class Dodo extends Animal {
    protected int buyPrice = 300;

    public Dodo(String name, String gender) {
        super(name, gender);
        feedsOn = 3;

    }

    public double getSellPrice(){
        double healthModifier = (getHealth() *0.01);
        buyPrice = (int) (buyPrice * healthModifier);
        return buyPrice;
    }
}
