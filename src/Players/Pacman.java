package Players;

import Objects.*;

import javax.swing.*;
import java.util.ArrayList;

public class Pacman extends Player {
    public int score = 0;
    private int lives = 3;
    private final int quarterCoins = 59;
    private int quartersEaten = 0;
    private int coinValue = 10;
    private int coinsEaten = 0;

    public int getQuartersEaten() {
        return quartersEaten;
    }

    //Pacman constructor
    public Pacman(int size) {
        startPointX = 13;
        startPointY = 21;
        startPoint();
        image = new ImageIcon("src/Pictures/PacmanLeftOpen.jpg");
//        lives = 3;
    }

    public boolean pacmanDeath(ArrayList<Ghost> ghosts) {
        for (Ghost ghost : ghosts) {
            if (ghost.getX() == getX() && ghost.getY() == getY()) {
                lives--;
                startPoint();
                return true;
            }
        }
        return false;
    }

    public void deathManage(){
        setImage(new ImageIcon("src/Pictures/PacmanDeath.gif"));
    }


//    public void lifeManager(ArrayList<Ghost> ghosts) {
//        for (Ghost ghost : ghosts) {
//            if (ghost.getX() == getX() && ghost.getY() == getY()) {
//                lives--;
//                startPoint();
//                setImage(new ImageIcon("src/Pictures/PacmanDeath.gif"));
//            }
//        }
//    }

    //Manage eating
    public int eat(int x, int y, GeneralElement[][] map) {
        if (map[y][x] instanceof Eatable) {
            int value = ((Eatable) map[y][x]).getValue();
            map[y][x] = new Empty(x * size, y * size);
            score += value;
            return value;
        }
        return 0;
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

//    public void setLives(int lives) {
//        this.lives = lives;
//    }

    // Check if pacman ate quarter of regular coins
    public boolean ateQuarter() {
        boolean signToOut;
        signToOut = coinsEaten == quarterCoins;
        if (signToOut){
            coinsEaten = 0;
            quartersEaten++;
        }
        return signToOut;
    }

    //Counts how many regular coins pacman ate
    public void updateCoinsEaten(int value) {
        if (value == coinValue) coinsEaten++;
    }
    }
