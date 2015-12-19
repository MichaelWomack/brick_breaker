package game.main;

import framework.util.InputHandler;
import game.state.LoadState;
import game.state.State;
import javax.swing.*;
import java.awt.*;

/**
 * Created by michaelwomack on 10/13/15.
 */
public class Game extends JPanel implements Runnable{
    private int gameWidth;
    private int gameHeight;
    private Image gameImage;

    private Thread gameThread;
    private volatile boolean running;
    private volatile State currentState;

    private InputHandler inputHandler;

    public Game(int gameWidth, int gameHeight){
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        //Inherited from JPanel
        setPreferredSize(new Dimension(gameWidth, gameHeight));
        setBackground(Color.BLACK);
        //allow us to receive user input, now available to game object
        setFocusable(true);
        requestFocus();
    }

    public void setCurrentState(State newState){
        System.gc();
        newState.init();
        currentState = newState;
        inputHandler.setCurrentState(currentState);
    }

    public void addNotify() {
        super.addNotify();
        initInput();
        setCurrentState(new LoadState());
        initGame();
    }

    private void initGame() {
        running = true;
        gameThread = new Thread(this, "Game Thread");
        gameThread.start();
    }

    @Override
    public void run() {
        while (running) {
            currentState.update();
            prepareGameImage();
            currentState.render(gameImage.getGraphics());
            repaint();

            try{
                Thread.sleep(14);

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.exit(0);
    }

    public void prepareGameImage() {
        if (gameImage == null) {
            gameImage = createImage(gameWidth, gameHeight);
        }
        Graphics g = gameImage.getGraphics();
        g.clearRect(0, 0, gameWidth, gameHeight);
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if (gameImage == null){
            return;
        }
        g.drawImage(gameImage, 0, 0, null);
    }

    public void exit() {
        running = false;
    }

    private void initInput(){
        inputHandler = new InputHandler();
        addKeyListener(inputHandler);
        addMouseListener(inputHandler);
    }
}
