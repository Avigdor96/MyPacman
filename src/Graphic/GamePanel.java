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
    final int size = 15;
    Pinky pinky = new Pinky();
    public Queue<Ghost> ghostQueueInside = new LinkedList<>();
    public ArrayList<Ghost> ghostListInside = new ArrayList<>();
    public ArrayList<Ghost> ghostListOutSide = new ArrayList<>();
    Pacman pacman = new Pacman(size);
    MapLevel1 mapLevel1 = new MapLevel1();
    GeneralElement[][] myMap = mapLevel1.ElementMap();
    public KeyControl keyControl = new KeyControl();
    Thread threadGame;

    public GamePanel() {
        this.addKeyListener(keyControl);
        ghostListOutSide.add(pinky);
        Ghost.createGhostInside(ghostListInside);
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
        drawGhostOutside(g);
        g.drawImage(pacman.getImage(), pacman.getX(), pacman.getY(), size, size, this);
        Toolkit.getDefaultToolkit().sync();
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
            for (int i = 0; i < ghostListInside.size(); i++) {
                g.drawImage(ghostListInside.get(i).getImage(), ghostListInside.get(i).getX(), ghostListInside.get(i).getY(), size, size, this);
            }
        }

    public void drawGhostOutside(Graphics g){
        for (int i = 0; i < ghostListOutSide.size(); i++) {
            g.drawImage(ghostListOutSide.get(i).getImage(), ghostListOutSide.get(i).getX(), ghostListOutSide.get(i).getY(), size, size, this);
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

    // Brings the ghosts to start point when Pacman fails
    public void backHome() {
        ArrayList<Ghost> toRemove = new ArrayList<>();
            for (Ghost ghost : ghostListOutSide) {
                ghost.startPoint();
                if (!(ghost instanceof Pinky)) {
                    ghostQueueInside.add(ghost);
                    ghostListInside.add(ghost);
                    toRemove.add(ghost);
                }
            }
            ghostListOutSide.removeAll(toRemove);
    }

    //Moves the ghosts outside randomly
    public void randomAll(){
            for (int i = 0; i < ghostListOutSide.size(); i++) {
                ghostListOutSide.get(i).randomMovement(myMap);
            }
        }
    public void becomeFood() {
        for (int i = 0; i < ghostListInside.size(); i++) {
            ghostListInside.get(i).setImage(new ImageIcon("src/Pictures/GhostEatable.jpg"));
        }
        for (int i = 0; i < ghostListOutSide.size(); i++) {
            ghostListOutSide.get(i).setImage(new ImageIcon("src/Pictures/GhostEatable.jpg"));
        }
    }
    public void becomeNoFood() {
        for (int i = 0; i < ghostListInside.size(); i++) {
            ghostListInside.get(i).setImage(new ImageIcon("src/Pictures/Redy.jpg"));
        }
        for (int i = 0; i < ghostListOutSide.size(); i++) {
            ghostListOutSide.get(i).setImage(new ImageIcon("src/Pictures/Redy.jpg"));
        }
    }
    public boolean onSamePosition(Player p, Player p1){
        return p.getX() == p1.getX() && p.getY() == p1.getY();
    }

    public void meetWithGhost() {
        for (int i = 0; i < ghostListOutSide.size(); i++) {
            if (onSamePosition(pacman, ghostListOutSide.get(i))) {
                if (pacman.ateBigCoin) {
                    ghostListOutSide.get(i).startPoint();
                    ghostListOutSide.get(i).setImage(ghostListOutSide.get(i).srcImage);

                } else {
                    pacman.pacmanCaught(this);
                }
            }
        }
    }
//        new Timer(pacman.bigCoinTime, e->{
//            for (int i = 0; i < ghostListInside.size(); i++) {
//                ghostListInside.get(i).setImage(new ImageIcon("src/Pictures/GhostEatable.jpg"));
//            }
//            for (int i = 0; i < ghostListOutSide.size(); i++) {
//                ghostListOutSide.get(i).setImage(new ImageIcon("src/Pictures/GhostEatable.jpg"));
//            }
//        }).start();

    @Override
    public void run() {
        while (pacman.getLives() > 0) {
            movePacman();
            meetWithGhost();
            repaint();
            if (pacman.ateBigCoin){
                becomeFood();
            }
            else {
                becomeNoFood();
            }
            randomAll();
            if (pacman.ateQuarter() &&  ghostQueueInside.peek() != null){
                ghostListOutSide.add(ghostQueueInside.peek());
                ghostListInside.remove(ghostQueueInside.peek());
                Ghost ghost = ghostQueueInside.poll();
                ghost.goOutGeneral();
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
