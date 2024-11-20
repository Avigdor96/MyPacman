package Graphic;

import Maps.MapLevel1;
import Objects.Channel;
import Objects.Coin;
import Objects.Empty;
import Objects.GeneralElement;
import Players.*;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int size = 25;
    int speed = 50;
    Reddy reddy = new Reddy(14, 13, size);
    Purpaley purpaley = new Purpaley(12, 13, size);
    Bluely bluely = new Bluely(11, 13, size);
    Pacman pacman = new Pacman(size);
    MapLevel1 mapLevel1 = new MapLevel1();
    GeneralElement[][] myMap = mapLevel1.ElementMap();
    Pinky pinky = new Pinky(size, myMap);
    public KeyControl keyControl = new KeyControl();
    boolean runGame = true;
    Thread thread;

    public GamePanel() {
        this.addKeyListener(keyControl);
        this.setFocusable(true);
        thread = new Thread(this);
        thread.start();
    }

    public JPanel top() {
        JPanel top = new JPanel();
        Font font = new Font("Ariel", Font.BOLD, 40);
        JLabel topLabel = new JLabel("Score: " + pacman.getScore());
        topLabel.setForeground(Color.WHITE);
        topLabel.setFont(font);
        top.add(topLabel, BorderLayout.BEFORE_LINE_BEGINS);
        top.setBackground(Color.BLACK);
        top.setPreferredSize(new Dimension(0, 50));
        top.setVisible(true);
        return top;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < myMap.length; i++) {
            for (int j = 0; j < myMap[i].length; j++) {
                g.drawImage(myMap[i][j].getImage(), myMap[i][j].getPoint().x, myMap[i][j].getPoint().y, size, size, this);
            }
        }
        g.drawImage(pacman.getImage(), pacman.getX(), pacman.getY(), size, size, this);
        g.drawImage(reddy.getImage(), reddy.getX(), reddy.getY(), size, size, this);
        g.drawImage(pinky.getImage(), pinky.getX(), pinky.getY(), size, size, this);
        g.drawImage(purpaley.getImage(), purpaley.getX(), purpaley.getY(), size, size, this);
        g.drawImage(bluely.getImage(), bluely.getX(), bluely.getY(), size, size, this);
        Toolkit.getDefaultToolkit().sync();
    }


    public void movePacman() {
        if (keyControl.desiredDirection.equals("UP")) {
            if (pacman.canMoveUp(myMap)) {
                keyControl.currentDirection = "UP";
            }
        }
        else if (keyControl.desiredDirection.equals("DOWN") && pacman.canMoveDown(myMap)) {
            keyControl.currentDirection = "DOWN";
        }
        else if (keyControl.desiredDirection.equals("RIGHT") && pacman.canMoveRight(myMap)) {
            keyControl.currentDirection = "RIGHT";
        } else if (keyControl.desiredDirection.equals("LEFT") && pacman.canMoveLeft(myMap)) {
            keyControl.currentDirection = "LEFT";
        }

        switch (keyControl.currentDirection){
            case "UP":
                pacman.changeMonthUp();
                pacman.upManager(myMap);
                break;
            case "DOWN":
                pacman.changeMonthDown();
                pacman.downManager(myMap);
                break;
            case "RIGHT":
                pacman.changeMonthRight();
                pacman.rightManager(myMap);
                break;
            case "LEFT":
                pacman.changeMouthLeft();
                pacman.leftManager(myMap);
                break;

        }
    }

    @Override
    public void run() {
        while (runGame) {
            movePacman();
            pinky.move(myMap);
            repaint();
            pacman.MouthControl();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
