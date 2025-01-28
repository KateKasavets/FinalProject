package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.filter.cookie.CookieFilter;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class ExtractCookie {

    public static Map<String, String> getCookies() {

        RestAssured.baseURI = ConfProperties.getBasePage();

        CookieFilter cookieFilter = new CookieFilter();

        Response loginPageResponse = RestAssured.given()
                .filters(cookieFilter)
                .get(ConfProperties.getLoginPageUrl());

        System.out.println("Статус первого Get-запроса: " + loginPageResponse.getStatusCode());

        Map<String, String> cookiesFromLoginResponse = loginPageResponse.getCookies();
        String sessionCookie = cookiesFromLoginResponse.get("orangehrm");
        System.out.println("Извлеченная кука из первого запроса: " + sessionCookie);

        String token = loginPageResponse.htmlPath()
                .getNode("html")
                .getNode("body")
                .getNode("div")
                .getNode("auth-login")
                .getAttribute("{urn:x-prefix:_}token");

        if (token == null) {
            throw new IllegalStateException("Токен не найден");
        }
        System.out.println("Извлеченный токен: " + token);


        Response loginResponse = RestAssured.given()
                .cookie("orangehrm", sessionCookie)
                .formParam("_token", token)
                .formParam("username", ConfProperties.getLogin())
                .formParam("password", ConfProperties.getPassword())
                .post(ConfProperties.getLoginValidateUrl());

        System.out.println("Статус второго Post-запроса: " + loginResponse.getStatusCode());


        Map<String, String> updatedCookies = new HashMap<>(loginResponse.getCookies());
        String newSessionCookie = updatedCookies.get("orangehrm");

        if (newSessionCookie != null && !newSessionCookie.equals(sessionCookie)) {
            System.out.println("Обновленная кука из второго запроса: " + newSessionCookie);
            sessionCookie = newSessionCookie;
        } else {
            System.out.println("Кука не изменилась после второго запроса.");
        }


        if (loginResponse.getStatusCode() == 302) {
            String redirectUrl = loginResponse.getHeader("Location");
            System.out.println("Произошел редирект на: " + redirectUrl);


            WebDriver driver = WebDriverSingleton.getDriver();

            driver.get(ConfProperties.getBasePage());


            driver.manage().deleteAllCookies();

            Cookie sessionCookieForDriver = new Cookie.Builder("orangehrm", sessionCookie)
                    .domain("opensource-demo.orangehrmlive.com")
                    .path("/web")
                    .isSecure(true)
                    .isHttpOnly(true)
                    .sameSite("Lax")
                    .build();
            driver.manage().addCookie(sessionCookieForDriver);


            driver.get(ConfProperties.getBasePage() + ConfProperties.getDashboardPageUrl());


            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlToBe(ConfProperties.getBasePage() + ConfProperties.getDashboardPageUrl()));

            System.out.println("Текущий URL после редиректа: " + driver.getCurrentUrl());
        }
        return updatedCookies;
    }
}
