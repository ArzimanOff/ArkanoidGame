package App.ArkanoidGame.ArzimanOff;

// Перечень импортированных библиотек
import java.awt.*;


/**
 * Класс нижней платформы для игры Арканоид
 * @autor Гюльахмед Арзиманов (ArzimanOff)
 */
class Platform {
    private int x, y;           // Координаты верхнего-левого угла платформы
    private int width, height;  // Размеры платформы
    private Color color;        // Цвет шара


    /**
     * Конструктор класса
     */
    public Platform(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }


    /**
     * Метод запроса для отрисовки нижней платформы
     */
    public void draw(Graphics g) {
        g.setColor(color);      // присвоение цвета платформы для кисти
        g.fillRoundRect(x, y, width, height, 5, 5); // заливка области платформы
    }


    /**
     * Метод для передвижения платформы ВЛЕВО
     */
    public void moveLeft() {
        x -= 30;    // изменение координаты X на -30
        if (x < 0) { // проверка условия того, что платформа может выйти за границы экрана СЛЕВА
            x = 0;
        }
    }


    /**
     * Метод для передвижения платформы ВПРАВО
     */
    public void moveRight() {
        x += 30;    // изменение координаты X на +30
        if (x + width > 400) {  // проверка условия того, что платформа может выйти за границы экрана СПРАВА
            x = 400 - width;
        }
    }


    /**
     * Метод возвращающий условный коллайдер для платформы
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getX(int x) {
        return this.x;  // метод возвращающий координату X текущего экземпляра класса
    }

    public void setX(int x) {
        this.x = x;     // метод переопределяющий координату X
    }

    public int getY() {
        return y;       // метод возвращающий координату Y текущего экземпляра класса
    }

    public void setY(int y) {
        this.y = y;     // метод переопределяющий координату Y
    }

    public int getWidth() {
        return width;   // метод возвращающий ширину платформы
    }

    public void setWidth(int width) {
        this.width = width;     // метод переопределяющий ширину платформы
    }

    public int getHeight() {
        return height;  // метод возвращающий высоту платформы
    }

    public void setHeight(int height) {
        this.height = height;   // метод переопределяющий высоту платформы
    }

    public Color getColor() {
        return color;   // метод возвращающий цвет платформы
    }

    public void setColor(Color color) {
        this.color = color;  // метод переопределяющий цвет платформы
    }
}