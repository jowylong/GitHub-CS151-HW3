package bargraph.view;

import bargraph.controller.Message;
import bargraph.controller.UpdateMessage;
import bargraph.controller.ResetMessage;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;

import static java.awt.Color.*;
import static java.lang.Integer.parseInt;

public class View extends JFrame {
    BlockingQueue<Message> queue;
    JTextField redField;
    JTextField greenField;
    JTextField blueField;

    JLabel redLabel;
    JLabel greenLabel;
    JLabel blueLabel;

    JButton updateBarButton;
    JButton resetBarButton;

    BarGraph redBar;
    BarGraph greenBar;
    BarGraph blueBar;

    public View(BlockingQueue<Message> queue) {
        this.queue = queue;
        this.redBar = new BarGraph(red,1000);

        this.greenBar = new BarGraph(green,1000);

        this.blueBar = new BarGraph(blue,1000);

        this.redField = new JTextField("0", 2);
        this.redLabel = new JLabel("Red");

        this.greenField = new JTextField("0",2);
        this.greenLabel = new JLabel("Green");

        this.blueField = new JTextField("0",2);
        this.blueLabel = new JLabel("Blue");

        this.updateBarButton = new JButton("Update");
        this.resetBarButton = new JButton("Reset");

        updateBarButton.addActionListener(e -> {
            int redV = parseInt(redField.getText());
            int greenV = parseInt(greenField.getText());
            int blueV = parseInt(blueField.getText());
            try {
                Message msg = new UpdateMessage(redV, greenV, blueV);
                queue.put(msg);
            } catch (InterruptedException exception) {
                // do nothing
            }
        });

        resetBarButton.addActionListener(e -> {
            try {
                queue.put(new ResetMessage());
            } catch (InterruptedException exception) {
                // do nothing
            }
        });

        this.add(redLabel);
        this.add(redField);
        this.add(greenLabel);
        this.add(greenField);
        this.add(blueLabel);
        this.add(blueField);
        this.add(updateBarButton);
        this.add(resetBarButton);
        this.add(redBar);
        this.add(greenBar);
        this.add(blueBar);

        this.setSize(400, 500);
        this.setLayout(new FlowLayout());
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void updateSizesInView(int red, int green, int blue) {
        this.redBar.setBounds(100,getHeight()-(10*red+40),40,10*red);
        this.greenBar.setBounds(150,getHeight()-(10*green+40),40,10*green);
        this.blueBar.setBounds(200,getHeight()-(10*blue+40),40,10*blue);
    }

    public void resetSizesInView(int red, int green, int blue) {
        this.redBar.setBounds(0,0,0, red);
        this.redField.setText("0");
        this.greenBar.setBounds(0,0,0, green);
        this.greenField.setText("0");
        this.blueBar.setBounds(0,0,0, blue);
        this.blueField.setText("0");
    }

}


