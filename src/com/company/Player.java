package com.company;

import java.util.*;

public class Player {

    protected ArrayList<Animal> animalInv;
    protected HashMap <Food, Integer> foodInv;
    private String name;
    //private int pNumber;
    private int cash;

    public Player(String name, int pNumber) {
        animalInv = new ArrayList<>();
        foodInv = new HashMap<>();
        this.name = name;
        //this.pNumber = pNumber;
        cash = 10000;

    }
    public ArrayList getAnimalInv() {
        return this.animalInv;
    }
    public void setAnimalInv(ArrayList<Animal> animalInv) {
        this.animalInv = animalInv;
    }

    public Map getFoodInv() {
        return this.foodInv;
    }

    public Map setFoodInv(HashMap<Food,Integer> food) {
        this.foodInv = food;
        return this.foodInv;
    }
    public String getName() {
        return this.name;
    }

    public int getCash() {
        return this.cash;
    }

    public int setCash(int balance) {
         this.cash = balance;
         return this.cash;
    }



}
