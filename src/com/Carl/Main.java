package com.Carl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static Random rand = new Random();
    static Scanner input = new Scanner(System.in);

    static int winningScore = 100;

    public static Player[] players = new Player[]{
            new Player("Carl", 0),
            new Player("Chris", 0)
    };

    public static void main(String[] args) {
        int currentTurn = 0;

        while(DoesPlayerHavePoints(players, winningScore) == false){
            for (int i = 0; i < players.length; i++) {
                System.out.println("-----------------------------");
                System.out.println(players[i].GetName() + ": Please type a character to roll your die");
                input.next();

                int total = 0;

                while (true){
                    int[] rolls = RollDie(2);
                    total += Sum(rolls);
                    int ones = ArraySearch(rolls, 1);

                    if (ones == 1){
                        System.out.println(players[i].GetName() + " rolled a single 1. Your turn is over and you get 0 points!");
                        break;
                    }
                    else if (ones == 2){
                        System.out.println(players[i].GetName() + " rolled double 1s. You have lost ALL of your points!");
                        players[i].ClearPoints();
                        break;
                    }
                    else{
                        System.out.println(players[i].GetName() + " has scored " + total + " so far. You would have " + (players[i].GetPoints() + total) + " Points! Would you like to roll again [R] or end your turn [Any Other Key]?");
                        if (input.next().equalsIgnoreCase("R") == true) {
                            continue;
                        }

                        players[i].AddPoints(total);
                        System.out.println(players[i].GetName() + " now has " + players[i].GetPoints() + " points");
                        break;
                    }
                }
            }
        }

        List<String> winners = GetPlayersWithPoints(players, winningScore);
        System.out.println("WINNERS: " + winners.toString());
    }

    public static List<String> GetPlayersWithPoints(Player[] players, int points){
        List<String> matches = new ArrayList<String>();

        for (int i = 0; i < players.length; i++){
            if (players[i].GetPoints() >= points){
                matches.add(players[i].GetName());
            }
        }

        return matches;
    }

    public static boolean DoesPlayerHavePoints(Player[] players, int points){
        int total = 0;

        for (int i = 0; i < players.length; i++){
            if (players[i].GetPoints() >= points){
                total++;
            }
        }

        return total > 0;
    }

    public static int ArraySearch(int[] nums, int numToSearch){
        int found = 0;

        for (int i = 0; i < nums.length; i ++){
            if (nums[i] == numToSearch){
                found++;
            }
        }

        return found;
    }

    public static int[] RollDie(int amount){
        int[] rolls = new int[amount];

        for (int i = 0; i < amount; i++) {
            rolls[i] = rand.nextInt(6);
        }

        return rolls;
    }

    public static int Sum(int[] nums) {
        int total = 0;

        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
        }

        return total;
    }
}
