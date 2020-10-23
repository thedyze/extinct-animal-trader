package com.company;

import java.sql.SQLOutput;
import java.util.*;


public class Player {

    protected ArrayList<Animal> animalInv;
    private ArrayList<Food> foodInv;
    //private HashMap <Food, Integer> foodInv;
    private final String name;
    private int cash;

    public Player(String name) {
        animalInv = new ArrayList<>();
        foodInv = new ArrayList<>();
        //foodInv = new HashMap<>();
        this.name = name;
        //this.pNumber = pNumber;
        cash = 250;
    }
    public ArrayList getAnimalInv() {
        return this.animalInv;
    }

    public void setAnimalInv(ArrayList<Animal> animalInv) {
        this.animalInv = animalInv;
    }

    public ArrayList getFoodInv() {
        return this.animalInv;
    }

    public void setFoodInv(ArrayList<Food> foodInv) {
        this.foodInv = foodInv;
    }

    /*
    public HashMap<Food, Integer> getFoodInv() {
        return this.foodInv;
    }

    public void setFoodInv(HashMap<Food,Integer> foodInv) {
        this.foodInv = foodInv;
        //return this.foodInv;
    }

     */
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
    public void showStats() {
        if (animalInv.size() > 0) {
            System.out.println("Player: \u001B[1m" + this.name +
                    "\033[0;0m, you have: \u001B[1m" + this.cash + "€\033[0;0m\nYour animals:");
            animalInv.forEach(Animal -> {
                int index = getAnimalInv().indexOf(Animal);
                var className = Animal.getClass().getSimpleName();
                var animalName = Animal.getName();
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
        System.out.println("Player: \u001B[1m" + this.name +
                "\033[0;0m, you have: \u001B[1m" + this.cash + "€\033[0;0m. Your food inventory:");
            foodInv.forEach(Food -> {
                int index = getFoodInv().indexOf(Food);
                var className = Food.getClass().getSimpleName();
                var amount = Food.getQuantity();
                System.out.println((index+1) + " " + className + " " + amount);
            });
        //getFoodInv().forEach((key, value) ->
        //        System.out.println(key.getClass().getSimpleName() + ": " + value + " kgs"));
    }

    public void reduceAnimalHealth() {
        animalInv.forEach(Animal -> {
            int reduceHealth = (int)(Math.random() * 21)+10;
             Animal.setHealth(reduceHealth);
        });
    }
    public void removeDeadAnimals() {
        animalInv.forEach(Animal -> {
            if (Animal.getHealth() <=0) {
                System.out.println(Animal.getClass().getSimpleName() + ": " + Animal.getName() + " is dead :(");
                Dialogs.enterToContinue();
            }
        });
        animalInv.removeIf(Animal -> (Animal.getHealth() <= 0));
        //}
    }
        /*
        animalInv.forEach(Animal -> {
            if (Animal.getHealth() <= 0) {
                System.out.println(Animal.getClass().getSimpleName() + Animal.getName() + "is dead :(");
                animalInv.removeIf(n -> (n.getHealth() <=0));
            }
        });


    }
    */
}
