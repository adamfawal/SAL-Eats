package Util;

import java.util.regex.Pattern;

public class Constant {
    //TODO replace it with your DB credentials
    static public String DBUserName = "";
    static public String DBPassword = "";
    private static String FileName = "restaurant_data.json";

    static public Pattern namePattern = Pattern.compile("^[ A-Za-z]+$");
    static public Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\."
            + "[a-zA-Z0-9_+&*-]+)*@"
            + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
            + "A-Z]{2,7}$");

}
