package me.brennan.discordauth.util.config;

import me.brennan.discordauth.DiscordAuth;
import me.brennan.discordauth.util.config.model.Config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author Brennan
 * @since 2/16/2021
 **/
public class ConfigUtil {

    public static Config loadConfig() {
        try {
            final File configFile = new File("configs.json");

            if(!configFile.exists()) {
                return null;
            }
            final BufferedReader bufferedReader = new BufferedReader(new FileReader(configFile));

            return DiscordAuth.getINSTANCE().getGson().fromJson(bufferedReader, Config.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void saveConfig(Config config) {
        try {
            final File configFile = new File("configs.json");

            if(!configFile.exists()) {
                configFile.createNewFile();
            } else {
                configFile.delete();
            }

            final FileWriter writer = new FileWriter(configFile);
            writer.write(config.toObject().toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
