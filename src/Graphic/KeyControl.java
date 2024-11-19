package Graphic;

import Objects.GeneralElement;
import Players.Pacman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyControl implements KeyListener {
    boolean up, down, left, right, enter;
    String currentDirection = "";
    String desiredDirection = "";



    @Override
    public void keyTyped(KeyEvent e) {

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


//    @Override
//    public void keyPressed(KeyEvent e) {
//        int code = e.getKeyCode();
//        if (code == KeyEvent.VK_UP){
//                up = true;
//                down = false;
//                right = false;
//                left = false;
//            }
//         else if (code == KeyEvent.VK_DOWN) {
//            up = false;
//            down = true;
//            right = false;
//            left = false;
//        } else if (code == KeyEvent.VK_RIGHT) {
//            up = false;
//            down = false;
//            right = true;
//            left = false;
//        } else if (code == KeyEvent.VK_LEFT) {
//            up = false;
//            down = false;
//            right = false;
//            left = true;
//        }
//    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
