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
//    protected int nextUpLoc;
//    protected int nextDownLoc;
//    protected int nextRightLoc;
//    protected int nextLeftLoc;

    public void startPoint(){
        setPoint(startPointX * size, startPointY * size);
    }


    public void setPoint(int x, int y) {
        point.x = x;
        point.y = y;
//        locationY = point.y / size;
//        locationX = point.x / size;
    }



    public int getLocationX() {
        return locationX;
    }

    public void setImage(ImageIcon image){
        this.image = image;
    }

    public boolean canMoveAndUpdate(GeneralElement[][] myMap, int plusX, int plusY) {
        int startX = point.x + plusX;
        int startY = point.y + plusY;
        int endX = startX + (size - 1);
        int endY = startY + (size - 1);
        int mapLeft = startX / size;
        int mapRight = endX / size;
        int mapTop = startY / size;
        int mapBottom = endY / size;

        if((myMap[mapTop][mapLeft].canPath())
                && (myMap[mapBottom][mapLeft].canPath())
                && myMap[mapTop][mapRight].canPath()
                && myMap[mapBottom][mapRight].canPath()) {
//            point.x += plusX;
//            point.y += plusY;
//            locationY = point.y / size;
//            locationX = point.x / size;

            return true;
        }
        return false;
    }
    public void updateAfterMove(int plusX, int plusY, GeneralElement[][] myMap) {
        int startX = point.x + plusX;
        int startY = point.y + plusY;
        int endX = startX + (size - 1);
        int endY = startY + (size - 1);
        int mapLeft = startX / size;
        int mapRight = endX / size;
        int mapTop = startY / size;
        int mapBottom = endY / size;
        if ((myMap[mapTop][mapLeft].canPath())
                && (myMap[mapBottom][mapLeft].canPath())
                && myMap[mapTop][mapRight].canPath()
                && myMap[mapBottom][mapRight].canPath()) {
            point.x += plusX;
            point.y += plusY;
            locationY = point.y / size;
            locationX = point.x / size;
        }
    }


//    public boolean canMoveLeft(GeneralElement[][] myMap) {
//        return !(myMap[locationY][nextLeftLoc] instanceof Block);
//    }
//
//    public boolean canMoveRight(GeneralElement[][] myMap) {
//        return !(myMap[locationY][nextRightLoc] instanceof Block);
//    }
//
//    public boolean canMoveDown(GeneralElement[][] myMap) {
//        return !(myMap[nextDownLoc][locationX] instanceof Block);
//    }
//
//    public boolean canMoveUp(GeneralElement[][] myMap){
//        return !((myMap[nextUpLoc][locationX]) instanceof Block);
//    }


    public Image getImage() {
        return image.getImage();
    }

//public void upManager(GeneralElement[][] myMap) {
//    int tempY = this.getNextUpLoc();
//    int tempX = this.getLocationX();
//    if (this.canMoveUp(myMap)) {
//        this.setPoint(this.getX(), this.getY() - speed);
//    }
//}

//    public void downManager(GeneralElement[][] myMap){
//        int tempY = this.getNextDownLoc();
//        int tempX = this.getLocationX();
//        if (this.canMoveDown(myMap)) {
//            this.setPoint(this.getX(), this.getY() + speed);
//        }
//
//    }

//    public void rightManager(GeneralElement[][] myMap) {
//        int tempY = getLocationY();
//        int tempX = getNextRightLoc();
//        if (canMoveRight(myMap)) {
//            setPoint(getX() + speed, getY());
//        }
//        channelRightManage(tempX, tempY, myMap);
//    }

    public void channelLeftManage(int x, int y, GeneralElement[][] map){
        if (map[y][x] instanceof Channel) this.setPoint((map[0].length - 1) * size, getY());
    }

    public void channelRightManage(int x, int y, GeneralElement[][] map){
        if (map[y][x] instanceof Channel){
            this.setPoint(15, getY());
        }
    }
}
