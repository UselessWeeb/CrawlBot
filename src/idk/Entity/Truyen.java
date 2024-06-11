/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package idk.Entity;

/**
 *
 * @author M7510
 */
public class Truyen {
    String header;
    String img;
    String url;
    String info;
    String content;

    public Truyen() {
        this.header = "";
        this.url = "";
        this.info = "";
        this.img = "";
        this.content = "";
    }

    public Truyen(String header, String img, String url, String info, String content) {
        this.header = header;
        this.img = img;
        this.url = url;
        this.info = info;
        this.content = content;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

//    public Truyen(String header, String img, String url, String info, String content) {
//        this.header = header;
//        this.url = url;
//        this.info = info;
//        this.img = img;
//        this.content = content;
//    }

    @Override
    public String toString() {
        return "Truyen: \n" + 
                "Tieu de=" + header + "\n" + 
                "img=" + img + "\n" +
                "url=" + url + "\n" +
                "info=" + info + "\n" +
                "content=" + content + "\n" +
                "---------------------------";               
    }  
}
