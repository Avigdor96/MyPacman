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

//    public class Blinky extends Ghost {
//        private int x, y; // מיקום נוכחי
//        private GeneralElement[][] gameBoard; // לוח המשחק
//        private int pacmanX, pacmanY; // מיקום פאקמן
//        private boolean isScatterMode; // האם המפלצת במצב פיזור
//
//        public Blinky(int startX, int startY, GeneralElement[][] board) {
//            this.x = startX;
//            this.y = startY;
//            this.gameBoard = board;
//            this.isScatterMode = false; // מתחילה במצב רדיפה
//        }
//
//        // עדכון מיקום פאקמן (נקרא מ-GamePanel)
//        public void updatePacmanPosition(int px, int py) {
//            this.pacmanX = px;
//            this.pacmanY = py;
//        }
//
//        // תנועה מבוססת התנהגות
//        public void move() {
//            if (isScatterMode) {
//                moveTowardsTarget(gameBoard.length - 1, gameBoard[0].length - 1); // פיזור לפינה
//            } else {
//                moveTowardsTarget(pacmanX, pacmanY); // רדיפה אחרי פאקמן
//            }
//        }
//
//        // תנועה לכיוון יעד מסוים
//        private void moveTowardsTarget(int targetX, int targetY) {
//            int[] dx = {0, 0, -1, 1}; // שינוי ב-x
//            int[] dy = {-1, 1, 0, 0}; // שינוי ב-y
//            int bestMove = -1;
//            double minDistance = Double.MAX_VALUE;
//
//            for (int i = 0; i < 4; i++) {
//                int newX = x + dx[i];
//                int newY = y + dy[i];
//
//                if (isValidMove(newX, newY)) {
//                    double distance = calculateDistance(newX, newY, targetX, targetY);
//                    if (distance < minDistance) {
//                        minDistance = distance;
//                        bestMove = i;
//                    }
//                }
//            }
//
//            // בצע את התנועה הטובה ביותר
//            if (bestMove != -1) {
//                x += dx[bestMove];
//                y += dy[bestMove];
//            }
//        }
//
//        // חישוב מרחק בין שתי נקודות
//        private double calculateDistance(int x1, int y1, int x2, int y2) {
//            return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
//        }
//
//        // בדיקה אם המיקום החדש חוקי
//        private boolean isValidMove(int newX, int newY) {
//            return newX >= 0 && newX < gameBoard.length &&
//                    newY >= 0 && newY < gameBoard[0].length &&
//                    gameBoard[newX][newY] != 'W'; // W מייצג קיר
//        }
//
//        public int getX() {
//            return x;
//        }
//
//        public int getY() {
//            return y;
//        }
//
//        public void toggleScatterMode() {
//            isScatterMode = !isScatterMode;
//        }
//    }

}
