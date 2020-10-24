package com.company.FoodSubClasses;
import com.company.Food;

public class Cheezeburgers extends Food {


    public Cheezeburgers(int amount) {
        super(amount);
        this.type= 1;
    }

    public int getPrice(){
        return this.price;
    }
}
