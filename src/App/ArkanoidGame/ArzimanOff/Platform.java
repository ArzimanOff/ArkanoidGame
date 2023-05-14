package App.ArkanoidGame.ArzimanOff;
import java.awt.*;

class Platform {
    private int x, y;
    private int width, height;
    private Color color;

    public Platform(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRoundRect(x, y, width, height, 5, 5);
    }

    public void moveLeft() {
        x -= 30;
        if (x < 0) {
            x = 0;
        }
    }

    public void moveRight() {
        x += 30;
        if (x + width > 400) {
            x = 400 - width;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getX(int x) {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}