package game.state;

import game.main.Game;
import game.main.GameMain;
import game.main.Resources;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by michaelwomack on 10/15/15.
 */
public class MenuState extends State {
    private String menuText;
    private String galaxyText;
    private String clickToPlay;
    private Font menuFont, subFont;

    @Override
    public void init() {
        menuText = "Brick Breaker:";
        galaxyText = "Galaxy";
        clickToPlay = "Click to play...";
        menuFont = new Font("Luminari", Font.BOLD, 65);
        subFont = new Font("Luminari", Font.ITALIC, 36);

        System.out.println("Entered Menu State");
    }

    @Override
    public void update() {
        //Nothing for now
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Resources.welcome, 0, 0, null);
        g.setFont(menuFont);
        g.setColor(Color.white);
        g.drawString(menuText, GameMain.GAME_WIDTH / 3, GameMain.GAME_HEIGHT / 3);
        g.drawString(galaxyText, GameMain.GAME_WIDTH / 3 + 100, GameMain.GAME_HEIGHT / 2);
        g.setFont(subFont);
        g.drawString(clickToPlay, (int)(GameMain.GAME_WIDTH / 2.3), (int)(GameMain.GAME_HEIGHT/ 1.5));
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
