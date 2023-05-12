package App.ArkanoidGame.ArzimanOff;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class GamePanel extends JPanel implements ActionListener, KeyListener {
    static int score = 0;
    private final int BALL_RADIUS = 10;
    private final int PLATFORM_WIDTH = 80;
    private final int PLATFORM_HEIGHT = 5;
    private final int SQUARE_SIZE = 40;
    private Timer timer;
    private Ball ball;
    private Platform platform;
    private ArrayList<Square> squares;

    public GamePanel() {
        setBackground(Color.decode("0x1D1D1D"));
        setFocusable(true);
        addKeyListener(this);
        initGameObjects();

        timer = new Timer(10, this);
        timer.start();
    }

    private void initGameObjects() {

        ball = new Ball(BALL_RADIUS, Color.decode("0xEB5757"));

        platform = new Platform(160, 550,
                PLATFORM_WIDTH,
                PLATFORM_HEIGHT,
                Color.decode("0x2F80ED"));

        squares = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                int random = (int) ((Math.random() * (1 + 1)) + 0);
                if (random == 1){
                    squares.add(new Square(i * (SQUARE_SIZE), j * (SQUARE_SIZE), SQUARE_SIZE));
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ball.draw(g);
        platform.draw(g);

        for (Square square : squares) {
            square.setColor();
            square.draw((Graphics2D) g);
        }
    }

    private void update() {
        ball.move();
        ball.checkCollisionWithWalls(getWidth(), getHeight());
        ball.checkCollisionWithPlatform(platform);

        squares.removeIf(square -> ball.checkCollisionWithSquare(square) && square.getHitCount() == 0);
        //squares.removeIf(square -> square.getHitCount() == 0);
        if (ball.getY() > platform.getY() + 5) {
            timer.stop();
            JOptionPane.showMessageDialog(this, "Поражение! Набрано баллов:" + score, "Игра окончена!", JOptionPane.INFORMATION_MESSAGE);
        } else if (squares.isEmpty()) {
            timer.stop();
            JOptionPane.showMessageDialog(this, "Победа! Набрано баллов:" + score, "Поздравляем!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            platform.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            platform.moveRight();
        }
    }



    @Override
    public void keyReleased(KeyEvent e) {
    }
}