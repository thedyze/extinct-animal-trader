package com.company;

public abstract class Food {

    protected int quantity;
    protected int type;

    public Food(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public int setQuantity(int quantity){
        this.quantity = quantity;
        return this.quantity;
    }

    public int getType(){
        this.type = type;
        return this.type;
    }
}
