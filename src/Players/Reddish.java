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
        setPoint(getX() - size, getY());
        setPoint(getX(), getY() - size);
        setPoint(getX(), getY() - size);
        setPoint(getX() + size, getY());
    }
}
