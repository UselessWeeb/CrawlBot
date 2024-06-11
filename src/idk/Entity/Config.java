/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package idk.Entity;

/**
 *
 * @author M7510
 */
public class Config {
    String name;
    String password;
    String port;

    public Config(String name, String password, String port) {
        this.name = name;
        this.password = password;
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "Config{" + "name=" + name + ", password=" + password + ", port=" + port + '}';
    }
}
