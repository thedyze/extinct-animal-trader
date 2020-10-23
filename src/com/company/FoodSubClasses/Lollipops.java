package com.company.FoodSubClasses;
import com.company.Food;

public class Lollipops extends Food {
    protected int type = 2;

    public Lollipops(int amount) {
        //int price = 20;
        super(amount);
    }

    public int getPrice(){
        return this.price;
    }
}
