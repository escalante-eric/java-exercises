/*This program will simulate a best-of-ten playoff series
using information supplied by the user.
It will Print information about the result of each simulated playoff
and about the final result of multiple simulations.
*/

import java.util.Random;
import java.util.Scanner;

public class Playoffs{
  public static final Scanner CONSOLE = new Scanner(System.in);
  public static void main(String[] args){
    System.out.println("Lab 6 written by Eric Escalante");
    System.out.println();

    Random rand = new Random();

    System.out.println("Enter the percent chance (1-100) that Team 1 will win: ");
    int userPercent = CONSOLE.nextInt();

    while(userPercent <= 0 || userPercent >= 100) {
        System.out.println("Enter the percent chance (1-100) that Team 1 will win: ");
        userPercent = CONSOLE.nextInt();
    }

    int manyPlayoffs = manyPlayoffs(userPercent, rand);

    if(manyPlayoffs == 1)
      System.out.println("\nFinal Result: Team 1 Wins");
    else if(manyPlayoffs == 2)
      System.out.println("\nFinal Result: Team 2 Wins");

  }

  /*This method has two parameters: the percent chance that Team 1 will win the game,
  and a Random object.
  This method only generates one random number.
  This method returns one value if Team 1 wins and a different value if Team 1 loses.
  */

  public static int oneGame(int team1, Random rand){

    int number1 = rand.nextInt(100);

    if(number1 <= team1){
      //System.out.println("Team 1 wins");
      return 1;
    }
    else{
      //System.out.println("Team 2 wins");
      return 2;
    }

  }

  /*This method simulates a playoff, that is, to play games until one team wins four games.
  This method uses a while loop for flow of control.
  This method has two parameters: the percent chance that Team 1 will win the game, and a Random object.
  This method returns different values depending on which team wins.
  */

  public static int onePlayoff(int team1, Random rand){

    int team1Wins = 0;
    int team2Wins = 0;
    int game;

    while(true){
      game = oneGame(team1, rand);
      if(game == 1)
        team1Wins++;
      else
        team2Wins++;

    if(team1Wins == 4)
      return 1;
    if(team2Wins == 4)
      return 2;
    }

  }

  /*This method keeps simulating playoffs until one team has won 10 more playoffs than the other team.
  This method uses a while loop for flow of control.
  This method has two parameters: the percent chance that Team 1 will win the game, and a Random object.
  This method prints the final results in the main method.
  */

  public static int manyPlayoffs(int team1, Random rand){

    int team1Playoffs = 0;
    int team2Playoffs = 0;

    while(true){

      int onePlayoff = onePlayoff(team1, rand);
      if(onePlayoff == 1){
        System.out.print("1");
        team1Playoffs++;
      }
      else if(onePlayoff == 2){
        System.out.print("2");
        team2Playoffs++;
      }

      if(team1Playoffs == 10)
        return 1;
      else if(team2Playoffs == 10)
        return 2;

    }

  }

}
