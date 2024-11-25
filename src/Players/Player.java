package Players;

import Objects.*;

import javax.swing.*;
import java.awt.*;

public abstract class Player extends GeneralElement implements Moveable {
    protected int speed = 25;
    protected int locationX;
    protected int locationY;
    protected int nextUpLoc;
    protected int nextDownLoc;
    protected int nextRightLoc;
    protected int nextLeftLoc;


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

    public int getLocationX() {
        return locationX;
    }

    public void setImage(ImageIcon image){
        this.image = image;
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

    public boolean canMoveLeft(GeneralElement[][] myMap) {
        return !(myMap[locationY][nextLeftLoc] instanceof Block) && nextLeftLoc > 0;
    }

    public boolean canMoveRight(GeneralElement[][] myMap) {
        return !(myMap[locationY][nextRightLoc] instanceof Block) && nextRightLoc < myMap[0].length;
    }

    public boolean canMoveDown(GeneralElement[][] myMap) {
        return !(myMap[nextDownLoc][locationX] instanceof Block);
    }

    public boolean canMoveUp(GeneralElement[][] myMap){
        return !((myMap[nextUpLoc][locationX]) instanceof Block);
    }

    public Point getPoint() {
        return point;
    }

    public int getSpeed() {
        return speed;
    }

    public Image getImage() {
        return image.getImage();
    }

public void upManager(GeneralElement[][] myMap) {
    int tempY = this.getNextUpLoc();
    int tempX = this.getLocationX();
    if (this.canMoveUp(myMap)) {
        this.setPoint(this.getX(), this.getY() - speed);
    }
}

    public void downManager(GeneralElement[][] myMap){
        int tempY = this.getNextDownLoc();
        int tempX = this.getLocationX();
        if (this.canMoveDown(myMap)) {
            this.setPoint(this.getX(), this.getY() + speed);
        }

    }

    public void rightManager(GeneralElement[][] myMap) {
        int tempY = getLocationY();
        int tempX = getNextRightLoc();
        if (canMoveRight(myMap)) {
            setPoint(getX() + speed, getY());
        }
        channelRightManage(tempX, tempY, myMap);
    }

    public void channelLeftManage(int x, int y, GeneralElement[][] map){
        if (map[y][x] instanceof Channel) setPoint((map[0].length - 1) * 25, getY());
    }

    public void channelRightManage(int x, int y, GeneralElement[][] map){
        if (map[y][x] instanceof Channel) setPoint(0, getY());
    }

    public void leftManager(GeneralElement[][] myMap) {
        int tempY = this.getLocationY();
        int tempX = this.getNextLeftLoc();
        if (this.canMoveLeft(myMap)) {
            this.setPoint(this.getX() - speed, this.getY());
        }
        channelLeftManage(tempX, tempY, myMap);
    }
}
