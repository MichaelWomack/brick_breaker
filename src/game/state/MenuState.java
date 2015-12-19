package game.state;

import game.main.Resources;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Created by michaelwomack on 10/15/15.
 */
public class MenuState extends State {
    @Override
    public void init() {
        System.out.println("Entered Menu State");
    }

    @Override
    public void update() {
        //Nothing for now
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Resources.welcome, 0, 0, null);
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
