package Graphic;

import Maps.MapLevel1;
import Objects.GeneralElement;
import Players.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GamePanel extends JPanel implements Runnable {
    final int size = 25;
    Reddish reddish = new Reddish(14, 13, size, "src/Pictures/Redy.jpg");
    Purplish purplish = new Purplish(15, 13, size, "src/Pictures/Purplish.jpeg");
    Bluish bluish = new Bluish(12, 13, size, "src/Pictures/Bluish.jpg");
    Pinky pinky = new Pinky(13, 13, size, "src/Pictures/gifmaker_me (1).gif");
    public Queue<Ghost> ghostQueueInside = new LinkedList<Ghost>();
    public ArrayList<Ghost> ghostOutSide = new ArrayList<>();
    Pacman pacman = new Pacman(size);
    MapLevel1 mapLevel1 = new MapLevel1();
    GeneralElement[][] myMap = mapLevel1.ElementMap();
    public KeyControl keyControl = new KeyControl();
    boolean runGame = true;
    Thread threadGame;

    public GamePanel() {
        this.addKeyListener(keyControl);
        ghostQueueInside.add(pinky);
        ghostQueueInside.add(bluish);
        ghostQueueInside.add(reddish);
        ghostQueueInside.add(purplish);
        this.setFocusable(true);
        threadGame = new Thread(this);
        threadGame.start();
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
        g.drawImage(reddish.getImage(), reddish.getX(), reddish.getY(), size, size, this);
        g.drawImage(pinky.getImage(), pinky.getX(), pinky.getY(), size, size, this);
        g.drawImage(purplish.getImage(), purplish.getX(), purplish.getY(), size, size, this);
        g.drawImage(bluish.getImage(), bluish.getX(), bluish.getY(), size, size, this);
        Toolkit.getDefaultToolkit().sync();
    }


    public void movePacman() {
        if (keyControl.desiredDirection.equals("UP")) {
            if (pacman.canMoveUp(myMap)) {
                keyControl.currentDirection = "UP";
            }
        } else if (keyControl.desiredDirection.equals("DOWN") && pacman.canMoveDown(myMap)) {
            keyControl.currentDirection = "DOWN";
        } else if (keyControl.desiredDirection.equals("RIGHT") && pacman.canMoveRight(myMap)) {
            keyControl.currentDirection = "RIGHT";
        } else if (keyControl.desiredDirection.equals("LEFT") && pacman.canMoveLeft(myMap)) {
            keyControl.currentDirection = "LEFT";
        }

        switch (keyControl.currentDirection) {
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

    public void randomAll(){
            for (int i = 0; i < ghostOutSide.size(); i++) {
                ghostOutSide.get(i).randomMovement(myMap);
            }
        }

    @Override
    public void run() {
        while (runGame) {
            movePacman();
            repaint();
            randomAll();
            pacman.MouthControl();
            if (pacman.ateQuarter()){
                System.out.println("Ate quarter");
                ghostOutSide.add(ghostQueueInside.peek());
                Ghost ghost = ghostQueueInside.poll();
                ghost.goOutGeneral();
                System.out.println(ghost.getClass());
                System.out.println("X: " + ghost.getX());
                System.out.println("Y: " + ghost.getY());
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
