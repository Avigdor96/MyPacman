package Players;

import Objects.GeneralElement;

import javax.swing.*;

public class Blinky extends Ghost implements GhostInterface{

    public Blinky() {
        startPointX = 14;
        startPointY = 13;
        srcImage = new ImageIcon("src/Pictures/Redy.jpg");
        image = srcImage;
        startPoint();
    }

    @Override
    public void goOut() {
        setPoint(getX() - size, getY());
        setPoint(getX(), getY() - size);
        setPoint(getX(), getY() - size);
        setPoint(getX() + size, getY());
    }

    public int chooseDirectionToPacman(Pacman pacman){
        int pacmanX = pacman.getX();
        int pacmanY = pacman.getY();

    }

    public int calculateDirection(int pacmanX, int pacmanY){
        int direction = -1;
        if (Math.abs(this.getX() - pacmanX) > Math.abs(this.getY() - pacmanY)){
            if (this.getX() < pacmanX && c)
        }
    }
}
