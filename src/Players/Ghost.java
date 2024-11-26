package Players;

import Objects.GeneralElement;
//import Objects.Speed;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Ghost extends Player{
    protected int currentDirection = -1;

    public Ghost(int x, int y, int size, String imagePath) {
        setPoint(x * size, y * size);
        setImage(new ImageIcon(imagePath));
    }

    public void randomMovement(GeneralElement[][] map) {
        switch (currentDirection) {
            case 0:
                if (canMoveUp(map)) {
                    upManager(map);
                } else {
                    currentDirection = -1;
                }
                break;
            case 1:
                if (canMoveDown(map)) {
                    downManager(map);
                } else {
                    currentDirection = -1;
                }
                break;
            case 2:
                if (canMoveRight(map)) {
                    rightManager(map);
                } else {
                    currentDirection = -1;
                }
                break;
            case 3:
                if (canMoveLeft(map)) {
                    leftManager(map);
                } else {
                    currentDirection = -1;
                }
                break;
            default:
                ArrayList<Integer> directions = new ArrayList<>();
                if (canMoveUp(map)) directions.add(0);
                if (canMoveDown(map)) directions.add(1);
                if (canMoveRight(map)) directions.add(2);
                if (canMoveLeft(map)) directions.add(3);
                if (!directions.isEmpty()) {
                    Random random = new Random();
                    currentDirection = directions.get(random.nextInt(directions.size()));
                }
                break;
        }
    }


    public void goOutGeneral(){
        ((GhostInterface) this).goOut();
    }
}
