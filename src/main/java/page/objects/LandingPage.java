package page.objects;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Slf4j
public class LandingPage extends BasePage{

    @FindBy(css = "a[title='Women']")
    private WebElement women;

    @FindBy(css = "[title='Blouses']")
    private WebElement blouses;

    public LandingPage(WebDriver driver){ super(driver);}

    public BlousesPage openBlousesSubCategory(){
        moseHover(women);
        moseHover(blouses);
        clickObject(blouses);
        log.info("Successfully opened 'Blouses' category");
        return new BlousesPage(driver);
    }
}
