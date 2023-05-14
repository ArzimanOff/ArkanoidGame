package App.ArkanoidGame.ArzimanOff;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;

class GamePanel extends JPanel implements ActionListener, KeyListener {
    private int width;
    private int height;
    static int score = 0;
    private final int BALL_RADIUS = 10;
    private final int PLATFORM_WIDTH = 80;
    private final int PLATFORM_HEIGHT = 5;
    private final int SQUARE_SIZE = 40;
    private String gameResult;
    private Timer timer;
    private Ball ball;
    private Platform platform;
    private ArrayList<Square> squares;

    public GamePanel(int width, int height) {
        this.width = width;
        this.height = height;

        setBackground(Color.decode("0x1D1D1D"));
        setFocusable(true);
        addKeyListener(this);
        initGameObjects();

        timer = new Timer(10, this);
        timer.start();
    }

    private void initGameObjects() {

        ball = new Ball(BALL_RADIUS, Color.decode("0xEB5757"));

        platform = new Platform(160, 530,
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
        if (gameResult != null){
            gameOver((Graphics2D) g, gameResult);
        }
    }

    private void update() {
        ball.move();
        ball.checkCollisionWithWalls(getWidth(), getHeight());
        ball.checkCollisionWithPlatform(platform);

        squares.removeIf(square -> ball.checkCollisionWithSquare(square) && square.getHitCount() == 0);
        //squares.removeIf(square -> square.getHitCount() == 0);
        if (ball.getY() > platform.getY() + 5) {
            gameResult = "Поражение";
        } else if (squares.isEmpty()) {
            gameResult = "Победа!";
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

    public void gameOver(Graphics2D g, String gameResult){
        int hitTextX;
        int hitTextY;


        if (Objects.equals(gameResult, "Поражение")){
            g.setColor(Color.RED);
        }else{
            g.setColor(Color.GREEN);
        }

        g.setFont(new Font("Arial", Font.BOLD, 24));
        FontMetrics metrics = g.getFontMetrics();
        String text = gameResult;
        hitTextX = (this.width - metrics.stringWidth(text)) / 2;
        hitTextY = 270 - (metrics.getHeight() / 2) + metrics.getAscent();
        g.drawString(text, hitTextX, hitTextY);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        text = "Набранное количество баллов:";
        metrics = g.getFontMetrics();
        hitTextX = (this.width - metrics.stringWidth(text)) / 2;
        hitTextY = 300 - (metrics.getHeight() / 2) + metrics.getAscent();
        g.drawString(text, hitTextX, hitTextY);

        g.setFont(new Font("Arial", Font.BOLD, 80));
        text = Integer.toString(score);
        metrics = g.getFontMetrics();
        hitTextX = (this.width - metrics.stringWidth(text)) / 2;
        hitTextY = 370 - (metrics.getHeight() / 2) + metrics.getAscent();
        g.drawString(text, hitTextX, hitTextY);

        timer.stop();
    }

}