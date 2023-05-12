package App.ArkanoidGame.ArzimanOff;
import javax.swing.*;

public class Main {
    public static void main(String ... args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new GameFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}