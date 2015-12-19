package game.main;

import javax.swing.*;

/**
 * Created by michaelwomack on 10/13/15.
 */
public class GameMain {
    private static final String GAME_TITLE = "Brick Breaker";
    public static final int GAME_HEIGHT = 625;
    public static final int GAME_WIDTH = 1000;
    public static Game game;

    public static void main(String[] args){
        JFrame frame = new JFrame(GAME_TITLE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        game = new Game(GAME_WIDTH, GAME_HEIGHT);
        frame.add(game);
        frame.pack();
        frame.setVisible(true);
    }
}
