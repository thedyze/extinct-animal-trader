package com.company;
import java.util.ArrayList;

public class Game {
    public static ArrayList<Player> playerList = new ArrayList<>();
    public static ArrayList<Player> toRemoveList;
    public static boolean actionsTaken;

    /*
    public ArrayList getPlayerList() { return Game.playerList;}
    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }
    */
    //loop game turns
    static void turn(int totalPlayers, int totalTurns) {
        int turn;
        for (turn = 1; turn <= totalTurns; turn++) {
            //loop player turns
            Player player;
            for (int i = 0; i < totalPlayers; i++) {
                Dialogs.clear();
                actionsTaken = false;
                player = playerList.get(i);
                System.out.println("Game turn: \u001B[1m" + turn + "/" + totalTurns +"\033[0;0m");
                player.removeDeadAnimals();
                playerAction(player);
                player.reduceAnimalHealth();

                //remove players that lost
                if (hasLost(player)) {
                    System.out.println("Player "+player.getName() + "/"+totalPlayers + player.getName() +
                            " has no cash or animals, and is therefore out of the game!");
                    Dialogs.enterToContinue();
                }
            }
            playerList.removeIf(x -> ((hasLost(x))));
            totalPlayers = playerList.size();
        }
    }
    //main loop
    static public void playerAction(Player player) {
        while (!Game.actionsTaken){
            player.showStats();
            var input = Dialogs.promptInt("\nChoose your action:\n [1.Visit Store] " +
                            "[2.Feed Animals] [3.Mate Animals]"
                , 1, 3);

            switch (input) {
                case 1 -> {Store.storeFront(player); break;}
                case 2 -> {feedAnimal(player); break;}
                case 3 -> {mateAnimals(player); break; }
            }
        }
    }

    public static void feedAnimal(Player player) {

        ArrayList<Food> tempFood = player.getFoodInv();
        player.showStats();
        int animalToFeed = 0;
        int foodToFeed = 0;
        boolean feeding = true;
        while (feeding && !Game.actionsTaken) {

            if (player.animalInv.size() > 0) {
                animalToFeed = Dialogs.promptInt("Select animal to feed:",
                        1, player.animalInv.size() + 1) - 1;
            }
            else {
                System.out.println("You have no animals to feed.");
                Dialogs.enterToContinue();
            }

            if (player.foodInv.size() > 0) {
                player.showCashNFood();
                foodToFeed = Dialogs.promptInt("Select food to feed animal with:",
                        1, player.foodInv.size() + 1)-1;

                int t = player.animalInv.get(animalToFeed).getFeedsOn();
                System.out.println(t);

                //right kind of food - add health, subtract food
                if ((foodToFeed+1) == player.animalInv.get(animalToFeed).getFeedsOn() && (player.foodInv.get(foodToFeed).getQuantity() > 0)) {

                    player.animalInv.get(animalToFeed).setHealth(-10);
                    System.out.println("Animal health: " + player.animalInv.get(animalToFeed).getHealth());
                    player.foodInv.get(foodToFeed).setQuantity(player.foodInv.get(foodToFeed).getQuantity()-1);
                    Game.actionsTaken = true;
                    Dialogs.enterToContinue();
                }
                //wrong type of food
                else if ((foodToFeed+1) != player.animalInv.get(animalToFeed).getFeedsOn()) {
                    System.out.println(player.animalInv.get(animalToFeed).getClass().getSimpleName() + " "
                            + player.animalInv.get(animalToFeed).getName() + " can't eat "
                            + player.foodInv.get(foodToFeed).getClass().getSimpleName() + "!");
                    Dialogs.enterToContinue();
                }
                else if (player.foodInv.get(foodToFeed).getQuantity() == 0) {
                    System.out.println("You have no food");
                }
            }
            else if (player.foodInv.size() == 0) {
                System.out.println("You have no food");
            }
        }
    }

    static void newGame() {
        int players;
        int totalTurns;
        int totalPlayers;
        //welcome
        Dialogs.clear();
        System.out.println("      Welcome to: \n \u001B[1mEXTINCT ANIMAL TRADER\033[0;0m \n ----------------------\n");
        Dialogs.promptInt("Press 1 for new game:", 1, 1);
        totalPlayers = Dialogs.promptInt("How many players? (1-4)", 1, 4);

        //add players
        for (int i = 0; i < totalPlayers; i++) {
            Player player = new Player(Dialogs.prompt("Player " + (i + 1) + " name?"));
            Game.playerList.add(player);
        }

        //select number of game turns
        totalTurns = Dialogs.promptInt("Select Game duration? (5-30 turns):", 5, 30);
        Game.turn(totalPlayers, totalTurns);
    }

    static void mateAnimals(Player player) {
        Dialogs.clear();
        player.showStats();
        //setup mating variables
        int mate1 = Dialogs.promptInt("First animal to mate:",1, player.animalInv.size() + 1) - 1;
        int mate2 = Dialogs.promptInt("Second animal to mate:",1, player.animalInv.size() + 1) - 1;
        Object animalType1 = player.animalInv.get(mate1).getClass().getSimpleName();
        Object animalType2 = player.animalInv.get(mate2).getClass().getSimpleName();
        String gender1 = player.animalInv.get(mate1).gender;
        String gender2 = player.animalInv.get(mate2).gender;

        //try to mate (not with self, only with same animal type, and different gender)
        if (!(mate1 == mate2) && (animalType1 == animalType2) && !(gender1 == gender2)) {
            boolean rand = Math.random() < 0.5;
            if (rand) {
                boolean rand2 = Math.random() < 0.5;
                String gender;
                if (rand2) {gender = "male";}
                else {gender = "female";}
                String name = Dialogs.prompt("Success!! It's a " + gender + "! Choose baby animal name:");
                Store.addNewAnimal(player, 0, name, gender, "Mammoth");
                Game.actionsTaken = true;
            }
            else {
                System.out.println("There was no baby :´(");
                Game.actionsTaken = true;
                Dialogs.enterToContinue();
            }

        }
        if (mate1 == mate2) {
            System.out.println("These animals are not hermaphrodites!");
            Dialogs.enterToContinue();
        }
        if (gender1 == gender2) {
            System.out.println("Only male + female can mate!");
            Dialogs.enterToContinue();
        }
        if (!(animalType1 == animalType2)) {
            System.out.println("Only animals of the same species can mate");
            Dialogs.enterToContinue();
        }

    }
    //check if player lost
    static boolean hasLost(Player player) {
        return (player.getCash() == 0 && player.animalInv.size() == 0);
    }
}

