package me.brennan.discordauth.mysql;

import com.mysql.cj.jdbc.MysqlDataSource;
import io.mokulu.discord.oauth.model.User;
import me.brennan.discordauth.models.StoredLicense;
import me.brennan.discordauth.models.StoredUser;
import me.brennan.discordauth.util.Util;
import me.brennan.discordauth.util.config.model.MySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

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

    public List<StoredLicense> createLicenses(int ammount) {
        final List<StoredLicense> licenses = new LinkedList<>();
        for(int i = 0; i < ammount; i++) {
            final StoredLicense createdLicense = createLicense();

            if(createdLicense != null)
                licenses.add(createdLicense);
        }

        return licenses;
    }

    public StoredLicense createLicense() {
        try {
            final String code = UUID.randomUUID().toString();
            final String date = Util.getDate();

            final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO licenses(code, created_at) VALUES (?, ?)");
            preparedStatement.setString(1, code);
            preparedStatement.setString(2, date);

            preparedStatement.execute();

            return new StoredLicense(code, date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean redeem(int userID, int id) {
        try {
            final PreparedStatement licenseStatement = connection.prepareStatement("UPDATE licenses SET used_by = ? WHERE id = ?");
            licenseStatement.setInt(1, userID);
            licenseStatement.setInt(2, id);
            licenseStatement.execute();

            final PreparedStatement userStatement = connection.prepareStatement("UPDATE users SET rank = ? WHERE id = ?");
            userStatement.setString(1, "user");
            userStatement.setInt(2, userID);
            userStatement.execute();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public StoredLicense getLicense(String code) {
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM licenses WHERE code = ?");
            preparedStatement.setString(1, code);

            final ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return new StoredLicense(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public StoredLicense getLicense(int id) {
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM licenses WHERE id = ?");
            preparedStatement.setInt(1, id);

            final ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return new StoredLicense(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
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
