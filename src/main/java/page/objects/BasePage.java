package page.objects;

import configuration.WebListener;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.events.internal.EventFiringMouse;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class BasePage {

    private static final Logger logger = LoggerFactory.getLogger("BasePage.class");
    protected EventFiringWebDriver eventFiringWebDriver;
    protected EventFiringMouse eventFiringMouse;
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WebListener listener;
    protected JavascriptExecutor jse;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.of(12, ChronoUnit.SECONDS));
        this.listener = new WebListener();
        this.eventFiringWebDriver = new EventFiringWebDriver(driver);
        this.eventFiringMouse = new EventFiringMouse(driver,listener);
        this.jse = ((JavascriptExecutor)driver);
        eventFiringWebDriver.register(listener);
        PageFactory.initElements(driver,this);
    }

    public void clickObject(WebElement element){
        logger.debug("Click perform on the object: "+element.getText());
        highLiterMethod(element);
        element.click();
    }

    public void performSendKeys(WebElement element, String value){
        logger.debug("SendKeys perform on the object "+element.getText()+" following text has been sent "+value);
        highLiterMethod(element);
        element.clear();
        element.sendKeys(value);
    }

    private void highLiterMethod(WebElement element){
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 5px solid blue;')",element);
    }

    public void moseHover(WebElement element){
        Locatable item = (Locatable) element;
        Coordinates coordinates = item.getCoordinates();
        eventFiringMouse.mouseMove(coordinates);
    }

    public void scrollToElement(WebElement element){
        jse.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public String getPartOfText(String orgValue, String regex, int part){
        String[] split = orgValue.split(regex);
        return split[part];
    }
}
