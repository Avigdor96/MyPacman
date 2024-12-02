package Graphic;
import javax.swing.*;
import java.awt.*;

public class GameOverScreen extends JFrame {

    public GameOverScreen() {
        // הגדרות מסך Game Over
        setTitle("Game Over");
        setSize(435, 515 + 25);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // הודעת Game Over
        JLabel message = new JLabel("Game Over", SwingConstants.CENTER);
        message.setFont(new Font("Arial", Font.BOLD, 40));
        message.setForeground(Color.RED);
        add(message, BorderLayout.NORTH);

        // כפתור לנסות שוב
        JButton retryButton = new JButton("נסה שוב");
        retryButton.setFont(new Font("Arial", Font.BOLD, 20));
        retryButton.addActionListener(e -> retryGame()); // מעבר למשחק מחדש
        add(retryButton, BorderLayout.CENTER);
    }

    private void retryGame() {
        // יצירת פריים המשחק מחדש
        //GameFrame gameFrame = new GameFrame();
        //gameFrame.setVisible(true);

        // סגירת מסך ה-Game Over
        this.dispose();
    }

    public static void main(String[] args) {
        // הצגת מסך Game Over לדוגמה
        GameOverScreen gameOverScreen = new GameOverScreen();
        gameOverScreen.setVisible(true);
    }
}
