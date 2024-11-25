package Objects;

import javax.swing.*;
import java.awt.*;

public class Coin extends GeneralElement implements Eatable{
    protected int value = 10;


    public Coin(int x, int y) {
        setPoint(x, y);
        image = new ImageIcon("src/Pictures/Coin.jpg");
    }

    public void setValue(int value) {
        this.value = value;
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

    @Override
    public int getValue() {
        return value;
    }
}
