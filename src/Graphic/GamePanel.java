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
    Pinky pinky = new Pinky();
    public Queue<Ghost> ghostQueueInside = new LinkedList<>();
    public ArrayList<Ghost> ghostListInside = new ArrayList<>();
    public ArrayList<Ghost> ghostListOutSide = new ArrayList<>();
    Pacman pacman = new Pacman(size);
    MapLevel1 mapLevel1 = new MapLevel1();
    GeneralElement[][] myMap = mapLevel1.ElementMap();
    public KeyControl keyControl = new KeyControl();
    boolean runGame = true;
    Thread threadGame;

    public GamePanel() {
        this.addKeyListener(keyControl);
        ghostListOutSide.add(pinky);
        createGhostInside();
        addInsideQueue();
        this.setFocusable(true);
        threadGame = new Thread(this);
        threadGame.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < myMap.length; i++) {
            for (int j = 0; j < myMap[i].length; j++) {
                g.drawImage(myMap[i][j].getImage(), myMap[i][j].getX(), myMap[i][j].getY(), size, size, this);
            }
        }
        drawListInside(g);
        g.drawImage(pacman.getImage(), pacman.getX(), pacman.getY(), size, size, this);
        g.drawImage(pinky.getImage(), pinky.getX(), pinky.getY(), size, size, this);
        drawGhostOutside(g);
        Toolkit.getDefaultToolkit().sync();
    }

//Create ghosts and adding to list inside
    public void createGhostInside(){
        Reddish reddish1 = new Reddish();
        Bluish bluish1 = new Bluish();
        Purplish purplish1 = new Purplish();
        ghostListInside.add(reddish1);
        ghostListInside.add(bluish1);
        ghostListInside.add(purplish1);
    }

//Adding to queue inside from list inside
    public void addInsideQueue() {
        if (ghostListInside != null) {
            for (Ghost ghost : ghostListInside) {
                ghostQueueInside.add(ghost);
            }
        }
    }

    public void drawListInside(Graphics g){
        if (ghostListInside.size() != 0) {
            for (Ghost ghost : ghostListInside) {
                g.drawImage(ghost.getImage(), ghost.getX(), ghost.getY(), size, size, this);
            }
        }
    }

    public void drawGhostOutside(Graphics g){
        for (Ghost ghost : ghostListOutSide) {
            g.drawImage(ghost.getImage(), ghost.getX(), ghost.getY(), size, size, this);
        }
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
                pacman.updateCoinsEaten(pacman.eat(pacman.getLocationX(), pacman.getNextUpLoc(), myMap));
                pacman.setImage(new ImageIcon("src/Pictures/PacmanUpGif.gif"));
                pacman.upManager(myMap);
                break;
            case "DOWN":
                pacman.updateCoinsEaten(pacman.eat(pacman.getLocationX(), pacman.getNextDownLoc(), myMap));
                pacman.setImage(new ImageIcon("src/Pictures/PacmanDownGif.gif"));
                pacman.downManager(myMap);
                break;
            case "RIGHT":
                pacman.updateCoinsEaten(pacman.eat(pacman.getNextRightLoc(), pacman.getLocationY(), myMap));
                pacman.setImage(new ImageIcon("src/Pictures/PacmanRightGif.gif"));
                pacman.rightManager(myMap);
                break;
            case "LEFT":
                pacman.updateCoinsEaten(pacman.eat(pacman.getNextLeftLoc(), pacman.getLocationY(), myMap));
                pacman.setImage(new ImageIcon("src/Pictures/PacmanLeftGif.gif"));
                pacman.leftManager(myMap);
                break;

        }
    }

    public void randomAll(){
            for (int i = 0; i < ghostListOutSide.size(); i++) {
                ghostListOutSide.get(i).randomMovement(myMap);
            }
        }

    @Override
    public void run() {
        while (pacman.getLives() > 0) {
            if (pacman.pacmanDeath(ghostListOutSide)){
                pacman.deathManage();
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
            movePacman();
            repaint();
            randomAll();
            if (pacman.ateQuarter() &&  ghostQueueInside.peek() != null){
                ghostListOutSide.add(ghostQueueInside.peek());
                ghostListInside.remove(ghostQueueInside.peek());
                Ghost ghost = ghostQueueInside.poll();
                ghost.goOutGeneral();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
