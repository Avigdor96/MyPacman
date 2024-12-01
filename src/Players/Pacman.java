package Players;

import Graphic.GamePanel;
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
    public boolean ateBigCoin = false;
    public int bigCoinTime = 8000;
    private int timeCaught = 4000;


    //Pacman constructor
    public Pacman(int size) {
        startPointX = 13;
        startPointY = 21;
        startPoint();
        image = new ImageIcon("src/Pictures/PacmanLeftOpen.jpg");
    }

    public void pacmanCaught(GamePanel gamePanel) {
        lives--;
        startPoint();
        deathImageManage();
        new Timer(timeCaught, e->{
            setImage(new ImageIcon("src/Pictures/PacmanLeftOpen.jpg"));
        }).start();
        gamePanel.backHome();
    }

    public void deathImageManage(){
        setImage(new ImageIcon("src/Pictures/PacmanDeath.gif"));
    }

    //Manage eating
    public int eat(int x, int y, GeneralElement[][] map) {
        if (map[y][x] instanceof Coin) {
            int value = ((Coin) map[y][x]).getValue();
            map[y][x] = new Empty(x * size, y * size);
            score += value;
            return value;
        }
        return 0;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }


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

    public boolean isAteBigCoin() {
        return ateBigCoin;
    }

    public void setAteBigCoin(boolean ateBigCoin) {
        this.ateBigCoin = ateBigCoin;
    }

    //Counts how many regular coins pacman ate and update "Ate big coin" bool for a few seconds
    public void updateCoinsEaten(int value) {
        if (value == coinValue){
            coinsEaten++;
        }
        else if (value == 30){
            this.setAteBigCoin(true);
//            new Timer(bigCoinTime, e-> {
//                ateBigCoin = false;
//            }).start();
        }
    }

    }
