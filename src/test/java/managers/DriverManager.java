package managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {
    private static DriverManager INSTANCE = null;

    private DriverManager(){

    }
    public static DriverManager getDriverManager(){
        if (INSTANCE == null) {
            INSTANCE = new DriverManager();
        }
        return INSTANCE ;

    }
    /**
     * Lazy Singletone
     */
    private WebDriver driver = null;

    public WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
        }
        return driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
