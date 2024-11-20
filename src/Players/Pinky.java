package Players;

import Objects.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Pinky extends Player {
    private int currentDirection = -1;

    public Pinky(int size, GeneralElement[][] myMap) {
        setPoint(15 * size, 21 * size);
        image = new ImageIcon("src/Pictures/Pinky1.jpg");

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

//            if (!directions.isEmpty()) {
//                    Random random = new Random();
//                    int randDirection = directions.get(random.nextInt(directions.size()));
//            }
//        }
    }


        @Override
        public boolean isEaten () {
            return false;
        }

        @Override
        public void setImage (ImageIcon image){
            this.image = new ImageIcon("src/Pictures/Pinky1.jpg");

        }

        @Override
        public int getWidth () {
            return width;
        }

        @Override
        public int getHeight () {
            return height;
        }
    }

