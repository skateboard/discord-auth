package me.brennan.discordauth.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Brennan
 * @since 2/16/2021
 **/
public class Util {
    private final static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    public static String getDate() {
        final Date date = new Date();
        return dateFormat.format(date);
    }
}
