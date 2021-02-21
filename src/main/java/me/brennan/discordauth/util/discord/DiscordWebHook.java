package me.brennan.discordauth.util.discord;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;

import java.awt.*;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Brennan
 * @since 2/21/2021
 **/
public class DiscordWebHook {
    private String url, username, content, avatarUrl, thumbnail, avatarMessage;

    private Color color;

    private WebhookEmbed embed;

    private final List<WebhookEmbed.EmbedField> fields = new LinkedList<>();

    public DiscordWebHook(String url) {
        this.url = url;
    }

    public DiscordWebHook setUrl(String url) {
        this.url = url;
        return this;
    }

    public DiscordWebHook setColor(Color color) {
        this.color = color;
        return this;
    }

    public DiscordWebHook setAvatar(String avatar) {
        this.avatarUrl = avatar;
        return this;
    }

    public DiscordWebHook setUsername(String username) {
        this.username = username;
        return this;
    }

    public DiscordWebHook setContent(String content) {
        this.content = content;
        return this;
    }

    public DiscordWebHook setAvatarMessage(String avatarMessage) {
        this.avatarMessage = avatarMessage;
        return this;
    }

    public DiscordWebHook addField(String name, String content, boolean sameLine) {
        this.fields.add(new WebhookEmbed.EmbedField(sameLine, name, content));
        return this;
    }

    public DiscordWebHook setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public DiscordWebHook addEmbed(String title, String description) {
        final WebhookEmbedBuilder embedBuilder = new WebhookEmbedBuilder();

        embedBuilder.setDescription(description);
        embedBuilder.setTitle(new WebhookEmbed.EmbedTitle(title, avatarUrl));
        embedBuilder.setColor(color.getRGB());
        embedBuilder.setTimestamp(Instant.now());
        embedBuilder.setThumbnailUrl(thumbnail);
        embedBuilder.setAuthor(new WebhookEmbed.EmbedAuthor(avatarMessage, avatarUrl, ""));
        this.fields.forEach(embedBuilder::addField);

        this.embed = embedBuilder.build();
        return this;
    }

    public void send() {
        final WebhookMessageBuilder webhookMessageBuilder = new WebhookMessageBuilder();
        webhookMessageBuilder.setUsername(username);
        webhookMessageBuilder.setAvatarUrl(avatarUrl);
        webhookMessageBuilder.addEmbeds(embed);

        try (WebhookClient client = WebhookClient.withUrl(url)) {
            client.send(webhookMessageBuilder.build());
        }
    }
}
