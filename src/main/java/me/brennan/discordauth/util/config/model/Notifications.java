package me.brennan.discordauth.util.config.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Brennan
 * @since 2/21/2021
 **/
public class Notifications {
    @SerializedName("enabled")
    private final boolean enabled;
    @SerializedName("webhook_url")
    private final String webHookUrl;

    @SerializedName("mode")
    private final String mode;

    public Notifications() {
        this.enabled = false;
        this.webHookUrl = "";
        this.mode = "basic";
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getMode() {
        return mode;
    }

    public String getWebHookUrl() {
        return webHookUrl;
    }
}
