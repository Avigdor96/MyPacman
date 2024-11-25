package Graphic;

import Players.Bluish;
import Players.Pacman;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BottomPanel extends JPanel {
    private ImageIcon heartIcon;
    private ArrayList<JLabel> hearts;
    private Pacman pacman;
    private JLabel bottomLabel;


    public BottomPanel(Pacman pacman) {
        this.pacman = pacman;
        heartIcon = new ImageIcon("src/Pictures/Heart.jpg");
        for (int i = 0; i < pacman.getLives(); i++) {
            JLabel jLabel = new JLabel(heartIcon);
            add(jLabel);
        }
    }


}
