package cli;

import java.util.Scanner;

public class Cli {
    Scanner input = new Scanner(System.in);

    public void display_cli(int[] wins, boolean is_game_running, int current_player, int[][] board, int information_code) {
        separate_from_old_board();

        create_lines();
        display_upper_tab(wins, is_game_running, current_player);
        create_lines();
        
        display_board(board);

        create_lines();
        display_bottom_tab(information_code);
        create_lines();
    }

    public int get_input(int current_player) {
        System.out.print("[Pemain ");
        if (current_player == 1) {
            System.out.print("X");
        } else {
            System.out.print("O");
        }
        System.out.print("] >> ");

        return input.nextInt();
    }

    private void separate_from_old_board() {
        for (int idx = 0; idx<40; idx++) {
            System.out.println();
        }
    }

    private void create_lines() {
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }

    private void new_line() {
        System.out.println();
    }

    private void display_upper_tab(int[] wins, boolean is_game_running, int current_player) {
        System.out.printf("\t       Pemain X: %d Kemenangan   |   Pemain O: %d Kemenangan%n", wins[0], wins[1]);
        new_line();

        if (is_game_running) {
            System.out.print("                                    Giliran ");
            if (current_player == 1) {
                System.out.println("X");
            } else {
                System.out.println("O");
            }
        } else {
            System.out.println("                               Permainan Berakhir");
        }
    }

    private void display_bottom_tab(int information_code) {
        System.out.print("[KONSOL] >> ");
        print_information(information_code);
    }

    private int[][] copy_board(int[][] board) {
        int[][] copied_board = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };

        for (int row = 0; row<3; row++) {
            System.arraycopy(board[row], 0, copied_board[row], 0, 3);
        }

        return copied_board;
    }

    private String[][] format_board(int[][] board) {
        int[][] oldBoard = copy_board(board);
        String[][] newBoard = {
            {"_", "_", "_"},
            {"_", "_", "_"},
            {"_", "_", "_"}
        };

        for (int row = 0; row<3; row++) {
            for (int column = 0; column<3; column++) {
                if (oldBoard[row][column] == 1) {
                    newBoard[row][column] = "X";
                }
                
                if (oldBoard[row][column] == 2) {
                    newBoard[row][column] = "O";
                }
            }
        }
        
        return newBoard;
    }

    private void display_board(int[][] board_) {
        String[][] board = format_board(board_);

        new_line();
        System.out.println("                 |     |     ");
        System.out.printf("              %s  |  %s  |  %s  %n", board[0][0], board[0][1], board[0][2]);
        System.out.println("            _____|_____|_____");
        System.out.println("                 |     |     ");
        System.out.printf("              %s  |  %s  |  %s  %n", board[1][0], board[1][1], board[1][2]);
        System.out.println("            _____|_____|_____");
        System.out.println("                 |     |     ");
        System.out.printf("              %s  |  %s  |  %s  %n", board[2][0], board[2][1], board[2][2]);
        System.out.println("                 |     |     ");
        new_line();
    }

    private void print_information(int information_code) {
        if (information_code == 0) {
            System.out.println("Permainan sedang berlangsung!");
        } else if (information_code == 1) {
            System.out.println("Pemain X telah memenangkan permainan!");
        } else if (information_code == 2) {
            System.out.println("Pemain O telah memenangkan permainan!");
        } else if (information_code == 3) {
            System.out.println("Permainan berakhir dengan seri!");
        }
    }
}