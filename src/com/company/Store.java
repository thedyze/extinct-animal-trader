package com.company;

import com.company.FoodSubClasses.Cheezeburgers;

import java.util.Collections;

public class Store {


    public static void storeFront(Player player) {

        var instore = true;
        while (instore && !Game.actionTaken) {
            Dialogs.clear();
            System.out.println("Welcome " + player.getName() + ", to the Extinct Animals store! Please make a selecton:");
            var input = Dialogs.promptInt("[1:Buy Animals] [2:Sell Animals] [3:Buy Food] " +
                    "[4:Exit Store]", 1, 4);
            switch (input) {
                case 1:    ;
                case 2:    ;
                case 3:  buyFood(player); break;
                case 4: { instore = false;
                          break;
                        }
            }
        }
    }

    public static void buyFood(Player player) {
        var buying = true;
        //select food
        while (buying) {
            Dialogs.clear();
            //display player food and cash
            System.out.println("You have: " + player.getCash() + "€. Your food inventory (kilos):");
            System.out.println(Collections.singletonList(player.getFoodInv()));
            //select what to buy
            int input = Dialogs.promptInt("Store stock: [1:Cheezeburgers 10€/kg] [2:Food2 10€/kg] [3:Food3 10€/kg] " +
                    "[4:Food4 10€/kg] [5:Food5 10€/kg] [6:Done]", 1, 6);
            switch (input) {
                case 1 -> buyKilos(player,"Cheezeburgers",10);
                case 2 -> buyKilos(player,"Cheezeburgers",10);
                case 3 -> buyKilos(player,"Cheezeburgers",10);
                case 4 -> buyKilos(player,"Cheezeburgers",10);
                case 5 -> buyKilos(player,"Cheezeburgers",10);
                case 6 -> buying = false;
            }
        }
    }

    //amount to buy
    public static void buyKilos(Player player, String foodType, int price) {

        System.out.println("You can afford max " + (player.getCash() / price) + " kilos of " + foodType);
        var input = Dialogs.promptInt("How many kilos do you want?",1, (player.getCash() / price));
        switch (foodType) {
            case "Cheezeburgers" -> buyCheezeburgers(player, foodType, input, price);
        }



    }
        //add Cheezeburgers to hashmap
        public static void buyCheezeburgers(Player player, String foodType, int input, int price) {
        if (player.getFoodInv().containsKey(foodType)) {
            int tmp = (int) player.getFoodInv().get(foodType);
            player.getFoodInv().put(new Cheezeburgers().getClass().getSimpleName(), tmp+input);
        }
        else {
            player.getFoodInv().put(new Cheezeburgers().getClass().getSimpleName(), input);
        }
        player.setCash(price * input);
        Game.actionTaken = true;


    }
}
