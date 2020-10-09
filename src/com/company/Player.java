package com.company;

import java.util.*;

public class Player {

    public static boolean actionTaken = false;
    public static int cash;
    //public static ArrayList<Integer> cash = new ArrayList<>();
    //public static ArrayList<Object> playerList = new ArrayList<>();
    public static Map <Food, Integer> foodInv;
    public static String playerName;
    int playerNumber;

    public Player(String name /*,  int pnumber */) {
        playerName = name;
        //playerNumber = pnumber;
        cash = 10000;
        foodInv = new HashMap<>();
        //names.add (Dialogs.prompt("Player"));

        Game.players.add (new Player(name));
    }

    //public static void createFoodList(){
    //    foodList = new HashMap<>();
    //}
    //public static Map <Integer, int[]> food = new HashMap<>();
    /*
    public static void createFoodHashMaps(String mapName ){

        for (int i = 0; i < Game.numberOfPlayers; i++ ) {
            //String mapName = ("foodMap" + i);
            public static Map <String, Integer>  mapName = new HashMap<>();
        }
    }

     */
}
