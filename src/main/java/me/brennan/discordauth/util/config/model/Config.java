package me.brennan.discordauth.util.config.model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import me.brennan.discordauth.DiscordAuth;

/**
 * @author Brennan
 * @since 2/16/2021
 **/
public class Config {

    @SerializedName("name")
    private final String name;

    @SerializedName("port")
    private final int port;

    @SerializedName("oauth")
    private final OAuth oAuth;

    @SerializedName("mysql")
    private final MySql mySQL;

    public Config(String name, int port, OAuth oAuth, MySql mySQL) {
        this.name = name;
        this.port = port;
        this.oAuth = oAuth;
        this.mySQL = mySQL;
    }

    public String getName() {
        return name;
    }

    public int getPort() {
        return port;
    }

    public MySql getMySQL() {
        return mySQL;
    }

    public OAuth getoAuth() {
        return oAuth;
    }

    public JsonObject toObject() {
        return DiscordAuth.getINSTANCE().getGson().fromJson(DiscordAuth.getINSTANCE().getGson().toJson(this), JsonObject.class);
    }
}
