package Players;

import javax.swing.*;

public class Bluish extends Ghost implements GhostInterface{

    public Bluish(){
        startPointX = 12;
        startPointY = 13;
        image = new ImageIcon("src/Pictures/Bluish.jpg");
        startPoint();
    }

    @Override
    public void goOut() {
        setPoint(getX() + speed, getY());
        setPoint(getX(), getY() - speed);
        setPoint(getX(), getY() - speed);
        setPoint(getX() - speed, getY());
    }
}
