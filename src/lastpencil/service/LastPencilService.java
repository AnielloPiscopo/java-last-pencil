package lastpencil.service;


import lastpencil.exception.ManyPencilsTakenException;
import lastpencil.exception.NotEnoughPlayersException;
import lastpencil.pojo.Player;
import lastpencil.utility.IterableUtility;
import lastpencil.utility.NumUtility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

/**
 * Main orchestrator of the Last Pencil game.
 * It handles input/output, user interaction and delegates
 * game logic to the dedicated services.
 */
public class LastPencilService {
    private final LastPencilBotService botService;
    private final LastPencilGameService gameService;
    private final List<Player> PLAYERS_LIST;
    private final List<String> PLAYERS_NAMES_LIST;
    private final int MAX_PENCILS_NUM_TO_GET;

    /**
     * Creates the main game service and initializes players.
     *
     * @param maxPencilsToGet maximum number of pencils per move
     * @param players         player names
     */
    public LastPencilService(int maxPencilsToGet, String... players) {
        MAX_PENCILS_NUM_TO_GET = maxPencilsToGet;

        final List<Player> tempPlayersList = new ArrayList<>();

        if (players.length == 0) {
            throw new NotEnoughPlayersException();
        } else if (players.length == 1) {
            tempPlayersList.add(new Player(players[0]));
            tempPlayersList.add(new Player("Jack", "bot"));
        } else {
            tempPlayersList.add(new Player(players[0]));
            tempPlayersList.add(new Player(players[1]));
        }

        this.PLAYERS_LIST = List.copyOf(tempPlayersList);

        this.PLAYERS_NAMES_LIST = this.PLAYERS_LIST.stream()
                .map(Player::getName)
                .toList();

        this.gameService = new LastPencilGameService(this.PLAYERS_LIST);
        this.botService = new LastPencilBotService();
    }

    /**
     * Starts the game and manages the main execution flow.
     */
    public void start() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            setPencilNum(br);
            play(br);
        } catch (IOException e) {
            System.err.println("IOException:\n" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    /**
     * Reads and validates the initial number of pencils.
     */
    private void setPencilNum(BufferedReader br) throws IOException {
        System.out.println("How many pencils would you like to use:");

        boolean isPencilNumValid = false;

        do {
            try {
                int pencilsNum = NumUtility.getNumGreaterThanX(0, br);
                gameService.setPencilsNum(pencilsNum);
                isPencilNumValid = true;
            } catch (NumberFormatException e) {
                System.out.println("The number of pencils should be numeric");
            } catch (IllegalArgumentException e) {
                System.out.println("The number of pencils should be positive");
            }
        } while (!isPencilNumValid);
    }

    /**
     * Main game loop.
     */
    private void play(BufferedReader br) throws IOException {
        setFirstPlayer(br);

        do {
            printStarterInfoOfTurn();
            playTurn(br);
            if (gameService.isGameOver()) {
                gameService.switchTurn();

                System.out.println(gameService.getCurrentPlayer().getName() + " won!");
                return;
            }
            gameService.switchTurn();
        } while (true);
    }

    /**
     * Handles the choice of the first player.
     */
    private void setFirstPlayer(BufferedReader br) throws IOException {
        System.out.println(IterableUtility.getMsg(PLAYERS_NAMES_LIST, "Who will be the first (", ", ", null, "):"));

        boolean isFirstPlayerValid = false;

        do {
            try {
                Player firstPlayer = IterableUtility.getValueInList(PLAYERS_LIST, Player::getName, br);
                gameService.setCurrentPlayer(firstPlayer);
                isFirstPlayerValid = true;
            } catch (NoSuchElementException e) {
                System.out.println(IterableUtility.getMsg(PLAYERS_NAMES_LIST, "Choose between '", "' and '", null, "'"));
            }
        } while (!isFirstPlayerValid);
    }

    /**
     * Executes the current player's turn.
     */
    private void playTurn(BufferedReader br) throws IOException {
        Player currentPlayer = gameService.getCurrentPlayer();

        if (currentPlayer.getPlayerTypeStr().equalsIgnoreCase("bot")) {
            gameService.applyMove(botService.getBotMove(gameService.getPencilsNum(), MAX_PENCILS_NUM_TO_GET));
        } else {
            playAsAHuman(br);
        }
    }

    /**
     * Prints the current state of the game before a move.
     */
    private void printStarterInfoOfTurn() {
        System.out.println("|".repeat(gameService.getPencilsNum()));
        System.out.println();
        System.out.println(gameService.getCurrentPlayer().getName() + "'s turn!");
    }

    /**
     * Handles the human player's input and validation.
     */
    private void playAsAHuman(BufferedReader br) throws IOException {
        boolean isPencilsNumGottenValid = false;

        do {
            try {
                int pencilsToGet = NumUtility.getNumInRange(0, MAX_PENCILS_NUM_TO_GET, br);

                gameService.applyMove(pencilsToGet);

                isPencilsNumGottenValid = true;
            } catch (IllegalArgumentException e) {
                List<String> list = IntStream
                        .rangeClosed(1, MAX_PENCILS_NUM_TO_GET)
                        .mapToObj(String::valueOf)
                        .toList();
                System.out.println(IterableUtility.getMsg(list, "Possible values: '",
                        "', '", " or ", "'"));
            } catch (ManyPencilsTakenException e) {
                System.out.println(e.getMessage());
            }
        } while (!isPencilsNumGottenValid);
    }
}
