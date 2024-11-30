package Players;

import Objects.GeneralElement;
//import Objects.Speed;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Ghost extends Player{
    protected int currentDirection = -1;
    protected boolean eatable;
    private int eatableTime = 5000;
    public ImageIcon srcImage;
    //protected ImageIcon srcImage;

    public Ghost() {
        setPoint(startPointX * size, startPointY * size);
        startPoint();
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

//    public void becomeFood(ArrayList<Ghost> in, ArrayList<Ghost> out){
//        for (int i = 0; i < in.size(); i++) {
//            in.get(i).setImage(new ImageIcon("src/Pictures/GhostEatable.jpg"));
//        }
//        for (int i = 0; i < out.size(); i++) {
//            out.get(i).setImage(new ImageIcon("src/Pictures/GhostEatable.jpg"));
//        }
//        new Timer(eatableTime, e->{
//            for (int i = 0; i < in.size(); i++) {
//                in.get(i).setImage(srcImage);
//            }
//            for (int i = 0; i < out.size(); i++) {
//                out.get(i).setImage(srcImage);
//            }
//        }).start();
//    }

    //Create ghosts and adding to list inside
    public static void createGhostInside(ArrayList<Ghost> in){
        Reddish reddish1 = new Reddish();
        Bluish bluish1 = new Bluish();
        Purplish purplish1 = new Purplish();
        in.add(reddish1);
        in.add(bluish1);
        in.add(purplish1);
    }
}
