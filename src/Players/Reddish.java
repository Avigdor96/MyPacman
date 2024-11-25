package Players;

import javax.swing.*;

public class Reddish extends Ghost implements GhostInterface{

    public Reddish(int x, int y, int size, String imagePath) {
        super(x, y, size, imagePath);
    }

    @Override
    public void goOut() {
        setPoint(getX() - speed, getY());
        setPoint(getX(), getY() - speed);
        setPoint(getX(), getY() - speed);
    }
}
