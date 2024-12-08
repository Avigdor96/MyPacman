package Graphic;

import Players.Bluish;
import Players.Pacman;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BottomPanel extends JPanel implements Runnable{
    private Pacman pacman;
    private JLabel bottomLabel;
    Thread bottomThread;


    public BottomPanel(Pacman pacman) {
        this.pacman = pacman;
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        setVisible(true);
        Font font = new Font("Ariel", Font.BOLD, 35);
        this.bottomLabel = new JLabel("Life: " + pacman.getLives());
        bottomLabel.setForeground(Color.BLACK);
        bottomLabel.setFont(font);
        this.add(bottomLabel, BorderLayout.WEST);
        bottomThread = new Thread(this);
        bottomThread.start();
    }

    public void refreshScore(){
        bottomLabel.setText("Life: "+ pacman.getLives());
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
