package Objects;

import javax.swing.*;
import java.awt.*;

public class Channel extends GeneralElement{



    public Channel(int x, int y) {
        setPoint(x, y);
        image = new ImageIcon("src/Pictures/SquareWhite.jpg");
    }

    @Override
    public void setPoint(int x, int y) {
        point.x = x;
        point.y = y;

    }

    @Override
    public boolean isChannel() {
        return true;
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
