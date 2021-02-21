package me.brennan.discordauth.models;

import java.sql.ResultSet;

/**
 * @author Brennan
 * @since 2/21/2021
 **/
public class StoredLicense {
    private final int id, usedBy;
    private final String code, createdAt;

    public StoredLicense(ResultSet resultSet) throws Exception {
        this.id = resultSet.getInt("id");
        this.usedBy = resultSet.getInt("used_by");
        this.code = resultSet.getString("code");
        this.createdAt = resultSet.getString("created_at");
    }

    public StoredLicense(String code, String createdAt) {
        this.id = 0;
        this.usedBy = 0;
        this.code = code;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public int getUsedBy() {
        return usedBy;
    }

    public String getCode() {
        return code;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
