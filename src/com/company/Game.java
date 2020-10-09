package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public static int currentTurn = 1;
    public static int totalTurns;
    public static int numberOfPlayers;
    public static int currentPlayer = 1;

    public static ArrayList<Object> players = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);

    public static void turn() {
        //currentPlayer = players.get(0);
        for (currentTurn = 1; currentTurn <= totalTurns; currentTurn++) {
            for (currentPlayer = 1; currentPlayer <= numberOfPlayers; currentPlayer++) {
                players.get(currentPlayer-1);
                //Dialogs.clear();
                System.out.println("Turn: " + currentTurn + "/" + totalTurns +
                        ". Player: " + Player.playerName + ", you have "
                        + Player.cash + "€.");
                Player.actionTaken = false;
                playerAction();
            }
        }

    }
/*
    public static void turn() {
        for (currentTurn = 1; currentTurn <= totalTurns; currentTurn++) {
            //int i = 0;
            for (playerList.indexOf(currentPlayer-1); playerList.indexOf(currentPlayer-1) <= playerList.size(); currentPlayer++) {
            //for (currentPlayer = 1; currentPlayer <= numberOfPlayers; currentPlayer++) {
            //for (Player turn:playerList) {
            //for (int i=0; i < playerList.size(); i++) {
                //Dialogs.clear();
                System.out.println("Turn: " + currentTurn + "/" + totalTurns +
                        ". Player: " + Player.playerName + ", you have "
                        + Player.cash + "€.");
                Player.actionTaken = false;
                playerAction();
            }
        }

    } */

    public static void playerAction() {
        var input = Dialogs.promptInt("\nChoose your action:\n [1:Visit Store] [2:Feed Animals] [3:Mate Animals]"
                , 1, 3);

        switch (input) {
            case 1: Store.menu();

            case 2: //feedAnimals();
            case 3: //mateAnimals();
        }

    }
    public static void init() {

        //Dialogs.clear();
        System.out.println("      Welcome to: \n \u001B[1mEXTINCT ANIMAL TRADER\033[0;0m \n ----------------------\n");
        var input = Dialogs.promptInt("Press 1 for new game :>", 1, 1);
        numberOfPlayers = Dialogs.promptInt("How may players? (1-4)", 1, 4);

        //add players
        for (int i = 0; i < numberOfPlayers; i++ ) {
            Player player = new Player(Dialogs.prompt("Player "  + (i + 1) + " name?")/*,i*/);

        }
        //select number of game turns
        totalTurns = Dialogs.promptInt("Select Game duration? (5-30 turns):", 5,30);
    }
}

