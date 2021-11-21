package bargraph.controller;

public class UpdateMessage implements Message {
    int redSize;
    int greenSize;
    int blueSize;

    public UpdateMessage(int red, int green, int blue) {
        redSize = red;
        greenSize = green;
        blueSize = blue;
    }

    public int getRedSize() {
        return redSize;
    }

    public int getGreenSize() {
        return greenSize;
    }

    public int getBlueSize() {
        return blueSize;
    }
}
