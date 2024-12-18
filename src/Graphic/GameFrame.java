package Graphic;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        GamePanel gamePanel = new GamePanel();
        this.setLayout(new BorderLayout());
        TopPanel topPanel = new TopPanel(gamePanel.getPacman());
        BottomPanel bottomPanel = new BottomPanel(gamePanel.getPacman());
        bottomPanel.setPreferredSize(new Dimension(0, 35));
        this.add(topPanel, BorderLayout.NORTH);
        this.add(gamePanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
        gamePanel.setVisible(true);
        gamePanel.setDoubleBuffered(true);
        //this.setSize(435, 515 + 25);
        this.setSize(578, 682);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
