package Objects;

import Maps.MapLevel1;
import Players.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class GhostManager {
    private Queue<Ghost> ghostQueueInside = new LinkedList<>();
    private ArrayList<Ghost> ghostListInside = createGhostInside();
    private ArrayList<Ghost> ghostListOutSide = new ArrayList<>();
    private ArrayList<Ghost> allGhosts = new ArrayList<>();
    private static MapLevel1 mapLevel1 = new MapLevel1();
    private GeneralElement[][] map = mapLevel1.ElementMap();
    JPanel jPanel;
    private Random random = new Random();



    public GhostManager(JPanel jp) {
        jPanel = jp;
        allGhosts.addAll(ghostListInside);
        addInsideQueue();
    }

    //Create ghosts and returns list of them
    public static ArrayList<Ghost> createGhostInside() {
        ArrayList<Ghost> in = new ArrayList<>();
        Blinky blinky = new Blinky();
        Bluish bluish1 = new Bluish();
        Purplish purplish1 = new Purplish();
        in.add(blinky);
        in.add(bluish1);
        in.add(purplish1);
        return in;
    }

    //Moves the ghosts outside randomly
    public void randomAll() {
        for (int i = 0; i < ghostListOutSide.size(); i++) {
            if (ghostListOutSide.get(i).isMove()) {
                ghostListOutSide.get(i).randomMovement1(map);
            }
        }
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
            g.drawImage(ghost.getImage(), ghost.getX(), ghost.getY(), ghost.size, ghost.size, jPanel);
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

    //Out one ghost when Pacman ate quarter of coins from board
    public void exitGhostManager(Pacman pacman){
        if (pacman.ateQuarter() && ghostQueueInside.peek() != null){
            ghostListOutSide.add(ghostQueueInside.peek());
            ghostListInside.remove(ghostQueueInside.peek());
            Ghost ghost = ghostQueueInside.poll();
            ghost.goOut();
        }
    }

    public Queue<Ghost> getGhostQueueInside() {
        return ghostQueueInside;
    }

    public void setGhostQueueInside(Queue<Ghost> ghostQueueInside) {
        this.ghostQueueInside = ghostQueueInside;
    }

    public ArrayList<Ghost> getGhostListInside() {
        return ghostListInside;
    }

    public void setGhostListInside(ArrayList<Ghost> ghostListInside) {
        this.ghostListInside = ghostListInside;
    }

    public ArrayList<Ghost> getGhostListOutSide() {
        return ghostListOutSide;
    }

    public void setGhostListOutSide(ArrayList<Ghost> ghostListOutSide) {
        this.ghostListOutSide = ghostListOutSide;
    }

    public ArrayList<Ghost> getAllGhosts() {
        return allGhosts;
    }

    public void setAllGhosts(ArrayList<Ghost> allGhosts) {
        this.allGhosts = allGhosts;
    }
}
