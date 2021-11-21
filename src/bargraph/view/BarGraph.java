package bargraph.view;
import javax.swing.*;
import java.awt.*;

public class BarGraph extends JComponent {
    Color color;
    int height;

    public BarGraph(Color c, int height) {
        this.color = c;
        this.height = height;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.fillRect(0,0,40,height);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(40, 0);
    }

}
