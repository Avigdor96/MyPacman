package Players;

import Objects.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Pacman extends Player {
    public int score = 0;
    private int lives;
    private final int quarterCoins = 59;
    private int coinValue = 10;
    private int coinsEaten = 0;

    //Pacman constructor
    public Pacman(int size) {
        setPoint(13 * size, 21 * size);
        image = new ImageIcon("src/Pictures/PacmanLeftOpen.jpg");
        lives = 3;
    }

    public void lifeManager( ArrayList<Ghost> ghosts) {
        for (Ghost ghost : ghosts) {
            if (ghost.getX() == getX() && ghost.getY() == getY()){
                lives--;
            }
        }
    }

    //Manage eating
    public int eat(int x, int y, GeneralElement[][] map) {
        if (map[y][x] instanceof Eatable) {
            int value = ((Eatable) map[y][x]).getValue();
            map[y][x] = new Empty(x * 25, y * 25);
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

    public void setLives(int lives) {
        this.lives = lives;
    }

    // Check if pacman ate quarter of regular coins
    public boolean ateQuarter() {
        boolean signOut;
        signOut = coinsEaten == quarterCoins;
        if (signOut) coinsEaten = 0;
        return signOut;
    }

    //Counts how many regular coins pacman ate
    public void updateCoinsEaten(int value) {
        if (value == coinValue) coinsEaten++;
    }
}
