package Players;

import Objects.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public abstract class Player extends GeneralElement implements Movement {
    private int speed = 25;
    private int locationX;
    private int locationY;
    private int nextUpLoc;
    private int nextDownLoc;
    private int nextRightLoc;
    private int nextLeftLoc;
    private boolean openMouth = true;
   


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

    public int getX() {
        return point.x;
    }

    public int getY() {
        return point.y;
    }

    public boolean canMoveLeft(GeneralElement[][] myMap) {
        if (nextLeftLoc < 0){
            return false;
        }
        return !(myMap[locationY][nextLeftLoc] instanceof Block);
    }

    public boolean canMoveRight(GeneralElement[][] myMap) {
        return !(myMap[locationY][nextRightLoc] instanceof Block) && !(myMap[locationY][nextRightLoc] instanceof Channel);
    }

    public boolean canMoveDown(GeneralElement[][] myMap) {
        return !(myMap[nextDownLoc][locationX] instanceof Block);
    }

    public boolean canMoveUp(GeneralElement[][] myMap){
        return !((myMap[nextUpLoc][locationX]) instanceof Block);
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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
        int tempY = this.getLocationY();
        int tempX = this.getNextRightLoc();
        if (this.canMoveRight(myMap)) {
            this.setPoint(this.getX() + speed, this.getY());
        }
    }

    public void leftManager(GeneralElement[][] myMap) {
        int tempY = this.getLocationY();
        int tempX = this.getNextLeftLoc();
        if (this.canMoveLeft(myMap)) {
            this.setPoint(this.getX() - speed, this.getY());
        }
    }

    public void move(GeneralElement[][] map) {
        if(currentDirection == -1) {
            ArrayList<Integer> directions = new ArrayList<>();
            if (canMoveUp(map)) directions.add(0);
            if (canMoveDown(map)) directions.add(1);
            if (canMoveRight(map)) directions.add(2);
            if (canMoveLeft(map)) directions.add(3);
            Random random = new Random();
            int randDirection = directions.get(random.nextInt(directions.size()));
            currentDirection = randDirection;
        }
        switch (currentDirection) {
            case 0:
                if(canMoveUp(map) )upManager(map);
                else currentDirection = -1;
                break;
            case 1:
                if(canMoveDown(map) )downManager(map);
                else currentDirection = -1;
                break;
            case 2:
                if(canMoveRight(map) )rightManager(map);
                else currentDirection = -1;
                break;
            case 3:
                if(canMoveLeft(map) ) leftManager(map);
                else currentDirection = -1;
                break;
        }


}
