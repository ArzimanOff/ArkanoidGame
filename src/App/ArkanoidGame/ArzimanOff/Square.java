package App.ArkanoidGame.ArzimanOff;

// Перечень импортированных библиотек
import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 * Класс коробки для игры Арканоид (квадратик в верхней части экрана)
 * @autor Гюльахмед Арзиманов (ArzimanOff)
 */
class Square {
    private int hitCount;   // Условный вес квадрата (необходимое кол-во столкновений с шаром для уничтожения квадрата)
    private final int x, y; // Координаты верхнего-левого угла квадрата
    private final int size; // Размер квадрата (ширина, высота)
    private Color color;    // Цвет квадрата


    /**
     * Конструктор класса в котором случайно генерируются некоторые
     */
    public Square(int x, int y, int size) {
        this.hitCount = getRandomHitCount(9, 2); // генерация случайного веса квадрата
        this.x = x;
        this.y = y;
        this.size = size;
        setColor(); // Присвоение цвета исходя из сгенерированного веса квадрата
    }


    /**
     * Лист содержащий HEX-коды различных цветов для квадратов
     */
    public List<String> squareColorsList = Arrays.asList(
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


    /**
     * Метод для случайного присвоения цвета квадрату
     * исходя из сгенерированного индекса листа
     */
    public void setColor(){
        this.color = Color.decode(squareColorsList.get(this.hitCount - 1));
    }


    /**
     * Метод запроса для отрисовки квадрата
     */
    public void draw(Graphics2D g) {
        g.setColor(color);                                           // присвоение цвета области квадрата
        g.fillRoundRect(x, y, size, size, 10, 10);  // заливка области квадрата
        g.setColor(Color.black);                                     // присвоение цвета для рамки квадрата
        g.setStroke(new BasicStroke(3));                       // выбор толщены рамки квадрата
        g.drawRoundRect(x, y, size, size, 10, 10);  // заполнение области кубика с закругления

        g.setFont(new Font("Arial", Font.BOLD, 24));      // создание экземпляра свойств шрифта
        g.setColor(Color.WHITE);                                    // присвоение цвета для шрифта
        FontMetrics metrics = g.getFontMetrics();                   // получение характеристик шрифта
        String text = Integer.toString(getHitCount());              // определение текста для отрисовки

        // определение координат для начала отрисовки текста
        int hitTextX = (this.x + this.size/2) - (metrics.stringWidth(text) / 2);
        int hitTextY = (this.y + this.size/2) - (metrics.getHeight() / 2) + metrics.getAscent();

        g.drawString(text, hitTextX, hitTextY);                     // сам процесс отрисовки
    }


    /**
     * Метод возвращающий условный коллайдер квадрата
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }

    /**
     * Метод возвращающий вес квадрата
     */
    public int getHitCount() {
        return hitCount;
    }


    /**
     * Метод уменьшающий вес коробки
     */
    public void hitCountDecrease() {
        hitCount--;
    }


    /**
     * Метод генерирующий случайный вес коробки
     */
    public int getRandomHitCount(int max, int min){
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }
}