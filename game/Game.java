package game;

public class Game {
    public int user_input = -1;
    public int current_player = 1;
    public boolean is_game_running = true;
    public int marked_columns = 0;
    public int[] wins = {0, 0};
    public int information_code = 0;
    public int[][] board = {
        {0, 0, 0},
        {0, 0, 0},
        {0, 0, 0}
    };

    public boolean verify_input_send_request() {
        if (is_game_running) {
            if (verify_input()) {
                int[] formatted_input = format_input();
                verify_mark_columns(formatted_input[0], formatted_input[1], current_player);
                return true;
            }
        }
        return false;
    }

    public boolean set_game_state() {
        int state = get_board_state();
        information_code = state;
        if (state != 0) {
            is_game_running = false;
            if (state == 1) {
                wins[0] += 1;
            } else if (state == 2) {
                wins[1] += 1;
            }
            return true;
        }
        return false;
    }

    public void reset() {
        user_input = -1;
        current_player = 1;
        is_game_running = true;
        information_code = 0;
        marked_columns = 0;
        
        for (int row = 0; row<3; row++) {
            for (int column = 0; column<3; column++) {
                board[row][column] = 0;
            }
        }
    }

    private void verify_mark_columns(int row, int column, int playercode_) {
        if (board[row][column] == 0 && playercode_ != 0) {
            mark_column(row, column, playercode_);
            next_turn();
        }
    }

    private int get_board_state() {
        // Horizontal
        for (int row = 0; row<3; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2] && board[row][2] != 0) {
                return board[row][1];
            }
        }

        // Vertical
        for (int column = 0; column<3; column++) {
            if (board[0][column] == board[1][column] && board[1][column] == board[2][column] && board[2][column] != 0) {
                return board[1][column];
            }
        }

        // Diagonal
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[2][2] != 0) {
            return board[1][1];
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[2][0] != 0) {
            return board[1][1];
        }

        // Draw
        if (is_board_full()) {
            return 3;
        }

        // Playing
        return 0;
    }

    private void next_turn() {
        current_player = current_player%2 + 1;
        information_code = 0;
    }

    private boolean is_board_full() {
        return marked_columns >= 9;
    }

    private int[] format_input() {
        int[] formatted_input = {0, 0};

        if (user_input == 2) {
            formatted_input[1] = 1;
        } else if (user_input == 3) {
            formatted_input[1] = 2;
        } else if (user_input == 4) {
            formatted_input[0] = 1;
        } else if (user_input == 5) {
            formatted_input[0] = 1;
            formatted_input[1] = 1;
        } else if (user_input == 6) {
            formatted_input[0] = 1;
            formatted_input[1] = 2;
        } else if (user_input == 7) {
            formatted_input[0] = 2;
        } else if (user_input == 8) {
            formatted_input[0] = 2;
            formatted_input[1] = 1;
        } else if (user_input == 9) {
            formatted_input[0] = 2;
            formatted_input[1] = 2;
        }

        return formatted_input;
    }

    private boolean verify_input() {
        return user_input >= 1 && user_input <= 9;
    }

    private void mark_column(int row, int column, int playercode_) {
        board[row][column] = playercode_;
        marked_columns += 1;
    }
}