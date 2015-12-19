package game.state;

import game.main.GameMain;
import game.main.Resources;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by michaelwomack on 12/19/15.
 */
public class WinState extends State {
    private Font font;
    private String winText;
    private String levelStats;
    private String continueGame;
    private int finalScore;

    @Override
    public void init() {
        font = new Font("Monospace", Font.BOLD, 36);
        finalScore = PlayState.playerScore;
        winText = "Level " + PlayState.currentLevel + " Cleared!";
        levelStats = "Final Score: " + finalScore;
        continueGame = "Click to Continue to Level " + ++PlayState.currentLevel;

    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        //Set BackGround
//        g.setColor(Color.black);
//        g.fillRect(0, 0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);
        g.drawImage(Resources.spaceBackground, 0, 0, null);

        //Info
        g.setColor(Color.darkGray);
        g.setFont(font);
        g.drawString(winText, GameMain.GAME_WIDTH / 3, GameMain.GAME_HEIGHT / 3);
        g.drawString(levelStats, GameMain.GAME_WIDTH / 3, GameMain.GAME_HEIGHT / 2);
        g.drawString(continueGame, GameMain.GAME_WIDTH / 3 - 100, (int)(GameMain.GAME_HEIGHT / 1.5));

    }

    @Override
    public void onClick(MouseEvent e) {
        setCurrentState(new PlayState());
    }

    @Override
    public void onKeyPress(KeyEvent e) {

    }

    @Override
    public void onKeyRelease(KeyEvent e) {

    }
}
