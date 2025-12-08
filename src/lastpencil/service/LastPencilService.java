package lastpencil.service;


import lastpencil.exception.ManyPencilsTakenException;
import lastpencil.pojo.Player;
import lastpencil.utility.IterableUtility;
import lastpencil.utility.NumUtility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.IntStream;

public class LastPencilService {
    private final List<Player> PLAYERS_LIST;
    private final List<String> PLAYERS_NAMES_LIST;
    private final int MAX_PENCILS_NUM_TO_GET;
    private int pencilsNum;
    private Player currentPlayer;

    public LastPencilService(int maxPencilsToGet, String... players) {
        MAX_PENCILS_NUM_TO_GET = maxPencilsToGet;

        final List<Player> tempPlayersList = new ArrayList<>();

        if (players.length == 0) {
            tempPlayersList.add(new Player("Jack", true));
            tempPlayersList.add(new Player("Jack", true));
        } else if (players.length == 1) {
            tempPlayersList.add(new Player(players[0]));          // human
            tempPlayersList.add(new Player("Jack", true));        // bot
        } else {
            tempPlayersList.add(new Player(players[0]));
            tempPlayersList.add(new Player(players[1]));
        }

        this.PLAYERS_LIST = List.copyOf(tempPlayersList);

        this.PLAYERS_NAMES_LIST = this.PLAYERS_LIST.stream()
                .map(Player::getName)
                .toList();
    }

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

    private void setPencilNum(BufferedReader br) throws IOException {
        System.out.println("How many pencils would you like to use:");

        boolean isPencilNumValid = false;

        do {
            try {
                pencilsNum = NumUtility.getNumGreaterThanX(0, br);
                isPencilNumValid = true;
            } catch (NumberFormatException e) {
                System.out.println("The number of pencils should be numeric");
            } catch (IllegalArgumentException e) {
                System.out.println("The number of pencils should be positive");
            }
        } while (!isPencilNumValid);
    }

    private void play(BufferedReader br) throws IOException {
        setFirstPlayer(br);

        do {
            playTurn(br);
            currentPlayer = PLAYERS_LIST.get(
                    (PLAYERS_LIST.indexOf(currentPlayer) + 1) % PLAYERS_LIST.size()
            );
        } while (pencilsNum > 0);

        System.out.println(currentPlayer.getName() + " won!");
    }

    private void setFirstPlayer(BufferedReader br) throws IOException {
        System.out.println(IterableUtility.getMsg(PLAYERS_NAMES_LIST, "Who will be the first (", ", ", "):"));

        boolean isFirstPlayerValid = false;

        do {
            try {
                currentPlayer = IterableUtility.getValueInList(PLAYERS_LIST, Player::getName, br);
                isFirstPlayerValid = true;
            } catch (NoSuchElementException e) {
                System.out.println(IterableUtility.getMsg(PLAYERS_NAMES_LIST, "Choose between '", "' and '", "'"));
            }
        } while (!isFirstPlayerValid);
    }

    private void playTurn(BufferedReader br) throws IOException {
        printStarterInfoOfTurn();

        if (currentPlayer.isABot()) {
            playAsABot();
        } else {
            playAsAHuman(br);
        }
    }

    private void printStarterInfoOfTurn() {
        for (int i = 1; i <= pencilsNum; i++) {
            System.out.print("|");
        }

        System.out.println();

        System.out.println(currentPlayer.getName() + "'s turn!");
    }

    private void playAsABot() {
        int botMove;

        if (pencilsNum == 1) {
            botMove = 1;
        } else {
            botMove = (pencilsNum - 1) % (MAX_PENCILS_NUM_TO_GET + 1);

            botMove = (botMove == 0) ?
                    Math.min(new Random().nextInt(MAX_PENCILS_NUM_TO_GET) + 1, pencilsNum) :
                    Math.min(botMove, pencilsNum);
        }

        System.out.println(botMove);

        pencilsNum -= botMove;
    }

    private void playAsAHuman(BufferedReader br) throws IOException {
        boolean isPencilsNumGottenValid = false;

        do {
            try {
                int pencilsToGet = NumUtility.getNumInRange(0, MAX_PENCILS_NUM_TO_GET, br);

                if (pencilsToGet > pencilsNum) {
                    throw new ManyPencilsTakenException();
                }

                pencilsNum -= pencilsToGet;
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
