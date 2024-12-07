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
    private static MapLevel1 mapLevel1 = new MapLevel1();
    public GeneralElement[][] map = mapLevel1.ElementMap();
    private ArrayList<Point> points = this.possiblePositions();
    private static boolean[] booleans = {true, false};


    public Fruit(){

    }

    public Fruit(Point point, int size, String path, int val, int sec) {
        imagePath = path;
        setPoint(point.x, point.y);
        image = new ImageIcon(imagePath);
        value = val;
        secOnScreen = sec;
        onScreen = new Random().nextBoolean();
    }

    public int getSecOnScreen() {
        return secOnScreen;
    }

    public ArrayList<Point> possiblePositions(){
        ArrayList<Point> points1 = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j].canPath()){
                    Point point1 = new Point(j * size, i * size);
                    points1.add(point1);
                }
            }
        }
        return points1;
    }


    public ArrayList<Fruit> createFruitsList(){
        ArrayList<Fruit> fruits = new ArrayList<>();
        Random random = new Random();
        Fruit apple = new Fruit(points.get(random.nextInt(points.size())), size, "src/Pictures/Apple.png", 700, 5);
        Fruit cherry = new Fruit(points.get(random.nextInt(points.size())), size, "src/Pictures/Cherry.jpg", 100, 10);
        Fruit melon = new Fruit(points.get(random.nextInt(points.size())), size, "src/Pictures/Melon.jpg", 1000, 3);
        Fruit orange = new Fruit(points.get(random.nextInt(points.size())), size, "src/Pictures/Orange.png", 500, 6);
        Fruit strawberry = new Fruit(points.get(random.nextInt(points.size())), size, "src/Pictures/תות.png", 300, 8);
        fruits.add(apple);
        fruits.add(cherry);
        fruits.add(melon);
        fruits.add(orange);
        fruits.add(strawberry);
        return fruits;
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
}
