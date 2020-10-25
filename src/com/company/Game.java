package com.company;
import java.util.ArrayList;

public class Game {
    public static ArrayList<Player> playerList = new ArrayList<>();
    public static boolean actionsTaken;

    //main game loop
    static void turn(int totalPlayers, int totalTurns) {
        int turn;
        for (turn = 1; turn <= totalTurns; turn++) {

            //loop player turns
            Player player;
            for (int i = 0; i < totalPlayers; i++) {
                Dialogs.clear();
                actionsTaken = false;
                player = playerList.get(i);
                if (turn==totalTurns) {
                    System.out.println("LAST TURN!");}
                player.removeDeadAnimals();
                playerAction(player, turn, totalTurns);
                player.reduceAnimalHealth();
                //remove players that lost
                if (hasLost(player)) {
                    System.out.println("Player "+player.getName() + "/"+totalPlayers + player.getName() +
                            " has no cash or animals, and is therefore out of the game!");
                    Dialogs.enterToContinue();
                }
            }
            //playerList.removeIf(x -> ((hasLost(x))));
            playerList.removeIf(Game::hasLost);
            totalPlayers = playerList.size();
            if (turn==totalTurns) { gameEndCheck(); }
        }


    }

    //main loop
    static public void playerAction(Player player, int turn, int totalTurns) {
        while (!Game.actionsTaken){
            player.showCashNAnimals();
            System.out.println("Game turn: \u001B[1m" + turn + "/" + totalTurns +"\033[0;0m");
            var input = Dialogs.promptInt("\nChoose your action:\n [1.Visit Store] " +
                            "[2.Feed Animals] [3.Mate Animals]"
                , 1, 3);

            switch (input) {
                case 1 -> Store.storeFront(player);
                case 2 -> feedAnimal(player);
                case 3 -> mateAnimals(player);
            }
        }
    }

