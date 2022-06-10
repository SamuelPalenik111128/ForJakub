import lombok.*;

import java.awt.*;

@Data
public class Stamp {

    @Getter
    @Setter
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    private boolean first;
    protected Color color;

    public Stamp(int x, int y, int width, int height, Color color){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.first = false;
    }

    public Stamp(){
        this.x = 0;
        this.y = 0;
    }

    public boolean clicked(int clickX, int clickY){
        return false;
    }

    public Point getMiddle() {
        return new Point((2*this.x +  this.width) / 2, (2*this.y + this.height) / 2);
    }
}
