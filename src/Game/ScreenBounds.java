package Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ScreenBounds{
    BufferedImage img;

    public ScreenBounds(BufferedImage img) {
        this.img = resize(img, 35, 35);
    }

    public static BufferedImage resize(BufferedImage img, int width, int height){
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage rimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = rimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return rimg;
    }

    public void drawTop(Graphics2D g) {
        Graphics2D g2d = g;
        for (int x = 0; x < GameInit.WORLD_WIDTH; x += 32) {
            g2d.drawImage(this.img, x, 0, null);
        }
    }
    public void drawSides(Graphics2D g) {
        Graphics2D g2d = g;
        for (int y = 0; y < GameInit.WORLD_HEIGHT - 50; y += 32) {
            g2d.drawImage(this.img, 0, y, null);
        }
        for (int z = 0; z < GameInit.WORLD_HEIGHT - 50; z += 32) {
            g2d.drawImage(this.img, GameInit.WORLD_WIDTH - 32, z, null);
        }
    }
}
