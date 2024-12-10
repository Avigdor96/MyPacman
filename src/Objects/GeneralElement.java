package Objects;

import Players.Player;

import javax.swing.*;
import java.awt.*;

public abstract class GeneralElement implements Element{
    protected Point point = new Point();
    protected ImageIcon image;
    protected int size = 20;


    public boolean canPath() {
        return  true;
    }

    public boolean canEat(){
        return false;
    }

    public boolean isChannel(){
        return false;
    }

    public int getX(){
        return point.x;
    }

    public int getY(){
        return point.y;
    }

    //Checks if two elements are meet
    public boolean onSamePosition(GeneralElement p1) {
        Rectangle rectangle2 = new Rectangle(p1.getX(), p1.getY(), p1.size ,p1.size);
        return new Rectangle(getX(), getY(),size, size).intersects(rectangle2);
    }
}
