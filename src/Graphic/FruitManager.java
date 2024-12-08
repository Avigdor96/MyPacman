package Graphic;

import Maps.MapLevel1;
import Objects.Eatable;
import Objects.Empty;
import Objects.Fruit;
import Objects.GeneralElement;
import Players.Ghost;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class FruitManager {
    private static MapLevel1 mapLevel1 = new MapLevel1();
    private static GeneralElement[][] map = mapLevel1.ElementMap();
    private static int size = 20;
    private ArrayList<Point> possiblePoint = possiblePositions();
    private ArrayList<Fruit> fruits = new ArrayList<>();
    private Random random = new Random();


    public FruitManager() {
        createFruitsToList();
    }

    public void updateFruits() {
        for (Fruit fruit : fruits) {
            fruit.setOnScreen();
            if (fruit.isOnScreen()){
                fruit.setPoint(possiblePoint.get(random.nextInt(possiblePoint.size())));
            }
        }
    }



    public ArrayList<Point> possiblePositions(){
        ArrayList<Point> points1 = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j].canPath() && ! (map[i][j] instanceof Empty) && !(map[i][j].isChannel())){
                    Point point1 = new Point(j * size, i * size);
                    points1.add(point1);
                }
            }
        }
        return points1;
    }

    public void createFruitsToList(){
        fruits.add(new Fruit(possiblePoint.get(random.nextInt(possiblePoint.size())), "src/Pictures/Apple.png", 700, 5));
        fruits.add(new Fruit(possiblePoint.get(random.nextInt(possiblePoint.size())),  "src/Pictures/Cherry.jpg", 100, 10));
        fruits.add(new Fruit(possiblePoint.get(random.nextInt(possiblePoint.size())),  "src/Pictures/Melon.jpg", 1000, 3));
        fruits.add(new Fruit(possiblePoint.get(random.nextInt(possiblePoint.size())),  "src/Pictures/Orange.png", 500, 6));
        fruits.add(new Fruit(possiblePoint.get(random.nextInt(possiblePoint.size())), "src/Pictures/תות.png", 300, 8));
    }


    public ArrayList<Fruit> getFruits() {
        return fruits;
    }
}
