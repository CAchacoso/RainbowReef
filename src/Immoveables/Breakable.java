package Immoveables;

import Game.GameObjects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Breakable extends GameObjects {

    private Rectangle bound;

    public Breakable(BufferedImage img, int x, int y) {
        super(x, y, img);
        this.img = resize(img, 60, 30);
        this.bound = new Rectangle(x,y,this.img.getWidth(), this.img.getHeight());
        objects.add(this);
    }

    public static BufferedImage resize(BufferedImage img, int width, int height) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage rimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = rimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return rimg;
    }

//    Image block;
//    boolean destroyed;
//
//    int posX, posY;
//    int width, height;
//
//    public Breakable(int x, int y, int width, int height, String s){
//        this.posX = x;
//        this.posY = y;
//        this.width = width;
//        this.height = height;
//
//        try {
//            block = ImageIO.read(new File("resources/Block1.gif"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void drawImage(Graphics g, Component c){
//     if(!destroyed)
//         g.drawImage(block, x, y, width, height, c);
//    }
}
