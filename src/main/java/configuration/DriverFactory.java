package configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static configuration.YamlReader.*;

@Slf4j
public class DriverFactory {

    public WebDriver getDriver(Browser browser){
        WebDriver driver = null;
        try{
            switch (browser){
                case CHROME: driver = getChromeDriverAndProperties(); break;
                case FIREFOX: driver =getFirefoxDriverAndProperties(); break;
                default: driver = getIEDriverAndProperties();
            }
        }catch (NullPointerException e){
            log.error("Please select the browser correctly");
        }
        return driver;
    }

    public Browser getBrowserFromYaml(){ return Browser.valueOf(getProperties().getBrowser());}

    private WebDriver getChromeDriverAndProperties(){
        log.info("Successfully chosen chrome browser");
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        chromeOptions.addArguments("start-maximized");
        return new ChromeDriver();
    }

    private WebDriver getFirefoxDriverAndProperties(){
        log.info("Successfully chosen firefox browser");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        WebDriverManager.firefoxdriver().setup();
        firefoxOptions.addArguments("start-maximized");
        return new FirefoxDriver();
    }

    private WebDriver getIEDriverAndProperties(){
        log.info("Successfully chosen IE browser");
        InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
        WebDriverManager.iedriver().setup();
        return new InternetExplorerDriver(internetExplorerOptions);
    }


}
