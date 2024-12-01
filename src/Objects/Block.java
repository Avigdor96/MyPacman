package Objects;

import javax.swing.*;
import java.awt.*;

public class Block extends GeneralElement{

    public Block(int x, int y) {
        setPoint(x, y);
        image = new ImageIcon("src/Pictures/Block2.jpeg");
    }

    @Override
    public void setPoint(int x, int y) {
        point.x = x;
        point.y = y;

    }

    @Override
    public boolean canPath() {
        return false;
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
