//----------------------------------------------------
// Programmer:		Eric Escalante
// Course:			COSC 1437 Section 005
// Semester:		Summer 2018
// Assignment #:	06
// Due Date:		June 20, 2018
//----------------------------------------------------
	
	// for GUI programming
import javax.swing.JFrame;

public class NumericKeypadTester
{
   
   public static void main (String[] args)
   {
	   	// create a frame and add pane to it and
	   	// make it visible
	   JFrame numericFrame = new JFrame("Numeric Keypad");
	   numericFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   numericFrame.getContentPane().add(new NumericKeypadPanel());
	   numericFrame.pack();
	   numericFrame.setVisible(true);

   }	// end method main
}	// end class NumericKeypadTester
