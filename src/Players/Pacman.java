package Players;

import Graphic.KeyControl;
import Objects.*;

import javax.swing.*;
import java.awt.*;

public class Pacman extends GeneralElement implements Speed, Movement {
    private int speed = 25;
    int score = 0;
    private int locationX;
    private int locationY;
    private int nextUpLoc;
    private int nextDownLoc;
    private int nextRightLoc;
    private int nextLeftLoc;
    private boolean openMouth = true;

    public void leftManager(GeneralElement[][] myMap){
        int tempY = this.getLocationY();
        int tempX = this.getNextLeftLoc();
        if (this.canMoveLeft(myMap)) {
            this.setPoint(this.getX() - speed, this.getY());
            if (myMap[tempY][tempX] instanceof Eatable) {
                int value = ((Eatable) myMap[tempY][tempX]).getValue();
                myMap[tempY][tempX] = new Empty(tempX * 25, tempY * 25);
                score += value;
            }
        } else if (myMap[tempY][tempX + 1] instanceof Channel) {
            tempX = myMap[0].length -1;
            this.setPoint(tempX * 25, this.getY());
        }

    }

    public void rightManager(GeneralElement[][] myMap) {
        int tempY = this.getLocationY();
        int tempX = this.getNextRightLoc();
        if (this.canMoveRight(myMap)) {
        this.setPoint(this.getX() + speed, this.getY());
        if (myMap[tempY][tempX] instanceof Eatable) {
            int value = ((Eatable) myMap[tempY][tempX]).getValue();
            myMap[tempY][tempX] = new Empty(tempX * 25, tempY * 25);
            score += value;
        }
        } else if (myMap[tempY][tempX] instanceof Channel) {
            if (tempX > 0) {
                tempX = 0;
                this.setPoint(tempX, this.getY());
            }
        }
    }

    public void downManager(GeneralElement[][] myMap){
        int tempY = this.getNextDownLoc();
        int tempX = this.getLocationX();
        if (this.canMoveDown(myMap)) {
            this.setPoint(this.getX(), this.getY() + speed);
            if (myMap[tempY][tempX] instanceof Eatable) {
                int value = ((Eatable) myMap[tempY][tempX]).getValue();
                myMap[tempY][tempX] = new Empty(tempX * 25, tempY * 25);
                score += value;
            }
        }

    }

    public void upManager(GeneralElement[][] myMap) {
        int tempY = this.getNextUpLoc();
        int tempX = this.getLocationX();
        if (this.canMoveUp(myMap)) {
            this.setPoint(this.getX(), this.getY() - speed);
            if (myMap[tempY][tempX] instanceof Eatable) {
                int value = ((Eatable) myMap[tempY][tempX]).getValue();
                myMap[tempY][tempX] = new Empty(tempX * 25, tempY * 25);
                score += value;
            }
        }
    }

    public int getScore() {
        return score;
    }

    public int getLocationX() {
        return locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public int getNextUpLoc() {
        return nextUpLoc;
    }

    public int getNextDownLoc() {
        return nextDownLoc;
    }

    public int getNextRightLoc() {
        return nextRightLoc;
    }

    public int getNextLeftLoc() {
        return nextLeftLoc;
    }



    public Pacman(int size) {
        setPoint(13 * size, 21 * size);
        image = new ImageIcon("src/Pictures/PacmanLeftOpen.jpg");
        score = 0;
    }

    @Override
    public Point getPoint() {
        return point;
    }

    @Override
    public void setPoint(int x, int y) {
        point.x = x;
        point.y = y;
        locationY = point.y / width;
        locationX = point.x / width;
        nextUpLoc = locationY  - 1;
        nextDownLoc = locationY  + 1;
        nextRightLoc = locationX  + 1;
        nextLeftLoc =  locationX  - 1;
    }

    @Override
    public boolean isEaten() {
        return true;
    }

    @Override
    public Image getImage() {
        return image.getImage();
    }

    @Override
    public void setImage(ImageIcon image) {
        this.image = image;
    }

//    public void setImage(ImageIcon imageClose, ImageIcon imageOpen) {
//        while(true){
//            this.image = imageClose;
//        }
//
//    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getSpeed() {
        return speed;
    }


    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isOnFullY(){
        return getY() % 25 == 0;
    }

    public boolean isOnFullX(){
        return getX() % 25 == 0;
    }

    public boolean canMoveUp(GeneralElement[][] myMap){
        System.out.println("next up = " + nextUpLoc);
        System.out.println("Location x = " + locationX);
        System.out.println(myMap[nextUpLoc][locationX] instanceof Block);
        return !((myMap[nextUpLoc][locationX]) instanceof Block);
    }

    @Override
    public boolean canMoveDown(GeneralElement[][] myMap) {
       return isOnFullX() && !(myMap[nextDownLoc][locationX] instanceof Block);
    }

    @Override
    public boolean canMoveRight(GeneralElement[][] myMap) {
       return isOnFullY() &&  !(myMap[locationY][nextRightLoc] instanceof Block);
    }

    @Override
    public boolean canMoveLeft(GeneralElement[][] myMap) {
        if (nextLeftLoc < 0){
            return false;
        }
        return isOnFullY() && !(myMap[locationY][nextLeftLoc] instanceof Block);
    }

//    public void moveUp(GeneralElement[][] myMap) {
//        int tempX;
//        int tempY;
//        this.setImage(new ImageIcon("src/Pictures/PacmanUpOpen.jpg"));
//        if (this.canMoveUp(myMap)) {
//                tempY = this.getNextUpLoc();
//                tempX = this.getLocationX();
//                this.setPoint(this.getX(), this.getY() - speed);
//                if (myMap[tempY][tempX] instanceof Coin){
//                    myMap[tempY][tempX] = new Empty(tempX * 25, tempY * 25);
//                }
//            }
//    }

    @Override
    public int getX() {
        return point.x;
    }

    @Override
    public int getY() {
        return point.y;
    }

    public void MouthControl(){
        openMouth = !openMouth;
    }

    public boolean getOpenMouth(){
        return openMouth;
    }

    public void setOpenMouth(boolean change){
        this.openMouth = change;
    }

    public void changeMouthLeft(){
        if (openMouth){
        setImage(new ImageIcon("src/Pictures/PacmanLeftClose.jpg"));
        }
    else{
        setImage(new ImageIcon("src/Pictures/PacmanLeftOpen.jpg"));
    }}

    public void changeMonthRight(){
        if (openMouth){
            setImage(new ImageIcon("src/Pictures/PacmanRightClose.jpg"));
        }else{
            setImage(new ImageIcon("src/Pictures/PacmanRightOpen.jpg"));
        }
    }

    public void changeMonthUp(){
        if (openMouth){
        setImage(new ImageIcon("src/Pictures/PacmanUpClose.jpg"));
        }
    else{
        setImage(new ImageIcon("src/Pictures/PacmanUpOpen.jpg"));
    }}

    public void changeMonthDown(){
        if (openMouth){
            setImage(new ImageIcon("src/Pictures/PacmanDownClose.jpg"));
        }else{
            setImage(new ImageIcon("src/Pictures/PacmanDownOpen.jpg"));    }
    }
}
