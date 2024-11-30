package Players;

import Objects.*;

import javax.swing.*;
import java.awt.*;

public abstract class Player extends GeneralElement implements Moveable {
    protected int speed = 5;
    protected int startPointX;
    protected int startPointY;
    protected int locationX;
    protected int locationY;
//    protected int endXLoc;
//    protected int endYLoc;
//    protected int nextEndX;
//    protected int nextEndY;
    protected int nextUpLoc;
    protected int nextDownLoc;
    protected int nextRightLoc;
    protected int nextLeftLoc;
//    protected int endUpNext;
//    protected int endDownNext;
//    protected int endRightNext;
////    protected int endLeftNext;
    protected int endX;
    protected int endY;
    protected int mapX;
    protected int mapY;
    protected int nextEndXMap;
    protected int nextEndYMap;

    public void startPoint(){
        setPoint(startPointX * size, startPointY * size);
    }

    public boolean onFullX(){
        return point.x % 25 == 0;
    }

    public boolean onFullY(){
        return point.y % 25 == 0;
    }

    public void setPoint(int x, int y) {
        point.x = x;
        point.y = y;
        endX = x + (size -1);
        endY = y + (size -1);
        mapX = x / size;
        mapY = y / size;
        nextEndXMap = endX / size;
        nextEndYMap = endY / size;
        locationY = point.y / size;
        locationX = point.x / size;
//        endXLoc = (point.x + (size - 1)) / size;
//        endYLoc = (locationY + (size -1)) / size;
        nextUpLoc = locationY  - 1;
//        endUpNext = endYLoc - 1;
        nextDownLoc = locationY  + 1;
//        endDownNext = endYLoc + 1;
        nextRightLoc = locationX  + 1;
//        endRightNext = locationX + 1;
        nextLeftLoc =  locationX  - 1;
//        endLeftNext = locationX - 1;
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

    public boolean canMove(GeneralElement[][] myMap){
        return !(myMap[mapY][mapX] instanceof Block) && !(myMap[endY][endX] instanceof Block) &&
                !(myMap[mapY][nextEndXMap] instanceof Block) && !(myMap[nextEndYMap][mapX] instanceof Block);
    }


    public boolean canMoveLeft(GeneralElement[][] myMap) {
        return !(myMap[locationY][nextLeftLoc] instanceof Block);
    }

    public boolean canMoveRight(GeneralElement[][] myMap) {
        return !(myMap[locationY][nextRightLoc] instanceof Block);
    }

    public boolean canMoveDown(GeneralElement[][] myMap) {
        return !(myMap[nextDownLoc][locationX] instanceof Block);
    }

    public boolean canMoveUp(GeneralElement[][] myMap){
        return !((myMap[nextUpLoc][locationX]) instanceof Block);
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
        if (map[y][x] instanceof Channel) setPoint((map[0].length - 1) * size, getY());
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

//    public boolean onSamePosition(Player p, Player p1){
//        return p.getX() == p1.getX() && p.getY() == p1.getY();
//    }
}
