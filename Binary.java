/*
The program allows the user to convert decimal numbers to binary numbers as long as the user desires.
 */

import java.util.*;

public class Binary{
    private static final Scanner CONSOLE = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("Lab 8 Written By Eric Escalante");
        System.out.println();


        /*while loop that asks the user to enter a positive int.
        If not given it will continue asking the user until a positive int is entered.
        */

        while(true){
            int number = -1;

            while(number<0){
                System.out.println ("Enter a positive integer: ");
                if(!CONSOLE.hasNextInt()){
                    String trash = CONSOLE.next();
                    System.out.println("Not an integer; try again.");
                }
                if(number > 0){
                    System.out.println("Not a positive integer try again");

                }
                if(number < 0){
                    number = CONSOLE.nextInt( );
                }
            }

            int[] binary = convertToBinary(number);
            System.out.print(number + " decimal is equal to ");
            printBinaryArray(binary);
            System.out.println(" binary");


            System.out.println("Do you want to continue (yes or no)?");
            String answer = CONSOLE.next();

            if(answer.equals("no")){
                System.out.println("You answered no. Have a nice day.");
                break;
            }
        }
    }

    //This method has an integer parameter and returns an integer array.
    public static int [] convertToBinary(int decimal){

        int [] binaryNumber = new int [decimal];
        int i =0;

        while(decimal > 0){
            binaryNumber[i] = decimal % 2;
            decimal = decimal / 2;
            i++;
        }

        return binaryNumber;
    }

    /*This method has an integer array parameter and returns nothing.
    It uses System.out.print to print the values in the array.
     */
    public static void printBinaryArray(int[] binary){

        int i;
        for(i = binary.length -1; i>=0; i--) {
            if (binary[i] == 1)
                break;
        }
        for(; i>=0; i--)
            System.out.print(binary[i]);
    }
}
