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

    //Сначала общая картина- что за чем
    public static Map<String, String> getCookies() {
        RestAssured.baseURI = ConfProperties.getBasePage();

        CookieFilter cookieFilter = new CookieFilter();
        Response loginPageResponse = sendGetRequest(cookieFilter);

        Map<String, String> cookiesFromLoginResponse = loginPageResponse.getCookies();
        String sessionCookie = getSessionCookie(cookiesFromLoginResponse);
        String token = getTokenFromResponse(loginPageResponse);

        Response loginResponse = sendPostRequest(sessionCookie, token);

        Map<String, String> updatedCookies = getUpdatedCookies(loginResponse, sessionCookie);

        Redirect(loginResponse, updatedCookies.get("orangehrm"));

        return updatedCookies;
    }

    //Дальше пошли все запросы и методы
    private static Response sendGetRequest(CookieFilter cookieFilter) {
        Response response = RestAssured.given()
                .filters(cookieFilter)
                .get(ConfProperties.getLoginPageUrl());
        System.out.println("Статус первого GET-запроса: " + response.getStatusCode());
        return response;
    }

    private static String getSessionCookie(Map<String, String> cookiesFromLoginResponse) {
        String sessionCookie = cookiesFromLoginResponse.get("orangehrm");
        System.out.println("Извлеченная кука из первого запроса: " + sessionCookie);
        return sessionCookie;
    }

    private static String getTokenFromResponse(Response response) {
        String token = response.htmlPath()
                .getNode("html")
                .getNode("body")
                .getNode("div")
                .getNode("auth-login")
                .getAttribute("{urn:x-prefix:_}token");
        if (token == null) {
            throw new IllegalStateException("Токен не найден");
        }
        System.out.println("Извлеченный токен: " + token);
        return token;
    }

    private static Response sendPostRequest(String sessionCookie, String token) {
        Response response = RestAssured.given()
                .cookie("orangehrm", sessionCookie)
                .formParam("_token", token)
                .formParam("username", ConfProperties.getLogin())
                .formParam("password", ConfProperties.getPassword())
                .post(ConfProperties.getLoginValidateUrl());
        System.out.println("Статус второго POST-запроса: " + response.getStatusCode());
        return response;
    }

    private static Map<String, String> getUpdatedCookies(Response loginResponse, String sessionCookie) {
        Map<String, String> updatedCookies = new HashMap<>(loginResponse.getCookies());
        String newSessionCookie = updatedCookies.get("orangehrm");

        if (newSessionCookie != null && !newSessionCookie.equals(sessionCookie)) {
            System.out.println("Обновленная кука из второго запроса: " + newSessionCookie);
        } else {
            System.out.println("Кука не изменилась после второго запроса.");
        }
        return updatedCookies;
    }

    private static void Redirect(Response loginResponse, String sessionCookie) {
        if (loginResponse.getStatusCode() == 302) {
            String redirectUrl = loginResponse.getHeader("Location");
            System.out.println("Произошел редирект на: " + redirectUrl);

            WebDriver driver = WebDriverSingleton.getDriver();
            driver.get(ConfProperties.getBasePage());
            driver.manage().deleteAllCookies();
            driver.manage().addCookie(new Cookie("orangehrm", sessionCookie));

            String dashboardUrl = ConfProperties.getBasePage() + ConfProperties.getDashboardPageUrl();
            driver.get(dashboardUrl);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlToBe(dashboardUrl));

            System.out.println("Текущий URL после редиректа: " + driver.getCurrentUrl());
        }
    }
}
