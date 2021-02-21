package me.brennan.discordauth.controllers;

import me.brennan.discordauth.DiscordAuth;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brennan
 * @since 2/16/2021
 **/
@Controller
public class AppController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute("name", DiscordAuth.getINSTANCE().getConfig().getName());
        return "index";
    }

    @GetMapping("dashboard/redeem")
    public String redeem(HttpServletRequest request, Model model) {
        if(request.getSession().getAttribute("user") != null) {
            model.addAttribute("name", DiscordAuth.getINSTANCE().getConfig().getName());

            return "redeem";
        }

        return "redirect:/";
    }

    @GetMapping("dashboard/")
    public String dashboard(HttpServletRequest request, Model model) {
        if(request.getSession().getAttribute("user") != null) {
            model.addAttribute("name", DiscordAuth.getINSTANCE().getConfig().getName());

            return "dashboard";
        }

        return "redirect:/";
    }

}
