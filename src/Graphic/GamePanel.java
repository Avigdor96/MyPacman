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
    final private int size = 20; // final size of all elements
    private Pinky pinky = new Pinky();
    private Pacman pacman = new Pacman(size);
    private static MapLevel1 mapLevel1 = new MapLevel1();
    private GeneralElement[][] myMap = mapLevel1.ElementMap();
    private static KeyControl keyControl = new KeyControl();
    private static FruitManager fruitManager = new FruitManager();
    private GhostManager ghostManager = new GhostManager(this);
    private Timer timer;
    Thread threadGame;

    public GamePanel() {
        this.addKeyListener(keyControl);
        ghostManager.getGhostListOutSide().add(pinky);
        ghostManager.getAllGhosts().add(pinky);
        this.setFocusable(true);
        threadGame = new Thread(this);
        timer = new Timer(10000, e -> fruitManager.updateFruits());
        timer.start();
        threadGame.start();
    }

    public GhostManager getGhostManager() {
        return ghostManager;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < myMap.length; i++) {
            for (int j = 0; j < myMap[i].length; j++) {
                g.drawImage(myMap[i][j].getImage(), myMap[i][j].getX(), myMap[i][j].getY(), size, size, this);
            }
        }
        ghostManager.drawAllGhosts(g);  //Draw all ghosts from "allGhosts" list
        g.drawImage(pacman.getImage(), pacman.getX(), pacman.getY(), size, size, this);//Draw pacman

        for (Fruit fruit : fruitManager.getFruits()) {
            if (fruit.isOnScreen()) {
                g.drawImage(fruit.getImage(), fruit.getX(), fruit.getY(), size, size, this);
            }
        }
        Toolkit.getDefaultToolkit().sync();
    }

    public void meetWithGhost(){
        for (Ghost ghost : ghostManager.getAllGhosts()) {
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
                    for (Ghost ghost1 : ghostManager.getGhostListOutSide()) {
                        ghost1.setNeedToGoAfter3sec(true);
                    }
                    for (Ghost ghost1 : ghostManager.getGhostListOutSide()) {
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
            ghostManager.randomAll();
            meetWithGhost();
            pacman.eatFruit(fruitManager.getFruits());
            ghostManager.exitGhostManager(pacman);
            try {
                Thread.sleep(8);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.exit(0);
    }
}
