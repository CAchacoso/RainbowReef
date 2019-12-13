package Game;

import Game.GameInit;
import Immoveables.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static javax.imageio.ImageIO.read;

public class Map {
    private int x;
    private int y;
    BufferedImage img;
    public static ArrayList<GameObjects> object = new ArrayList<>();
    private BufferedImage shell, squid, pblock, yblock, rblock, gblock, lbblock, dbblock, wblock, sblock, lifeblock;

    public Map(BufferedImage img){
        this.img = resize(img, GameInit.WORLD_WIDTH, GameInit.WORLD_HEIGHT);
    }

    public static BufferedImage resize(BufferedImage img, int width, int height){
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage rimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = rimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return rimg;
    }

    public void init() {
        BufferedImage back = null;
        try {
            shell = read(new File("resources/Shell.png"));
            squid = read(new File("resources/Squid.png"));
            wblock = read(new File("resources/Block7.gif"));
            rblock = read(new File("resources/Block3.gif"));
            yblock = read(new File("resources/Block2.gif"));
            gblock = read(new File("resources/Block4.gif"));
            lbblock = read(new File("resources/Block5.gif"));
            dbblock = read(new File("resources/Block6.gif"));
            pblock = read(new File("resources/Block1.gif"));
            sblock = read(new File("resources/Block_solid.gif"));
            lifeblock = read(new File("resources/Block_life.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Solid Blocks (x = 30, y = 60)
        for (int a = 30; a < GameInit.WORLD_WIDTH - 30; a += 60) {
            object.add(new Unbreakable(sblock, a, 35));
        }
        for (int b = 60; b < GameInit.WORLD_HEIGHT - 30; b += 30) {
            object.add(new Unbreakable(sblock, 30, b));
        }
        for (int c = 60; c < GameInit.WORLD_HEIGHT - 30; c += 30) {
            object.add(new Unbreakable(sblock, GameInit.WORLD_WIDTH - 90, c));
        }
        //Squid
        object.add(new Squid(squid, GameInit.WORLD_WIDTH / 2 - 60, 70));
        //Life Blocks
        object.add(new LifeBlock(lifeblock, 90, 65));
        object.add(new LifeBlock(lifeblock, GameInit.WORLD_WIDTH - 150, 65));
        //White Blocks
        for (int a = 90; a < GameInit.WORLD_WIDTH - 90; a += 60) {
            object.add(new Breakable(wblock, a, 180));
        }
        //Red Blocks
        for (int a = 90; a < GameInit.WORLD_WIDTH - 90; a += 60) {
            object.add(new Breakable(rblock, a, 210));
        }
        //Yellow Blocks
        for (int a = 90; a < GameInit.WORLD_WIDTH - 90; a += 60) {
            object.add(new Breakable(yblock, a, 240));
        }
        //Green Blocks
        for (int a = 90; a < GameInit.WORLD_WIDTH - 90; a += 60) {
            object.add(new Breakable(gblock, a, 270));
        }
        //Light Blue Blocks
        for (int a = 90; a < GameInit.WORLD_WIDTH - 90; a += 60) {
            object.add(new Breakable(lbblock, a, 300));
        }
        //Dark Blue Blocks
        for (int a = 90; a < GameInit.WORLD_WIDTH - 90; a += 60) {
            object.add(new Breakable(dbblock, a, 330));
        }
        //Purple Blocks
        for (int a = 90; a < GameInit.WORLD_WIDTH - 90; a += 60) {
            object.add(new Breakable(pblock, a, 360));
        }
    }
    public void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(img, x*img.getWidth(), y*img.getHeight(), null);
        for(int i = 0; i < object.size(); i++){
            object.get(i).drawImage(g2d);
        }
    }
}
