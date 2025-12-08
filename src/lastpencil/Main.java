package lastpencil;

import lastpencil.service.LastPencilService;

public class Main {
    public static void main(String[] args) {
        LastPencilService lastPencilService = new LastPencilService(3 , "John");

        lastPencilService.start();
    }
}
