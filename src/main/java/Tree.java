import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.awt.geom.*;

@Data
public class Tree extends Stamp{


    public Tree(int xCoord, int yCoord, int width, int height, Color color){
        super(xCoord, yCoord, width, height, color);
    }

    public void paint(Graphics g){
        g.setColor(this.color);
        g.fillOval(this.x, this.y, this.width, this.height);
        g.fillRect(this.x + this.width/3, this.y + this.height/2, this.width/3, this.height);
    }

    @Override
    public boolean clicked(int clickX, int clickY) {
        Rectangle rect = new Rectangle(this.x + this.width/3, this.y + this.height/2, this.width/3, this.height);
        Ellipse2D ellipse = new Ellipse2D.Double(this.x, this.y, this.width, this.height);
        return rect.contains(clickX, clickY) || ellipse.contains(clickX, clickY);
    }

}
