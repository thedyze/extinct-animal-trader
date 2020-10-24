package com.company;
import com.company.FoodSubClasses.*;
import com.company.AnimalSubClasses.*;
import java.util.*;

public class Store {

    public static void storeFront(Player player) {
        var inStore = true;
        //don't exit until player has made his actions
        while (inStore && !Game.actionsTaken) {
            Dialogs.clear();
            //store menu
            player.showStats();
            System.out.println("Welcome to the Extinct Animals store! Please make a selection:");
            var input = Dialogs.promptInt("[1.Buy Animals] [2.Sell Animals] [3.Buy Food] " +
                    "[4.Exit Store]", 1, 4);
            switch (input) {
                case 1 -> buyAnimals(player); //break;
                case 2 -> sellAnimal(player); //break;
                case 3 -> buyFood(player); //break;
                case 4 -> {inStore = false; break;}
            }
        }
    }

    //check if player have enough cash
    public static boolean checkCash(Player player, int input){
        int price = 0;
        switch (input) {
            case 1 -> price = 250;
            case 2 -> price = 150;
            case 3 -> price = 300;
        }
        if(player.getCash() >= price ) {
            return true;
        }
        else {
            System.out.println("Not enough cash");
            Dialogs.enterToContinue();
            return false;
        }
    }

    //buy animals
    public static void buyAnimals(Player player) {
        var buying = true;
        //select food
        while (buying) {

            int price = 0;
            String animalType = "";
            String gender = "";
            Dialogs.clear();
            player.showStats();
            //select what to buy
            int input = Dialogs.promptInt("Buy animals: [1.Mammoth 250€] [2.Flying Fox 150€] " +
                    "[3.Dodo 150€] [6.Done]", 1, 6);
            switch (input) {
                case 1 -> {  if (checkCash(player, input)) {animalType = "Mammoth"; price = 250;} else continue;}
                case 2 -> {  if (checkCash(player, input)) {animalType = "FlyingFox"; price = 150;} else continue;}
                case 3 -> {  if (checkCash(player, input)) {animalType = "Dodo"; price = 300;} else continue;}
                case 6 -> { buying = false;}
            }
            //select gender, name
            if (buying) {
                int tmpGender = Dialogs.promptInt("Which gender? [1.Female] [2.Male]", 1, 2);
                switch (tmpGender) {
                    case 1 -> gender = "female";
                    case 2 -> gender = "male";
                }
                String name = Dialogs.prompt("Animal name:");
                addNewAnimal(player, price, name, gender, animalType);
            }
        }
    }

    //create new animal, add to player
    public static void addNewAnimal(Player player, int price, String name, String gender, String animalType) {
        Animal newAnimal = null;
        switch(animalType){
            case "Mammoth" -> newAnimal = new Mammoth(name, gender);
            case "FlyingFox" -> newAnimal = new FlyingFox(name, gender);
            case "Dodo" -> newAnimal = new Dodo(name, gender);
        }
        ArrayList<Animal> temp = player.getAnimalInv();
        temp.add(newAnimal);
        player.setAnimalInv(temp);
        player.setCash(player.getCash() - price);
        Game.actionsTaken = true;
    }

