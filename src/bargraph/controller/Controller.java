package bargraph.controller;

import bargraph.controller.ResetMessage;
import bargraph.controller.Message;
import bargraph.controller.UpdateMessage;
import bargraph.model.BarModel;
import bargraph.view.View;

import java.util.concurrent.BlockingQueue;

public class Controller {
    BlockingQueue<Message> queue;
    BarModel barModel;
    View view;

    public Controller(BlockingQueue<Message> queue, BarModel barModel, View view) {
        this.queue = queue;
        this.barModel = barModel;
        this.view = view;
    }

    public void mainLoop() {
        while (view.isDisplayable()) {
            Message message = null;
            try {
                message = queue.take();
            } catch (InterruptedException exception) {
                // do nothing
            }

            if(message.getClass() == UpdateMessage.class) {
                UpdateMessage barMessage = (UpdateMessage) message;
                barModel.setRedSize(barMessage.getRedSize());
                barModel.setGreenSize(barMessage.getGreenSize());
                barModel.setBlueSize(barMessage.getBlueSize());
                view.updateSizesInView(barModel.getRedSize(), barModel.getGreenSize(), barModel.getBlueSize());
            }
            else if(message.getClass() == ResetMessage.class) {
                ResetMessage barMessage = (ResetMessage) message;
                barModel.setRedSize(barMessage.ResetMessage());
                barModel.setGreenSize(barMessage.ResetMessage());
                barModel.setBlueSize(barMessage.ResetMessage());
                view.resetSizesInView(0, 0,0);
            }

        }
    }
}

