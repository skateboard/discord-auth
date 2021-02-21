package me.brennan.discordauth.util.discord;

import me.brennan.discordauth.DiscordAuth;
import me.brennan.discordauth.models.StoredLicense;
import me.brennan.discordauth.models.StoredUser;

import java.awt.*;

/**
 * @author Brennan
 * @since 2/21/2021
 **/
public class Embeds {

    public static void sendUserCreatedNotification(StoredUser user) {
        if (DiscordAuth.getINSTANCE().getDiscordWebHook() != null) {
            DiscordAuth.getINSTANCE().getDiscordWebHook()
                    .addField("Username", user.getUsername(), false)
                    .addField("Email", user.getEmail(), false)
                    .setColor(new Color(0x5FDB25))
                    .setAvatarMessage("User Created")
                    .setThumbnail(user.getAvatar())
                    .addEmbed("", "")
                    .send();
        } else {
            System.out.println("Couldn't send discord webhook!");
        }
    }

    public static void sendRedeemNotification(StoredUser user, StoredLicense license) {
        if(DiscordAuth.getINSTANCE().getDiscordWebHook() != null) {
            DiscordAuth.getINSTANCE().getDiscordWebHook()
                    .addField("User", user.getUsername() + "#" + user.getId(), false)
                    .addField("License", license.getCode(), false)
                    .setColor(new Color(0x5FDB25))
                    .setAvatarMessage("Successful Redeem")
                    .addEmbed("", "")
                    .send();
        } else {
            System.out.println("Couldn't send discord webhook!");
        }
    }

}
