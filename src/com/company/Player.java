package com.company;

import java.util.*;

public class Player {

    public static boolean actionTaken = false;
    public static Map <Food, Integer> foodInv;
    private String name;
    private int pNumber;
    public static int cash;

    public Player(String name, int pNumber) {
        this.name = name;
        this.pNumber = pNumber;
        cash = 10000;
        foodInv = new HashMap<>();
    }

    public String getName() {
        return this.name;
    }


    public static void playerAction() {
        var input = Dialogs.promptInt("\nChoose your action:\n [1:Visit Store] [2:Feed Animals] [3:Mate Animals]"
                , 1, 3);

        switch (input) {
            case 1: Store.menu();

            case 2: //feedAnimals();
            case 3: //mateAnimals();
        }

    }
}
