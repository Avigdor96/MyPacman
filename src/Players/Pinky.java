package Players;

import Objects.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Pinky extends Ghost implements GhostInterface {

    public Pinky(){
        startPointX = 13;
        startPointY = 11;
        srcImage = new ImageIcon("src/Pictures/gifmaker_me (1).gif");
        image = srcImage;
        startPoint();
    }

    @Override
    public void goOut() {
        setPoint(getX() + size, getY());
    }
}

