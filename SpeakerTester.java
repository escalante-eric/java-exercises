//----------------------------------------------------
// Programmer: 		Eric Escalante
// Course: 			COSC 1437 Section 005
// Semester: 		Summer 2018
// Assignment #: 	09
// Due Date: 		June 27, 2018
//----------------------------------------------------


import javax.swing.*;
public class SpeakerTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		   // declare object variables
		   // to be used in program
		Speaker rover, aristotle, someone;
		
		   // instantiate 'rover' to be a 'Dog' object
		rover = new Dog("Rover");
		rover.speak();
		rover.announce("Ruff ruff to all my fellow dogs!");
		
		   // instantiate 'aristotle' to be a 'Philosopher' object
		aristotle = new Philosopher("Aristotle", "A true friend is one soul in two bodies");
		aristotle.speak();
		aristotle.announce("All men by nature desire to know");
		
			// have aristotle pontificate!
		((Philosopher) aristotle).pontificate();
		
			// instantiate 'someone' to be a 'Dog' object
		someone = new Dog("Someone");
		someone.speak();
		someone.announce("Somebody thinks he is a dog");
		
			// instantiate 'someone' to be a 'Philosopher' object
		someone = new Philosopher("Someone", "I think like a philosopher");
		someone.speak();
		someone.announce("Someone thinks he is smart");
		
			// have 'someone' pontificate!
		((Philosopher) someone).pontificate();
		
		JOptionPane.showMessageDialog(null, "This program was written by Eric Escalante.");

	}   // end method main

}   // end class SpeakerTester
