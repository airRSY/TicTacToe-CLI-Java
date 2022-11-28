package run;


import cli.Cli;
import game.Game;
import java.util.Scanner;

public class run {
    static Scanner input = new Scanner(System.in);
    static Cli CLI = new Cli();
    static Game GAME = new Game();

    static boolean is_restart_game() {
        String[] yes = {"iya", "ya", "ulang", "kembali", "y", "mengulang", "yes"};

        System.out.println();
        System.out.println("Ingin mengulang permainan?");
        String reset_input = input.next();

        for (String value : yes) {
            if (reset_input.equals(value)) {
                GAME.reset();
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        while (true) {
            CLI.display_cli(GAME.wins, GAME.is_game_running, GAME.current_player, GAME.board, GAME.information_code);
            GAME.user_input = CLI.get_input(GAME.current_player);
            if (!GAME.verify_input_send_request()) {
                continue;
            }
            if (GAME.set_game_state()) {
                CLI.display_cli(GAME.wins, GAME.is_game_running, GAME.current_player, GAME.board, GAME.information_code);
            }

            if (!GAME.is_game_running && !is_restart_game()) {
                input.close();
                break;
            }
        }
    }
}