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
    final private int size = 15; // final size of all elements
    private Pinky pinky = new Pinky();
    ArrayList<Ghost> toGoAfter3Sec = new ArrayList<>();
    private Queue<Ghost> ghostQueueInside = new LinkedList<>();
    private ArrayList<Ghost> ghostListInside = new ArrayList<>();
    private ArrayList<Ghost> ghostListOutSide = new ArrayList<>();
    private ArrayList<Ghost> allGhosts = new ArrayList<>();
    private Pacman pacman = new Pacman(size);
    private static MapLevel1 mapLevel1 = new MapLevel1();
    private static GeneralElement[][] myMap = mapLevel1.ElementMap();
    private static KeyControl keyControl = new KeyControl();
    Thread threadGame;

    public Pacman getPacman() {
        return pacman;
    }

    public GamePanel() {
        this.addKeyListener(keyControl);
        ghostListOutSide.add(pinky);
        allGhosts.add(pinky);
        //Ghost.createGhostInside(ghostListInside);
        ghostListInside = Ghost.createGhostInside();
        allGhosts.addAll(ghostListInside);
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
        drawAllGhosts(g);    //Draw all ghosts from "allGhosts" list
        g.drawImage(pacman.getImage(), pacman.getX(), pacman.getY(), size, size, this);//Draw pacman
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

    public void drawAllGhosts(Graphics g){
        for (Ghost ghost : allGhosts) {
            g.drawImage(ghost.getImage(), ghost.getX(), ghost.getY(), size, size, this);
        }
    }

    public void movePacman() {
        if (keyControl.desiredDirection.equals("UP")) {
            if (pacman.canMove(myMap, 0, - pacman.getSpeed())) {
                keyControl.currentDirection = "UP";
            }
        } else if (keyControl.desiredDirection.equals("DOWN") && pacman.canMove(myMap, 0, pacman.getSpeed())) {
            keyControl.currentDirection = "DOWN";
        } else if (keyControl.desiredDirection.equals("RIGHT") && pacman.canMove(myMap, pacman.getSpeed(), 0)) {
            keyControl.currentDirection = "RIGHT";
        } else if (keyControl.desiredDirection.equals("LEFT") && pacman.canMove(myMap, -pacman.getSpeed(), 0)) {
            keyControl.currentDirection = "LEFT";
        }
        switch (keyControl.currentDirection) {
            case "UP":
                pacman.updateCoinsEaten(pacman.eat(pacman.getX() / size, (pacman.getY() - pacman.getSpeed()) / size, myMap), this);
                pacman.setImage(new ImageIcon("src/Pictures/PacmanUpGif.gif"));
                pacman.updateAfterMove(0, -pacman.getSpeed(), myMap);
                break;
            case "DOWN":
                pacman.updateCoinsEaten(pacman.eat(pacman.getX() / size, (pacman.getY() + pacman.getSpeed()) / size, myMap), this);
                pacman.setImage(new ImageIcon("src/Pictures/PacmanDownGif.gif"));
                pacman.updateAfterMove(0, pacman.getSpeed(), myMap);
                break;
            case "RIGHT":
                pacman.updateCoinsEaten(pacman.eat((pacman.getX() + pacman.getSpeed()) / size, pacman.getY() / size, myMap), this);
                pacman.setImage(new ImageIcon("src/Pictures/PacmanRightGif.gif"));
                pacman.channelRightManage((pacman.getX() + pacman.getSpeed()) / size, pacman.getY() / size, myMap);
                pacman.updateAfterMove(pacman.getSpeed(), 0, myMap);
                break;
            case "LEFT":
                pacman.updateCoinsEaten(pacman.eat((pacman.getX() - pacman.getSpeed()) / size, pacman.getY() / size, myMap), this);
                pacman.setImage(new ImageIcon("src/Pictures/PacmanLeftGif.gif"));
                pacman.updateAfterMove(-pacman.getSpeed(), 0, myMap);
                pacman.channelLeftManage((pacman.getX() - pacman.getSpeed()) / size, pacman.getY() / size, myMap);
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
        for (Ghost ghost : allGhosts) {
            ghost.setImage(ghost.eatableImage);
            ghost.setFood(true);
        }
    }

    //Returns ghost to source their image
    public void becomeNoFood() {
        for (Ghost ghost : allGhosts) {
            ghost.backToSrc();
            ghost.setFood(false);
        }
    }

//    public void meetWithGhost(){
//        for (Ghost ghost : allGhosts) {
//            if (ghost.isFood()){
//                pacman.addScore(200);
//                ghostListOutSide.remove(ghost);
//                ghost.backToSrc();
//                ghost.startPoint();
//                ghost.setFood(false);
//                new Timer(3000, e -> {
//                    ghost.goOutGeneral();
//                    ghostListOutSide.add(ghost);
//                }).start();
//            }
//            else {
//                pacman.pacmanCaught(this);
//
//            }
//        }
//    }
//    public void meetWithGhost(){
//        for (Ghost ghost : allGhosts) {
//            if (pacman.onSamePosition(ghost)){
//                if (pacman.ateBigCoin){
//                    pacman.addScore(200);
//                    ghostListOutSide.remove(ghost);
//                    ghost.backToSrc();
//                    ghost.startPoint();
//                    new Timer(3000, e->{
//                        ghost.goOutGeneral();
//                        ghostListOutSide.add(ghost);
//                    }).start();
//                }
//                else {
//                    pacman.pacmanCaught(this);
//                }
//            }
//        }
//    }

//    public void meetWithGhost() {
//        for (int i = 0; i < ghostListOutSide.size(); i++) {
//            Ghost ghost = ghostListOutSide.get(i);
//            if (pacman.onSamePosition(ghost)) {
//                if (pacman.ateBigCoin) {
//                    pacman.addScore(200);
//                    ghostListOutSide.remove(ghost);
//                    ghost.backToSrc();
//                    ghost.startPoint();
//
//                } else {
//                    pacman.pacmanCaught(this);
//                }
//            }
//        }
//    }
    public void goAfter() throws InterruptedException {
        if (!toGoAfter3Sec.isEmpty()){
            for (Ghost ghost : toGoAfter3Sec) {
                ghost.waite3SecondsAndGo();
                //ghost.setNeedToGoAfter3sec(false);
            }
        }
    }

    public void meetWithGhost(){
        for (Ghost ghost : allGhosts) {
            if (pacman.onSamePosition(ghost)) {
                if (ghost.isFood()) {
                    pacman.addScore(200);
                    ghost.startPoint();
                    ghost.backToSrc();
                    ghostListOutSide.remove(ghost);
                    //ghost.waite3SecondsAndGo();
                    ghost.setNeedToGoAfter3sec(true);
                    toGoAfter3Sec.add(ghost);
                }
                else{
                    pacman.pacmanCaught(this);
            }
            }
        }

    }

    @Override
    public void run() {
        while (pacman.getLives() > 0) {
            System.out.println(pacman.getLives());
            randomAll();
            movePacman();
            meetWithGhost();
            try {
                goAfter();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            repaint();
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
        System.exit(0);
    }
}
