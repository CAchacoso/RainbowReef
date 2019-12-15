package Shell;

import Game.GameInit;
import Game.GameObjects;
import Game.Map;
import Game.ScreenBounds;
import Immoveables.LifeBlock;
import Immoveables.Squid;
import Immoveables.Unbreakable;
import Star.Star;
import javafx.stage.Screen;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Shell extends GameObjects {
    private int x;
    private int y;
    private int vx;
    private int vy;
    private int angle;

    private int R = 3;

    private BufferedImage img;
    private Rectangle bound = new Rectangle(this.x, this.y);
    private boolean RightPressed;
    private boolean LeftPressed;
    private int coolDown = 50;
    private int coolDownTime = 0;

    public Star star;

    public Shell(int x, int y, int vx, int vy, int angle, BufferedImage img) {
        super(x, y, img);
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.img = resize(img, 90, 50);
        this.angle = angle;
        Map.object.add(this);
    }

    public static BufferedImage resize(BufferedImage img, int width, int height) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage rimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = rimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return rimg;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    void toggleRightPressed() {
        this.RightPressed = true;
    }

    void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    void unToggleRightPressed() {
        this.RightPressed = false;
    }

    void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    public void update() {
        if (this.LeftPressed) {
            this.moveLeft();
            updateBounds();
        }
        if (this.RightPressed) {
            this.moveRight();
            updateBounds();
        }
        if (coolDownTime < coolDown) {
            coolDownTime += 1;
        }
        checkCollision(this);
    }

    private void moveLeft() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        x -= vx;
        checkBorder();
    }

    private void moveRight() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        x += vx;
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

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", angle=" + angle;
    }

    public void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);
        g2d.drawImage(this.img, rotation, null);
    }

    public void checkCollision(Shell s) {
        GameObjects obj;
        Rectangle sbound = s.getBounds();
        for (int i = 0; i < Map.object.size(); i++) {
            obj = Map.object.get(i);
            if (sbound.intersects(obj.getBounds())) {
                handle(obj);
            }
        }
    }

    public Rectangle getBounds() {
        return this.bound;
    }

    public void updateBounds() {
        this.bound = new Rectangle(this.x, this.y, img.getWidth(), img.getHeight());
    }

    public void handle(GameObjects obj) {
        if (obj instanceof Unbreakable) {
            if (this.LeftPressed) {
                this.x += vx;
            }
            if (this.RightPressed) {
                this.x -= vx;
            }
        }
    }
}
