package Players;

import javax.swing.*;

public class Bluish extends Ghost implements GhostInterface{

    public Bluish(){
        startPointX = 12;
        startPointY = 13;
        srcImage = new ImageIcon("src/Pictures/Bluish.jpg");
        image = srcImage;
        startPoint();
    }

    @Override
    public void goOut() {
        setPoint(getX() + size, getY());
        setPoint(getX(), getY() - size);
        setPoint(getX(), getY() - size);
        setPoint(getX() - size, getY());
    }
}
