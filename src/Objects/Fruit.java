package Objects;

import javax.swing.*;
import java.awt.*;

public class Fruit extends GeneralElement implements Eatable{
    private int value;

    public Fruit(int x, int y) {
        setPoint(x, y);
        image = new ImageIcon("src/Pictures/Fruit.jpg");
    }

    @Override
    public int getValue() {
        return value;
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

//    @Override
//    public boolean isEaten() {
//        return true;
//    }

    @Override
    public Image getImage() {
        return image.getImage();
    }

    @Override
    public void setImage(ImageIcon image) {
        this.image = image;
    }

//    @Override
//    public int getWidth() {
//        return width;
//    }

//    @Override
//    public int getHeight() {
//        return height;
//    }
}
