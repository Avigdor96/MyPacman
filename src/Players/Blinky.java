package Players;

import Maps.MapLevel1;
import Objects.GeneralElement;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    public List<int[]> bfsPath(int[][] map, int startX, int startY, int targetX, int targetY) {
        int rows = map.length;
        int cols = map[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Queue<Node> queue = new LinkedList<>();

        // הוספת נקודת ההתחלה
        queue.add(new Node(startX, startY, new ArrayList<>()));
        visited[startX][startY] = true;

        // כיוונים (למעלה, למטה, שמאלה, ימינה)
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            int x = current.x;
            int y = current.y;
            List<int[]> path = current.path;

            // אם הגענו לפאקמן, החזר את הדרך
            if (x == targetX && y == targetY) {
                return path;
            }

            // מעבר על כל השכנים
            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                if (isValid(map, newX, newY, visited)) {
                    visited[newX][newY] = true;
                    List<int[]> newPath = new ArrayList<>(path);
                    newPath.add(new int[]{newX, newY});
                    queue.add(new Node(newX, newY, newPath));
                }
            }
        }
        return null; // אם לא נמצא מסלול
    }

    // בדיקת חוקיות תא
    private boolean isValid(int[][] map, int x, int y, boolean[][] visited) {
        return x >= 0 && y >= 0 && x < map.length && y < map[0].length
                && map[x][y] != 1 && !visited[x][y]; // 1 מייצג קיר
    }

    // מחלקה פנימית לצמתים
    private static class Node {
        int x, y;
        List<int[]> path;

        Node(int x, int y, List<int[]> path) {
            this.x = x;
            this.y = y;
            this.path = path;
        }
    }
}
