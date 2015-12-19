package game.model;

import framework.util.RandomNumberGenerator;
import game.main.GameMain;

import java.awt.*;

/**
 * Created by michaelwomack on 12/16/15.
 */
public class Ball {
    private int x, y, width, height, velX, velY;
    private Rectangle rect;

    public Ball(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        rect = new Rectangle(x, y, width, height);
        velX = RandomNumberGenerator.getRandomIntBetween(-4, -3);
        velY = RandomNumberGenerator.getRandomIntBetween(8,9);
    }

    public void update() {
        x += velX;
        y += velY;
        correctCollision();
        updateRect();
    }

    private void correctCollision() {
        if (x < 0) {
            x = 0;
            velX = -velX;
        } else if (x + width > GameMain.GAME_WIDTH) {
            x = GameMain.GAME_WIDTH - width;
            velX = -velX;
        } else if (y < 0) {
            y = 0;
            velY = -velY;
        }

        //Bounce Sound
    }

    public void onCollidesWith(Paddle p) {
        velY = -velY;
        //Make bounce sound
    }

    //BrickCollision
    public void onCollidesWith(Brick b) {
        int topOfBrick = b.getY();
        int bottomOfBrick = b.getY() + b.getHeight();
        int leftSide = b.getX();
        int rightSide = b.getX() + b.getWidth();

        if (x + width > leftSide && x < rightSide)
            if (y > topOfBrick && y + height < bottomOfBrick)
                velX = -velX;
            else
                velY = -velY;

        b.hit();
    }

    public boolean isDead() {
        return y + height > GameMain.GAME_HEIGHT;
    }

    public void reset() {
        x = GameMain.GAME_WIDTH/2 - width/2;
        y = GameMain.GAME_HEIGHT / 2;
        velX = RandomNumberGenerator.getRandomIntBetween(-3, 4);
        velY = RandomNumberGenerator.getRandomIntBetween(7,9);
    }

    public void updateRect() {
        rect.setBounds(x, y, width, height);
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

    public Rectangle getRect() {
        return rect;
    }
}
