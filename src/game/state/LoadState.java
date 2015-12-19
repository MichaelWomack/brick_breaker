package game.state;

import game.main.Resources;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Created by michaelwomack on 10/15/15.
 */
public class LoadState extends State {

    @Override
    public void init() {
        Resources.load();
        System.out.println("Loaded Successfully");
    }

    @Override
    public void update() {
        setCurrentState(new MenuState());
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void onClick(MouseEvent e) {

    }

    @Override
    public void onKeyPress(KeyEvent e) {

    }

    @Override
    public void onKeyRelease(KeyEvent e) {

    }
}
