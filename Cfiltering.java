// **********************************************************
// Assignment0:
// UTORID:pahirath
// UT Student #:1003371549
// Author:Harri Pahirathan
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences. In this semester
// we will select any three of your assignments from total of 5 and run it
// for plagiarism check. 
// *********************************************************
package a0;

//Imports needed to produce outcome and hold values
import java.text.DecimalFormat;

public class Cfiltering {
  // this is a 2d matrix i.e. user*movie
  private int userMovieMatrix[][];
  // this is a 2d matrix i.e. user*movie
  private float userUserMatrix[][];

  /**
   * Default Constructor.
   */
  public Cfiltering() {
    // this is 2d matrix of size 1*1
    userMovieMatrix = new int[1][1];
    // this is 2d matrix of size 1*1
    userUserMatrix = new float[1][1];
  }

  /*
   * TODO:COMPLETE THIS I.E. APPROPRIATELY CREATE THE userMovieMatrix AND
   * userUserMatrix WITH CORRECT DIMENSIONS.
   */
  /**
   * Constructs an object which contains two 2d matrices, one of size
   * users*movies which will store integer movie ratings and one of size
   * users*users which will store float similarity scores between pairs of
   * users.
   * 
   * @param numberOfUsers Determines size of matrix variables.
   * @param numberOfMovies Determines size of matrix variables.
   */
  public Cfiltering(int numberOfUsers, int numberOfMovies) {
	  // this will create the movie matrix
	  userMovieMatrix = new int[numberOfUsers][numberOfMovies];
	  // this will create the user matrix
	  userUserMatrix = new float[numberOfUsers][numberOfUsers];

  }

  /**
   * The purpose of this method is to populate the UserMovieMatrix. As input
   * parameters it takes in a rowNumber, columnNumber and a rating value. The
   * rating value is then inserted in the UserMovieMatrix at the specified
   * rowNumber and the columnNumber.
   * 
   * @param rowNumber The row number of the userMovieMatrix.
   * @param columnNumber The column number of the userMovieMatrix.
   * @param ratingValue The ratingValue to be inserted in the userMovieMatrix
   */
  public void populateUserMovieMatrix(int rowNumber, int columnNumber,
      int ratingValue) {
	// Adds the value into the corresponding row and column
    userMovieMatrix[rowNumber][columnNumber] = ratingValue;
  }

  /**
   * The purpose of this method is to create and add the similarity scores of
   * the users into the user matrix.
   */
  public void createUserUserMatrix() {
	  // Integer count to hold the row index
	  int row = 0;
	  // Integer count to hold the column index
	  int col = 0;
	  // Loops through the rows of the matrix and put similarity scores
	  for(row = 0; row < userUserMatrix.length; row++) {
		  // Loops through the columns of the matrix and put similarity scores
		  for (col = 0; col < userUserMatrix.length; col++) {
			  // Assigns the score between two users into the matrix
			  userUserMatrix[row][col] = calculateSimilarityScore(userMovieMatrix[row], 
					  userMovieMatrix[col]);
		  }
	  }
  }
	  
  /*
   * TODO:COMPLETE THIS YOU ARE FREE TO CHANGE THE FUNCTION SIGNATURE BUT DO NOT
   * CHANGE THE FUNCTION NAME AND DO NOT MAKE THIS FUNCTION STATIC. Add/remove
   * 
   * @param AND
   * 
   * @return as required below.
   */
  /**
   * Determines how similar each pair of users is based on their ratings. This
   * similarity value is represented with with a float value between 0 and 1,
   * where 1 is perfect/identical similarity. Stores these values in the
   * userUserMatrix.
   * 
   * @param user1 The first users ratings from Matrix
   * @param user2 The second users ratings from Matrix
   * @return score The final rating between both users using formula
   */
  public float calculateSimilarityScore(int[] user1, int[] user2) {
	  // Creates float to track the total difference between both users
	  float finalDifference = 0;
	  // Creates integer for loop to keep track of the length
	  int ratings;
	  // Creates integer to hold on how much movies there are
	  int totalRatings = user1.length;
	  // Creates integer to hold on the difference
	  int difference;
	  // Creates the decimal places of 4
	  DecimalFormat four = new DecimalFormat("0.0000");
	  // Loops through all the ratings to create the total difference
	  for (ratings = 0; ratings < totalRatings; ratings++) {
		  // Takes the difference between both values
		  difference = user2[ratings] - user1[ratings];
		  // Squares the difference to get final answer before square rooting
		  finalDifference += (float) Math.pow(difference, 2);
	  }
	  // Takes the square root from the distance formula
	  float ratingDistance = (float) Math.sqrt(finalDifference);
	  // Creates string version of score to 4 decimals
	  String stringScore = four.format(1 / (1 + ratingDistance));
	  // Recreates score from string back to float
	  float score = Float.valueOf(stringScore);
	  // Returns the final score in float of 4 decimals
	  return score;
  }

