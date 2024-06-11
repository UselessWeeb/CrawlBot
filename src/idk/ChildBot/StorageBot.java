package idk.ChildBot;

import idk.Entity.Truyen;

import java.util.concurrent.BlockingQueue;

public class StorageBot implements Runnable {

    private final BlockingQueue<Truyen> dataQueue;

    public StorageBot(BlockingQueue<Truyen> dataQueue) {
        this.dataQueue = dataQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Truyen truyen = dataQueue.take(); // Take processed data from the queue
                if (truyen.getHeader().equals("end")) {
                    break; // Check for sentinel value
                }
                storeData(truyen);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    protected void storeData(Truyen truyen) {
        // Implement your data storage logic here
        System.out.println(truyen);
    }
}
