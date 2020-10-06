package com.company;
import java.util.*;

public class MainMenu {

    public static int player = 1;
    public static int totalTurns = 5;
    public static int numberOfPlayers = 4;
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<String> pNames = new ArrayList<>();
    public static ArrayList<Integer> playerCash = new ArrayList<>();


    public static void init() {

        Dialogs.clear();
        System.out.println("      Welcome to: \n \u001B[1mEXTINCT ANIMAL TRADER\033[0;0m \n ----------------------\n");
        var input = Dialogs.promptInt("Press 1 for new game :>", 1, 1);
        numberOfPlayers = Dialogs.promptInt("How may players? (1-4)", 1, 4);

        //loop through number of players and set names, add cash
        for (int i = 0; i < numberOfPlayers; i++ ) {
            System.out.println("Player "  + (i + 1) + " name?");
            pNames.add (scanner.nextLine());
            playerCash.add(10000);
        }

        System.out.println("Welcome to the game ");
        for (String pName : pNames) {
            System.out.println(pName);
        }
        //select number of game turns
        totalTurns = Dialogs.promptInt("How many turns should the game be? (5-30)", 5,30);
    }

}
