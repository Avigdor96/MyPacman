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

    public void startPoint(){
        setPoint(startPointX * size, startPointY * size);
    }


    public void setPoint(int x, int y) {
        point.x = x;
        point.y = y;
    }



    public int getLocationX() {
        return locationX;
    }

    public void setImage(ImageIcon image){
        this.image = image;
    }

    public boolean canMove(GeneralElement[][] myMap, int plusX, int plusY) {
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

    public Image getImage() {
        return image.getImage();
    }

    public boolean leftChannelManage(int x, int y, GeneralElement[][] map){
        if (map[y][x].isChannel()){
            this.setPoint((map[0].length - 2) * size, getY());
            return true;
        }
        return false;
    }

    public boolean channelRightManage(int x, int y, GeneralElement[][] map){
        if (map[y][x].isChannel()){
            this.setPoint(30, getY());
            return true;
        }
        return false;
    }

//    //Checks if two players are meet
//    public boolean onSamePosition(Player p1) {
//        Rectangle rectangle2 = new Rectangle(p1.getX(), p1.getY(), p1.size ,p1.size);
//        return new Rectangle(getX(), getY(),size, size).intersects(rectangle2);
//    }


}
