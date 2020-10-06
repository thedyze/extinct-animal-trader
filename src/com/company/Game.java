package com.company;
import java.util.*;

public class Game {
    public static int currentTurn = 0;
    public static int totalTurns = 5;
    public static int numberOfPlayers = 4;
    public static int player = 1;
    public static ArrayList<String> pNames = new ArrayList<>();
    public static ArrayList<Integer> pCash = new ArrayList<>();


    public static void turn() {
            if (currentTurn <totalTurns) {
                currentTurn++;
            }

            for (player = 1; player <= numberOfPlayers; player++) {
                System.out.println("Turn: " +currentTurn + ", Player: " + pNames.get(player-1));
                System.out.println("You have " + pCash.get(player-1) + "€");
            }
    }
}
