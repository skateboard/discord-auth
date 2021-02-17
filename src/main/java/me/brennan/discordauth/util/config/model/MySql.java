package me.brennan.discordauth.util.config.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Brennan
 * @since 2/16/2021
 **/
public class MySql {
    @SerializedName("ip")
    private final String ip;

    @SerializedName("port")
    private final int port;

    @SerializedName("username")
    private final String username;

    @SerializedName("password")
    private final String password;

    @SerializedName("database")
    private final String database;

    public MySql(String ip, int port, String username, String password, String database) {
        this.ip = ip;
        this.port = port;
        this.username = username;
        this.password = password;
        this.database = database;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDatabase() {
        return database;
    }
}