  /*
   * TODO:COMPLETE THIS YOU ARE FREE TO CHANGE THE FUNCTION SIGNATURE BUT DO NOT
   * CHANGE THE FUNCTION NAME AND DO NOT MAKE THIS FUNCTION STATIC
   */
  /**
   * Prints out the similarity scores of the userUserMatrix, with each row and
   * column representing each/single user and the cell position (i,j)
   * representing the similarity score between user i and user j.
   */

  public void printUserUserMatrix() {
	  // Integer to hold for the count to print
	  int row = 0;
	  int col = 0;
	  // The length of the user matrix
	  int len = userUserMatrix.length;
	  // Goes through each row of the matrix
	  for(row = 0; row < len; row++) {
		  // Starts the row with the bracket
		  System.out.print("[");
		  // Goes through each column of the matrix
		  for(col = 0; col < len - 1; col++) {
			  // Prints the values in every column of the row
			  System.out.printf("%.4f, ", userUserMatrix[row][col]);
		  }
	  // Prints the last column from the matrix without commas
	  System.out.printf("%.4f", userUserMatrix[row][len - 1]);
	  // Adds the end of the row
	  System.out.print("]");
	  // Starts a new line for next row or end
	  System.out.println("");
	  }
  }

  /*
   * TODO:COMPLETE THIS YOU ARE FREE TO CHANGE THE FUNCTION SIGNATURE BUT DO NOT
   * CHANGE THE FUNCTION NAME AND DO NOT MAKE THIS FUNCTION STATIC
   */
  /**
   * This function finds and prints the most similar pair of users in the
   * userUserMatrix.
   */
  public void findAndprintMostSimilarPairOfUsers() {
	  // Holds the count for both row and column
	  int row = 0;
	  int col = 0;
	  // Hold the users that are the most similar
	  int pairRow = 0;
	  int pairCol = 0;
	  // Float holding the most similarity score
	  float mostSim = (float) 0.0000;
	  // Goes through the entire matrix in both rows and columns
	  for(row = 0; row < userUserMatrix.length; row++) {
		  for(col = 0; col < userUserMatrix.length; col++) {
			  // Checks if its the most similar pair at the giver row and column
			  if(mostSim < userUserMatrix[row][col] && userUserMatrix[row][col] != 1.0000){
				  // Assigns the similarity score
				  mostSim = userUserMatrix[row][col];
				  // Assigns first of the pair
				  pairRow = row + 1;
				  // Assigns second of the pair
				  pairCol = col + 1;
			  }
		  }
	  }
	  // Default if no pair is found
	  if(pairRow == 0) {
		  mostSim = (float) 1.0000;
		  pairRow = pairRow + 1;
		  pairCol = pairCol + 2;
	  }
	  // Prints the required statements with the best pair
	  System.out.println("User"+ pairRow + " and User" + pairCol);
	  System.out.println("with similarity score of " + mostSim);
  }

  /*
   * TODO:COMPLETE THIS YOU ARE FREE TO CHANGE THE FUNCTION SIGNATURE BUT DO NOT
   * CHANGE THE FUNCTION NAME AND DO NOT MAKE THIS FUNCTION STATIC
   */
  /**
   * This function finds and prints the most dissimilar pair of users in the
   * userUserMatrix.
   */
  public void findAndprintMostDissimilarPairOfUsers() {
	  // Holds the count for both row and column
	  int row = 0;
	  int col = 0;
	  // Hold the users that are the most similar
	  int pairRow = 0;
	  int pairCol = 0;
	  // Float holding the most dissimilarity score
	  float mostDiff = (float) 10.0000;
	  // Goes through the entire matrix in both rows and columns
	  for(row = 0; row < userUserMatrix.length; row++) {
		  for(col = 0; col < userUserMatrix.length; col++) {
			  // Checks if its the most dissimilar pair at the giver row and column
			  if(mostDiff > userUserMatrix[row][col]){
				  // Assigns the dissimilarity score
				  mostDiff = userUserMatrix[row][col];
				  // Assigns the pair first user
				  pairRow = row + 1;
				  // Assigns the pair second user
				  pairCol = col + 1;
			  }
		  }
	  }
	  // Default if none is found
	  if(pairRow == 0) {
		  mostDiff = (float) 1.0000;
		  pairRow = pairRow + 1;
		  pairCol = pairCol + 2;
	  }
	  // Prints out required statements  with the given pairs
	  System.out.println("User"+ pairRow + " and User" + pairCol);
	  System.out.println("with similarity score of " + mostDiff);
  }
}
