package utils;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class CookieAuth {
    public static void setCookies(WebDriver driver) {

        Map<String, String> cookies = ExtractCookie.getCookies();

        if (cookies == null || cookies.isEmpty()) {
            throw new RuntimeException("Не удалось получить куки для авторизации.");
        }

        String baseUrl = ConfProperties.getBasePage();
        String loginPage = ConfProperties.getLoginPageUrl();
        String dashboardPage = ConfProperties.getDashboardPageUrl();

        driver.get(baseUrl + loginPage);

        driver.manage().deleteAllCookies();

        cookies.forEach((name, value) -> {
            Cookie webCookie = new Cookie(name, value);
            driver.manage().addCookie(webCookie);
        });
        driver.get(baseUrl + dashboardPage);
    }
}
