package com.company;

public class Store {


    public static void menu() {
        System.out.println("Welcome to the Extinct Animals store! Please make a selecton:");
        var input = Dialogs.promptInt("[1:Buy Animals] [2:Sell Animals] [3:Buy Food]", 1, 3);
    }
}
