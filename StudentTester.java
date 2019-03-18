//----------------------------------------------------
// Programmer:		Eric Escalante
// Course:			COSC 1437 Section 005
// Semester:		Summer 2018
// Assignment #:	05
// Due Date:		June 20, 2018
//----------------------------------------------------

import javax.swing.*;   // for GUIs using swing

public class StudentTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
			// declare local variables to be used in program
		String street, town, state, zipCodeText;
		String firstName, lastName, testScore1Text;
		String testScore2Text, testScore3Text;
		int zipCode, testScore1, testScore2, testScore3;
		
		Address school = new Address ("800 Lancaster Ave", "Villanova", "PA", 19085);
		
		
			// enter student information via
			// option panes
		street = JOptionPane.showInputDialog(null, "Please enter your street address");
		town = JOptionPane.showInputDialog(null, "Please enter your town");
		state = JOptionPane.showInputDialog(null, "Please enter your state");
		zipCodeText = JOptionPane.showInputDialog(null, "Please enter your zip code");
		zipCode = Integer.parseInt(zipCodeText);
		
			// prompt and read in first
			// name and last name of student
		firstName = JOptionPane.showInputDialog(null, "Please enter your first name");
		lastName = JOptionPane.showInputDialog(null, "Please enter your last name");
		
			// prompt and read in three test scores of
			// student and convert each to an integer
		testScore1Text = JOptionPane.showInputDialog(null, "Please enter first test score for " +
				firstName);
		testScore1 = Integer.parseInt(testScore1Text);
		
		testScore2Text = JOptionPane.showInputDialog(null, "Please enter second test score for " +
				firstName);
		testScore2 = Integer.parseInt(testScore2Text);
		
		testScore3Text = JOptionPane.showInputDialog(null, "Please enter third test score for " +
				firstName);
		testScore3 = Integer.parseInt(testScore3Text);
		
			// instantiate an object about
			// student's home address
		Address sHome = new Address(street, town, state, zipCode);
		
			// instantiate an object about student (home and
			// school address and test scores)
		Student myStudent = new Student(firstName, lastName, sHome, school, testScore1,
				testScore2, testScore3);
		
			// print out info about student using option pane
		JOptionPane.showMessageDialog(null, myStudent);
		
			// replace test score two with another value
		testScore2Text = JOptionPane.showInputDialog(null, "Please enter updated second "
				+ "test score for " + firstName);
		testScore2 = Integer.parseInt(testScore2Text);
		
			// now update test score number two
		myStudent.setTestScore(2, testScore2);
		
			// show in option pane that a new score for test
			// two has taken place
		JOptionPane.showMessageDialog(null, firstName + "'s second test score is now " + 
			myStudent.getTestScore(2));
		
			// print out info about student using option pane
		JOptionPane.showMessageDialog(null, myStudent);
		
		JOptionPane.showMessageDialog(null,
				"This program was written by Eric Escalante" +
				"\nEnd of program.");
		
	}   // end method main

}   // end class StudentTester
