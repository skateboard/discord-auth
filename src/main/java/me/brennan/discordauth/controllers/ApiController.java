package me.brennan.discordauth.controllers;

import com.google.gson.JsonObject;
import me.brennan.discordauth.DiscordAuth;
import me.brennan.discordauth.models.StoredLicense;
import me.brennan.discordauth.models.StoredUser;
import me.brennan.discordauth.util.discord.Embeds;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brennan
 * @since 2/13/2021
 **/
@RestController
@RequestMapping("api/v1/")
public class ApiController {

    @PostMapping("redeem")
    @ResponseBody
    public String redeemLicense(HttpServletRequest request, @RequestBody String requestBody) {
        final JsonObject responseObject = new JsonObject();
        final JsonObject requestObject = DiscordAuth.getINSTANCE().getGson().fromJson(requestBody, JsonObject.class);

        final StoredLicense storedLicense = DiscordAuth.getINSTANCE().getMySQL().getLicense(requestObject.get("license").getAsString());

        if(storedLicense != null) {
            final StoredUser storedUser = (StoredUser) request.getSession().getAttribute("user");

            if(storedUser != null) {
                if(storedLicense.getUsedBy() == 0) {
                    if(DiscordAuth.getINSTANCE().getMySQL().redeem(storedUser.getId(), storedLicense.getId())) {
                        Embeds.sendRedeemNotification(storedUser, storedLicense);
                        responseObject.addProperty("status", true);
                    } else {
                        responseObject.addProperty("status", false);
                        responseObject.addProperty("message", "Couldn't redeem license at this time!");
                    }
                } else {
                    responseObject.addProperty("status", false);
                    responseObject.addProperty("message", "License has already been used!");
                }
            } else {
                responseObject.addProperty("status", false);
                responseObject.addProperty("message", "User not found in session? Re-log");
            }
        } else {
            responseObject.addProperty("status", false);
            responseObject.addProperty("message", "License does not exist!");
        }

        return DiscordAuth.getINSTANCE().getGson().toJson(responseObject);
    }

}
