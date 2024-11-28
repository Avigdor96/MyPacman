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
        setPoint(getX() + speed, getY());
        setPoint(getX(), getY() - speed);
        setPoint(getX(), getY() - speed);
        setPoint(getX() - speed, getY());
    }
}
