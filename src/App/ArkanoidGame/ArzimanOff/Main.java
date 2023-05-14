package App.ArkanoidGame.ArzimanOff;

// Перечень импортированных библиотек
import javax.swing.*;

/**
 * Класс Main, который служит точкой входа в приложение.
 */
public class Main {

    /**
     * Главный метод, который запускает приложение.
     *
     * @param args аргументы командной строки
     */
    public static void main(String ... args) {

        // Метод invokeLater гарантирует, что GUI будет работать в потоке обработки событий (Event Dispatch Thread - EDT),
        // в котором должны выполняться все операции с пользовательским интерфейсом в Swing.
        SwingUtilities.invokeLater(() -> {

            // Создаем новый объект GameFrame, который представляет главное окно приложения
            JFrame frame = new GameFrame();

            // Устанавливаем операцию, которая будет выполняться по умолчанию при закрытии окна.
            // В данном случае приложение полностью завершится при закрытии окна.
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Делаем окно видимым. По умолчанию окна в Swing невидимы.
            frame.setVisible(true);
        });
    }
}