    public static void feedAnimal(Player player) {

        ArrayList<Food> tempFoodInv = player.getFoodInv();
        player.showCashNAnimals();
        int animalToFeed = 0;
        int foodToFeed;
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

            if (tempFoodInv.size() > 0) {
                player.showCashNFood();
                foodToFeed = Dialogs.promptInt("Select food to feed animal with:",
                        1, tempFoodInv.size() + 1)-1;

                int t = player.animalInv.get(animalToFeed).getFeedsOn();
                System.out.println(t);

                //right kind of food - add health, subtract food
                if (tempFoodInv.get(foodToFeed).getType() == player.animalInv.get(animalToFeed).getFeedsOn()
                        && (tempFoodInv.get(foodToFeed).getQuantity() > 0)) {

                    player.animalInv.get(animalToFeed).setHealth(-10);
                    tempFoodInv.get(foodToFeed).setQuantity(tempFoodInv.get(foodToFeed).getQuantity() - 1);
                    //notify player that animal ate, remaining food
                    System.out.println(player.animalInv.get(animalToFeed).getClass().getSimpleName() + " " +
                            player.animalInv.get(animalToFeed).getName() + " ate and now have "
                            + player.animalInv.get(animalToFeed).getHealth() + " health.\n" +
                            "You have " + tempFoodInv.get(foodToFeed).getQuantity() + "kgs of " +
                            tempFoodInv.get(foodToFeed).getClass().getSimpleName() + " left");



                        //remove food if quantity = 0
                        if (tempFoodInv.get(foodToFeed).getQuantity() == 0) {
                            tempFoodInv.remove(foodToFeed);
                        }
                        Game.actionsTaken = true;
                        Dialogs.enterToContinue();
                    //}
                }

                //wrong type of food
                else if (tempFoodInv.get(foodToFeed).getType() != player.animalInv.get(animalToFeed).getFeedsOn()) {
                    System.out.println(player.animalInv.get(animalToFeed).getClass().getSimpleName() + " "
                            + player.animalInv.get(animalToFeed).getName() + " can't eat "
                            + tempFoodInv.get(foodToFeed).getClass().getSimpleName() + "!");
                    Dialogs.enterToContinue();
                    feeding = false;
                }
                else if (player.foodInv.get(foodToFeed).getQuantity() == 0) {
                    System.out.println("You have no food");
                    feeding = false;
                    Dialogs.enterToContinue();
                }
            }
            else if (player.foodInv.size() == 0) {
                System.out.println("You have no food");
                feeding = false;
                Dialogs.enterToContinue();
            }
        }
            player.setFoodInv(tempFoodInv);
    }

    static void mateAnimals(Player player) {
        Dialogs.clear();
        player.showCashNAnimals();

        //setup mating variables
        int mate1 = Dialogs.promptInt("First animal to mate:",1, player.animalInv.size() + 1) - 1;
        int mate2 = Dialogs.promptInt("Second animal to mate:",1, player.animalInv.size() + 1) - 1;
        String animalType1 = player.animalInv.get(mate1).getClass().getSimpleName();
        String animalType2 = player.animalInv.get(mate2).getClass().getSimpleName();
        String gender1 = player.animalInv.get(mate1).gender;
        String gender2 = player.animalInv.get(mate2).gender;

        //try to mate (not with self, only with same animal type, and different gender)
        if (!(mate1 == mate2) && (animalType1.equals(animalType2)) && !(gender1.equals(gender2))) {
            boolean rand = Math.random() < 0.5;
            if (rand) {
                boolean rand2 = Math.random() < 0.5;
                String gender;
                if (rand2) {gender = "male";}
                else {gender = "female";}
                String name = Dialogs.prompt("Success!! It's a " + gender + "! Choose baby animal name:");
                Store.addNewAnimal(player, 0, name, gender, animalType1);
                Game.actionsTaken = true;
                player.showCashNAnimals();
                Dialogs.enterToContinue();
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
        if ((mate1 != mate2) && gender1 == gender2) {
            System.out.println("Only male + female can mate!");
            Dialogs.enterToContinue();
        }
        if (!animalType1.equals(animalType2)) {
            System.out.println("Only animals of the same species can mate");
            Dialogs.enterToContinue();
        }

    }

    static void newGame() {
        int totalTurns;
        int totalPlayers;
        //welcome
        Dialogs.clear();
        System.out.println("      Welcome to: \n \u001B[1mEXTINCT ANIMAL TRADER\033[0;0m \n ----------------------\n");
        Dialogs.promptInt("Press [1] for new game:", 1, 1);
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

    //check if player lost
    static boolean hasLost(Player player) {
        return (player.getCash() == 0 && player.animalInv.size() == 0);
    }
    //who got the most cash?
    static void gameEndCheck() {
        Player player;
        System.out.println("GAME FINISHED!\nSelling all remaining animals.");
        Dialogs.enterToContinue();
        int endCash = 0;
        String winner = "";

        //for (int i = 0; i < playerList.size(); i++) {

        //sell remaining animals
        for (Player value : playerList) {

            player = value;
            for (int a = player.animalInv.size() - 1; a >= 0; a--) {
                if (player.animalInv.get(a).getHealth() <= 0) {
                    System.out.println(player.animalInv.get(a).getClass().getSimpleName()
                            + player.animalInv.get(a).getName() + "died");
                    player.animalInv.remove(a);
                }
                if (player.animalInv.size() > 0) {
                    int sellPrice = (int) player.animalInv.get(a).getSellPrice();
                    player.setCash(player.getCash() + sellPrice);
                    player.animalInv.remove(a);
                }
            }
            //check who won
            if (player.getCash() > endCash) {
                endCash = player.getCash();
                winner = player.getName();
            }
            System.out.println("Player \u001B[1m" + player.getName() + "\033[0;0m your final cash: \u001B[1m" +
                    player.getCash() + "\033[0;0m€");
        }
        System.out.println("\nWinner is Player: \u001B[1m" + winner +"\033[0;0m!");
    }
}

