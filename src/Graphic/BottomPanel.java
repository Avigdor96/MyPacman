package Graphic;

import Players.Bluish;
import Players.Pacman;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BottomPanel extends JPanel implements Runnable{
    private ImageIcon heartIcon = new ImageIcon("src/Pictures/Heart.jpg");
    private Pacman pacman;
    private JLabel bottomLabel;
    Thread bottomThread;


    public BottomPanel(Pacman pacman) {
        this.pacman = pacman;
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        setVisible(true);
        Font font = new Font("Avi", Font.BOLD, 20);
        this.bottomLabel = new JLabel("Life: " + pacman.getLives());
        JLabel heart = new JLabel(heartIcon);
        bottomLabel.setForeground(Color.BLACK);
        this.add(bottomLabel, BorderLayout.WEST);
        this.add(heart);
        bottomThread = new Thread(this);
        bottomThread.start();
    }

    public void refreshScore(){
        //bottomLabel.setText("Life: "+ pacman.getLives());
        ImageIcon image = new ImageIcon("src/Pictures/Heart.jpg");
        JLabel label = new JLabel(image);
        for (int i = 0; i < pacman.getLives(); i++) {
            this.bottomLabel.add(label);
        }
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
