package game.model;

import game.main.Resources;
import game.state.PlayState;

import java.awt.*;

/**
 * Created by michaelwomack on 12/16/15.
 */

/** I want to make the bricks all green to represent full strength.
 * As they get hit, their color changes to represent their strength;
 */

public class Brick {
    private int x, y, width, height, strength;
    private Color color;
    private Rectangle rect;

    public Brick(int x, int y, int width, int height, int strength) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rect = new Rectangle(x, y, width, height);
        this.strength = strength;
        update();
    }

    public void hit() {
        strength -= 10;
        update();
        if (isDestroyed())
            destroy();
        //Make hit sound
    }

    private void destroy() {
        rect.setBounds(0, 0, 0, 0);
        PlayState.numBricks--;
    }

    private void update() {
        if (strength <= 10) {
            color = Resources.red;
        } else if (strength <= 20) {
            color = Resources.orange;
        } else if (strength <= 30) {
            color = Resources.yellow;
        } else if (strength <= 40) {
            color = Resources.lightGreen;
        } else if (strength <= 50) {
            color = Resources.green;
        } else if (strength <= 60) {
            color = Resources.blue;
        } else if (strength <= 70) {
            color = Resources.purple;
        }
    }

    public boolean isDestroyed(){
        return strength <= 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getStrength() {
        return strength;
    }

    public Color getColor() {
        return color;
    }

    public Rectangle getRect() {
        return rect;
    }
}
