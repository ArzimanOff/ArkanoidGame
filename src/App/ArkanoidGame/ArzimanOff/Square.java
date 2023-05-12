package App.ArkanoidGame.ArzimanOff;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

class Square {
    private int hitCount;
    private int x, y;
    private int size;
    private Color color;

    public Square(int x, int y, int size) {
        this.hitCount = getRandomHitCount(9, 2);
        this.x = x;
        this.y = y;
        this.size = size;
        setColor();
    }

    public List<String> colorList = Arrays.asList(
            // Список HEX - кодов цветов соответственно оставшемуся весу коробки
            "0x05CD49",   // 1
            "0x7BDB00",   // 2
            "0xF9C425",   // 3
            "0xFF971E",   // 4
            "0xFF7152",   // 5
            "0xFF5D39",   // 6
            "0xFF2C84",   // 7
            "0xFF006B",   // 8
            "0xE31747"    // 9
    );

    public void setColor(){
        this.color = Color.decode(colorList.get(this.hitCount - 1));
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillRoundRect(x, y, size, size, 10, 10);
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(3));
        g.drawRoundRect(x, y, size, size, 10, 10);

        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.setColor(Color.WHITE);
        FontMetrics metrics = g.getFontMetrics();
        String text = Integer.toString(getHitCount());

        int hitTextX = (this.x + this.size/2) - (metrics.stringWidth(text) / 2);
        int hitTextY = (this.y + this.size/2) - (metrics.getHeight() / 2) + metrics.getAscent();
        g.drawString(text, hitTextX, hitTextY);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }

    public int getHitCount() {
        return hitCount;
    }

    public void hitCountDecrease() {
        hitCount--;
    }

    public int getRandomHitCount(int max, int min){
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }
}