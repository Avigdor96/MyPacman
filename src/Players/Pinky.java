package Players;

import Objects.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Pinky extends Ghost implements GhostInterface {

    public Pinky(int x, int y, int size, String imagePath) {
        super(x, y, size, imagePath);
    }

    @Override
    public void goOut() {
        setPoint(getX() , getY() - speed);
        setPoint(getX(), getY() - speed);
    }
}

