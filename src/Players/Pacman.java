package Players;

import Graphic.GamePanel;
import Graphic.KeyControl;
import Maps.MapLevel1;
import Objects.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Pacman extends Player {
    private int speed = 1;
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
    private int bigCoinValue = 30;
    public boolean ateBigCoin = false;
    public int bigCoinTime = 7000;
    private int timeCaught = 4000;
    private MapLevel1 mapLevel1 = new MapLevel1();
    private Timer timer;


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
        lives--;
        startPoint();
        deathImageManage();
        new Timer(timeCaught, e->{
            setImage(new ImageIcon("src/Pictures/PacmanLeftOpen.jpg"));
        }).start();
        gamePanel.backHome();      //Returns all ghosts to their start points
    }

    public void deathImageManage(){
        setImage(new ImageIcon("src/Pictures/PacmanDeath.gif"));
    }

    //Manage eating
    public int eat(int x, int y, GeneralElement[][] map){
        int value = 0;
        if (map[y][x].canEat()){
            value = ((Coin) map[y][x]).getValue();
            map[y][x] = new Empty(x * size, y * size);
        }
        score += value;
        return value;
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
            coinsEaten = 0; //Recount "coinsEaten"
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
        else if (value == bigCoinValue){
            bigCoinsEaten++;
            this.setAteBigCoin(true);
            gamePanel.becomeFood();
            timer = new Timer(bigCoinTime, e-> {
                ateBigCoin = false;
                gamePanel.becomeNoFood();
                timer.stop();
            });
            timer.start();
        }
        addLive();
        }


    public void addLive(){
        if (sumCoinsEaten == quarterCoins * 4 && !addedLive){
            addedLive = true;
            lives++;
        }
    }

    public void movePacman(KeyControl keyControl, GamePanel gamePanel) {
        if (keyControl.getDesiredDirection().equals("UP")) {
            if (canMove(gamePanel.getMyMap(), 0, - getSpeed())) {
                keyControl.setCurrentDirection("UP");
            }
        } else if (keyControl.getDesiredDirection().equals("DOWN") && canMove(gamePanel.getMyMap(), 0, getSpeed())) {
            keyControl.setCurrentDirection( "DOWN");
        } else if (keyControl.getDesiredDirection().equals("RIGHT") && canMove(gamePanel.getMyMap(), getSpeed(), 0)) {
            keyControl.setCurrentDirection("RIGHT");
        } else if (keyControl.getDesiredDirection().equals("LEFT") && canMove(gamePanel.getMyMap(), -getSpeed(), 0)) {
            keyControl.setCurrentDirection("LEFT");
        }
        switch (keyControl.getCurrentDirection()) {
            case "UP":
                updateCoinsEaten(eat(getX() / size, (getY() - getSpeed()) / size, gamePanel.getMyMap()), gamePanel);
                setImage(new ImageIcon("src/Pictures/PacmanUpGif.gif"));
                updateAfterMove(0, -getSpeed(), gamePanel.getMyMap());
                break;
            case "DOWN":
                updateCoinsEaten(eat(getX() / size, (getY() + getSpeed()) / size, gamePanel.getMyMap()), gamePanel);
                setImage(new ImageIcon("src/Pictures/PacmanDownGif.gif"));
                updateAfterMove(0, getSpeed(), gamePanel.getMyMap());
                break;
            case "RIGHT":
                updateCoinsEaten(eat((getX() + getSpeed()) / size, getY() / size, gamePanel.getMyMap()), gamePanel);
                setImage(new ImageIcon("src/Pictures/PacmanRightGif.gif"));
                channelRightManage((getX() +getSpeed()) / size, getY() / size, gamePanel.getMyMap());
                updateAfterMove(getSpeed(), 0, gamePanel.getMyMap());
                break;
            case "LEFT":
                updateCoinsEaten(eat((getX() - getSpeed()) / size, getY() / size, gamePanel.getMyMap()), gamePanel);
                setImage(new ImageIcon("src/Pictures/PacmanLeftGif.gif"));
                updateAfterMove(-getSpeed(), 0, gamePanel.getMyMap());
                leftChannelManage((getX() -getSpeed()) / size, getY() / size, gamePanel.getMyMap());
                break;
        }
    }

    public boolean onLife(){
        return getLives() > 0;
    }

    public void eatFruit(ArrayList<Fruit> fruits){
        for (Fruit fruit : fruits) {
            if (this.onSamePosition(fruit) && fruit.isOnScreen()){
                addScore(fruit.getValue());
                fruit.setOnScreen(false);
            }
        }
    }

    }
