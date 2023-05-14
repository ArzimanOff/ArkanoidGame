package App.ArkanoidGame.ArzimanOff;

import javax.swing.*;
import java.awt.*;

class GameFrame extends JFrame {
    private final int frameWidth = 412;
    private final int frameHeight = 600;
    private GamePanel gamePanel;

    public GameFrame() {
        setTitle("Arkanoid Game");
        setSize(frameWidth, frameHeight);
        setResizable(false);
        setLocationRelativeTo(null);

        gamePanel = new GamePanel(frameWidth, frameHeight);
        add(gamePanel);
        addRestartButton();
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
    public int getFrameWidth() {
        return frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    private void addRestartButton(){
        JButton restartButton = new JButton("Начать заново");
        restartButton.setBackground(Color.decode("0x1D1D1D"));
        restartButton.setBorderPainted(false);
        restartButton.addActionListener(e -> restartGame());
        add(restartButton, BorderLayout.SOUTH);
    }

    private void restartGame() {
        gamePanel.remove(this);
        remove(gamePanel);
        gamePanel = new GamePanel(frameWidth, frameHeight);


        add(gamePanel, BorderLayout.CENTER);
        gamePanel.requestFocusInWindow();
        revalidate();
        repaint();
    }
}