package Objects;

import Maps.MapLevel1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Fruit extends GeneralElement implements Eatable {
    private int value;
    private String imagePath;
    private int secOnScreen;
    private boolean onScreen;
    private MapLevel1 mapLevel1;


    public Fruit(int x, int y, int size, String path, int val, int sec) {
        imagePath = path;
        setPoint(x, y);
        image = new ImageIcon(imagePath);
        value = val;
        secOnScreen = sec;
    }

    @Override
    public int getValue() {
        return value;
    }


    @Override
    public void setPoint(int x, int y) {
        point.x = x;
        point.y = y;

    }

    @Override
    public Image getImage() {
        return image.getImage();
    }

    @Override
    public void setImage(ImageIcon image) {
        this.image = image;
    }
}
