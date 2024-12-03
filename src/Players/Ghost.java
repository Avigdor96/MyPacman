package Players;

import Graphic.GamePanel;
import Maps.MapLevel1;
import Objects.GeneralElement;
//import Objects.Speed;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Ghost extends Player {
    protected int speed = 4;
    protected int currentDirection = -1;
    protected ImageIcon srcImage;
    public ImageIcon eatableImage = new ImageIcon("src/Pictures/GhostEatable.jpg");
    protected MapLevel1 mapLevel1;
    protected boolean food;
    protected boolean needToGoAfter3sec;
    protected boolean move;
    protected Timer exitTimer;

    public Ghost() {
        this.mapLevel1 = new MapLevel1();
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

    public void randomMovement(GeneralElement[][] map) {
        switch (currentDirection) {
            case 0:
                if (this.canMove(map, 0, -speed)) {
                    this.updateAfterMove(0, -speed, map);
                } else {
                    currentDirection = -1;
                }
                break;
            case 1:
                if (this.canMove(map, 0, speed)) {
                    this.updateAfterMove(0, speed, map);
                } else {
                    currentDirection = -1;
                }
                break;
            case 2:
                if (this.canMove(map, speed, 0)) {
                    this.updateAfterMove(speed, 0, map);
                } else {
                    currentDirection = -1;
                }
                break;
            case 3:
                if (this.canMove(map, -speed, 0)) {
                    this.updateAfterMove(-speed, 0, map);
                } else {
                    currentDirection = -1;
                }
                break;
            default:
                ArrayList<Integer> directions = new ArrayList<>();
                if (canMove(map, 0, -speed)) directions.add(0);
                if (canMove(map, 0, speed)) directions.add(1);
                if (canMove(map, speed, 0)) directions.add(2);
                if (canMove(map, -speed, 0)) directions.add(3);
                if (!directions.isEmpty()) {
                    Random random = new Random();
                    currentDirection = directions.get(random.nextInt(directions.size()));
                }
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

    public void goOutGeneral() {
        ((GhostInterface) this).goOut();
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isFood() {
        return food;
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
                goOutGeneral();
                move = true;
                exitTimer.stop();
                exitTimer = null;
            });
            exitTimer.setRepeats(false);
            exitTimer.start();
//            setNeedToGoAfter3sec(false);
//            goOutGeneral();
        }

//            public void waite3SecondsAndGo () {
//                if (needToGoAfter3sec) {
//                    new Timer(3000, e -> {
//                        setNeedToGoAfter3sec(false);
//                        goOutGeneral();
//                    }).start();
//                }
//            }
//
//            public boolean isNeedToGoAfter3sec () {
//                return needToGoAfter3sec;
//            }
        //Create ghosts and adding to list inside



        }
}