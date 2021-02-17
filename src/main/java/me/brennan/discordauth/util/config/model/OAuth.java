package me.brennan.discordauth.util.config.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Brennan
 * @since 2/16/2021
 **/
public class OAuth {
    @SerializedName("client_id")
    private final String clientID;

    @SerializedName("client_secret")
    private final String clientSecret;

    @SerializedName("redirect")
    private final String redirect;

    @SerializedName("scopes")
    private final String[] scopes;

    public OAuth(String clientID, String clientSecret, String redirect, String[] scopes) {
        this.clientID = clientID;
        this.clientSecret = clientSecret;
        this.redirect = redirect;
        this.scopes = scopes;
    }

    public String getClientID() {
        return clientID;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getRedirect() {
        return redirect;
    }

    public String[] getScopes() {
        return scopes;
    }
}
