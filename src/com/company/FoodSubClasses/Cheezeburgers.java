package com.company.FoodSubClasses;

import com.company.Food;

public class Cheezeburgers extends Food {
     protected int type = 1;

    public Cheezeburgers(int amount) {
        //int price = 10;
        super(amount);
    }

    public int getPrice(){
        return this.price;
    }
}
