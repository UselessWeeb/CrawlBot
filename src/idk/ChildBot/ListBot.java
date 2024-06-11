package idk.ChildBot;

import idk.Entity.Header;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.parser.Tag;

public class ListBot extends BaseBot {

    private final BlockingQueue<Element> urlQueue;
    private final BlockingQueue<String> headerUrlQueue;

    public ListBot(String baseUrl, BlockingQueue<Element> urlQueue, BlockingQueue<String> headerUrlQueue) {
        super(baseUrl);
        this.urlQueue = urlQueue;
        this.headerUrlQueue = headerUrlQueue;
    }

    @Override
    public void crawl(String baseUrl) {
        super.crawl(baseUrl);
        for (String url : visitedUrls) {
            if (Thread.currentThread().isInterrupted()) {
                break; // Thoát nếu luồng bị gián đoạn
            }
            try {
                Document doc = Jsoup.connect(url).get();
                String type = ".news-item"; // Nội dung cho câu chuyện
                
                Header header = this.extractHeader(url);
                if (header != null) System.out.println(header);
                
                Elements data = doc.select(type);
                for (Element item : data) {
                    if (Thread.currentThread().isInterrupted()) {
                        break; // Thoát nếu luồng bị gián đoạn
                    }
                    urlQueue.put(item); // Thêm dữ liệu vào hàng đợi
                }
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(ListBot.class.getName()).log(Level.SEVERE, null, ex);
                Thread.currentThread().interrupt();
                break; // Thoát nếu xảy ra gián đoạn
            }
        }
        try {
            System.out.println("End of the page");
            urlQueue.put(new Element(Tag.valueOf("end"), "")); // Thêm tín hiệu vào hàng đợi để thông báo kết thúc trang
        } catch (InterruptedException ex) {
            Logger.getLogger(ListBot.class.getName()).log(Level.SEVERE, null, ex);
            Thread.currentThread().interrupt();
        }
    }

    protected Header extractHeader(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            String pageNumber = extractPageNumber(url);
            String tags = doc.select(".title > h1 > a").html();
            if (tags.isEmpty()) return null;
            Header header = new Header(tags, pageNumber);
            return header;
        } catch (IOException ex) {
            Logger.getLogger(ListBot.class.getName()).log(Level.SEVERE, null, ex);
            Thread.currentThread().interrupt();
        }
        return null;
    }

    private String extractPageNumber(String url) {
        String[] parts = url.split("/"); // Tách URL bằng "/"
        String pagePart = parts[parts.length - 1]; // Lấy phần cuối cùng "num.html"
        String pageNumber = pagePart.split("\\.")[0]; // Tách bằng "." và lấy "2"
        if (!pageNumber.matches("\\d+")) {
            pageNumber = "1";
        }
        return pageNumber;
    }
}
