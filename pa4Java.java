/*
# Author: Indronil Bhattacharjee
# Date: 22/03/2023
# Assignment: 4
# Program Description: File conversion -- removing control blocking data from backups
# Language: Java
*/

import java.io.*;
import java.util.*;
public class pa4Java {
  public static void main( String[] args ) {
    try {
        // Taking in the input file
        FileReader input = new FileReader ("control-char.txt");
        Scanner s = new Scanner (input);
        // Storing in output file
        FileWriter output = new FileWriter ("javaOutput.txt");
        // Declaring variables to tell precisely if the control is inside ^C and outside ^B
        boolean inChar = false;
        boolean outChar = false;
        String line;

        while (s.hasNextLine() ) {
            // Reading the input file
            line = s.nextLine();
            int length = line.length();

            /*
            The for loop is used to convert characters to decimal value and
            compare to ETX == 3 (^C) in decimal and STX == 2 (^B) in decimal.
            The character between ETX and STX is not printed
            */
            for ( int i=0; i<length; i++ ) {
                // Setting condition after ^C occurs
                if ( (int)line.charAt(i) == 3 && inChar == false ) {
                    inChar = true;
                    outChar = false;
                }
                // Getting conditions after ^B occurs
                if ( (int)line.charAt(i) == 2 && inChar == true ) {
                    inChar = false;
                    outChar = true;
                }
                // Printing the file, except the part in between ^C and ^B
                if ( inChar == false && outChar == false ) {
                    System.out.print(line.charAt(i));
                    output.write(line.charAt(i));
                }
                if ( outChar == true ) {
                    outChar = false;
                }

            }

            if (inChar == false) {
                System.out.println();
                output.write("\n");
            }
        }

    input.close();
    output.close();
    s.close();
    } catch ( IOException e ) {
        e.printStackTrace();
        System.exit(1);
    }
  }
} 