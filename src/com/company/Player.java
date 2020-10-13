package com.company;

import java.util.*;


public class Player {

    protected ArrayList<Animal> animalInv;
    protected HashMap <Food, Integer> foodInv;
    private String name;
    //private int pNumber;
    private int cash;
    private Animal animal;
    private Animal animal1;

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
    public void showCashNAnimals() {
        System.out.println(this.name + ", you have: " + this.cash + "€. Your animal inventory:");
        animalInv.forEach(Animal -> {
            var className = Animal.getClass().getSimpleName();
            var animalName = Animal.name;
            var animalGender = Animal.gender;
            var animalHealth = Animal.getHealth();
            //var salePrice = animal.getBuyPrice();
            System.out.println(className + " " + "'" + animalName +"' (" +animalGender +") Health: " + animalHealth);
        });
    }
    public void showCashNFood() {
        System.out.println(this.name + ", you have: " + this.cash + "€. Your food inventory:");
        getFoodInv().forEach((key, value) ->
                System.out.println(key.getClass().getSimpleName() + ": " + value + " kgs"));
    }


}
