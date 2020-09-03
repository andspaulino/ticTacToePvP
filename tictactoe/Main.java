package tictactoe;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();

        int moveCount = 0;

        game.start();

        while (!game.validWin() || !game.setDraw(moveCount)) {

            if (game.isMoveValid()) {

                game.printMove(moveCount);
                ++moveCount;
            }

            if (game.validWin()) {
                break;
            } else if (game.setDraw(moveCount)) {
                break;
            }
        }
    }
}

class TicTacToe {

    public Scanner scanner = new Scanner(System.in);
    public int rows = 3;
    public int col = 3;
    public char[][] matrix = new char[rows][col];
    public int xCordReal, yCordReal;


    public void start() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = ' ';
            }
        }
        printMatrix();
    }

    public void printMatrix() {
        System.out.println("---------");
        for (int i = 0; i < rows; i++) {
            System.out.print("| ");
            for (int j = 0; j < col; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");
    }

    public boolean isMoveValid() {
        boolean validMove = false;
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
                yCord = scanner.nextInt();
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
            if (matrix[xCordReal][yCordReal] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            validMove = true;

        }
        return true;
    }

    public void printMove(int moveCount) {
        if (moveCount % 2 == 0) {
            matrix[xCordReal][yCordReal] = 'X';
        } else {
            matrix[xCordReal][yCordReal] = 'O';
        }
        printMatrix();
    }

    public boolean validWin() {
        boolean winner = false;
        String oWins = "O wins";
        String xWins = "X wins";


        for (int i = 0; i < rows; i++) {
            int rowWinner = matrix[i][0] + matrix[i][1] + matrix[i][2];
            if (rowWinner == 264) {
                System.out.println(xWins);
                winner = true;
            } else if (rowWinner == 237) {
                System.out.println(oWins);
                winner = true;
            }
        }

        for (int i = 0; i < rows; i++) {
            int colWinner = matrix[0][i] + matrix[1][i] + matrix[2][i];
            if (colWinner == 264) {
                System.out.println(xWins);
                winner = true;
            } else if (colWinner == 237) {
                System.out.println(oWins);
                winner = true;
            }

        }

        int diagDownwardWinner = matrix[0][0] + matrix[1][1] + matrix[2][2];
        if (diagDownwardWinner == 264) {
            System.out.println(xWins);
            winner = true;
        } else if (diagDownwardWinner == 237) {
            System.out.println(oWins);
            winner = true;
        }


        int diagUpwardWinner = matrix[2][0] + matrix[1][1] + matrix[0][2];
        if (diagUpwardWinner == 264) {
            System.out.println(xWins);
            winner = true;
        } else if (diagUpwardWinner == 237) {
            System.out.println(oWins);
            winner = true;
        }
        return winner;
    }
    public boolean setDraw(int moveCount) {
        boolean draw = false;

        if (!validWin() && moveCount == 9) {
            System.out.println("Draw");
            draw = true;
        }
        return draw;
    }
}
