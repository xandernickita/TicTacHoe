package tictachoe;

import java.util.*;

public class TicTacHoe {
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> CPUPositions = new ArrayList<Integer>();

    public static void main(String[] args) {
        char [][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};
        // method for creating the game board. \\
        printGameBoard(gameBoard);

        while (true) {
            Scanner scan = new Scanner(System.in);
            // Game begins \\
            System.out.println("Welcome to Tic-Tac-Toe!\nPlease choose your marker position (1-9)");
            int playerPosition = scan.nextInt(); // User input \\
            while (playerPositions.contains(playerPosition) || CPUPositions.contains(playerPositions)) {
                System.out.println("Your opponent has already taken this spot! Please try again.");
                playerPosition = scan.nextInt();
            }
            System.out.println(playerPosition);

            // method for creating the game board. \\
            playerPlacement(gameBoard, playerPosition, "Player");

            // Procedural generation of CPU's move \\
            Random randomNumber = new Random();
            int CPUMove = randomNumber.nextInt(9) + 1;
            while (playerPositions.contains(CPUPositions) || CPUPositions.contains(CPUPositions)) {
                CPUMove = randomNumber.nextInt(9) + 1;
            }
            playerPlacement(gameBoard, CPUMove, "CPU");

            // Calling method that renders the game board \\
            printGameBoard(gameBoard);

            // Print final result of the match \\
            String finalResult = determineWinner();
            System.out.println(finalResult);
        }

    }
    public static void printGameBoard(char [][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
    public static void playerPlacement(char[][] gameBoard, int position, String user) {

        char symbol = ' ';
        if (user.equalsIgnoreCase("Player")) {
            symbol = 'X';
            playerPositions.add(position);
        } else if (user.equalsIgnoreCase("CPU")) {
            symbol = 'O';
            CPUPositions.add(position);
        }

        switch(position) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }
    public static String determineWinner() {

        List topRow = Arrays.asList(1, 2, 3);
        List middleRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);
        List topColumn = Arrays.asList(1, 4, 7);
        List middleColumn = Arrays.asList(2, 5, 8);
        List bottomColumn = Arrays.asList(3, 6, 9);
        List diagonal1 = Arrays.asList(1, 5, 9);
        List diagonal2 = Arrays.asList(7, 5, 3);

        List<List> winConditions = new ArrayList<List>();
        winConditions.add(topRow);
        winConditions.add(middleRow);
        winConditions.add(bottomRow);
        winConditions.add(topColumn);
        winConditions.add(middleColumn);
        winConditions.add(bottomColumn);
        winConditions.add(diagonal1);
        winConditions.add(diagonal2);

        for (List l : winConditions) {
            if (playerPositions.containsAll(l)) {
                return "You WIN!!!!";
            } else if (CPUPositions.containsAll(l)) {
                return "You LOSE :(";
            } else if (playerPositions.size() + CPUPositions.size() == 9) {
                return "It's a TIE!";
            }
        }

        return "";
    }
}
