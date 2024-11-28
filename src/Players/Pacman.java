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
    private boolean ateBigCoin;
    private int bigCoinTime = 5000;
    private int timeCaught = 4000;

//    public int getQuartersEaten() {
//        return quartersEaten;
//    }

    //Pacman constructor
    public Pacman(int size) {
        startPointX = 13;
        startPointY = 21;
        startPoint();
        image = new ImageIcon("src/Pictures/PacmanLeftOpen.jpg");
    }

    public void pacmanCaught() {
        lives--;
        startPoint();
        deathImageManage();
        new Timer(timeCaught, e->{
            setImage(new ImageIcon("src/Pictures/PacmanLeftOpen.jpg"));
        });
    }

    public void become(ArrayList<Ghost> in, ArrayList<Ghost> out){
        for (Ghost ghost : in) {
            ghost.becomeFood();
        }
        for (Ghost ghost : out) {
            ghost.becomeFood();
        }
    }


    public void meetWithGhost(ArrayList<Ghost> ghosts, ArrayList<Ghost> in){
        for (Ghost ghost : ghosts) {
            if (onSamePosition(this, ghost)){
                if (ateBigCoin){
                    ghost.startPoint();
                    ghost.setImage(image);
                }
                else{
                    pacmanCaught();
                }
            }
//            if (ghost.getX() == getX() && ghost.getY() == getY() && ghost.eatable){
//                score += 200;
//                ghost.setImage(new ImageIcon("src/Pictures/GhostEatable.jpg"));
//                ghost.startPoint();
//            }

        }
    }

//    public void meetWithGhost(Ghost ghost){
//        if (! ghost.eatable){
//            lives--;
//            startPoint();
//            deathImageManage();
//        }
//    }

//    public boolean pacmanDeath(ArrayList<Ghost> ghosts) {
//        for (Ghost ghost : ghosts) {
//            if (ghost.getX() == getX() && ghost.getY() == getY() && !ghost.eatable) {
//                    lives--;
//                    startPoint();
//                    return true;
//                }
//                else {
//                    score += 200;
//                    ghost.setImage(new ImageIcon(ghost.getImage()));
//                    ghost.startPoint();
//                }
//            }
//        return false;
//        }


    public void deathImageManage(){
        setImage(new ImageIcon("src/Pictures/PacmanDeath.gif"));
    }


    //Manage ghosts when Pacman ate bigCoin and makes them to eatable
    public void bigCoinManage(ArrayList<Ghost> in, ArrayList<Ghost> out) {
        if (ateBigCoin) {
            for (Ghost ghost : in) {
                ghost.becomeFood();
            }
            for (Ghost ghost : out) {
                ghost.becomeFood();
            }
        }
    }

    public boolean collisionWithGhost(ArrayList<Ghost> ghosts){
        boolean res = false;
        for (Ghost ghost : ghosts) {
            res = ghost.getX() == getX() && ghost.getY() == getY();
        }
        return res;
    }

    //Manage eating
    public int eat(int x, int y, GeneralElement[][] map) {
        if (map[y][x] instanceof Coin && !(map[y][x] instanceof BigCoin)) {
            int value = ((Coin) map[y][x]).getValue();
            map[y][x] = new Empty(x * size, y * size);
            score += value;
            return value;
//        } else if (map[y][x] instanceof BigCoin) {
//            int value = ((BigCoin) map[y][x]).getValue();
//            map[y][x] = new Empty(x * size, y * size);
//            score += value;
//            ateBigCoin = true;
//        }
        } else if (map[y][x] instanceof BigCoin) {
            int value = ((Coin) map[y][x]).getValue();
            map[y][x] = new Empty(x * size, y * size);
            score += value;
            ateBigCoin = true;
            new Timer(bigCoinTime, e->{
                ateBigCoin = false;
            });
            }
        return 0;
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

    //Counts how many regular coins pacman ate
    public void updateCoinsEaten(int value) {
        if (value == coinValue){
            coinsEaten++;
        }
        else if (value == 30){
            ateBigCoin = true;
            new Timer(bigCoinTime, e-> {
                ateBigCoin = false;
            });
        }
    }

    }
