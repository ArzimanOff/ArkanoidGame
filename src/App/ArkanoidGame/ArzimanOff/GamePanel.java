package App.ArkanoidGame.ArzimanOff;

// Перечень импортированных библиотек
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;


/**
 * Класс игровой ЗОНЫ, наследующийся от JPanel,
 * и имплементирующий интерфейсы прослушки событий
 * @autor Гюльахмед Арзиманов (ArzimanOff)
 */
class GamePanel extends JPanel implements ActionListener, KeyListener {
    private final int width, height;                // Размеры игровой зоны
    static int score = 0;                           // Счётчик текущего кол-ва баллов
    private final static int BALL_RADIUS = 10;      // Константа хранящая радиус шара
    private final static int PLATFORM_WIDTH = 80;   // Константа хранящая ширину нижней платформы
    private final static int PLATFORM_HEIGHT = 5;   // Константа хранящая высоту нижней платформы
    private final static int SQUARE_SIZE = 40;      // Константа хранящая размер квадрата коробки
    private String gameResult;                      // Строка хранящая результат игры (для уведомления в конце игры)
    private Timer timer;                            // Создание экземпляра таймера для периодичности отрисовки игровой зоны
    private Ball ball;                              // Создание экземпляра шара
    private Platform platform;                      // Создание экземпляра нижней платформы
    private ArrayList<Square> squares;              // Создание списка хранящий экземпляры коробок из игрового поля


    /**
     * Конструктор класса игровой зоны
     */
    public GamePanel(int width, int height) {
        score = 0;                                   // Обнуление счётчика очков
        this.width = width;
        this.height = height;

        setBackground(Color.decode("0x1D1D1D")); // Присвоение кастомного цвета для фона игровой зоны
        setFocusable(true);                          // Фокусировка на текущей игровой зоне
        addKeyListener(this);                      // Добавление прослушки клавиатуры для этой игровой зоны
        initGameObjects();                           // Запрос на инициализацию необходимых объектов для игры

        timer = new Timer(10, this);    // Инициализация таймера с периодом в 10 мс.
        timer.start();                               // Начало отсчёта времени
    }


    /**
     * Метод для инициализации всех игровых объектов
     */
    private void initGameObjects() {

        ball = new Ball(BALL_RADIUS, Color.decode("0xEB5757")); // Инициализация шара через конструктор

        platform = new Platform(160, 530,
                PLATFORM_WIDTH,
                PLATFORM_HEIGHT,
                Color.decode("0x2F80ED")); // Инициализация платформы через конструктор

        squares = new ArrayList<>();           // Инициализация списка коробок

        // Генерация условного поля коробок
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                // проходясь по двумерному массиву, размера 10 x 5 для каждой ячейки генерируется число (1 либо 0)
                int random = (int) ((Math.random() * (1 + 1)) + 0);
                if (random == 1){
                    // если сгенерированное число = 1, в ячейку и в список добавляется коробка
                    squares.add(new Square(i * (SQUARE_SIZE), j * (SQUARE_SIZE), SQUARE_SIZE));
                }
            }
        }
    }


    /**
     * Переопределение метода для отрисовки всех компонентов игры
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ball.draw(g);       // Отрисовка шара
        platform.draw(g);   // Отрисовка нижней платформы

        // Отрисовка каждой коробки из списка
        for (Square square : squares) {
            square.setColor();
            square.draw((Graphics2D) g);
        }
        if (gameResult != null){
            // Отрисовка сообщения об окончании игры, если переменная результата не null
            gameOver((Graphics2D) g, gameResult);
        }
    }

    private void update() {
        ball.move();    // Запрос не передвижение шара
        ball.checkCollisionWithWalls(getWidth(), getHeight());  // Запрос на проверку и обработку столкновения мяча со стенами
        ball.checkCollisionWithPlatform(platform);  // Запрос на проверку и обработку столкновения мяча с платформой

        // Запрос на проверку и обработку столкновения мяча с коробками, удаление из списка, в случае, если вес коробки = 0
        squares.removeIf(square -> ball.checkCollisionWithSquare(square) && square.getHitCount() == 0);

        if (ball.getY() > platform.getY() + 5) {    // Условие проверки, того что шар опустился ниже уровня платформы (поражение)
            gameResult = "Поражение";               // Завершение игры (поражение)
        } else if (squares.isEmpty()) {             // Условие проверки, того, что поле коробок пустое (победа)
            gameResult = "Победа!";                 // Завершение игры (победа)
        }
    }


    /**
     * Переопределение метода выполняющего
     * любые изменения в игровой зоне
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        update();   // Запрос на программное изменение компонентов
        repaint();  // Запрос на отрисовку измененных компонентов
    }


    /**
     * Переопределение метода прослушки
     * ПЕРВОНАЧАЛЬНОГО НАЖАТИЯ клавиш
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // не используется, но необходим для работы
    }


    /**
     * Переопределение метода прослушки
     * ЗАЖАТОГО СОСТОЯНИЯ клавиш
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            platform.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            platform.moveRight();
        }
    }


    /**
     * Переопределение метода прослушки
     * ЗАЖАТОГО СОСТОЯНИЯ клавиш
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // не используется, но необходим для работы
    }


    /**
     * Метод обрабатывающий завершение игры
     */
    public void gameOver(Graphics2D g, String gameResult){
        int textX, textY; // Координаты начала написания текста в уведомлении об окончании игры

        // Условие выбора цвета для текста, в зависимости от исхода игры
        if (Objects.equals(gameResult, "Поражение")){
            g.setColor(Color.RED);    // Поражение - красный
        }else{
            g.setColor(Color.GREEN);  // Победа - зелёный
        }

        // Настройки параметров шрифта для текста результата
        g.setFont(new Font("Arial", Font.BOLD, 24));
        FontMetrics metrics = g.getFontMetrics();
        String text = gameResult;
        textX = (this.width - metrics.stringWidth(text)) / 2;
        textY = 270 - (metrics.getHeight() / 2) + metrics.getAscent();
        g.drawString(text, textX, textY);

        // Настройки параметров шрифта для текста объявления кол-ва баллов
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        text = "Набранное количество баллов:";
        metrics = g.getFontMetrics();
        textX = (this.width - metrics.stringWidth(text)) / 2;
        textY = 300 - (metrics.getHeight() / 2) + metrics.getAscent();
        g.drawString(text, textX, textY);

        // Настройки параметров шрифта кол-ва баллов
        g.setFont(new Font("Arial", Font.BOLD, 80));
        text = Integer.toString(score);
        metrics = g.getFontMetrics();
        textX = (this.width - metrics.stringWidth(text)) / 2;
        textY = 370 - (metrics.getHeight() / 2) + metrics.getAscent();
        g.drawString(text, textX, textY);

        timer.stop(); // Остановка времени (остановка игры)
    }
}