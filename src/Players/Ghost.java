package Players;

import Objects.GeneralElement;
import Objects.Speed;

import javax.swing.*;
import java.awt.*;

public class Ghost extends GeneralElement implements Speed, Movement {
    //private int speed;
    private int speed = 25;
    //int score = 0;
    private int locationX;
    private int locationY;
    private int nextUpLoc;
    private int nextDownLoc;
    private int nextRightLoc;
    private int nextLeftLoc;
    private boolean openMouth = true;

    public Ghost(int x, int y, int size) {
        setPoint(x * size, y * size);

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
        return false;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public Image getImage() {
        return image.getImage();
    }

    @Override
    public void setImage(ImageIcon image) {
        this.image = image;
    }

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

    @Override
    public boolean canMoveUp(GeneralElement[][] myMap) {
        return false;
    }

    @Override
    public boolean canMoveDown(GeneralElement[][] myMap) {
        return false;
    }

    @Override
    public boolean canMoveRight(GeneralElement[][] myMap) {
        return false;
    }

    @Override
    public boolean canMoveLeft(GeneralElement[][] myMap) {
        return false;
    }

    @Override
    public int getX() {
        return point.x;
    }

    @Override
    public int getY() {
        return point.y;
    }
}
