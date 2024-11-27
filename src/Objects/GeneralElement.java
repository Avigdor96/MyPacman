package Objects;

import javax.swing.*;
import java.awt.*;

public abstract class GeneralElement implements Element{
    protected Point point = new Point();
    protected ImageIcon image;
    protected boolean isEaten;
    protected int width = 15;


    public int getX(){
        return point.x;
    }

    public int getY(){
        return point.y;
    }

}
