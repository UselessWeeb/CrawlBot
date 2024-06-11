package idk.ChildBot;

import idk.Entity.Truyen;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StoryBot implements Runnable {
    private final BlockingQueue<Element> urlQueue;
    private final BlockingQueue<Truyen> dataQueue;

    public StoryBot(BlockingQueue<Element> urlQueue, BlockingQueue<Truyen> dataQueue) {
        this.urlQueue = urlQueue;
        this.dataQueue = dataQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Element item = urlQueue.take(); // Take story URLs from the queue
                if (item.tagName().equals("end")) {
                    dataQueue.put(new Truyen("end", "", "", "", "")); // Add a signal to the queue to notify the end of the page
                    break; // Check for sentinel value
                }
                processPageData(item);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    protected void processPageData(Element item) {
        try {
            String title = item.select("h4 > a").html();
            String linkUrl = item.select("h4 > a").attr("href");
            String img = item.select(".img > a > img").attr("src");
            String info = item.select(".info-post").text();
            String sapo = item.select(".sapo").text();

            Truyen t = new Truyen(title, img, linkUrl, info, sapo);
            dataQueue.put(t); // Add processed data to the queue
        } catch (InterruptedException ex) {
            Logger.getLogger(StoryBot.class.getName()).log(Level.SEVERE, null, ex);
            Thread.currentThread().interrupt();
        }
    }
}