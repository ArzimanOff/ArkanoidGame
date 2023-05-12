package App.ArkanoidGame.ArzimanOff;

import java.awt.*;
import java.util.Arrays;

class Ball {
    private int x, y;
    private int dx, dy;
    private int radius;
    private Color color;

    public Ball(int radius, Color color) {
        this.x = getRandomBallValue(41, 341);
        this.y = getRandomBallValue(321, 461);
        this.dx = getRandomSpeedVX();
        this.dy = getRandomSpeedVY();
        this.radius = radius;
        this.color = color;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
    }

    public void move() {
        x += dx;
        y += dy;
    }

    public void checkCollisionWithWalls(int width, int height) {
        if (x - radius < 0 || x + radius > width) {
            dx = -dx;
        }
        if (y - radius < 0) {
            dy = -dy;
        }
    }

    public void checkCollisionWithPlatform(Platform platform) {
        if (getBounds().intersects(platform.getBounds())) {
            dy = -dy;
        }
    }

    public boolean checkCollisionWithSquare(Square square) {
        if (getBounds().intersects(square.getBounds())) {
            Rectangle intersection = getBounds().intersection(square.getBounds());

            if (intersection.width > intersection.height) {
                dy = -dy;
            } else {
                dx = -dx;
            }
            GamePanel.score++;
            square.hitCountDecrease();
            return true;
        }
        return false;
    }

    public Rectangle getBounds() {
        return new Rectangle(x - radius, y - radius, 2 * radius, 2 * radius);
    }

    public int getY() {
        return y;
    }
    public int getX() {
        return x;
    }

    public int getRandomSpeedVX(){
        int min = -4;
        int max = 4;
        int random = 0;
        Integer[] TooLowSpeed = {-2, -1, 0, 1, 2};
        while (Arrays.asList(TooLowSpeed).contains(random)){
            random = (int) (Math.random() * ((max - min) + 1)) + min;
        }
        return random;
    }

    public int getRandomSpeedVY(){
        int min = -6;
        int max = 0;
        int random = 0;
        Integer[] TooLowSpeed = {-2, -1, 0, 1, 2};
        while (Arrays.asList(TooLowSpeed).contains(random)){
            random = (int) (Math.random() * ((max - min) + 1)) + min;
        }
        return random;
    }

    public int getRandomBallValue(int max, int min){
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }

}