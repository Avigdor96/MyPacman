package Players;

import javax.swing.*;

public class Purplish extends Ghost implements GhostInterface{


    public Purplish() {
        startPointX = 15;
        startPointY = 13;
        srcImage = new ImageIcon("src/Pictures/Purplish.jpeg");
        image = srcImage;
        startPoint();
    }

    @Override
    public void goOut() {
        setPoint(getX() - speed, getY());
        setPoint(getX() - speed, getY());
        setPoint(getX(), getY() - speed);
        setPoint(getX(), getY() - speed);
        setPoint(getX() - speed, getY());
    }
}
