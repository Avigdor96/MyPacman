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
        setPoint(getX() - size, getY());
        setPoint(getX() - size, getY());
        setPoint(getX(), getY() - size);
        setPoint(getX(), getY() - size);
        setPoint(getX() - size, getY());
    }
}
