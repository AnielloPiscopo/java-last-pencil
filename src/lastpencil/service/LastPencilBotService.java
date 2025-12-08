package lastpencil.service;

import java.util.Random;

/**
 * Service responsible for calculating the bot's move.
 * This class is stateless and contains only the decision logic.
 */
public class LastPencilBotService {
    private static final Random RANDOM = new Random();


    /**
     * Calculates the move of the bot based on the current game state.
     *
     * @param pencilsNum          number of remaining pencils
     * @param maxPencilsTogGet   maximum number of pencils that can be taken
     * @return the number of pencils the bot decides to take
     */
    public int getBotMove(int pencilsNum , int maxPencilsTogGet) {
        int botMove;

        if (pencilsNum == 1) {
            botMove = 1;
        } else {
            botMove = (pencilsNum - 1) % (maxPencilsTogGet + 1);

            botMove = (botMove == 0) ?
                    Math.min(RANDOM.nextInt(maxPencilsTogGet) + 1, pencilsNum) :
                    Math.min(botMove, pencilsNum);
        }

        System.out.println(botMove);

        return botMove;
    }
}
