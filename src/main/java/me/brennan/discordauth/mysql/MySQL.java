package me.brennan.discordauth.mysql;

import com.mysql.cj.jdbc.MysqlDataSource;
import io.mokulu.discord.oauth.model.User;
import me.brennan.discordauth.models.StoredUser;
import me.brennan.discordauth.util.Util;
import me.brennan.discordauth.util.config.model.MySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Brennan
 * @since 2/13/2021
 **/
public class MySQL {
    private Connection connection;

    public MySQL(MySql mySql) {
        try {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUser(mySql.getUsername());
            dataSource.setPassword(mySql.getPassword());
            dataSource.setServerName(mySql.getIp());
            dataSource.setPort(mySql.getPort());
            dataSource.setDatabaseName(mySql.getDatabase());

            this.connection = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public StoredUser getUser(String discordID) {
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE discord_id = ?");
            preparedStatement.setString(1, discordID);

            final ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return new StoredUser(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public StoredUser createUser(User discordUser, String avatar) {
        try {
            final String date = Util.getDate();

            final PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO users(username, email, discord_id, avatar, last_login, created_at) VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, discordUser.getUsername());
            preparedStatement.setString(2, discordUser.getEmail());
            preparedStatement.setString(3, discordUser.getId());
            preparedStatement.setString(4, avatar);
            preparedStatement.setString(5, date);
            preparedStatement.setString(6, date);

            if(preparedStatement.execute()) {
                return new StoredUser(discordUser, avatar, date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
