package page.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends BasePage{

    @FindBy(css = "a[title='Women']")
    private WebElement women;

    @FindBy(css = "[title='Blouses']")
    private WebElement blouses;

    public LandingPage(WebDriver driver){ super(driver);}

    public BlousesPage shouldOpenBlousesSubCategory(){
        moseHover(women);
        moseHover(blouses);
        clickObject(blouses);
        return new BlousesPage(driver);
    }
}
