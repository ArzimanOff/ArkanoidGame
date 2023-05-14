package App.ArkanoidGame.ArzimanOff;

// Перечень импортированных библиотек
import java.awt.*;
import java.util.Arrays;
import java.util.List;


/**
 * Класс шара для игры Арканоид
 * @autor Гюльахмед Арзиманов (ArzimanOff)
 */
class Ball {
    private boolean isIntersect = false;    // Переменная хранящая состояние шара относительно коробок
    private int x, y;                       // Координаты центра шара
    private int dx, dy;                     // Скорость шара по оси X и Y соответственно
    private final int radius;               // Радиус шара
    private Color color;                    // Цвет шара


    /**
     * Конструктор класса в котором случайно генерируются свойства шара
     */
    public Ball(int radius, Color color) {
        this.x = getRandomValue(41, 341);   // случайная координата X шарика
        this.y = getRandomValue(321, 461);  // случайная координата Y шарика

        this.dx = getRandomSpeedVX();                // случайная скорость по оси X
        this.dy = getRandomSpeedVY();                // случайная скорость по оси Y

        this.radius = radius;                        // Радиус из конструктора класса
        this.color = color;                          // Цвет из конструктора класса
    }


    /**
     * Метод запроса для отрисовки шара
     */
    public void draw(Graphics g) {
        g.setColor(color);  // присвоение цвета шара для кисти
        g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);  // заливка области шара
    }


    /**
     * Метод передвижения шара
     */
    public void move() {
        x += dx;    // изменение скорости по оси X
        y += dy;    // изменение скорости по оси Y
    }


    /**
     * Метод для определения и обработки столкновений с границами игровой области
     */
    public void checkCollisionWithWalls(int width, int height) {
        if (x - radius < 0 || x + radius > width) {
            dx = -dx;
            this.updateColor();
        }
        if (y - radius < 0) {
            dy = -dy;
            this.updateColor();
        }
    }


    /**
     * Метод для определения и обработки столкновений с нижней платформой
     */
    public void checkCollisionWithPlatform(Platform platform) {
        if (getBounds().intersects(platform.getBounds())) {
            dy = -dy;
            this.updateColor();
        }
    }


    /**
     * Метод для определения и обработки столкновений с коробками на игровом поле
     */
    public boolean checkCollisionWithSquare(Square square) {

        // Проверка условия того, что условный коллайдер шара и коробки пересекаются
        if (getBounds().intersects(square.getBounds()) && !isIntersect) {
            Rectangle intersection = getBounds().intersection(square.getBounds());

            if (intersection.width > intersection.height) {
                dy = -dy;   // Ревёрсинг направления скорости по оси X
            } else {
                dx = -dx;   // Ревёрсинг направления скорости по оси Y
            }
            this.updateColor(); // Запрос на возможное изменение цвета
            GamePanel.score++;  // увеличение количества очков
            square.hitCountDecrease();
            this.isIntersect = true;
            return true;
        }else{
            this.isIntersect = false;
        }
        return false;
    }


    /**
     * Метод возвращающий условный коллайдер шара (квадрат описанный около шара)
     */
    public Rectangle getBounds() {
        return new Rectangle(x - radius, y - radius, 2 * radius, 2 * radius);
    }


    public int getX() {
        return x;   // метод возвращающий координату X текущего экземпляра класса
    }
    public int getY() {
        return y;   // метод возвращающий координату Y текущего экземпляра класса
    }
    public void setX(int x) {
        this.x = x; // метод переопределяющий координату X
    }
    public void setY(int y) {
        this.y = y; // метод переопределяющий координату Y
    }


    /**
     * Метод генерирующий случайную скорость по оси X
     * в диапазоне от -4 до 4, исключая слишком маленькое значение
     */
    public int getRandomSpeedVX(){
        int min = -4;
        int max = 4;
        int random = 0;
        Integer[] TooLowSpeed = {-2, -1, 0, 1, 2}; // перечень скоростей считающимися слишком маленькими
        while (Arrays.asList(TooLowSpeed).contains(random)){
            random = (int) (Math.random() * ((max - min) + 1)) + min;
        }
        return random;
    }


    /**
     * Метод генерирующий случайную скорость по оси Y
     * в диапазоне от -6 до 0, исключая слишком маленькое значение
     */
    public int getRandomSpeedVY(){
        int min = -6;
        int max = 0;
        int random = 0;
        Integer[] TooLowSpeed = {-2, -1, 0, 1, 2}; // перечень скоростей считающимися слишком маленькими
        while (Arrays.asList(TooLowSpeed).contains(random)){
            random = (int) (Math.random() * ((max - min) + 1)) + min;
        }
        return random;
    }


    /**
     * Метод генерирующий случайное значение из заданного диапазона (max, min)
     */
    public int getRandomValue(int max, int min){
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }


    /**
     * Лист содержащий HEX-коды различных цветов для присвоения их шара
     */
    public List<String> ballColorsList = Arrays.asList(
            "0xEB5757",   // 1
            "0xBB6BD9",   // 2
            "0xF2C94C",   // 3
            "0x6FCF97"    // 4
    );


    /**
     * Метод случайно присваивающий цвет шару
     */
    public void updateColor(){
        if (getRandomValue(4, 0) == 1){  // цвет переприсваивается, только если случайное число = 1
            int randomColorIndex = getRandomValue(4, 1) - 1; // Генерация индекса случайного цвета
            this.color = Color.decode(ballColorsList.get(randomColorIndex));
        }
    }
}