package Graphic;

import Players.Pacman;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel implements Runnable{
    Pacman pacman;
    JLabel topLabel;
    Thread topThread;

    public TopPanel(Pacman pacman) {
        this.pacman = pacman;
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        setVisible(true);
        Font font = new Font("Avi", Font.BOLD, 20);
        this.topLabel = new JLabel("Score: " + pacman.getScore());
        topLabel.setForeground(Color.BLACK);
        topLabel.setFont(font);
        this.add(topLabel, BorderLayout.WEST);
        topThread = new Thread(this);
        topThread.start();
    }

    public void refreshScore(){
        topLabel.setText(" Score: " + pacman.getScore());
    }

    @Override
    public void run() {
        while (true){
            refreshScore();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
