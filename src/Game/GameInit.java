package Game;

import Shell.*;
import Star.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.Timer;

import static javax.imageio.ImageIO.read;

public class GameInit extends JPanel{
    public static final int WORLD_WIDTH = 1440;
    public static final int WORLD_HEIGHT = 880;
    private BufferedImage world;
    private Graphics2D buffer;
    private JFrame jf;
    private Shell s1;
    private Map background;
    private Star star;
    private ScreenBounds bounds;

    public static void main(String[] args) {
        Thread x;
        GameInit shell = new GameInit();
        Endgame end = new Endgame();
        shell.init();
        try {

            while (true) {
                shell.s1.update();
                shell.star.update();
                shell.repaint();
                System.out.println(shell.s1);
                Thread.sleep(1000 / 144);
            }
        } catch (InterruptedException ignored) {
        }
    }
    private void init() {
        this.jf = new JFrame("Super Rainbow Reef");
        this.world = new BufferedImage(WORLD_WIDTH, WORLD_HEIGHT, BufferedImage.TYPE_INT_RGB);
        BufferedImage s1img = null;
        BufferedImage back = null;
        BufferedImage bound = null;
        BufferedImage projectile = null;

        try {
            System.out.println(System.getProperty("user.dir"));

            back = read(new File("resources/Background2.bmp"));
            s1img = read(new File("resources/Shell.png"));
            bound = read(new File("resources/Wall.gif"));
            projectile = read(new File("resources/Star.png"));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        s1 = new Shell(GameInit.WORLD_WIDTH/2 - 60, 700, 0, 0, 0, s1img);
        background = new Map(back);
        background.init();
        star = new Star(GameInit.WORLD_WIDTH/2 - 60, 700, -1 ,-2, projectile);
        bounds = new ScreenBounds(bound);

        ShellControl sc1 = new ShellControl(s1, KeyEvent.VK_A, KeyEvent.VK_D);

        this.jf.setLayout(new BorderLayout());
        this.jf.add(this);

        this.jf.addKeyListener(sc1);

        this.jf.setSize(WORLD_WIDTH, WORLD_HEIGHT);
        this.jf.setResizable(true);
        jf.setLocationRelativeTo(null);

        this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jf.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        buffer = world.createGraphics();
        super.paintComponent(g2);

        this.background.drawImage(buffer);
        this.bounds.drawTop(buffer);
        this.bounds.drawSides(buffer);

        this.s1.drawImage(buffer);
        g2.drawImage(world, 0, 0, null);

        this.star.drawImage(buffer);
        g2.drawImage(world, 0, 0, null);

        this.s1.GameOver();
    }
}
