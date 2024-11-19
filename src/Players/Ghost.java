package Players;

import Objects.GeneralElement;
import Objects.Speed;

import javax.swing.*;
import java.awt.*;

public class Ghost extends GeneralElement implements Speed, Movement {
    private int speed;

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
