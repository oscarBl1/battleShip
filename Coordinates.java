package coordinates;

import java.util.Scanner;
import java.util.Random;

public class Coordinates {

    static int columns = 10;
    static int rows = 10;
    static String[][] gridSize = new String[rows][columns];
    static int playerSh;
    static int compSh;

    public static void main(String[] args) {
        map();
        playerShips();
        computerShips();

        while (playerSh > 0 && compSh > 0) {
            game();
        }

    }

    public static void map() {
        System.out.print(" ");
        for (int i = 0; i < columns; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < gridSize.length; i++) {
            for (int j = 0; j < gridSize[i].length; j++) {
                gridSize[i][j] = " ";
                if (j == 0) {
                    System.out.print(i + gridSize[i][j]);
                } else if (j == gridSize[i][j].length() - 1) {
                    System.out.print(gridSize[i][j] + i);
                } else {
                    System.out.print(gridSize[i][j]);
                }

            }
            System.out.println();
        }
        System.out.print("  ");

    }

    public static void playerShips() {
        Scanner scan = new Scanner(System.in);

        playerSh = 5;
        for (int i = 1; i < playerSh;) {
            System.out.print("Enter the x coordinate for ship " + i + ": ");
            int xCoord = scan.nextInt();
            System.out.print("Enter the y coordinate for ship " + i + ": ");
            int yCoord = scan.nextInt();

            if ((xCoord >= 0 && xCoord < rows) && (yCoord >= 0 && yCoord < columns) && (gridSize[xCoord][yCoord] == " ")) {
                gridSize[xCoord][yCoord] = "O";
                i++;
            } else if ((xCoord >= 0 && xCoord <= rows) && (yCoord >= 0 && yCoord <= columns) && (gridSize[xCoord][yCoord] == "O")) {
                System.out.println("You cant have 2 ships on the same place!");
            } else if ((xCoord < 0 || xCoord >= rows) || (yCoord < 0 || yCoord >= columns)) {
                System.out.println("Ships cannot be placed outside of the grid");
            }
            map();
        }

    }

    public static void computerShips() {

        Random rand = new Random();
        compSh = 5;
        for (int i = 1; i < compSh;) {
            int xComp = rand.nextInt(rows);
            int yComp = rand.nextInt(columns);

            if ((xComp >= 0 && xComp < rows) && (yComp >= 0 && yComp < columns)) {
                gridSize[xComp][yComp] = "X";
                i++;
            }
        }
        map();
    }

    public static void player() {
        System.out.println("Your turn");
        int x = -1, y = -1;

        do {
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter x coordinate: ");
            x = scan.nextInt();

            System.out.print("Enter y coordinate: ");
            y = scan.nextInt();

            if ((x >= 0 && x < rows) && (y >= 0 && y < columns)) {

                if (gridSize[x][y].equals("X")) {
                    System.out.println("You hit the computers ship");
                    compSh--;
                } else {
                    System.out.println("You missed");
                }
            } else if ((x < 0 || x >= rows) || (y < 0 || y > columns)) {
                System.out.println("You cant shoot outside the grid");
            }
        } while ((x < 0 || x >= rows) || (y < 0 || y >= columns));

    }

    public static void computer() {
        System.out.println("Computers turn");
        int x, y;
        Random rand = new Random();
        do {
            x = rand.nextInt(rows);
            y = rand.nextInt(columns);

            if (gridSize[x][y].equals("O")) {
                System.out.println("The computer hit a ship");
                playerSh--;
            } else {
                System.out.println("Computer missed");
            }

        } while ((x < 0 || x >= rows) || (y < 0 || y >= columns));
    }

    public static void game() {
        player();
        computer();
        map();
        System.out.println("\nYou have " + playerSh + " left, Computer has " + compSh + " left");
    }
}
