package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfProperties {
    private static Properties properties = new Properties();

    static {
        try (FileInputStream fileInputStream = new FileInputStream("src/test/resources/conf.properties")) {
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getLogin() {
        return getProperty("login");
    }

    public static String getPassword() {
        return getProperty("password");
    }

    public static String getLoginPageUrl() {
        return getProperty("login_page");
    }

    public static String getChromeDriverPath() {
        return getProperty("chromedriverpath");
    }

    public static String getBasePage() {
        return getProperty("base_url");
    }

    public static String getLoginValidateUrl() {
        return getProperty("login_validate");
    }

    public static String getDashboardPageUrl() {
        return getProperty("dashboard_page");
    }

}

