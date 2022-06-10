import jdk.jfr.DataAmount;
import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class Game {
    private JFrame frame;
    private MyPanel panel;
    private GameLogic logic;
    private JButton treeButton;
    private JButton houseButton;
    private JButton roadButton;
    private JLabel label;
    private JPanel menu;

    public Game(){
        this.frame = new JFrame();
        this.frame.setSize(500, 500);
        this.frame.setResizable(false);
        this.panel = new MyPanel();
        this.label = new JLabel();
        this.label.setOpaque(true);

        this.logic = new GameLogic(this);
        this.menu = new JPanel(new GridLayout(1, 4));
        this.menu.setPreferredSize(new Dimension(500, 100));

        this.houseButton = new JButton("House");
        this.houseButton.addActionListener(this.logic);
        this.houseButton.setFocusable(false);

        this.treeButton = new JButton("Tree");
        this.treeButton.addActionListener(this.logic);
        this.treeButton.setFocusable(false);

        this.roadButton = new JButton("Road");
        this.roadButton.addActionListener(this.logic);
        this.roadButton.setFocusable(false);

        this.panel.addMouseListener(this.logic);
        this.panel.addMouseMotionListener(this.logic);

        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(500, 500);
        this.frame.setLayout(new BorderLayout());
        this.frame.add(this.panel, BorderLayout.CENTER);

        this.menu.add(this.treeButton);
        this.menu.add(this.houseButton);
        this.menu.add(this.roadButton);
        this.menu.add(this.label);
        this.frame.add(this.menu, BorderLayout.PAGE_START);
        this.frame.addWindowListener(this.logic);
        this.frame.setFocusable(true);
        this.frame.setVisible(true);
    }
}
