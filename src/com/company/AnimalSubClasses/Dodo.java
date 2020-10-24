package com.company.AnimalSubClasses;
import com.company.Animal;


public class Dodo extends Animal {
    protected int buyPrice = 300;

    public Dodo(String name, String gender) {
        super(name, gender);

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
