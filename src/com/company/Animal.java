package com.company;

public abstract class Animal {

    protected int health = 100;
    protected String gender;
    protected String name;

    public Animal(String name, String gender){
        this.name = name;
        this.gender = gender;

    }
    public int getHealth(){
        return this.health;
    }
    public int setHealth(int reduceHealth){
        this.health = health - reduceHealth;
        return this.health;
    }
    public String getName() {
        return this.name;
    }
    public abstract int getBuyPrice();
}
