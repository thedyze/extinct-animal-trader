package com.company;

import java.util.*;


public class Player {

    protected ArrayList<Animal> animalInv;
    protected HashMap <Food, Integer> foodInv;
    private final String name;
    //private int pNumber;
    private int cash;

    public Player(String name) {
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
    public void showStatsNAnimals() {
        if (animalInv.size() > 0) {
            System.out.println("Player: \u001B[1m" + this.name +
                    "\033[0;0m, you have: \u001B[1m" + this.cash + "€\033[0;0m\nYour animals:");
            animalInv.forEach(Animal -> {
                int index = getAnimalInv().indexOf(Animal);
                var className = Animal.getClass().getSimpleName();
                var animalName = Animal.name;
                var animalGender = Animal.gender;
                var animalHealth = Animal.getHealth();
                System.out.println((index+1) + ": " + className + " " + "'" + animalName +
                "' (" +animalGender +") Health: " + animalHealth + "");
                });
            System.out.println("");
        }
        else if (animalInv.size() == 0){System.out.println("Player: \u001B[1m" + this.name +
                "\033[0;0m, you have: \u001B[1m" + this.cash + "€\033[0;0m");
              System.out.println("");
        }


    }

    public void showCashNFood() {
        System.out.println(this.name + ", you have: " + this.cash + "€. Your food inventory:");
        getFoodInv().forEach((key, value) ->
                System.out.println(key.getClass().getSimpleName() + ": " + value + " kgs"));
    }



}
