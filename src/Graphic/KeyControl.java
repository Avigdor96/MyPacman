package Graphic;

import Objects.GeneralElement;
import Players.Pacman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyControl implements KeyListener {
    String currentDirection = "";
    String desiredDirection = "";



    @Override
    public void keyTyped(KeyEvent e) {

    }

    public String getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(String currentDirection) {
        this.currentDirection = currentDirection;
    }

    public String getDesiredDirection() {
        return desiredDirection;
    }

    public void setDesiredDirection(String desiredDirection) {
        this.desiredDirection = desiredDirection;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP){
            desiredDirection = "UP";
        } else if (code == KeyEvent.VK_DOWN) {
            desiredDirection = "DOWN";
        } else if (code == KeyEvent.VK_RIGHT) {
            desiredDirection = "RIGHT";
        } else if (code == KeyEvent.VK_LEFT) {
            desiredDirection = "LEFT";
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
