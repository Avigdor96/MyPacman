package Players;

import Objects.GeneralElement;
//import Objects.Speed;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Ghost extends Player{
    protected int currentDirection = -1;
    protected ImageIcon srcImage;
    public ImageIcon eatableImage = new ImageIcon("src/Pictures/GhostEatable.jpg");

    public Ghost() {
        image = srcImage;
        setPoint(startPointX * size, startPointY * size);
        startPoint();
    }

    public void randomMovement(GeneralElement[][] map) {
        switch (currentDirection) {
            case 0:
                if (this.canMoveAndUpdate(map, 0, -5)) {
                    //upManager(map);
                    this.updateAfterMove(0, - 5, map);
                } else {
                    currentDirection = -1;
                }
                break;
            case 1:
                if (this.canMoveAndUpdate(map, 0, 5)) {
                    //downManager(map);
                    this.updateAfterMove(0, 5, map);
                } else {
                    currentDirection = -1;
                }
                break;
            case 2:
                if (this.canMoveAndUpdate(map, 5, 0)) {
                    //rightManager(map);
                    this.updateAfterMove(5, 0, map);
                } else {
                    currentDirection = -1;
                }
                break;
            case 3:
                if (this.canMoveAndUpdate(map, -5, 0)) {
                    //leftManager(map);\
                    this.updateAfterMove(-5, 0, map);
                } else {
                    currentDirection = -1;
                }
                break;
            default:
                ArrayList<Integer> directions = new ArrayList<>();
                //if (canMoveUp(map)) directions.add(0);
                //if (canMoveDown(map)) directions.add(1);
//                if (canMoveRight(map)) directions.add(2);
//                if (canMoveLeft(map)) directions.add(3);
                if (canMoveAndUpdate(map, 0, -5)) directions.add(0);
                if (canMoveAndUpdate(map, 0, 5)) directions.add(1);
                if (canMoveAndUpdate(map, 5, 0)) directions.add(2);
                if (canMoveAndUpdate(map, -5, 0)) directions.add(3);
                if (!directions.isEmpty()) {
                    Random random = new Random();
                    currentDirection = directions.get(random.nextInt(directions.size()));
                }
                break;
        }
    }

    public void backToSrc(){
        this.image = srcImage;
    }


    public ImageIcon getSrcImage() {
        return srcImage;
    }

    public void goOutGeneral(){
        ((GhostInterface) this).goOut();
    }


    //Create ghosts and adding to list inside
    public static void createGhostInside(ArrayList<Ghost> in){
        Blinky blinky = new Blinky();
        Bluish bluish1 = new Bluish();
        Purplish purplish1 = new Purplish();
        in.add(blinky);
        in.add(bluish1);
        in.add(purplish1);
    }
}
