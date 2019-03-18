//----------------------------------------------------
// Programmer: 		Eric Escalante
// Course: 			COSC 1437 Section 005
// Semester: 		Summer 2018
// Assignment #: 	07
// Due Date: 		June 27, 2018
//----------------------------------------------------

import javax.swing.*;
import java.text.NumberFormat;

public class MonetaryCoinTester {

	public static void main(String[] args) {
		   	// declare an array from the 
			// MonetaryCoin class to
		   	// represent different coins
		MonetaryCoin[] coins = new MonetaryCoin[6];

		
		   	// declare local variables to be
			// used in our program
		int i;
		double sum = 0.0;
		String output;
		
		   	// for formatting nice numbers
		NumberFormat fmt = NumberFormat.getCurrencyInstance();
				
		   	// instantiate each element of the array
		   	// 'coins' and pass to the constructor
			// the value of that coin (each coin
			// will be flipped one at a time)
		coins[0] = new MonetaryCoin(1);
		coins[1] = new MonetaryCoin(5);
		coins[2] = new MonetaryCoin(10);
		coins[3] = new MonetaryCoin(25);
		coins[4] = new MonetaryCoin(50);
		coins[5] = new MonetaryCoin(100);
		
		   	// flip all the coins again
		for (i = 0; i < coins.length; i++)
		{
			// method flip is in the parent class
			coins[i].flip();
		}
	
		   	// compute the total value of the coins
		   	// with heads only
		for (i = 0; i < coins.length; i++)
		{
			// method getValue is in the child class
			sum += coins[i].getValue();
		}
		
		sum = sum / 100.0;
		
			// prepare string for display
		output = "Flip outcome for all coins:\n\n";
		
		for (i = 0; i < coins.length; i++)
		{
			output += coins[i] + "\n";
		}
				
		   	// format our total sum nicely
		output += "Total value: " + fmt.format(sum);
		
		  	// display final result!
		JOptionPane.showMessageDialog(null, output);
		JOptionPane.showMessageDialog(null, "This program was written by Eric Escalante."
				+ "\nEnd of program.");
		
	}   // end method main

}   // end class MonetaryCoinTester

