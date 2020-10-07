package com.company;

import java.util.Collections;

public class Store {


    public static void menu() {

        var instore = true;
        while (instore) {
            Dialogs.clear();
            System.out.println("Welcome to the Extinct Animals store! Please make a selecton:");
            var input = Dialogs.promptInt("[1:Buy Animals] [2:Sell Animals] [3:Buy Food] " +
                    "[4:Exit Store]", 1, 4);
            switch (input) {
                case 1:    ;
                case 2:    ;
                case 3:  buyFood(); break;
                case 4: { instore = false;
                          break;
                        }
            }
        }
    }

    public static void buyFood() {
        var inFood = true;
        //select food
        while (inFood) {
            //display player food and cash
            Dialogs.clear();
            System.out.println("You have: " + Player.cash + "€. Your food inventory:");
            System.out.println(Collections.singletonList(Player.food));

            var input = Dialogs.promptInt("Current stock: [1:Food1 10€/kg] [2:Food2 10€/kg] [3:Food3 10€/kg] " +
                    "[4:Food4 10€/kg] [5:Food5 10€/kg] [6:Back to Store Front]", 1, 6);
            switch (input) {
                case 1 -> purchaseAmount(1, 10);
                case 2 -> purchaseAmount(2, 10);
                case 3 -> purchaseAmount(3, 10);
                case 4 -> purchaseAmount(4, 10);
                case 5 -> purchaseAmount(5, 10);
                case 6 -> inFood = false;
            }
        }
    }
    //amount to buy
    public static void purchaseAmount(int foodtype, int price) {
        var foodName = "empty";
        switch (foodtype) {
            case 1 -> foodName = "Food1";
            case 2 -> foodName = "Food2";
            case 3 -> foodName = "Food3";
            case 4 -> foodName = "Food4";
            case 5 -> foodName = "Food5";
        }
        System.out.println("You can afford max " + (Player.cash/price) + " kilos of " + foodName);
        var input = Dialogs.promptInt("How many kilos do you want?", 1, (Player.cash/price));

        //add to hashmap
        if (Player.food.containsKey(foodName)) {
            Integer count = Player.food.get(foodName) + input;
            Player.food.put(foodName, count);

        }
        else {
            Player.food.put(foodName, input);
        }
        Player.cash -= (price*input);
    }
}
