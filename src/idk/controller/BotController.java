package idk.controller;

import idk.ChildBot.ListBot;
import idk.ChildBot.StorageBot;
import idk.ChildBot.StoryBot;
import idk.Entity.Header;
import idk.Entity.Truyen;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import org.jsoup.nodes.Element;

public class BotController {
    private final Thread listThread;
    private final Thread storyThread;
    private final Thread storageThread;

    public BotController(String baseUrl) {
        BlockingQueue<Element> urlQueue = new LinkedBlockingQueue<>();
        BlockingQueue<String> headerUrlQueue = new LinkedBlockingQueue<>();
        BlockingQueue<Truyen> dataQueue = new LinkedBlockingQueue<>();

        ListBot listBot = new ListBot(baseUrl, urlQueue, headerUrlQueue);
        StoryBot storyBot = new StoryBot(urlQueue, dataQueue);
        StorageBot storageBot = new StorageBot(dataQueue);

        listThread = new Thread(listBot);
        storyThread = new Thread(storyBot);
        storageThread = new Thread(storageBot);
    }

    public void startBots() {
        listThread.start();
        storyThread.start();
        storageThread.start();
    }

    public void stopBots() {
        listThread.interrupt();
        storyThread.interrupt();
        storageThread.interrupt();
    }
}
