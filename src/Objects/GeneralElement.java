package Objects;

import javax.swing.*;
import java.awt.*;

public abstract class GeneralElement implements Element{
    protected Point point = new Point();
    protected ImageIcon image;
    protected int size = 15;


    public boolean canPath() {
        return  true;
    }

    public int getX(){
        return point.x;
    }

    public int getY(){
        return point.y;
    }
}
