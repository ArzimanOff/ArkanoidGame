package App.ArkanoidGame.ArzimanOff;

import javax.swing.*;

class GameFrame extends JFrame {
    public GameFrame() {
        setTitle("Arkanoid Game");
        setSize(413, 600);
        setResizable(false);
        setLocationRelativeTo(null);

        GamePanel gamePanel = new GamePanel();
        add(gamePanel);
    }
}