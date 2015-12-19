package game.main;

import javax.imageio.ImageIO;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.Buffer;

/**
 * Created by michaelwomack on 10/14/15.
 */
public class Resources {
    public static BufferedImage welcome, icon, spaceBackground;
    public static Color backgroundColor = Color.DARK_GRAY;
    public static Color paddleColor = Color.lightGray;
    public static Color ballColor = Color.white;

    public static Color red = new Color(255, 0, 0);
    public static Color orange = new Color(255, 102, 0);
    public static Color yellow = new Color(255, 255, 6);
    public static Color lightGreen = new Color(153, 255, 51);
    public static Color green = new Color(0, 204, 0);
    public static Color blue =  new Color(0, 64, 255);
    public static Color purple = new Color(89, 0, 179);

    public static void load() {
        try {
            welcome = loadImage("spaceCool.jpg");
            spaceBackground = loadImage("spaceHeavens.jpg");
            //icon = loadImage("icon.png");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static AudioClip loadSound(String fileName) {
        URL fileUrl = Resources.class.getResource("/resources/" + fileName);
        return Applet.newAudioClip(fileUrl);
    }

    private static BufferedImage loadImage(String fileName) throws IOException {
        BufferedImage image = null;
        try {
            image = ImageIO.read(Resources.class.getResourceAsStream("/resources/" + fileName));
        } catch (IOException e) {
            System.out.println("An error occurred reading file..." + e.getMessage());
        }
        return image;
    }
}
