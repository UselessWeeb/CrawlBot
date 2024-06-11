/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package idk;

import idk.ChildBot.BaseBot;
import idk.ChildBot.ListBot;
import idk.ChildBot.StorageBot;
import idk.ChildBot.StoryBot;
import idk.Entity.Config;
import idk.Entity.Truyen;
import idk.controller.BotController;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.jsoup.nodes.Element;

/**
 *
 * @author M7510
 */
public class Idk {

    public static void main(String[] args) throws InterruptedException {
        BotController controller = new BotController("https://cotich.net/");
        controller.startBots();
//        Thread.sleep(5000); // Run for 5 seconds
//        controller.stopBots();
    }
    
    

}
