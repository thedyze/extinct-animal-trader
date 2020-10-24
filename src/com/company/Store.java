package com.company;
import com.company.FoodSubClasses.*;
import com.company.AnimalSubClasses.*;
import java.util.*;

public class Store {

    public static void storeFront(Player player) {
        boolean inStore = true;
        //don't exit until player has made his actions
        while (inStore && !Game.actionsTaken) {
            Dialogs.clear();
            //store menu
            player.showStats();
            System.out.println("Welcome to the \u001B[1mExtinct Animals store!\033[0;0m \n\n    Select:\n" +
                    "<==============>");
            var input = Dialogs.promptInt(" 1.Buy Animals \n 2.Sell Animals \n 3.Buy Food " +
                    "\n 4.Exit Store\n<==============>", 1, 4);
            switch (input) {
                case 1 -> buyAnimals(player); //break;
                case 2 -> sellAnimal(player); //break;
                case 3 -> buyFood(player); //break;
                case 4 -> inStore = false; //break;}
            }
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
            int input = Dialogs.promptInt("\n\u001B[1m   Animal Selection:\033[0;0m\n<======================>" +
                    " \n1.Mammoth 250€ \n2.Flying Fox 150€ \n3.Dodo 125€ \n4.Giant Rat 100€\n4.Elephant Bird 175€" +
                    "\n<======================>\n6.DONE", 1, 6);
            switch (input) {
                case 1 -> {  price = 250; if (checkCash(player, price)) {animalType = "Mammoth"; } else continue;}
                case 2 -> {  price = 150; if (checkCash(player, price)) {animalType = "FlyingFox";} else continue;}
                case 3 -> {  price = 125; if (checkCash(player, price)) {animalType = "Dodo";} else continue;}
                case 4 -> {  price = 100; if (checkCash(player, price)) {animalType = "GiantRat"; } else continue;}
                case 5 -> {  price = 175; if (checkCash(player, price)) {animalType = "ElephantBird"; } else continue;}
                case 6 -> buying = false;
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
            case "GiantRat" -> newAnimal = new GiantRat(name, gender);
            case "ElephantBird" -> newAnimal = new ElephantBird(name, gender);
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
        }
        else {
            System.out.println("You have no animals to sell.");
        }
        Dialogs.enterToContinue();

    }

    static void buyFood(Player player) {
        var buyingFood = true;
        var foodType = "";
        var price = 0;

        while (buyingFood) {
            Dialogs.clear();
            player.showCashNFood();

            //select what to buy
            int input = Dialogs.promptInt("    Food selection:                 " +
                    "Can eat:\n<======================><------------------------>\n1.Cheezeburgers 15€/kg" +
                    "     Flying Foxes, Mammoths\n2.Lollipops     11€/kg             Elephant Birds" +
                    "\n3.Garbage        8€/kg          Giant Rats, Dodos" +
                    " \n<======================><------------------------>\n4.DONE", 1, 4);
            switch (input) {
                case 1 -> {foodType = "Cheezeburgers"; price = 15;}
                case 2 -> {foodType = "Lollipops"; price = 11;}
                case 3 -> {foodType = "Garbage"; price = 8;}
                case 4 -> buyingFood = false;
            }

            //select amount to buy
            if (input < 4) {
                var kilos = Dialogs.promptInt("You can afford max \u001B[1m" + (player.getCash() / price) +
                        "\033[0;0m kgs of \u001B[1m" + foodType + "\033[0;0m. \nHow many do you want?", 1, (player.getCash() / price));
                addToFoodInv(player, foodType, kilos, price);
            }
            /*
                switch (foodType) {
                    case "Cheezeburgers" -> addToFoodInv(player, foodType, kilos, price);
                    case "Lollipops" -> addToFoodInv(player, foodType, kilos, price);
                    case "Garbage" -> addToFoodInv(player, foodType, kilos, price);
                }

             */
        }
    }

    //add food to player
    static void addToFoodInv (Player player, String foodType, int kilos, int price) {
        Food newFood = null;
        ArrayList<Food> temp = player.getFoodInv();

        switch(foodType) {
            case "Cheezeburgers" -> newFood = new Cheezeburgers(kilos);
            case "Lollipops" -> newFood = new Lollipops(kilos);
            case "Garbage" -> newFood = new Garbage(kilos);
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
        //update player foodInv & cash
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
    //check if player have enough cash
    public static boolean checkCash(Player player/*,int input */, int price){
        /*
        int price = 0;
        switch (input) {
            case 1 -> price = 250;
            case 2 -> price = 150;
            case 3 -> price = 300;
        }

         */
        if(player.getCash() >= price ) {
            return true;
        }
        else {
            System.out.println("Not enough cash");
            Dialogs.enterToContinue();
            return false;
        }
    }
}
