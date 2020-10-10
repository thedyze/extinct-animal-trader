package com.company;

import java.util.*;

public class Player {

    //public static boolean actionTaken = false;
    private HashMap <Food, Integer> foodInv;
    private String name;
    private int pNumber;
    private int cash;

    public Player(String name, int pNumber) {
        this.name = name;
        this.pNumber = pNumber;
        cash = 10000;
        foodInv = new HashMap<>();
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

    public Map getFoodInv() {
        return this.foodInv;
    }
    /*
    public HashMap<Food, Integer> setFoodInv(HashMap<Food,Integer> food) {
        this.foodInv = food;
        return this.foodInv;
    } */

}
