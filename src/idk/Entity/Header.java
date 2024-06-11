/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package idk.Entity;

/**
 *
 * @author M7510
 */
public class Header {
    String Tags;
    String page;

    public Header() {
    }

    public Header(String Tags, String page) {
        this.Tags = Tags;
        this.page = page;
    }

    public String getTags() {
        return Tags;
    }

    public void setTags(String Tags) {
        this.Tags = Tags;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "Tags=" + Tags + ", page=" + page;
    }
}
