package game.state;

import game.main.Game;
import game.main.GameMain;
import game.main.Resources;
import game.model.Ball;
import game.model.Brick;
import game.model.Paddle;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.util.*;
import java.util.List;

/**
 * Created by michaelwomack on 12/16/15.
 */
public class PlayState extends State{
    private Paddle paddle;
    private static final int PADDLE_WIDTH = 90;
    private static final int PADDLE_HEIGHT = 15;

    private Ball ball;
    private static final int DIAMETER = 15;

    private static List<Brick> bricks;
    private static final int BRICK_WIDTH = 150;
    private static final int BRICK_HEIGHT = 50;

    public static int numBricks;
    public static int currentLevel = 1;
    public static int playerScore = 0;
    private Font scoreFont;



    @Override
    public void init() {
        paddle = new Paddle((GameMain.GAME_WIDTH / 2) - PADDLE_WIDTH / 2,
                GameMain.GAME_HEIGHT - PADDLE_HEIGHT,
                PADDLE_WIDTH, PADDLE_HEIGHT);

        ball = new Ball((GameMain.GAME_WIDTH / 2) - DIAMETER / 2,
                GameMain.GAME_HEIGHT - 50, DIAMETER, DIAMETER);

        scoreFont = new Font("Cambria", Font.BOLD, 20);

        bricks = createLevel();
    }

    @Override
    public void update() {
        paddle.update();
        ball.update();

        if (ballCollide(paddle.getRect())) {
            ball.onCollidesWith(paddle);
            playerScore += 10;
        } else if (ballCollide(bricks)) {
            playerScore += 50;
        } else if (ball.isDead()) {
            ball.reset();
            playerScore -= 200;
        }

        if (levelCleared()){
            setCurrentState(new WinState());
        }
    }

    private boolean levelCleared() {
        return numBricks == 0;
    }

    @Override
    public void render(Graphics g) {
        //Draw Background
        g.drawImage(Resources.welcome, 0, 0, null);

        //Draw Paddle
        g.setColor(Resources.paddleColor);
        g.fillRect(paddle.getX(), paddle.getY(),
                paddle.getWidth(), paddle.getHeight());

        //Draw Ball
        g.setColor(Resources.ballColor);
        g.fillRect(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());

        //Display Score and Level
        g.setFont(scoreFont);
        g.drawString("Score: " + String.valueOf(playerScore), 25, 25);
        g.drawString("Level " + String.valueOf(currentLevel), 25, 50);


        //Set Bricks
        for (Brick brick: bricks)
            if (!brick.isDestroyed()) {
                g.setColor(brick.getColor());
                g.fillRect(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
            }
    }

    private List<Brick> createLevel() {
        List<Brick> list = new ArrayList<>();
        switch(currentLevel) {
            case 1:
                list.add(new Brick((GameMain.GAME_WIDTH / 4) - BRICK_WIDTH - 10,
                        (GameMain.GAME_HEIGHT / 8), BRICK_WIDTH, BRICK_HEIGHT, 50));
                list.add(new Brick((GameMain.GAME_WIDTH / 2) - BRICK_WIDTH / 2,
                        (GameMain.GAME_HEIGHT / 8), BRICK_WIDTH, BRICK_HEIGHT, 50));
                list.add(new Brick(GameMain.GAME_WIDTH - (int) (BRICK_WIDTH * 1.6),
                        (GameMain.GAME_HEIGHT / 8), BRICK_WIDTH, BRICK_HEIGHT, 50));

                //Middle Row
                list.add(new Brick(GameMain.GAME_WIDTH / 3 - (int)(BRICK_WIDTH / 1.8),
                        (GameMain.GAME_HEIGHT / 3), BRICK_WIDTH, BRICK_HEIGHT, 50));
                list.add(new Brick(GameMain.GAME_WIDTH - (int) (BRICK_WIDTH * 2.6),
                        (GameMain.GAME_HEIGHT / 3), BRICK_WIDTH, BRICK_HEIGHT, 50));

                //Bottom Row
                list.add(new Brick((GameMain.GAME_WIDTH / 2 - (BRICK_WIDTH/2)),
                        (int)(GameMain.GAME_HEIGHT / 1.7), BRICK_WIDTH, BRICK_HEIGHT, 50));
                break;

            case 2: //Level 2 Bricks

                //Top Row
                list.add(new Brick((GameMain.GAME_WIDTH / 4) - BRICK_WIDTH - 10,
                        (GameMain.GAME_HEIGHT / 8), BRICK_WIDTH, BRICK_HEIGHT, 70));
                list.add(new Brick((GameMain.GAME_WIDTH / 2) - BRICK_WIDTH / 2,
                        (GameMain.GAME_HEIGHT / 8), BRICK_WIDTH, BRICK_HEIGHT, 70));
                list.add(new Brick(GameMain.GAME_WIDTH - (int) (BRICK_WIDTH * 1.6),
                        (GameMain.GAME_HEIGHT / 8), BRICK_WIDTH, BRICK_HEIGHT, 70));

                //Middle Row
                list.add(new Brick(GameMain.GAME_WIDTH / 3 - (BRICK_WIDTH / 2),
                        (GameMain.GAME_HEIGHT / 3), BRICK_WIDTH, BRICK_HEIGHT, 70));
                list.add(new Brick(GameMain.GAME_WIDTH - (int) (BRICK_WIDTH * 2.6),
                        (GameMain.GAME_HEIGHT / 3), BRICK_WIDTH, BRICK_HEIGHT, 70));

                //Bottom Row
                list.add(new Brick((GameMain.GAME_WIDTH / 4) - BRICK_WIDTH - 10,
                        (int) (GameMain.GAME_HEIGHT / 1.7), BRICK_WIDTH, BRICK_HEIGHT, 70));
                list.add(new Brick((GameMain.GAME_WIDTH / 2) - BRICK_WIDTH / 2,
                        (int) (GameMain.GAME_HEIGHT / 1.7), BRICK_WIDTH, BRICK_HEIGHT, 70));
                list.add(new Brick(GameMain.GAME_WIDTH - (int) (BRICK_WIDTH * 1.6),
                        (int) (GameMain.GAME_HEIGHT / 1.7), BRICK_WIDTH, BRICK_HEIGHT, 70));
                break;

            case 3: //Level 3 Bricks
                break;
        }

        numBricks = list.size();
        return list;
    }


    private boolean ballCollide(Rectangle rect) {
        return ball.getRect().intersects(rect);
    }

    private boolean ballCollide(List<Brick> list) {
        for (Brick brick: list) {
            if (ball.getRect().intersects(brick.getRect())) {
                ball.onCollidesWith(brick);
                return true;
            }
        }
        return false;
    }

    @Override
    public void onClick(MouseEvent e) {

    }

    @Override
    public void onKeyPress(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            paddle.accelRight();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            paddle.accelLeft();
        }
        update();
    }

    @Override
    public void onKeyRelease(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT
                || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            paddle.stop();
        }
    }
}
