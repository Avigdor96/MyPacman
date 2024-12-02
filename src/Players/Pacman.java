package Players;

import Graphic.GamePanel;
import Objects.*;

import javax.swing.*;
import java.util.ArrayList;

public class Pacman extends Player {
    private int speed = 3;
    public int score = 0;
    private int lives = 3;
    private final int quarterCoins = 59;
    private int coinsEaten = 0;     //Counts coins were eaten and reset all quarter
    private int sumCoinsEaten = 0;  //Counts coins were eaten in total
    private boolean addedLive = false;
    private final int sumBigCoins = 4;
    private int bigCoinsEaten = 0;
    private int quartersEaten = 0;
    private int coinValue = 10;
    public boolean ateBigCoin = false;
    public int bigCoinTime = 7000;
    private int timeCaught = 4000;


    //Pacman constructor
    public Pacman(int size) {
        startPointX = 13;
        startPointY = 21;
        startPoint();
        image = new ImageIcon("src/Pictures/PacmanLeftOpen.jpg");
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    //Manage situation when pacman catching by a ghost
    public void pacmanCaught(GamePanel gamePanel) {
        lives--;                         //Less 1 live
        startPoint();                   //Returns pacman to start points
        deathImageManage();            //Changes image to death gif
        new Timer(timeCaught, e->{    //Waiting a few seconds before returns pacman to src image
            setImage(new ImageIcon("src/Pictures/PacmanLeftOpen.jpg"));
        }).start();
        gamePanel.backHome();      //Returns all ghosts to their start points
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

    public void setAteBigCoin(boolean ateBigCoin) {
        this.ateBigCoin = ateBigCoin;
    }

    //Counts how many regular coins pacman ate and update "Ate big coin" bool for a few seconds
    public void updateCoinsEaten(int value, GamePanel gamePanel) {
        if (value == coinValue){
            coinsEaten++;
            sumCoinsEaten++;
        }
        else if (value == 30){
            bigCoinsEaten++;
            this.setAteBigCoin(true);
            gamePanel.becomeFood();
            new Timer(bigCoinTime, e-> {
                ateBigCoin = false;
                gamePanel.becomeNoFood();
            }).start();
        }
        addLive();

        }


    public void addLive(){
        if (sumCoinsEaten == quarterCoins * 4 && !addedLive){
            addedLive = true;
            lives++;
        }
    }
    }
