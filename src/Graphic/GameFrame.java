package Graphic;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        GamePanel gamePanel = new GamePanel();
        this.setLayout(new BorderLayout());
        this.add(gamePanel.top(), BorderLayout.NORTH);
        this.add(gamePanel, BorderLayout.CENTER);
        this.add(bottom(), BorderLayout.SOUTH);
        gamePanel.setVisible(true);
        this.setSize(715, 920);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.BLACK);
        this.setVisible(true);
    }

//    public JPanel top(int score){
//        JPanel top = new JPanel();
//        Font font = new Font("Ariel", Font.BOLD, 40);
//        JLabel topLabel = new JLabel("Score: " + score);
//        topLabel.setForeground(Color.WHITE);
//        topLabel.setFont(font);
//        top.add(topLabel, BorderLayout.BEFORE_LINE_BEGINS);
//        top.setBackground(Color.BLACK);
//        top.setPreferredSize(new Dimension(0,50));
//        top.setVisible(true);
//        return top;
//    }

    public JPanel bottom(){
        JPanel bottom = new JPanel();
        bottom.setBackground(Color.BLACK);
        bottom.setPreferredSize(new Dimension(0,50));
        bottom.setVisible(true);
        return bottom;
    }
}
