package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public static int currentTurn = 1;
    public static int totalTurns;
    public static int numberOfPlayers;
    public static Object currentPlayer;

    public static ArrayList<Player> players = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);




    static void turn() {
        //loop game turns
        for (currentTurn = 1; currentTurn <= totalTurns; currentTurn++) {
            //loop player turns
            Player currentPlayer;
            for (int i = 0; i < numberOfPlayers; i++) {
                currentPlayer = players.get(i);
                //Dialogs.clear();
                System.out.println("Turn: " + currentTurn + "/" + totalTurns +
                        ". Player: " + currentPlayer.getName() + ", you have "
                        + currentPlayer.getCash() + "€.");
                Player.actionTaken = false;
                Player.playerAction();
            }
        }

    }
    static void init() {

        //Dialogs.clear();
        System.out.println("      Welcome to: \n \u001B[1mEXTINCT ANIMAL TRADER\033[0;0m \n ----------------------\n");
        var input = Dialogs.promptInt("Press 1 for new game :>", 1, 1);
        numberOfPlayers = Dialogs.promptInt("How many players? (1-4)", 1, 4);

        //add players
        for (int i = 0; i < numberOfPlayers; i++ ) {
            Player player = new Player(Dialogs.prompt("Player "  + (i + 1) + " name?"),i);
            players.add(player);
            //players.add(new Player(Dialogs.prompt("Player "  + (i + 1) + " name?"),i));

        }
        //select number of game turns
        totalTurns = Dialogs.promptInt("Select Game duration? (5-30 turns):", 5,30);
        Game.turn();
        //Game.turn(players);

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



}

