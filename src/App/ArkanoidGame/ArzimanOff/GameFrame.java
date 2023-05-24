package App.ArkanoidGame.ArzimanOff;

// Перечень импортированных библиотек
import javax.swing.*;
import java.awt.*;


/**
 * Класс игрового ОКНА, наследующийся от JFrame
 * @autor Гюльахмед Арзиманов (ArzimanOff)
 */
class GameFrame extends JFrame {
    private final int FRAME_WIDTH  = 412;     // Ширина игрового окна
    private final int FRAME_HEIGHT  = 600;    // Высота игрового окна
    private GamePanel gamePanel;              // Создание экземпляра класса Игровой зоны


    /**
     * Конструктор класса игрового окна
     */
    public GameFrame() {
        setTitle("Arkanoid Game");             // Некий заголовок (title) игрового окна
        setSize(FRAME_WIDTH , FRAME_HEIGHT);   // Присвоение размеров игрового окна
        setResizable(false);                   // Запрет на изменение размеров вручную
        setLocationRelativeTo(null);           // Центрирование изначального положения окна на экране пользователя

        gamePanel = new GamePanel(FRAME_WIDTH , FRAME_HEIGHT); // Создание экземпляра игровой ЗОНЫ внутри игрового ОКНА
        add(gamePanel);                        // Добавление игровой зоны в игровое окно
        addRestartButton();                    // Метод добавляющий кнопку для перезапуска игры
    }


    /**
     * Метод для создания кнопки перезапуска игры
     */
    private void addRestartButton(){
        JButton restartButton = new JButton("Начать заново");   // Создание дефолтной кнопки
        restartButton.setBackground(Color.decode("0x1D1D1D"));  // Присвоение кастомного цвета кнопке
        restartButton.setBorderPainted(false);                      // Отмена отрисовки рамок кнопки
        restartButton.addActionListener(e -> restartGame());        // Прослушка на нажатия для кнопки с последующим вызовом функции рестарта
        add(restartButton, BorderLayout.SOUTH);                     // Добавление кнопки в окне (в нижней части)
    }


    /**
     * Функция перезапуска игры
     */
    private void restartGame() {
        remove(gamePanel);                                    // Удаление текущей игровой зоны
        gamePanel = new GamePanel(FRAME_WIDTH, FRAME_HEIGHT); // Создание новой игровой зоны

        add(gamePanel, BorderLayout.CENTER);                  // Добавление игровой зоны по центру в текущее окно
        gamePanel.requestFocusInWindow();                     // Изменение фокусировки на текущую игровую зону
        revalidate();                                         // Пересчёт и актуализация компонентов приложения
        repaint();                                            // Перерисовка компонентов игры
    }
}