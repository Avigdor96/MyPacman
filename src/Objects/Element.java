package Objects;

import javax.swing.*;
import java.awt.*;

public interface Element {
    public Point getPoint();
    public void setPoint(int x, int y);
    public boolean isEaten();
    public Image getImage();
    public void setImage(ImageIcon image);
    public int getWidth();
    public int getHeight();
}
