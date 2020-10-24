package com.company;
import java.util.*;


public class Player {

    protected ArrayList<Animal> animalInv;
    protected ArrayList<Food> foodInv;
    private final String name;
    private int cash;

    public Player(String name) {
        animalInv = new ArrayList<>();
        foodInv = new ArrayList<>();
        this.name = name;
        cash = 500;
    }
    public ArrayList<Animal> getAnimalInv() {
        return this.animalInv;
    }

    public void setAnimalInv(ArrayList<Animal> animalInv) {
        this.animalInv = animalInv;
    }

    public ArrayList<Food> getFoodInv() {
        return this.foodInv;
    }

    public void setFoodInv(ArrayList<Food> foodInv) {
        this.foodInv = foodInv;
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
    public void showStats() {
        if (animalInv.size() > 0) {
            System.out.println("Player: \u001B[1m" + this.name +
                    "\033[0;0m, you have: \u001B[1m" + this.cash + "€\033[0;0m\n\n                  Your animals:" +
                    "\n-----------------------------------------------");
            animalInv.forEach(x -> {
                int index = getAnimalInv().indexOf(x);
                var className = x.getClass().getSimpleName();
                var animalName = x.getName();
                var animalGender = x.gender;
                var animalHealth = x.getHealth();
                System.out.println(" " + (index+1) + ": " + className + " " + "'" + animalName +
                "' (" +animalGender +") Health: " + animalHealth + "");
                });
            System.out.println("-----------------------------------------------\n");
        }
        else if (animalInv.size() == 0){System.out.println("Player: \u001B[1m" + this.name +
                "\033[0;0m, you have: \u001B[1m" + this.cash + "€\033[0;0m");
        }


    }

    public void showCashNFood() {
        System.out.println("Player: \u001B[1m" + this.name +
                "\033[0;0m, you have: \u001B[1m" + this.cash + "€\033[0;0m.\n  Your food inventory:\n|¨                       ¨|");
            this.foodInv.forEach(foodItem -> {
                int index = getFoodInv().indexOf(foodItem);
                var className = foodItem.getClass().getSimpleName();
                var amount = foodItem.getQuantity();
                System.out.println(" " + (index+1) + ": " + className + " (" + amount + " kgs)");
            });
        System.out.println("|_                       _|\n");
    }

    public void reduceAnimalHealth() {
        animalInv.forEach(x -> {
            int reduceHealth = (int)(Math.random() * 21)+10;
             x.setHealth(reduceHealth);
        });
    }

    public void removeDeadAnimals() {
        animalInv.forEach(x -> {
            if (x.getHealth() <=0) {
                System.out.println(x.getClass().getSimpleName() + ": " + x.getName() + " is dead :(");
                Dialogs.enterToContinue();
            }
        });
        animalInv.removeIf(x -> (x.getHealth() <= 0));
        //}
    }


}
