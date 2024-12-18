package Players;

import Graphic.GamePanel;
import Maps.MapLevel1;
import Objects.Eatable;
import Objects.GeneralElement;
//import Objects.Speed;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Ghost extends Player implements Eatable {
    protected int speed = 1;
    protected int currentDirection = -1;
    protected ImageIcon srcImage;
    public ImageIcon eatableImage = new ImageIcon("src/Pictures/GhostEatable.jpg");
    protected MapLevel1 mapLevel1 = new MapLevel1();
    protected GeneralElement[][] map = mapLevel1.ElementMap();
    protected boolean food;
    protected int value = 200;
    protected boolean needToGoAfter3sec;
    protected boolean move;
    protected Timer exitTimer;

    public Ghost() {
        image = srcImage;
        setPoint(startPointX * size, startPointY * size);
        startPoint();
        move = true;
    }

     public static ArrayList<Ghost> createGhostInside() {
        ArrayList<Ghost> in = new ArrayList<>();
        Blinky blinky = new Blinky();
        Bluish bluish1 = new Bluish();
        Purplish purplish1 = new Purplish();
        in.add(blinky);
        in.add(bluish1);
        in.add(purplish1);
        return in;
     }

    //Moves the ghosts outside randomly
    public void randomAll(ArrayList<Ghost> ghostListOutSide) {
        for (int i = 0; i < ghostListOutSide.size(); i++) {
            if (ghostListOutSide.get(i).isMove()) {
                ghostListOutSide.get(i).randomMovement1(map);
            }
        }
    }


    public void randomMovement1(GeneralElement[][] map){
        boolean canContinue = false;
        Random random = new Random();
        switch (currentDirection) {
            case 0: canContinue = canMove(map, 0, -speed); break;
            case 1: canContinue = canMove(map, 0, speed); break;
            case 2: if(!(channelRightManage(getX() / size, getY() / size, map))) {
                canContinue = canMove(map, speed, 0);
                break;
            }
            case 3: if (!(leftChannelManage((getX() - getSpeed()) / size, getY() / size, map))) {
                canContinue = canMove(map, -speed, 0);
                break;
            }
        }
        ArrayList<Integer> directions = new ArrayList<>();
            if (canMove(map, 0, -speed)) directions.add(0); // up
            if (canMove(map, 0, speed)) directions.add(1);  //down
            if (canMove(map, speed, 0)) directions.add(2);  // right
            if (canMove(map, -speed, 0)) directions.add(3); // left

        if ((!canContinue || (canContinue && directions.size() > 2)) && (!(directions.isEmpty()))){
            currentDirection = directions.get(random.nextInt(directions.size()));
        }

        switch (currentDirection){
            case 0:
                updateAfterMove(0, -speed, map);
                break;
            case 1:
               updateAfterMove(0, speed, map);
                break;
            case 2:
                updateAfterMove(speed, 0, map);
                break;
            case 3:
                updateAfterMove(-speed, 0, map);
                break;
        }
    }

    public void backToSrc() {
        setImage(srcImage);
    }


    public ImageIcon getSrcImage() {
        return srcImage;
    }

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    public void goOut() {
        ((GhostInterface) this).goOut();
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isFood() {
        return food;
    }

    @Override
    public boolean canEat() {
        return isFood();
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setNeedToGoAfter3sec(boolean needToGoAfter3sec) {
        this.needToGoAfter3sec = needToGoAfter3sec;
    }

    public void waite3SecondsAndGo() {
        if (needToGoAfter3sec && exitTimer == null) {
            move = false;
            exitTimer = new Timer(3000, e -> {
                setNeedToGoAfter3sec(false);
                goOut();
                move = true;
                exitTimer.stop();
                exitTimer = null;
            });
            exitTimer.setRepeats(false);
            exitTimer.start();
        }
        }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void afterPacmanAte(GeneralElement[][] map) {

    }
}