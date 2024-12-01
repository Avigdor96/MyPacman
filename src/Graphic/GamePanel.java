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
    final int size = 15; // final size of all elements
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

    public void drawListInside(Graphics g) {
        for (int i = 0; i < ghostListInside.size(); i++) {
            g.drawImage(ghostListInside.get(i).getImage(), ghostListInside.get(i).getX(), ghostListInside.get(i).getY(), size, size, this);
        }
    }

    public void drawGhostOutside(Graphics g) {
        for (int i = 0; i < ghostListOutSide.size(); i++) {
            g.drawImage(ghostListOutSide.get(i).getImage(), ghostListOutSide.get(i).getX(), ghostListOutSide.get(i).getY(), size, size, this);
        }
    }


    public void movePacman() {
        if (keyControl.desiredDirection.equals("UP")) {
            if (pacman.canMoveAndUpdate(myMap, 0, -5)) {
                keyControl.currentDirection = "UP";
            }
        } else if (keyControl.desiredDirection.equals("DOWN") && pacman.canMoveAndUpdate(myMap, 0, 5)) {
            keyControl.currentDirection = "DOWN";
        } else if (keyControl.desiredDirection.equals("RIGHT") && pacman.canMoveAndUpdate(myMap, 5, 0)) {
            keyControl.currentDirection = "RIGHT";
        } else if (keyControl.desiredDirection.equals("LEFT") && pacman.canMoveAndUpdate(myMap, -5, 0)) {
            keyControl.currentDirection = "LEFT";
        }
        switch (keyControl.currentDirection) {
            case "UP":
                pacman.updateCoinsEaten(pacman.eat(pacman.getX() / size, (pacman.getY() - 5) / size, myMap));
                pacman.setImage(new ImageIcon("src/Pictures/PacmanUpGif.gif"));
                pacman.updateAfterMove(0, -5, myMap);
                //pacman.upManager(myMap);
                break;
            case "DOWN":
                pacman.updateCoinsEaten(pacman.eat(pacman.getX() / size, (pacman.getY() + 5) / size, myMap));
                pacman.setImage(new ImageIcon("src/Pictures/PacmanDownGif.gif"));
                pacman.updateAfterMove(0, 5, myMap);
                //pacman.downManager(myMap);
                break;
            case "RIGHT":
                pacman.updateCoinsEaten(pacman.eat((pacman.getX() + 5) / size, pacman.getY() / size, myMap));
                pacman.setImage(new ImageIcon("src/Pictures/PacmanRightGif.gif"));
                pacman.channelRightManage((pacman.getX() + 5) / size, pacman.getY() / size, myMap);
                pacman.updateAfterMove(5, 0, myMap);

                //pacman.rightManager(myMap);
                break;
            case "LEFT":
                pacman.updateCoinsEaten(pacman.eat((pacman.getX() - 5) / size, pacman.getY() / size, myMap));
                pacman.setImage(new ImageIcon("src/Pictures/PacmanLeftGif.gif"));
                pacman.updateAfterMove(-5, 0, myMap);
                pacman.channelLeftManage((pacman.getX() - 5) / size, pacman.getY() / size, myMap);

                //pacman.leftManager(myMap);
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
    public void randomAll() {
        for (int i = 0; i < ghostListOutSide.size(); i++) {
            ghostListOutSide.get(i).randomMovement(myMap);
        }
    }

    //Makes ghosts to eatable and sets their image to blue
    public void becomeFood() {
            for (int i = 0; i < ghostListInside.size(); i++) {
                ghostListInside.get(i).setImage(ghostListInside.get(i).eatableImage);
            }
            for (int i = 0; i < ghostListOutSide.size(); i++) {
                ghostListOutSide.get(i).setImage(ghostListOutSide.get(i).eatableImage);
            }
        new Timer(pacman.bigCoinTime, e -> {
            becomeNoFood();
        }).start();
        pacman.setAteBigCoin(false);
    }

    //Returns ghost to source their image
    public void becomeNoFood() {
        for (int i = 0; i < ghostListInside.size(); i++) {
            ghostListInside.get(i).backToSrc();
        }
        for (int i = 0; i < ghostListOutSide.size(); i++) {
            ghostListOutSide.get(i).backToSrc();
        }
    }

    //Checks if two players are meet
    public boolean onSamePosition(Player p, Player p1) {
        Rectangle rectangle1 = new Rectangle(p.getX(), p.getY(), size, size);
        Rectangle rectangle2 = new Rectangle(p1.getX(), p1.getY(), size ,size);
        return rectangle1.intersects(rectangle2);
    }

    public void meetWithGhost() {
        for (int i = 0; i < ghostListOutSide.size(); i++) {
            Ghost ghost = ghostListOutSide.get(i);
            if (onSamePosition(pacman, ghost)) {
                System.out.println(pacman.ateBigCoin);
                if (pacman.ateBigCoin) {
//                    ghostListOutSide.get(i).backToSrc();
//                    ghostListOutSide.get(i).startPoint();
                    ghost.startPoint();
                    ghost.backToSrc();
                    pacman.addScore(200);
                    //ghostListOutSide.get(i).setImage(ghostListOutSide.get(i).getSrcImage());

                }else {
                    pacman.pacmanCaught(this);
                }
            }
        }
    }

    @Override
    public void run() {
        while (pacman.getLives() > 0) {
            movePacman();
            meetWithGhost();
            randomAll();
            repaint();
            if (pacman.ateBigCoin){
                becomeFood();
            }
            if (pacman.ateQuarter() &&  ghostQueueInside.peek() != null){
                ghostListOutSide.add(ghostQueueInside.peek());
                ghostListInside.remove(ghostQueueInside.peek());
                Ghost ghost = ghostQueueInside.poll();
                ghost.goOutGeneral();
            }
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
