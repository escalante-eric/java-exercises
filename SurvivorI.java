/*This project write a program for a simple game.
In this game there will be a runner and a chaser.
The computer will control the chaser, which should always move toward the runner.
The user (you) will control the runner by keyboard input.
The goal is to keep away from the chaser for a short amount of time.
This project will involve graphics and animation.
The user is controlling the movement of the runner, the green square.
*/


import java.util.*;
import java.awt.*;


public class SurvivorI{
  public static final Scanner CONSOLE = new Scanner(System.in);
  public static void main(String[] args){

    System.out.println("Project 2 written by ERIC ESCALANTE");
    System.out.println();


    System.out.print("Enter the boxSize, sleepTime, and moveSize: ");

    // The box is a square of this size.
    //int boxSize = 760;
    int boxSize = CONSOLE.nextInt();
    if(boxSize<0 || boxSize>800)
      boxSize = 760;


    // The program should wait sleepTime ms between moves.
    //int sleepTime = 1000;
    int sleepTime = CONSOLE.nextInt();
    if(sleepTime<0 || sleepTime>800)
      sleepTime = 1000;

    //int moveSize = 10;
    int moveSize = CONSOLE.nextInt();
    if(moveSize<0 || moveSize>800)
      moveSize = 10;

    // Create DrawingPanel and draw a box in the panel.
    DrawingPanel panel = new DrawingPanel(boxSize, boxSize);
    Graphics g = panel.getGraphics();
    g.fillRect(10, 10, 10, boxSize - 20);
    g.fillRect(10, 10, boxSize - 20, 10);
    g.fillRect(boxSize - 20, 10, 10, boxSize -20);
    g.fillRect(10, boxSize - 20, boxSize - 20, 10);

    // Initialize positions of runner and chaser.
    Point runner = new Point(boxSize / 4, boxSize / 2);
    Point chaser = new Point((boxSize /4) *3, boxSize /2);

    // Variable for input from user to move runner.
    char keyInput = ' ';

    // Display players using Color.GREEN and Color.RED (or whatever colors you want)
    displayPlayers(panel, runner, chaser);

    // Wait one second before start of game.
    panel.sleep(1000);

    int i = 0;

    for(i=0; i<=300; i++){
      // Erase players from the panel using Color.WHITE.
      erasePlayers(panel, runner, chaser);

      // Get input from user if any.
      char newKeyInput = panel.getKeyChar();
      if (newKeyInput == 'w' || newKeyInput == 'a' || newKeyInput == 's' || newKeyInput == 'd'){
       keyInput = newKeyInput;
     }

     // Move the players according to parameters.
     movePlayers(runner, chaser, keyInput, boxSize, moveSize);

     // Display players using Color.GREEN and Color.RED (or whatever colors you want).
     displayPlayers(panel, runner, chaser);

     // Game is over if the chaser catches up to the runner.
     if (collision(runner, chaser) == true){

       g.drawString("YOU LOST!!!", boxSize / 2, boxSize /2);
       break;
     }
     // Wait sleepTime ms between moves.
     panel.sleep(sleepTime);


   }

     // NEED AN IF STATEMENT TO DETERMINE WHEN TO PRINT THIS.
     if (collision(runner,chaser) == false){
        g.drawString("YOU WON!!!", boxSize / 2, boxSize /2);

     }
}

  // The runner should move moveSize (or zero) pixels each time step.
  // The chaser should move moveSize - 1 pixels each time step.
  // Move the players according to parameters.
  public static void movePlayers(Point runner, Point chaser, char keyInput, int boxSize, int moveSize){
    if (keyInput == 'w' && runner.y>20)
     runner.translate(0, -moveSize);

   else if (keyInput == 's' && runner.y<boxSize - 20)
     runner.translate(0, moveSize);

   else if (keyInput == 'a' && runner.x>20)
     runner.translate(-moveSize, 0);

   else if (keyInput == 'd' && runner.x<boxSize -20)
     runner.translate(moveSize, 0);


    double tempX = chaser.getX();
    double tempY = chaser.getY();

    double up, down, left, right;

    up = distance(runner.getX(),runner.getY(), tempX, tempY-1);
    down = distance(runner.getX(),runner.getY(), tempX, tempY+1);
    left = distance(runner.getX(), runner.getY(), tempX-1, tempY);
    right = distance(runner.getX(), runner.getY(), tempX+1, tempY);

    if(up < down && up < left && up < right)
      chaser.translate(0,-(moveSize-1));

    else if(down < up && down < left && down < right)
      chaser.translate(0, (moveSize-1));

    else if(left < up && left < down && left < right)
      chaser.translate(-(moveSize-1), 0);

    else if(right < up && right < down && right < left)
      chaser.translate((moveSize-1), 0);


}

// Display players using Color.GREEN and Color.RED (or whatever colors you want).
public static void displayPlayers(DrawingPanel panel, Point runner, Point chaser){
    Graphics g = panel.getGraphics();

    g.setColor(Color.GREEN);
    g.fillRect(runner.x, runner.y, 10, 10);

    g.setColor(Color.RED);
    g.fillRect(chaser.x, chaser.y, 10, 10);

    g.setColor(Color.BLACK);
}

//Method that turns the runner and chaser white after it moves
public static void erasePlayers(DrawingPanel panel, Point runner, Point chaser){
    Graphics g = panel.getGraphics();

    g.setColor(Color.WHITE);
    g.fillRect(runner.x, runner.y, 10, 10);

    g.setColor(Color.WHITE);
    g.fillRect(chaser.x, chaser.y, 10, 10);

  }

  //This method will user the distance formula to show the distance between two points
  public static double distance(double x1, double y1, double x2, double y2){

    double x  = x1 - x2;
    double y = y1 - y2;
    double sqrX = Math.pow(x,2);
    double sqrY = Math.pow(y,2);

    return Math.sqrt(sqrX + sqrY);

  }

  //This method will give the return statemens if there was or was not a collision
  public static boolean collision(Point runner, Point chaser){
    if(distance(runner.getX(), runner.getY(), chaser.getX(), chaser.getY())<12){
      return true;
    }
    else
      return false;
  }

}
