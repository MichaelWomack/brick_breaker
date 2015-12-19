package game.state;

import game.main.GameMain;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Created by michaelwomack on 10/15/15.
 */
public abstract class State {

    public abstract void init();

    public abstract void update();

    public abstract void render(Graphics g);

    public abstract void onClick(MouseEvent e);

    public abstract void onKeyPress(KeyEvent e);

    public abstract void onKeyRelease(KeyEvent e);

    public void setCurrentState(State newState){
        GameMain.game.setCurrentState(newState);
    }
}
