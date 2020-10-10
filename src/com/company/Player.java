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

    public int getCash() {
        return this.cash;
    }

}
