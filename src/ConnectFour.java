//Benjamin Larkin & Zephren de la Cerda
//CSCI 145
//Connect Four Program
//Project 1
//2/1/18


//importing scanner so we can take in user input
import java.util.Scanner;
public class ConnectFour {


    //main function displays board game, calls user input, and checks if the user's input is available
    public static void main (String[] args) {
        int n = 0;
        int[][] board = new int[7][6];

        gameBoard(board);
        while(n!= 42){
            //player 1's turn
            boolean moveMade = false;
            while (moveMade == false) {
                System.out.println("Player 1: ");
                int input = checkUserInt() -1;
                int i = 5;
                while (i >= 0) {
                    if (board[input][i] != 0) {
                        i--;
                    } else {
                        board[input][i] = -1;
                        moveMade = true;
                        break ;
                    }
                }
                if (i == -1) {
                    System.out.println("That column is full, choose another.");
                }
            }
            gameBoard(board);


            if(endOfGame(board) == true)
            {
                System.out.println("Player 1 won!");
                break;
            }

            n=n+1;
            //player 2's turn
            moveMade = false;
            while (moveMade == false) {
                System.out.println("Player 2:");
                int input = checkUserInt() -1;
                int i = 5;
                while (i >= 0) {
                    if (board[input][i] != 0) {
                        i--;
                    } else {
                        board[input][i] = 1;
                        moveMade = true;
                        break ;
                    }
                }
                if (i == -1) {
                    System.out.println("That column is full, choose another.");
                }
            }
            gameBoard(board);
            if(endOfGame(board) == true)
            {
                System.out.println("Player 2 won!");
                break;
            }

            n=n+1;
        }
        //if they have hit 42 plays the game is tied
        if(n==42)
        {
            System.out.println("It seems you've tied. Check Wikipedia for common strategies to beat your next opponent!");
        }
    }




    //gameBoard prints out visual representation of the 2D arrays as a gameboard
    public static void gameBoard(int[][] newMove ) {
        String xVal = "X"; //player 1's marker is an X
        String oVal = "O"; //player 2's maker is an O
        for (int i = 0; i < 6; i++) {
            for (int k = 0; k < 7; k++) {
                if (newMove[k][i] == -1) {
                    System.out.print("|  " + xVal + "  ");

                } else if (newMove[k][i] == 1) {
                    System.out.print("|  " + oVal + "  ");
                }
                else {
                    System.out.print("|     ");
                }
            }
            System.out.print("|");
            System.out.println("");
        }
        for (int t = 0; t < 7; t++) {
            System.out.print("+-----");
        }
        System.out.print("+");
        System.out.println("");

        for (int c = 1; c < 8; c++) {
            System.out.print("   " + c + "  ");
        }
        System.out.println("");
    }

    //checkUserInt calls scanner and tests if user's input is a valid int, re-prompts if not
    public static int checkUserInt(){
        boolean isInt = true;
        int userNum = 1;
        Scanner sc = new Scanner(System.in);
        while (isInt){
            System.out.println("Enter your move on the range 1-7");
            //checking input for int
            if(sc.hasNextInt())
            {
                userNum = sc.nextInt();
                //checking that input is between 1 and 7
                if((userNum>=1) && (userNum<=7))
                {
                    isInt = false;
                }


            }
            else{
                //if the user doesn't enter an int, prompt again
                sc.next();
            }
        }
        return userNum;



    }

    //checkHorixontalX checks for 4 X's in-a-row horizontally (row by column)
    public static boolean checkHorizontalX(int[][] board) {
        int max_run = 0;
        int cur_run = 0;
        final int OccupiedX  = -1;

        for(int row = 5; row >= 0; row--) {
            for(int col = 0; col < 7; col++) {

                if(board[col][row] == OccupiedX) {
                    cur_run++;

                    max_run = Math.max(max_run, cur_run);

                }
                else {
                    cur_run = 0;
                }
            }
        }
        return max_run >= 4;
    }

    //checkHorixontalX checks for 4 O's in-a-row horizontally (row by column)
    public static boolean checkHorizontalO(int[][] board) {
        int max_run = 0;
        int cur_run = 0;
        final int OccupiedO  = 1;

        for(int row = 5; row >= 0; row--) {
            for(int col = 0; col < 7; ++col) {

                if(board[col][row] == OccupiedO) {
                    cur_run ++;
                    max_run = Math.max(max_run, cur_run);
                }

                else {
                    cur_run = 0;
                }
            }
        }
        return max_run >= 4;
    }

