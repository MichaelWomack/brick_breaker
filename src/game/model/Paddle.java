package game.model;

import game.main.GameMain;

import java.awt.*;

/**
 * Created by michaelwomack on 12/16/15.
 */
public class Paddle {
    private int x, y, width, height, velX;
    private Rectangle rect;
    private static final int MOVE_SPEED = 6;

    public Paddle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        rect = new Rectangle(x, y, width, height);
        velX = 0;
    }

    public void update() {
        x += velX;
        if (x < 0) {
            x = 0;
        } else if (x + width > GameMain.GAME_WIDTH) {
            x = GameMain.GAME_WIDTH - width;
        }
        updateRect();
    }

    private void updateRect() {
        rect.setBounds(x, y, width, height);
    }

    public void accelRight() {
        velX = MOVE_SPEED;
    }

    public void accelLeft() {
        velX = -MOVE_SPEED;
    }

    public void stop() {
        velX = 0;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Rectangle getRect() {
        return this.rect;
    }
}
