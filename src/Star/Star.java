package Star;

import Immoveables.*;
import Shell.*;
import Game.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

public class Star extends GameObjects {
    Shell shell;
    private int x;
    private int y;
    private int vx;
    private int vy;
    private int angle;
    boolean active = true;
    private boolean SpacePressed;
    private boolean isMoving;
    private static BufferedImage star;
    private Rectangle bound = new Rectangle(this.x, this.y, this.star.getWidth(), this.star.getHeight());

    static {
        try {
            star = read(new File("resources/Star.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Star(int x, int y, int vx, int vy, BufferedImage img) {
        super(x, y, star);
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.img = img;
        this.isMoving = false;
        Map.object.add(this);
        active = true;
    }

    public boolean isActive() {
        return active;
    }

    public void update() {
        this.move();
        updateBounds();
        checkCollision(this);
    }

    public void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.star.getWidth() / 2.0, this.star.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.star, rotation, null);

    }

    private void move() {
        x += vx;
        y += vy;
        if (x < 500) vx = -vx;
        if (y < 0) vy = -vy;
        if(x > 1440) vx = -vx;
        isMoving = true;
        checkBorder();
    }

    private void checkBorder() {
        if (x < 30) {
            x = 30;
        }
        if (x >= GameInit.WORLD_WIDTH - 88) {
            x = GameInit.WORLD_WIDTH - 88;
        }
        if (y < 40) {
            y = 40;
        }
        if (y >= GameInit.WORLD_HEIGHT - 80) {
            y = GameInit.WORLD_HEIGHT - 80;
        }
    }

    public void checkCollision(Star star) {
        GameObjects obj;
        Rectangle sbound = star.getBounds();
        for (int i = 0; i < Map.object.size(); i++) {
            obj = Map.object.get(i);
            if (sbound.intersects(obj.getBounds()) && obj != shell) {
                if (obj instanceof Breakable) {
                    this.x += vx;
                    this.y += vy;
                    this.angle += angle;
                    Map.object.remove(obj);
                }
                if (obj instanceof Unbreakable) {
                    this.x += vx;
                    this.y += vy;
                    this.angle -= angle;
                }
                if (obj instanceof Squid) {
                    System.exit(1);
                }
                active = false;
            }
        }
    }

    public Rectangle getBounds(){
        return this.bound;
    }

    public void updateBounds() {
        this.bound = new Rectangle(this.x, this.y, star.getWidth(), star.getHeight());
    }

    public void handle(GameObjects obj){

    }
}
