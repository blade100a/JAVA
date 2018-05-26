// **********************************************************
// Assignment0:
// UTORID: pahirath
// UT Student #: 1003371549
// Author: Harri Pahirathan
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
package driver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import a0.Cfiltering;

public class CfilteringDriver {

  /**
   * Reads user movie ratings from a text file, calculates similarity scores and
   * prints a score matrix.
   */
  public static void main(String[] args) {
    try {

      // open file to read
      String fileName;
      Scanner in = new Scanner(System.in);
      System.out.println("Enter the name of input file? ");
      fileName = in.nextLine();
      FileInputStream fStream = new FileInputStream(fileName);
      BufferedReader br = new BufferedReader(new InputStreamReader(fStream));

      // Read dimensions: number of users and number of movies
      int numberOfUsers = Integer.parseInt(br.readLine());
      int numberOfMovies = Integer.parseInt(br.readLine());

      /*
       * create a new CFiltering object that contains: a) 2d matrix
       * i.e.userMovieMatrix (#users*#movies) b) 2d matrix i.e. userUserMatrix
       * (#users*#users)
       */
      Cfiltering cfObject = new Cfiltering(numberOfUsers, numberOfMovies);

      // this is a blankline being read
      br.readLine();

      // read each line of movie ratings and populate the
      // userMovieMatrix
      String row;
      int numRow = 0;
      while ((row = br.readLine()) != null) {
        // allRatings is a list of all String numbers on one row
        String allRatings[] = row.split(" ");
        int numCol = 0;
        for (String singleRating : allRatings) {
          // make the String number into an integer
        	int rating = Integer.valueOf(singleRating);
          // populate userMovieMatrix
          cfObject.populateUserMovieMatrix(numRow, numCol, rating);
          // TODO: COMPLETE THIS I.E. POPULATE THE USER_MOVIE MATRIX
          numCol = numCol + 1;
        }
        numRow = numRow + 1;
      }
      // close the file
      fStream.close();
      in.close();

      /*
       * COMPLETE THIS ( I.E. CALL THE APPROPRIATE FUNCTIONS THAT DOES THE
       * FOLLOWING)
       */
      // TODO:1.) CALCULATE THE SIMILARITY SCORE BETWEEN USERS.
      cfObject.createUserUserMatrix();
      // TODO:2.) PRINT OUT THE userUserMatrix
      System.out.println("");
      System.out.println("");
      System.out.println("userUserMatrix is:");
      cfObject.printUserUserMatrix();
      // TODO:3.) PRINT OUT THE MOST SIMILAR PAIRS OF USER AND THE MOST
      System.out.println("");
      System.out.println("");
      System.out.println("The most similar pairs of users from above userUserMatrix are:");
      cfObject.findAndprintMostSimilarPairOfUsers();
      // DISSIMILAR
      System.out.println("");
      System.out.println("");
      System.out.println("The most dissimilar pairs of users from above userUserMatrix are:");
      cfObject.findAndprintMostDissimilarPairOfUsers();
      // PAIR OF USERS.
    } catch (IOException e) {
      System.err.print(e.getMessage());
    }
  }

}
