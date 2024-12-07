package Graphic;

import Maps.MapLevel1;
import Objects.*;
import Players.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {
    //final private int size = 15; // final size of all elements
    final private int size = 20; // final size of all elements
    private Pinky pinky = new Pinky();
    private Queue<Ghost> ghostQueueInside = new LinkedList<>();
    private ArrayList<Ghost> ghostListInside = new ArrayList<>();
    private ArrayList<Ghost> ghostListOutSide = new ArrayList<>();
    private ArrayList<Ghost> allGhosts = new ArrayList<>();
    private Pacman pacman = new Pacman(size);
    private static MapLevel1 mapLevel1 = new MapLevel1();
    private GeneralElement[][] myMap = mapLevel1.ElementMap();
    private static KeyControl keyControl = new KeyControl();
    private static FruitManager fruitManager = new FruitManager();
    private Timer timer;
    private Timer timerForFruit;
    private Random random = new Random();
    private Ghost ghost = new Ghost();
    Thread threadGame;

    public GamePanel() {
        this.addKeyListener(keyControl);
        ghostListOutSide.add(pinky);
        allGhosts.add(pinky);
        ghostListInside = Ghost.createGhostInside();
        allGhosts.addAll(ghostListInside);
        addInsideQueue();
        this.setFocusable(true);
        threadGame = new Thread(this);
        timer = new Timer(4000, e -> fruitManager.updateFruits());
        timer.start();
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
        for (Fruit fruit : fruitManager.getFruits()) {
            if (fruit.isOnScreen()) {
                g.drawImage(fruit.getImage(), fruit.getX(), fruit.getY(), size, size, this);
            }
        }
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


    //draw all ghosts
    public void drawAllGhosts(Graphics g){
        for (Ghost ghost : allGhosts) {
            g.drawImage(ghost.getImage(), ghost.getX(), ghost.getY(), size, size, this);
        }
    }

    // Brings the ghosts to start point when Pacman fails or ate big coin
    public void backHome() {
        for (Ghost ghost : ghostListOutSide) {
            ghost.startPoint();
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

    public void meetWithGhost(){
        for (Ghost ghost : allGhosts) {
            if (pacman.onSamePosition(ghost)) {
                if (ghost.isFood()) {
                    ghost.setFood(false);
                    pacman.addScore(200);
                    ghost.startPoint();
                    ghost.backToSrc();
                    ghost.setNeedToGoAfter3sec(true);
                    ghost.waite3SecondsAndGo();
                }
                else{
                    pacman.pacmanCaught(this);
                    for (Ghost ghost1 : ghostListOutSide) {
                        ghost1.setNeedToGoAfter3sec(true);
                    }
                    for (Ghost ghost1 : ghostListOutSide) {
                        ghost1.waite3SecondsAndGo();
                    }
            }
            }
        }
    }
    public Pacman getPacman() {
        return pacman;
    }

    public GeneralElement[][] getMyMap() {
        return myMap;
    }

    @Override
    public void run() {
        while (pacman.onLife()) {
            pacman.movePacman(keyControl, this);
            repaint();
            ghost.randomAll(ghostListOutSide);
            meetWithGhost();
            pacman.eatFruit(fruitManager.getFruits());
            if (pacman.ateQuarter() &&  ghostQueueInside.peek() != null){
                ghostListOutSide.add(ghostQueueInside.peek());
                ghostListInside.remove(ghostQueueInside.peek());
                Ghost ghost = ghostQueueInside.poll();
                ghost.goOut();
            }
            try {
                Thread.sleep(8);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.exit(0);
    }
}
