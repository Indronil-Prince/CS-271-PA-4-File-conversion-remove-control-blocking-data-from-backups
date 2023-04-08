# Author: Indronil Bhattacharjee
# Date: 22/03/2023
# Assignment: 4
# Program Description: File conversion -- removing control blocking data from backups
# Language: Python

import sys
def main():
    # Taking in the input file
    input = open("control-char.txt", 'r')
    # Storing in output file
    output = open('pythonOutput.txt', 'w')
    # Declaring variables to tell precisely if the control is inside ^C and outside ^B
    inChar = False
    outChar = False
    # Reading the input file
    line = input.read()

        # The for loop is used to convert characters to decimal value and
        # compater to ETX == 3 (^C) in decimal and STX == 2 (^B) in decimal.
        # The character between ETX and STX is not printed
    for i in line:
        if not line:
            break

        # Setting condition after ^C occurs
        if ord(i) == 3 and inChar == False:
            inChar = True
            outChar = False
        # Getting conditions after ^B occurs
        if ord(i) == 2 and inChar == True:
            inChar = False
            outChar = True

        # Printing the file, except the part in between ^C and ^B
        if inChar == False and outChar == False:
            print(i, end = "")
            output.write(i)

        if outChar == True:
            outChar = False
 
    input.close()
    output.close()

if __name__ == "__main__":
    main()