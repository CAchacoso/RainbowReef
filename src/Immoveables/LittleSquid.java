package Immoveables;

import Game.GameObjects;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LittleSquid extends GameObjects {
    private Rectangle bound;

    public LittleSquid(BufferedImage img, int x, int y) {
        super(x, y, img);
        this.img = resize(img, 50, 60);
        this.bound = new Rectangle(x, y, this.img.getWidth(), this.img.getHeight());
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


}
