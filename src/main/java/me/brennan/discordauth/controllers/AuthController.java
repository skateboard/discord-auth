package me.brennan.discordauth.controllers;

import io.mokulu.discord.oauth.DiscordAPI;
import io.mokulu.discord.oauth.model.TokensResponse;
import io.mokulu.discord.oauth.model.User;
import me.brennan.discordauth.DiscordAuth;
import me.brennan.discordauth.models.StoredUser;
import me.brennan.discordauth.util.discord.Embeds;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brennan
 * @since 2/13/2021
 **/
@Controller
@RequestMapping("auth/")
public class AuthController {

    @GetMapping("logout")
    public String logout(HttpServletRequest request) {
        request.getSession().setAttribute("user", null);

        return "redirect:../";
    }

    @GetMapping("login")
    public void discordLogin(HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Location", DiscordAuth.getINSTANCE().getOAuthHandler().getAuthorizationURL(""));
        httpServletResponse.setStatus(302);
    }

    @GetMapping("callback")
    public String discordAuth(@RequestParam("code") String code, HttpServletRequest request) {
        try {
            final TokensResponse tokens = DiscordAuth.getINSTANCE().getOAuthHandler().getTokens(code);
            final DiscordAPI discordAPI = new DiscordAPI(tokens.getAccessToken());
            final User discordUser = discordAPI.fetchUser();
            final String avatar = "https://cdn.discordapp.com/avatars/" + discordUser.getId() + "/" + discordUser.getAvatar() + ".png";

            StoredUser storedUser = DiscordAuth.getINSTANCE().getMySQL().getUser(discordUser.getId());

            if(storedUser == null) {
                storedUser = DiscordAuth.getINSTANCE().getMySQL().createUser(discordUser, avatar);
            }
            request.getSession().setAttribute("user", storedUser);

            return "redirect:../dashboard/";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }

}
