package Objects;

import Maps.MapLevel1;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Random;

public class Fruit extends GeneralElement implements Eatable {
    private int value;
    private String imagePath;
    private int secOnScreen;
    private boolean onScreen;
    private Random random = new Random();
    private Timer timer;


    public Fruit(Point point, String path, int val, int sec) {
        imagePath = path;
        setPoint(point.x, point.y);
        image = new ImageIcon(imagePath);
        value = val;
        onScreen = random.nextBoolean();
        secOnScreen = sec;
    }

    public int getSecOnScreen() {
        return secOnScreen;
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

    public void setPoint(Point p){
        point.x = p.x;
        point.y = p.y;
    }

    @Override
    public Image getImage() {
        return image.getImage();
    }

    @Override
    public void setImage(ImageIcon image) {
        this.image = image;
    }

    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public int getY() {
        return super.getY();
    }

    public boolean isOnScreen() {
        return onScreen;
    }

    public void setOnScreen(boolean b) {
        this.onScreen = b;
    }

    public void setOnScreen() {
        boolean to = !onScreen;
        timer = new Timer(this.getSecOnScreen(), e -> {
            this.onScreen = to;
            timer.stop();
        });
        timer.start();

    }
//public void setOnScreen() {
//    if (timer != null && timer.isRunning()) {
//        timer.stop(); // עוצר טיימר קודם אם קיים
//    }
//    boolean to = !onScreen; // מחליף את המצב (מופיע או לא)
//    timer = new Timer(secOnScreen * 1000, e -> {
//        this.onScreen = to;
//        timer.stop(); // עוצר את הטיימר אחרי עדכון המצב
//    });
//    timer.setRepeats(false); // הטיימר לא יחזור על עצמו
//    timer.start();
//}

}
