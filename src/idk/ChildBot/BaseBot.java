package idk.ChildBot;

import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class BaseBot extends Thread {

    final String baseUrl;
    final List<String> visitedUrls = new ArrayList<>();

    public BaseBot(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public void run() {
        crawl(baseUrl);
    }

    public Elements display(String url, String type) {
        Elements datas = null;
        try {
            Document doc = Jsoup.connect(url).get();
            datas = doc.select(type);
        } catch (IOException e) {
        }
        return datas;
    }

    public void crawl(String url) {
        try {
            if (!visitedUrls.contains(url)) {
                Document doc = Jsoup.connect(url).get();
                visitedUrls.add(url);
                Elements links = doc.select(".menuTop a[href]");
                // Get all the links inside the menuTop class
                for (Element link : links) {
                    String linkHref = link.attr("href");
                    if (!visitedUrls.contains(linkHref)) {
                        visitedUrls.add(linkHref);
                        // Upon there, iterate the web inside
                        crawlPage(linkHref);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error crawling " + url + ": " + e.getMessage());
        }
    }

    protected void crawlPage(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            // Search for paging (if exist)
            Elements pagings = doc.select(".paging a[href]");
            for (Element page : pagings) {
                String pageHref = page.attr("href");
                // Go each page too, make sure each is not already in visitedUrl
                if (!visitedUrls.contains(pageHref) && !pageHref.endsWith("/1.html")) {
                    visitedUrls.add(pageHref);
                    // Also, each sub has some new paging too
                    crawlPage(pageHref); // Recursively crawl the pagination links
                }
            }
        } catch (IOException e) {
            System.err.println("Error crawling " + url + ": " + e.getMessage());
        }     
    }

    private void printVisitedUrls() {
        for(String s : visitedUrls){
            System.out.println(s);
        }
    }
}