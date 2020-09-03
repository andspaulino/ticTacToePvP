package tictactoe;

import java.util.Scanner;

public class Main {

    final static Scanner scanner = new Scanner(System.in);
    final static int rows = 3;
    final static int col = 3;

    public static void main(String[] args) {
        char[][] ticTacToe = new char[rows][col];
        fillTicTacToe(ticTacToe);
        printTicTacToe(ticTacToe);
        playGame(ticTacToe);
    }

    public static void fillTicTacToe(char[][] ticTacToe) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                ticTacToe[i][j] = ' ';
            }
        }
    }

    public static void playGame(char[][] ticTacToe) {
        playerOneTicTacToe(ticTacToe);
        playerTwoTicTacToe(ticTacToe);
        //isFull(ticTacToe);
    }

    public static void playerOneTicTacToe(char[][] ticTacToe){
        boolean validMove = false;
        int xCordReal, yCordReal;
        int xCord, yCord;

        while (!validMove) {
            System.out.print("Enter the coordinates: ");
            if (scanner.hasNextInt()) {
                xCord = scanner.nextInt();
            } else {
                scanner.nextLine();
                System.out.println("You should enter numbers!");
                continue;
            }
            if (scanner.hasNextInt()) {
                yCord= scanner.nextInt();
            } else {
                scanner.nextLine();
                System.out.println("You should enter numbers!");
                continue;
            }
            xCordReal = 3 - yCord;
            yCordReal = xCord - 1;
            if (xCordReal < 0 || xCordReal > 2 || yCordReal < 0 || yCordReal > 2) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            if (ticTacToe[xCordReal][yCordReal] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            validMove = true;

            ticTacToe[xCordReal][yCordReal] = 'X';
            printTicTacToe(ticTacToe);
            resultChecker(ticTacToe);
        }
    }

    public static void playerTwoTicTacToe(char[][] ticTacToe){
        boolean validMove = false;
        int xCordReal, yCordReal;
        int xCord, yCord;

        while (!validMove) {
            System.out.print("Enter the coordinates: ");
            if (scanner.hasNextInt()) {
                xCord = scanner.nextInt();
            } else {
                scanner.nextLine();
                System.out.println("You should enter numbers!");
                continue;
            }
            if (scanner.hasNextInt()) {
                yCord= scanner.nextInt();
            } else {
                scanner.nextLine();
                System.out.println("You should enter numbers!");
                continue;
            }
            xCordReal = 3 - yCord;
            yCordReal = xCord - 1;
            if (xCordReal < 0 || xCordReal > 2 || yCordReal < 0 || yCordReal > 2) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            if (ticTacToe[xCordReal][yCordReal] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            validMove = true;

            ticTacToe[xCordReal][yCordReal] = 'O';
            printTicTacToe(ticTacToe);
            resultChecker(ticTacToe);
        }
    }

    public static int isFull(char[][] ticTacToe) {
        int counter = 0;
        for (char[] whiteSpaceFinderOne : ticTacToe) {
            for (char whiteSpaceFinderTwo : whiteSpaceFinderOne) {
                if (whiteSpaceFinderTwo == ' ') {
                    counter++;
                }
            }
        }
        return counter;
    }

    public static void resultChecker(char[][] ticTacToe) {

        boolean impossible = false;
        boolean draw = false;
        boolean xWins = false;
        boolean oWins = false;
        boolean whiteSpace = false;


        int rowWinner;
        for (int i = 0; i < rows; i++) {
            rowWinner = ticTacToe[i][0] + ticTacToe[i][1] + ticTacToe[i][2];
            if (rowWinner == 264 || rowWinner == 237) {
                if (rowWinner == 264) {
                    xWins = true;
                } else {
                    oWins = true;
                }
            }
        }

        int colWinner;
        for (int i = 0; i < rows; i++) {
            colWinner = ticTacToe[0][i] + ticTacToe[1][i] + ticTacToe[2][i];
            if (colWinner == 264 || colWinner == 237) {
                if (colWinner == 264) {
                    xWins = true;
                } else {
                    oWins = true;
                }
            }

        }

        int diagDownwardWinner = ticTacToe[0][0] + ticTacToe[1][1] + ticTacToe[2][2];
        if (diagDownwardWinner == 264 || diagDownwardWinner == 237) {
            if (diagDownwardWinner == 264) {
                xWins = true;
            } else {
                oWins = true;
            }
        }

        int diagUpwardWinner = ticTacToe[2][0] + ticTacToe[1][1] + ticTacToe[0][2];
        if (diagUpwardWinner == 264 || diagUpwardWinner == 237) {
            if (diagUpwardWinner == 264) {
                xWins = true;
            } else {
                oWins = true;
            }
        }


        int xFinderCount = 0;
        for (char[] xFinders : ticTacToe) {
            for (char xFinder : xFinders) {
                if (xFinder == 'X') {
                    xFinderCount++;
                }
            }
        }

        int oFinderCount = 0;
        for (char[] oFinders : ticTacToe) {
            for (char oFinder : oFinders) {
                if (oFinder == 'O') {
                    oFinderCount++;
                }
            }
        }

        for (char[] whiteSpaceFinderOne : ticTacToe) {
            for (char whiteSpaceFinderTwo : whiteSpaceFinderOne) {
                if (whiteSpaceFinderTwo == ' ') {
                    whiteSpace = true;
                    break;
                }
            }
        }

        if (xFinderCount - oFinderCount > 1) {
            impossible = true;
        } else if (xWins && oWins) {
            impossible = true;
        }

        if (impossible) {
            System.out.println("Impossible");
        }
        if (!xWins && !oWins) {
            draw = true;
        }

        if(draw && !impossible) {
            if (whiteSpace) {
                System.out.println("Game not finished");
                playGame(ticTacToe);
            }
        }

        if (draw && !whiteSpace) {
            System.out.println("Draw");
        }

        if (xWins && !impossible) {
            System.out.println("X wins");
        }

        if (oWins && !impossible) {
            System.out.println("O wins");
        }



    }

    public static void printTicTacToe(char[][] ticTacToe) {
        System.out.println("---------");
        for (int i = 0; i < rows; i++) {
            System.out.print("| ");
            for (int j = 0; j < col; j++) {
                System.out.print(ticTacToe[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");
    }
}