    //sell animals (if you have any)
    public static void sellAnimal(Player player) {
        player.showStats();

        if (player.animalInv.size() > 0) {

            int animalToSell = Dialogs.promptInt("Number of the animal you want to sell:",
            1, player.animalInv.size() + 1);

            //calculate sellprice and withdraw it
            int sellPrice = (int) player.animalInv.get(animalToSell - 1).getSellPrice();
            player.setCash(player.getCash() + sellPrice);

            player.animalInv.remove(animalToSell - 1);
            Game.actionsTaken = true;
            System.out.println("You sold an animal for " + sellPrice + "€. Your balance: " + player.getCash() + "€");
            Dialogs.enterToContinue();
        }
        else {
            System.out.println("You have no animals to sell.");
            Dialogs.enterToContinue();
        }

    }
    static void buyFood(Player player) {
        var buyingFood = true;
        var foodType = "";
        var price = 0;

        while (buyingFood) {
            Dialogs.clear();
            player.showCashNFood();

            //select what to buy
            int input = Dialogs.promptInt("    Food selection:\n<=====================>\n1.Cheezeburgers: 10€/kg" +
                    " \n2.Lollipops: 10€/kg\n3.Food3 10€/kg\n4.Food4 10€/kg\n5.Food5 10€/kg\n6.Back to Storefront" +
                    " \n<=====================>", 1, 6);
            switch (input) {
                case 1 -> {foodType = "Cheezeburgers"; price = 10;}
                case 2 -> {foodType = "Lollipops"; price = 20;}
                case 3 -> {foodType = "Cheezeburgers"; price = 10;}
                case 4 -> {foodType = "Cheezeburgers"; price = 10;}
                case 5 -> {foodType = "Cheezeburgers"; price = 10;}
                case 6 -> {buyingFood = false; break; }
            }

            //select amount to buy
            if (input<6) {
                //System.out.println("You can afford max " + (player.getCash() / price) + " kgs of " + foodType);
                var kilos = Dialogs.promptInt("You can afford max \u001B[1m" + (player.getCash() / price) +
                        "\033[0;0m kgs of \u001B[1m" + foodType + "\033[0;0m. \nHow many do you want?" , 1, (player.getCash() / price)) ;

                switch (foodType) {
                    case "Cheezeburgers" -> addToFoodInv(player, foodType, kilos, price);
                    case "Lollipops" -> addToFoodInv(player, foodType, kilos, price);
                }
            }
        }
    }

    //add food to player
    static void addToFoodInv (Player player, String foodType, int kilos, int price) {
        Food newFood = null;
        ArrayList<Food> temp = player.getFoodInv();

        switch(foodType) {
            case "Cheezeburgers" -> newFood = new Cheezeburgers(kilos);
            case "Lollipops" -> newFood = new Lollipops(kilos);
        }
        //loop and add/update food items
        boolean existingFood = false;
        for (Food food : temp) {
            if (food.getClass().equals(newFood.getClass()))
            {
                food.setQuantity(food.getQuantity() + kilos);
                player.setCash(player.getCash() - ( price * kilos ));
                Game.actionsTaken = true;
                existingFood = true;
            }
        }

        if (!existingFood){
            temp.add(newFood);
            player.setFoodInv(temp);
            player.setCash(player.getCash() - (price * kilos));
            Game.actionsTaken = true;
        }

        else if (existingFood) {
            player.setFoodInv(temp);
        }
    }
/*
    static void addToFoodInv (Player player, Food foodType, int kilos, int price) {
        HashMap<Food, Integer> tmpFoodInv = player.getFoodInv();
        //switch (foodType) {
        //case "Cheezeburgers" ->

        //}
        if (tmpFoodInv.containsKey(foodType.getClass().getSimpleName())) {
        //tmpFoodInv.forEach((key, value) ->
        //if (val != null) {
            int tmp = tmpFoodInv.get(foodType);
            //int tmp = ((int) tempFood.get(foodType));
            System.out.println("temp" + tmp);
            //tempFood.computeIfPresent(foodType, (k, v) -> (v + kilos));
            tmpFoodInv.merge(foodType,tmp, (oldValue, newValue) -> tmp + kilos);

            //tempFood.put(new Cheezeburgers().getClass().getSimpleName(), tmp+kilos);
            //tempFood.put(foodType, tmp+kilos);
            player.setFoodInv(tmpFoodInv);
        }
        else if (!tmpFoodInv.containsKey(foodType)){
            //Cheezeburgers cheezeburgers = new Cheezeburgers();
            //System.out.println("test");
            tmpFoodInv.put(foodType, kilos);
            player.setFoodInv(tmpFoodInv);

        }
        player.setCash(player.getCash() - (price * kilos));
        Game.actionsTaken = true;
    }

  /*
    //amount to buy
    public static void buyKilos(Player player, String foodType, int price) {

        System.out.println("You can afford max " + (player.getCash() / price) + " kgs of " + foodType);
        var kilos = Dialogs.promptInt("How many do you want?",1, (player.getCash() / price));
        switch (foodType) {
            case "Cheezeburgers" -> buyCheezeburgers(player, foodType, kilos, price);
            case "Lollipops" -> buyLollipops(player, foodType, kilos, price);
        }
    }

   */
}
