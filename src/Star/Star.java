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
    private int lives = 4;
    private int spawnX = GameInit.WORLD_WIDTH/2 - 60;
    private int spawnY = 500;
    private int angle;
    boolean active = true;
    private boolean isMoving;
    private static BufferedImage star;
    private Graphics2D buffer;
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

    public void update() {
        move();
        updateBounds();
        if (lives > 0){
            if (y > 840){
                this.x = spawnX;
                this.y = spawnY;
                loseLife();
            }
        }
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
        if(x < 30) vx = -vx;
        if(y < 0) vy = -vy;
        if(x > 1400) vx = -vx;
        isMoving = true;
    }

    public void checkCollision(Star star) {
        GameObjects obj;
        Rectangle sbound = star.getBounds();
        for (int i = 0; i < Map.object.size(); i++) {
            obj = Map.object.get(i);
            if (sbound .intersects(obj.getBounds())) {
                handle(obj);
            }
        }
    }

    public void displayLives(Graphics g2){
        g2.setFont(new Font("CourierNew", Font.BOLD, 25));
        g2.setColor(Color.BLUE);
        g2.drawString("Lives : " + this.getLives(), GameInit.WORLD_WIDTH/2 + 420, GameInit.WORLD_HEIGHT * 35 / 40);
        g2.setColor(Color.BLUE);
    }

    public int getLives(){
        return this.lives;
    }

    public void loseLife(){
        this.lives -= 1;
    }

    public void gainLife(){
        this.lives += 1;
    }

    public Rectangle getBounds(){
        return this.bound;
    }

    public void updateBounds() {
        this.bound = new Rectangle(this.x, this.y, star.getWidth(), star.getHeight());
    }

    public void handle(GameObjects obj){
        if(obj instanceof Shell) {
            if(this.isMoving){
                vy = -vy;
            }else {
                vx = -vx;
            }
        }
        if(obj instanceof Squid || this.lives < 0) {
            if(this.isMoving){
                vy = +vy;
            }else {
                vx = +vx;
            }
            System.exit(1);
        }
        if (obj instanceof Breakable) {
            if(this.isMoving){
                vy = -vy;
                Map.object.remove(obj);
            }else {
                vx = -vx;
            }
        }
        if (obj instanceof LittleSquid) {
            if(this.isMoving){
                vy = -vy;
                Map.object.remove(obj);
            }else {
                vx = -vx;
            }
        }
        if (obj instanceof Unbreakable) {
            if(this.isMoving){
                vy = -vy;
            }else{
                vx = -vx;
            }
        }
        if(obj instanceof LifeBlock){
            if(this.isMoving){
                vy = -vy;
                Map.object.remove(obj);
                gainLife();
            }else{
                vx = -vx;
            }
        }
    }
}
