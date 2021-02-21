package me.brennan.discordauth;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.mokulu.discord.oauth.DiscordOAuth;
import me.brennan.discordauth.mysql.MySQL;
import me.brennan.discordauth.util.config.ConfigUtil;
import me.brennan.discordauth.util.config.model.Config;
import me.brennan.discordauth.util.config.model.MySql;
import me.brennan.discordauth.util.config.model.OAuth;
import me.brennan.discordauth.util.discord.DiscordWebHook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Brennan
 * @since 2/13/2021
 **/
@SpringBootApplication
public class DiscordAuth {
    private static DiscordAuth INSTANCE;

    private DiscordOAuth oauthHandler;

    private MySQL mySQL;

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private Config config;

    private DiscordWebHook discordWebHook;

    public void start() {
        this.config = ConfigUtil.loadConfig();

        if(config == null) {
            this.config = new Config("Cool Panel", 8080,
                    new OAuth("", "", "", new String[]{}),
                    new MySql("", 3306, "", "", ""));
            ConfigUtil.saveConfig(config);
        }
        if(config.getNotifications().isEnabled()) {
            this.discordWebHook = new DiscordWebHook(config.getNotifications().getWebHookUrl()).setUsername(config.getName());
        }

        this.mySQL = new MySQL(config.getMySQL());
        this.oauthHandler = new DiscordOAuth(config.getoAuth().getClientID(), config.getoAuth().getClientSecret(),
                config.getoAuth().getRedirect(), config.getoAuth().getScopes());

        SpringApplication.run(DiscordAuth.class);
    }

    public Gson getGson() {
        return gson;
    }

    public Config getConfig() {
        return config;
    }

    public DiscordOAuth getOAuthHandler() {
        return oauthHandler;
    }

    public MySQL getMySQL() {
        return mySQL;
    }

    public static DiscordAuth getINSTANCE() {
        if(INSTANCE == null) {
            INSTANCE = new DiscordAuth();
        }

        return INSTANCE;
    }

    public DiscordWebHook getDiscordWebHook() {
        return discordWebHook;
    }
}
