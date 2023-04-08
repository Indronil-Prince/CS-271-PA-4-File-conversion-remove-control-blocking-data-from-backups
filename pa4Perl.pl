# Author: Indronil Bhattacharjee
# Date: 22/03/2023
# Assignment: 4
# Program Description: File conversion -- removing control blocking data from backups
# Language: Perl

use strict;
use warnings;
# Taking in the input file
my $input = 'control-char.txt';
open(FH, '<', $input) or die $!;

# Storing in output file
open (FW, '>', "perlOutput.txt") or die $!;
# Declaring variables to tell precisely if the control is inside ^C and outside ^B
my $inChar = 1; # False
my $outChar = 1; # False
# Reading the input file
my $line = <FH>;
# The for loop is used to convert characters to decimal value and
# compater to ETX == 3 (^C) in decimal and STX == 2 (^B) in decimal.
# The character between ETX and STX is not printed
while ( $line ) {
    my $str = $line;
    # using split() function
    my @spl = split('', $str);

    # displaying result using foreach loop
    foreach my $i (@spl) {
        if (ord($i) == 3 and $inChar == 1) {
            $inChar = 0;
            $outChar = 1;
        }
        # Getting conditions after ^B occurs
        if (ord($i) == 2 and $inChar == 0) {
            $inChar = 1;
            $outChar = 0;
        }
        # Printing the file, except the part in between ^C and ^B
        if ($inChar == 1 and $outChar == 1) {
            print $i;
            $a = $i;
            print FW $a;
        }
        if ($outChar == 0) {
            $outChar = 1;
        }
    }
    $line = <FH>;
}
close(FW) or die $!;