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
        this.health = health;
        return this.health;
    }

}
