import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

public class GameLogic extends UniversalAdapter{

    private final Game game;
    private Color currentColor;
    private Stamp selected;

    public GameLogic(Game game){
        this.game = game;
        this.currentColor = null;
        this.resolveColor();
        this.selected = null;
        this.game.getPanel().setCurrentTree(new Tree(0, 0, 30, 30, this.currentColor));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.game.getTreeButton()){
            this.game.getPanel().setCurrentTree(new Tree(0, 0, 30, 30, this.currentColor));
            this.game.getPanel().setCurrentHouse(null);
            this.game.getPanel().setPaintRoad(false);
        }
        if (e.getSource() == this.game.getHouseButton()){
            this.game.getPanel().setCurrentHouse(new House(0, 0, 30, 30, this.currentColor));
            this.game.getPanel().setCurrentTree(null);
            this.game.getPanel().setPaintRoad(false);
        }
        if (e.getSource() == this.game.getRoadButton()){
            this.game.getPanel().setPaintRoad(true);
            this.game.getPanel().setCurrentTree(null);
            this.game.getPanel().setCurrentHouse(null);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(e.getSource() == this.game.getPanel() && this.game.getPanel().getCurrentTree() != null){
            this.game.getPanel().getCurrentTree().setX(e.getX()- 15);
            this.game.getPanel().getCurrentTree().setY(e.getY()- 15);
        }

        if(e.getSource() == this.game.getPanel() && this.game.getPanel().getCurrentHouse() != null){
            this.game.getPanel().getCurrentHouse().setX(e.getX()- 15);
            this.game.getPanel().getCurrentHouse().setY(e.getY()- 15);
        }

        this.game.getPanel().revalidate();
        this.game.getPanel().repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(this.game.getPanel().getCurrentTree() != null){
            this.game.getPanel().getTrees().add(this.game.getPanel().getCurrentTree());
            this.resolveColor();
            this.game.getPanel().setCurrentTree(new Tree(e.getX(), e.getY(), 30, 30, this.currentColor));
        }
        if(this.game.getPanel().getCurrentHouse() != null){
            this.game.getPanel().getHouses().add(this.game.getPanel().getCurrentHouse());
            this.resolveColor();
            this.game.getPanel().setCurrentHouse(new House(e.getX(), e.getY(), 30, 30, this.currentColor));
        }
        if(this.game.getPanel().isPaintRoad()){

            Stamp stamp = getObjectFromCanvas(e.getX(), e.getY());
            if(stamp == null){
                return;
            }
            if(this.selected == null){
                this.selected = stamp;
                return;
            }
            if(stamp instanceof House){
                if(selected instanceof Tree){
                    this.connectObjects(stamp);
                    return;
                }
            }
            else if(stamp instanceof Tree){
                if(selected instanceof House){
                    this.connectObjects(stamp);
                    return;
                }
            }
        }
    }

    private void connectObjects(Stamp stamp){
        this.game.getPanel().getRoads().add(new Road(this.selected.getMiddle().x, this.selected.getMiddle().y, stamp.getMiddle().x, stamp.getMiddle().y));
        this.selected = null;
        this.game.getPanel().repaint();
    }

    @Override
    public void windowClosing(WindowEvent e){
        this.game.getFrame().dispose();
        System.exit(0);
    }

    private void resolveColor(){
        if(this.currentColor == Color.RED){
            this.currentColor = Color.GREEN;
        }
        else if(this.currentColor == Color.GREEN){
            this.currentColor = Color.BLUE;
        }
        else if(this.currentColor == Color.BLUE){
            this.currentColor = Color.YELLOW;
        }
        else{
            this.currentColor = Color.RED;
        }
        this.game.getLabel().setBackground(this.currentColor);
    }

    private Stamp getObjectFromCanvas(int x, int y){
        for(Tree tree : this.game.getPanel().getTrees()){
            if(tree.clicked(x, y)){
                return tree;
            }
        }
        for(House house : this.game.getPanel().getHouses()){
            if(house.clicked(x, y)){
                return house;
            }
        }
        return null;
    }
}
