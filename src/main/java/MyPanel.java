import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@Data
public class MyPanel extends JPanel {

    private ArrayList<Tree> trees;
    private ArrayList<House> houses;
    private ArrayList<Road> roads;
    private Tree currentTree;
    private House currentHouse;
    private boolean paintRoad;
    private Point oldPoint;
    private Point newPoint;

    public MyPanel(){
        this.trees = new ArrayList<>();
        this.houses = new ArrayList<>();
        this.roads = new ArrayList<>();
        this.newPoint = null;
        this.oldPoint = null;
        this.currentHouse = null;
        this.currentTree = null;
        this.paintRoad = false;
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(500, 400));
        this.setFocusable(false);
        this.setFocusTraversalKeysEnabled(false);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(this.currentTree != null){
            this.currentTree.paint(g);
        }
        if(this.currentHouse != null){
            this.currentHouse.paint(g);
        }
        this.setBackground(Color.white);
        for(Tree tree : trees){
            tree.paint(g);
        }
        for(House house : houses){
            house.paint(g);
        }
        for(Road road : roads){
            road.paint(g);
        }

    }
}
