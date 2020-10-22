package com.company;
import com.company.AnimalSubClasses.Mammoth;
import com.company.FoodSubClasses.*;
import java.util.*;
//import java.util.function.Predicate;

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
                case 4 -> {inStore = false;
                          break;}
                        }
            }
        }


    public static void sellAnimal(Player player) {
        player.showStats();
        int animalToSell = Dialogs.promptInt("Number of the animal you want to sell:",
                1,player.animalInv.size()+1);
        int sellPrice = player.animalInv.get(animalToSell-1).getBuyPrice();
        player.setCash(player.getCash() + sellPrice);
        System.out.println(sellPrice);
        player.animalInv.remove(animalToSell-1);
        Game.actionsTaken = true;
        //Predicate<Animal> condition = animal -> animal.getName().matches(animalToSell);
        //player.animalInv.removeIf(condition);
    }
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
            int input = Dialogs.promptInt("Available animals: [1.Mammoth 250€] [6.Done]", 1, 6);
            switch (input) {
                case 1 -> { animalType = "Mammoth"; price = 250;}
                case 6 -> { buying = false;}
            }
            if (buying) {
                int tmpGender = Dialogs.promptInt("Which gender? [1.Female] [2.Male]", 1, 2);
                switch (tmpGender) {
                    case 1 -> gender = "female";
                    case 2 -> gender = "male";
                }
                String name = Dialogs.prompt("Animal name:");

                buyAnimal(player, price, name, gender, animalType);

                /*switch (animalType) {
                    case "Mammoth" -> buyMammoths(player, price, name, gender);
                }*/
            }
        }
    }
    public static void buyAnimal(Player player, int price, String name, String gender, String animalType){
        Animal newAnimal = null;
        switch(animalType){
            case "Mammoth" -> newAnimal = new Mammoth(name, gender);
        }

        ArrayList<Animal> temp = player.getAnimalInv();
        temp.add(newAnimal);
        player.setAnimalInv(temp);
        player.setCash(player.getCash() - price);
        Game.actionsTaken = true;
    }

    public static void buyFood(Player player) {
        var buying = true;
        var foodType = "";
        var price = 0;
        //select food
        while (buying) {
            //Dialogs.clear();
            player.showCashNFood();
            //select what to buy
            int input = Dialogs.promptInt("Store stock: [1:Cheezeburgers 10€/kg] [2:Lollipops 10€/kg]" +
                    " [3:Food3 10€/kg] [4:Food4 10€/kg] [5:Food5 10€/kg] [6:Done]", 1, 6);

            switch (input) {
                case 1 -> {foodType = "Cheezeburgers"; price = 10;}
                case 2 -> {foodType = "Lollipops"; price = 20;}
                case 3 -> {foodType = "Cheezeburgers"; price = 10;}
                case 4 -> {foodType = "Cheezeburgers"; price = 10;}
                case 5 -> {foodType = "Cheezeburgers"; price = 10;}
                case 6 -> {buying = false; break; }
            }
            System.out.println("You can afford max " + (player.getCash() / price) + " kgs of " + foodType);
            var kilos = Dialogs.promptInt("How many do you want?",1, (player.getCash() / price));
            switch (foodType) {
                case "Cheezeburgers" -> {

                    addToFoodInv(player, foodType, kilos, price);
                }
                case "Lollipops" -> addToFoodInv(player, foodType, kilos, price);
            }
        }
    }
    static void addToFoodInv (Player player, String foodType, int kilos, int price) {
        Food newFood = null;
        switch(foodType){
            case "Mammoth" -> newFood = new Cheezeburgers(kilos);
        }
        ArrayList<Food> temp = player.getFoodInv();

        if (!temp.contains(newFood)) {
            temp.add(newFood);
            player.setFoodInv(temp);
            player.setCash(player.getCash() - (price * kilos));
            Game.actionsTaken = true;
        }
        else if (temp.contains(newFood)) {
            for (Food food : temp) {
                if (food.getType()==1) {
                    food.setQuantity(food.getQuantity()+kilos);
                    player.setFoodInv(temp);
                    player.setCash(player.getCash() - (price * kilos));
                    Game.actionsTaken = true;
                    break;
                }
            }

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


    //add Foods to hashmap
    static void buyCheezeburgers(Player player, String foodType, int kilos, int price) {
        ///
        Cheezeburgers cheezeburgers = new Cheezeburgers();
        if (player.getFoodInv().containsKey(cheezeburgers)) {
            int tmp = player.foodInv.get(cheezeburgers);
            System.out.println("temp" + tmp);
            //int tmp = ((int) player.getFoodInv().get(foodType));
            //player.foodInv.computeIfPresent(cheezeburgers, (k, v) -> (v + kilos));
            //player.getFoodInv().merge(cheezeburgers, 1, (prev, kilos) -> tmp + kilos);

            player.getFoodInv().put(new Cheezeburgers().getClass().getSimpleName(), tmp+kilos);
            //player.getFoodInv().put(cheezeburgers, tmp+kilos);
        }
        else {
            //player.foodInv.put(cheezeburgers, kilos);
            player.getFoodInv().put(cheezeburgers, kilos);
            //player.getFoodInv().put(new Cheezeburgers().getClass().getSimpleName(), kilos);
        }
        player.setCash(player.getCash() - (price * kilos));
        Game.actionsTaken = true;
    }
    static void buyLollipops(Player player, String foodType, int kilos, int price) {

        if (player.getFoodInv().containsKey(foodType)) {
            int tmp = (int) player.getFoodInv().get(foodType);
            player.getFoodInv().put(new Lollipops().getClass().getSimpleName(), tmp+kilos);
        }
        else {
            player.getFoodInv().put(new Lollipops().getClass().getSimpleName(), kilos);
        }
        player.setCash(player.getCash() - (price * kilos));
        Game.actionsTaken = true;
    }

     */
}
