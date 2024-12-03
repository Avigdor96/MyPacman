package Players;

import Maps.MapLevel1;
import Objects.GeneralElement;

import javax.swing.*;

public class Blinky extends Ghost implements GhostInterface{
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

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
//    public int chooseDirectionToPacman(Pacman pacman) {
//        int pacmanX = pacman.getX();
//        int pacmanY = pacman.getY();
//
//        // אם פאקמן לא באותו קו X או Y, בוחר את כיוון X או Y המוביל ישירות לפאקמן
//        if (getX() < pacmanX && canMove(mapLevel1.ElementMap(), speed, 0)) {
//            return RIGHT; // ינוע ימינה
//        } else if (getX() > pacmanX && canMove(mapLevel1.ElementMap(), -speed, 0)) {
//            return LEFT; // ינוע שמאלה
//        } else if (getY() < pacmanY && canMove(mapLevel1.ElementMap(), 0, speed)) {
//            return DOWN; // ינוע למטה
//        } else if (getY() > pacmanY && canMove(mapLevel1.ElementMap(), 0, -speed)) {
//            return UP; // ינוע למעלה
//        }
//
//        return -1; // אין כיוון אפשרי
//    }

//    public int chooseDirectionToPacman(Pacman pacman) {
//        return calculateDirection(pacman.getX(), pacman.getY());
//    }
//
//    public int calculateDirection(int pacmanX, int pacmanY) {
//        if (Math.abs(getX() - pacmanX) > Math.abs(getY() - pacmanY)) {
//            if (getX() < pacmanX && canMove(mapLevel1.ElementMap(), speed, 0)) {
//                return RIGHT;
//            } else if (getX() > pacmanX && canMove(mapLevel1.ElementMap(), -speed, 0)) {
//                return LEFT;
//            }
//        } else {
//            if (getY() < pacmanY && this.canMove(mapLevel1.ElementMap(), 0, speed)) {
//                return DOWN;
//            } else if (getY() > pacmanY && this.canMove(mapLevel1.ElementMap(), 0, -speed)) {
//                return UP;
//            }
//        }
//
//        if (getX() - pacmanX != 0) { // אם אי אפשר בכיוון אופקי, נסה אנכי
//            if (getY() - pacmanY > 0 && canMove(mapLevel1.ElementMap(), 0, speed)) {
//                return DOWN;
//            } else if (getY() - pacmanY < 0 && canMove(mapLevel1.ElementMap(), 0, -speed)) {
////                return UP;
////            }
////        }
////        if (getY() - pacmanY != 0) { // אם אי אפשר בכיוון אנכי, נסה אופקי
////            if (getY() - pacmanY > 0 && canMove(mapLevel1.ElementMap(), speed, 0)) {
////                return RIGHT;
////            } else if (getY() - pacmanY < 0 && canMove(mapLevel1.ElementMap(), -speed, 0)) {
////                return LEFT;
////            }
////        }
////        return -1; // אין כיוון אפשרי
////    }
////
//public int chooseDirectionToPacman(Pacman pacman) {
//    // חישוב הכיוון הקרוב ביותר לפאקמן
//    int direction = calculateDirection(pacman.getX(), pacman.getY());
//
//    // אם הכיוון לא מאפשר תנועה, ננסה כיוון אחר
//    if (direction == -1 || !canMove(mapLevel1.ElementMap(), getDirectionX(direction), getDirectionY(direction))) {
//        // נבדוק את הכיוונים האפשריים אם הכיוון הנבחר לא פנוי
//        direction = tryAlternativeDirections(pacman.getX(), pacman.getY());
//    }
//
//    return direction;
//}
//
//    // חישוב כיוון התנועה כלפי פאקמן
//    public int calculateDirection(int pacmanX, int pacmanY) {
//        if (Math.abs(getX() - pacmanX) > Math.abs(getY() - pacmanY)) {
//            if (getX() < pacmanX && canMove(mapLevel1.ElementMap(), size, 0)) {
//                return RIGHT;
//            } else if (getX() > pacmanX && canMove(mapLevel1.ElementMap(), -size, 0)) {
//                return LEFT;
//            }
//        } else {
//            if (getY() < pacmanY && canMove(mapLevel1.ElementMap(), 0, size)) {
//                return DOWN;
//            } else if (getY() > pacmanY && canMove(mapLevel1.ElementMap(), 0, -size)) {
//                return UP;
//            }
//        }
//
//        return -1; // אין כיוון אפשרי
//    }
//
//    // ניסיון לבחור כיוון חלופי אם הכיוון המקורי חסום
//    public int tryAlternativeDirections(int pacmanX, int pacmanY) {
//        // ננסה לבחור כיוון חלופי אם הכיוון לא פנוי
//        if (getX() != pacmanX && canMove(mapLevel1.ElementMap(), size, 0)) {
//            return RIGHT;
//        }
//        if (getY() != pacmanY && canMove(mapLevel1.ElementMap(), 0, size)) {
//            return DOWN;
//        }
//        if (getX() != pacmanX && canMove(mapLevel1.ElementMap(), -size, 0)) {
//            return LEFT;
//        }
//        if (getY() != pacmanY && canMove(mapLevel1.ElementMap(), 0, -size)) {
//            return UP;
//        }
//
//        return -1; // אם אין כיוון פנוי
//    }
//
//    // הפונקציה הזו מחשבת את תוספות ה-X וה-Y בהתאם לכיוון
//    public int getDirectionX(int direction) {
//        switch (direction) {
//            case UP: case DOWN: return 0;
//            case LEFT: return -size;
//            case RIGHT: return size;
//            default: return 0;
//        }
//    }
//
//    public int getDirectionY(int direction) {
//        switch (direction) {
//            case UP: return -size;
//            case DOWN: return size;
//            case LEFT: case RIGHT: return 0;
//            default: return 0;
//        }
//    }
//    public void moveToDirection(int direction) {
//        switch (direction) {
//            case UP -> updateAfterMove(0, -speed, mapLevel1.ElementMap());
//            case DOWN -> updateAfterMove(0,  speed, mapLevel1.ElementMap());
//            case LEFT -> updateAfterMove(- speed, 0, mapLevel1.ElementMap());
//            case RIGHT -> updateAfterMove(speed, 0, mapLevel1.ElementMap());
//            //case - 1 -> randomMovement(mapLevel1.ElementMap());
//        }
//    }
}
