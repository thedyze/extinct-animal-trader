package com.company.FoodSubClasses;
import com.company.Food;

public class Lollipops extends Food {


    public Lollipops(int amount) {
        super(amount);
        this.type=2;

    }

    public int getPrice(){
        return this.price;
    }
}
