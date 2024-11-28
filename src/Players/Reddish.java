package Players;

import javax.swing.*;

public class Reddish extends Ghost implements GhostInterface{


    public Reddish() {
        startPointX = 14;
        startPointY = 13;
        srcImage = new ImageIcon("src/Pictures/Redy.jpg");
        image = srcImage;
        startPoint();
    }

    @Override
    public void goOut() {
        setPoint(getX() - speed, getY());
        setPoint(getX(), getY() - speed);
        setPoint(getX(), getY() - speed);
        setPoint(getX() + speed, getY());
    }
}
