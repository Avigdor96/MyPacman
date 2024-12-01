package Graphic;

import javax.swing.*;
import java.awt.*;

public class StartScreen extends JPanel {

    public StartScreen(JFrame frame) {
        setLayout(new BorderLayout());
        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.add(new GameFrame());
            frame.revalidate();
            frame.repaint();
        });
        add(startButton, BorderLayout.CENTER);
    }
}
