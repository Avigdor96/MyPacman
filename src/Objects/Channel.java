package Objects;

import javax.swing.*;
import java.awt.*;

public class Channel extends GeneralElement{



    public Channel(int x, int y) {
        setPoint(x, y);
        image = new ImageIcon("src/Pictures/SquareWhite.jpg");
    }

    @Override
    public Point getPoint() {
        return point;
    }

    @Override
    public void setPoint(int x, int y) {
        point.x = x;
        point.y = y;

    }

    @Override
    public boolean isEaten() {
        return false;
    }

    @Override
    public Image getImage() {
        return image.getImage();
    }

    @Override
    public void setImage(ImageIcon image) {
        image = image;

    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
}
