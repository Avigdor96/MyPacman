package Players;

import Objects.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Pinky extends Ghost implements GhostInterface {

    public Pinky(){
        startPointX = 13;
        startPointY = 11;
        image = new ImageIcon("src/Pictures/gifmaker_me (1).gif");
        startPoint();
    }

    @Override
    public void goOut() {
        setPoint(getX() + speed, getY());

    }

//    @Override
////    public void goOut() {
////        setPoint(getX() , getY() - speed);
////        setPoint(getX(), getY() - speed);
////        setPoint(getX() + speed, getY());
////    }
}

