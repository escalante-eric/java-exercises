//----------------------------------------------------
// Programmer: 		Eric Escalante
// Course: 			COSC 1437 Section 005
// Semester: 		Summer 2018
// Assignment #: 	08
// Due Date: 		June 27, 2018
//----------------------------------------------------

import javax.swing.JFrame;

public class CheckboxesTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
			// instantiate a frame
			// from the class JFrame
		JFrame frame = new JFrame("Fun with check boxes");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
			// the primary panel is added
			// to the frame and made visible
		frame.getContentPane().add(new CheckboxesGUI());
		frame.pack();
		frame.setVisible(true);
		
	}	// end method main

}	// end class CheckboxesTester
