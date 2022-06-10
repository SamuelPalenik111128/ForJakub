import lombok.Data;

import java.awt.*;
import java.awt.geom.Ellipse2D;

@Data
public class House extends Stamp{

    public House(int xCoord, int yCoord, int width, int height, Color color){
        super(xCoord, yCoord, width, height, color);
    }

    public void paint(Graphics g){
        g.setColor(this.color);
        g.fillPolygon(new int[]{this.x, this.x + this.width, this.x + (this.width / 2)}, new int[]{this.y, this.y, this.y - this.height}, 3);
        g.fillRect(this.x, this.y, this.width, this.height);
    }

    @Override
    public boolean clicked(int clickX, int clickY) {
        Polygon polygon = new Polygon(new int[]{this.x, this.x + this.width, this.x / 2}, new int[]{this.y, this.y, this.y - this.height}, 3);
        Rectangle rectangle = new Rectangle(this.x, this.y, this.width, this.height);
        return rectangle.contains(clickX, clickY) || polygon.contains(clickX, clickY);
    }

}
