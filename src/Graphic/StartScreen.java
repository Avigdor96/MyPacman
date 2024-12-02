package Graphic;

import javax.swing.*;
import java.awt.*;

public class StartScreen extends JFrame {

    public StartScreen() {
        //Settings of start screen
        setTitle("Pacman - Start Screen");
        setSize(435, 515 + 25);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.BLACK);

        // General title
        JLabel title = new JLabel("Welcome to Pacman", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.YELLOW);
        add(title, BorderLayout.NORTH);

        //Start button
        JButton startButton = new JButton("Start");
        startButton.setBackground(Color.YELLOW);
        startButton.setFont(new Font("Arial", Font.BOLD, 50));
        startButton.addActionListener(e -> startGame()); // Go to game
        add(startButton, BorderLayout.CENTER);
    }

    private void startGame() {
        //Create GameFrame
        GameFrame gameFrame = new GameFrame();
        gameFrame.setVisible(true);

        //Close start screen
        this.dispose();
    }


    public static void main(String[] args) {
        StartScreen startScreen = new StartScreen();
        startScreen.setVisible(true);
    }
}

