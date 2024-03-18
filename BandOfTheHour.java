package firstProject;

import java.util.Scanner;

public class BandOfTheHour {

    private static final Scanner keyboard = new Scanner(System.in);
    private static final double MIN_WEIGHT = 45.0;
    private static final double MAX_WEIGHT = 200.0;
    private static final double MAX_ROW_WEIGHT = 100.0;
    private static final int MAX_ROWS = 10;
    private static final int MAX_POSITIONS = 8;

    public static void main(String[] args) {

        System.out.println("Welcome to the Band of the Hour");
        System.out.println("-------------------------------");

        int num_rows;

        do {
            System.out.print("Please enter number of rows: ");
            num_rows = keyboard.nextInt();
            if (num_rows < 1 || num_rows > MAX_ROWS)
                System.out.println("ERROR: Out of range, try again.");
        } while (num_rows < 1 || num_rows > MAX_ROWS);

        char[] rowLabels = new char[num_rows];
        int[][] assignment = new int[num_rows][];

        for (int i = 0; i < num_rows; i++) {
            rowLabels[i] = (char) ('A' + i); // Assigning row labels automatically starting from 'A'
            // get input number of positions, check if its in range
            int numPositions;
            do {
                System.out.print("Please enter number of positions in row " + rowLabels[i] + ": ");
                numPositions = keyboard.nextInt();
                if (numPositions < 1 || numPositions > MAX_POSITIONS)
                    System.out.println("ERROR: Out of range, try again.");
            } while (numPositions < 1 || numPositions > MAX_POSITIONS);

            assignment[i] = new int[numPositions];
        } // end of the for loop

        // while loop to loop through menu options
        while (true) {
            System.out.print("\n(A)dd, (R)emove, (P)rint, e(X)it: ");
            char choice = Character.toUpperCase(keyboard.next().charAt(0));
            // switch to run methods according to options
            switch (choice) {
                case 'A':
                    addMusician(assignment, MAX_ROW_WEIGHT, rowLabels);
                    break;
                case 'R':
                    removeMusician(assignment, rowLabels);
                    break;
                case 'P':
                    printAssignment(assignment, rowLabels);
                    break;
                case 'X':
                    return;
                default:
                    System.out.println("ERROR: Invalid option, try again.");
            } // end of the switch
        } // end of the while loop
    } // end of main method

    // method to print the weights
    private static void printAssignment(int[][] assignment, char[] rowLabels) {
        for (int i = 0; i < assignment.length; i++) {
            double totalWeight = 0;
            int[] rowWeights = assignment[i];
            for (int weight : rowWeights) {
                totalWeight += weight;
            } // end of for loop


            double averageWeight = totalWeight / rowWeights.length;
            System.out.print(rowLabels[i] + ": ");
            for (int j = 0; j < rowWeights.length; j++) {
                System.out.print((double) rowWeights[j] + "  ");
            }
            System.out.printf("%-40s [%5.1f, %5.1f]\n", "", totalWeight, averageWeight);

        } //end of for loop
    } // end of printAssignment method

    // method to add musicians
    private static void addMusician(int[][] assignment, double MAX_ROW_WEIGHT, char[] rowLabels) {
        while (true) {
            System.out.print("Please enter row letter: ");
            char rowLabel = Character.toUpperCase(keyboard.next().charAt(0));
            int rowIndex = -1;
            for (int i = 0; i < rowLabels.length; i++) {
                if (rowLabels[i] == rowLabel) {
                    rowIndex = i;
                    break;
                }
            }
            while (rowIndex == -1) {
                System.out.print("ERROR: Out of range, try again: ");
                rowLabel = Character.toUpperCase(keyboard.next().charAt(0));
                for (int i = 0; i < rowLabels.length; i++) {
                    if (rowLabels[i] == rowLabel) {
                        rowIndex = i;
                        break;
                    }
                }// end of for loop
            }

            System.out.print("Please enter position number (1 to " + assignment[rowIndex].length + "): ");
            int position = keyboard.nextInt();
            while (position < 1 || position > assignment[rowIndex].length) {
                System.out.print("ERROR: Out of range, try again.");
                position = keyboard.nextInt();
            }

            if (assignment[rowIndex][position - 1] != 0) {
                System.out.println("ERROR: There is already a musician there.");
                break;
            }

            System.out.print("Please enter weight (45.0 to 200.0): ");
            double weight = keyboard.nextDouble();
            while (weight < MIN_WEIGHT || weight > MAX_WEIGHT) {
                System.out.print("ERROR: Out of range, try again: ");
                weight = keyboard.nextDouble();
            }

            double totalWeight = 0;
            int[] rowWeights = assignment[rowIndex];
            for (int i = 0; i < rowWeights.length; i++) {
                totalWeight += rowWeights[i];
            }
            if ((totalWeight + weight) / rowWeights.length > MAX_ROW_WEIGHT) {
                System.out.println("ERROR: That would exceed the average weight limit.");
                break;
            }

            assignment[rowIndex][position - 1] = (int) weight;
            System.out.println("****** Musician added.");
            break;
        } // end of while loop
    } // end of addMusician method

    private static void removeMusician(int[][] assignment, char[] rowLabels) {
        while (true) {
            System.out.print("Please enter row letter: ");
            char rowLabel = Character.toUpperCase(keyboard.next().charAt(0));
            int rowIndex = -1;
            for (int i = 0; i < rowLabels.length; i++) {
                if (rowLabels[i] == rowLabel) {
                    rowIndex = i;
                    break;
                }
            }
            if (rowIndex == -1) {
                System.out.println("ERROR: Out of range, try again.");
                rowLabel = Character.toUpperCase(keyboard.next().charAt(0));
                for (int i = 0; i < rowLabels.length; i++) {
                    if (rowLabels[i] == rowLabel) {
                        rowIndex = i;
                        break;
                    }
                }
            }
            // check if its out of range
            System.out.print("Please enter position number (1 to " + assignment[rowIndex].length + "): ");
            int position = keyboard.nextInt();
            while (position < 1 || position > assignment[rowIndex].length) {
                System.out.println("ERROR: Out of range, try again.");
                position = keyboard.nextInt();
            }
            // check if there is a musician there to remove
            if (assignment[rowIndex][position - 1] == 0) {
                System.out.println("ERROR: That position is vacant.");
                break;
            }

            assignment[rowIndex][position - 1] = 0;
            System.out.println("****** Musician removed.");
            break;
        }
    } //end of remove musician method
} // end of the BandOfTheHour class

