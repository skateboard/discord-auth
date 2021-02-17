package me.brennan.discordauth.models;

import io.mokulu.discord.oauth.model.User;

import java.sql.ResultSet;

/**
 * @author Brennan
 * @since 2/13/2021
 **/
public class StoredUser {
    private final int id;

    private final String username, email, discord_id, avatar, lastLogin, createdAt;

    public StoredUser(int id, String username, String email, String discord_id, String avatar, String createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.discord_id = discord_id;
        this.avatar = avatar;
        this.lastLogin = createdAt;
        this.createdAt = createdAt;
    }

    public StoredUser(ResultSet resultSet) throws Exception {
        this.id = resultSet.getInt("id");
        this.username = resultSet.getString("username");
        this.email = resultSet.getString("email");
        this.discord_id = resultSet.getString("discord_id");
        this.avatar = resultSet.getString("avatar");
        this.lastLogin = resultSet.getString("last_login");
        this.createdAt = resultSet.getString("created_at");
    }

    public StoredUser(User discordUser, String avatar, String date) {
        this.username = discordUser.getUsername();
        this.email = discordUser.getEmail();
        this.discord_id = discordUser.getId();
        this.avatar = avatar;
        this.id = 0;
        this.createdAt = date;
        this.lastLogin = date;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getDiscord_id() {
        return discord_id;
    }

    public String getAvatar() {
        return avatar;
    }
}