    //checkVerticalX checks for 4 X's in-a-row vertically (column by row)
    public static boolean checkVerticalX(int[][] board){
        int max_run = 0;
        int cur_run = 0;
        final int OccupiedX  = -1;
        for(int col = 0; col < 7; ++col) {
            for(int row = 0; row < 6; ++row) {

                if(board[col][row] == OccupiedX) {
                    cur_run ++;
                    max_run = Math.max(max_run, cur_run);
                }
                else {
                    cur_run = 0;
                }
            }
        }
        return max_run >= 4;
    }

    //checkVerticalO checks for 4 O's in-a-row vertically (column by row)
    public static boolean checkVerticalO(int[][] board){
        int max_run = 0;
        int cur_run = 0;
        final int OccupiedO  = 1;
        for(int col = 0; col < 7; ++col) {
            for(int row = 0; row < 6; ++row) {

                if(board[col][row] == OccupiedO) {
                    cur_run ++;
                    max_run = Math.max(max_run, cur_run);
                }
                else {
                    cur_run = 0;
                }
            }
        }
        return max_run >= 4;
    }


    //checkDiagonalIncreasingX despite the name of the function, checkDiagonalIncreasingX checks for 4 decreasing-
    //X's in-a-row
    public static boolean checkDiagonalIncreasingX(int[][] board) {
        final int OccupiedX = -1;
        int max_run = 0;
        int cur_run = 0;
        for(int start = -2; start < 3; ++start) {
            for(int col = 0; col < 7; ++col) {
                int row = start + col;
                if(0 <= row && row < 6) {
                    if(board[col][row] == OccupiedX) {
                        cur_run ++;
                        max_run = Math.max(max_run, cur_run);
                    }
                    else {
                        cur_run = 0;
                    }
                }
            }
        }
        return max_run >= 4;
    }

    //checkDiagonalIncreasingO checks for 4 decreasing O's in-a-row
    public static boolean checkDiagonalIncreasingO(int[][] board) {
        final int OccupiedO = 1;
        int max_run = 0;
        int cur_run = 0;
        for(int start = -2; start < 3; ++start) {
            for(int col = 0; col < 7; ++col) {
                int row = start + col;
                if(0 <= row && row < 6) {

                    if(board[col][row] == OccupiedO) {
                        cur_run ++;
                        max_run = Math.max(max_run, cur_run);
                    }
                    else {
                        cur_run = 0;
                    }
                }

            }
        }
        return max_run >= 4;
    }


    //checkDiagonalDecreasingX checks for 4 increasing X's in-a-row
    public static boolean checkDiagonalDecreasingX(int[][] board) {
        int max_run = 0;
        int cur_run = 0;
        final int OccupiedX  = -1;
        for(int start = -2; start <= 3; ++start) {
            for(int col = 0; col < 7; ++col) {
                int row = 5 + start - col;
                if(0 <= row && row < 6) {

                    if(board[col][row] == OccupiedX) {
                        cur_run ++;
                        max_run = Math.max(max_run, cur_run);
                    }
                    else {
                        cur_run = 0;
                    }
                }
            }
        }
        return max_run >= 4;
    }

    //checkDiagonalDecreasingO checks for 4 increasing O's in-a-row
    public static boolean checkDiagonalDecreasingO(int[][] board) {
        int max_run = 0;
        int cur_run = 0;
        final int OccupiedO  = 1;
        for(int start = -2; start <= 3; ++start) {
            for(int row = 0; row < 7; ++row) {
                int col = 5 + start - row;
                if(0 <= col && col < 6) {

                    if(board[row][col] == OccupiedO) {
                        cur_run ++;
                        max_run = Math.max(max_run, cur_run);
                    }
                    else {
                        cur_run = 0;
                    }
                }
            }
        }
        return max_run >= 4;
    }

    //endOfGame calls xWon or yWon to check values within the array board for 4 in-a-row
    public static boolean endOfGame(int[][] board) {
        return xWon(board) || yWon(board);
    }

    //xWon calls the functions listed to test if there are 4 X values in-a-row
    public static boolean xWon(int[][] board) {
        return checkHorizontalX(board) ||
                checkVerticalX(board) ||
                checkDiagonalIncreasingX(board) ||
                checkDiagonalDecreasingX(board);
    }

    //yWon checks if there are 4 O's in-a-row vertically, horizontally, and diagonally
    public static boolean yWon(int[][] board) {
        return checkHorizontalO(board) ||
                checkVerticalO(board) ||
                checkDiagonalIncreasingO(board) ||
                checkDiagonalDecreasingO(board);
    }

}