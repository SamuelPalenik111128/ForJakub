import java.awt.*;

public class Road {

    private final Color color;
    private int xOld;
    private int yOld;
    private int xNew;
    private int yNew;

    public Road(int x, int y, int x1, int y1) {
        this.color = Color.black;
        this.xOld = x;
        this.yOld = y;
        this.xNew = x1;
        this.yNew = y1;
    }

    public void paint(Graphics g){
        g.setColor(this.color);
        g.drawLine(this.xOld, this.yOld, this.xNew, this.yNew);
    }
}
