package lastpencil.service;

import java.util.Random;

public class LastPencilBotService {
    private static final Random RANDOM = new Random();


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
