package Game;

import Game.GameInit;
import Shell.Shell;
import Star.Star;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

public class Endgame extends JPanel {

    private BufferedImage world;
    private BufferedImage endgame;
    private Graphics2D buffer;

    public void init() {
        world = new BufferedImage(GameInit.WORLD_WIDTH, GameInit.WORLD_HEIGHT, BufferedImage.TYPE_INT_RGB);
        BufferedImage end = null;

        try {
            System.out.println(System.getProperty("user.dir"));

            end = read(new File("resources/Congratulations.gif"));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        endgame = end;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        buffer = world.createGraphics();
        super.paintComponent(g2);

        this.endgame.createGraphics();
    }
}
