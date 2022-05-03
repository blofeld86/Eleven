package page.objects;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class BlousesPage extends BasePage{

    @FindBy(css = "[title='Blouse']")
    private WebElement blouse;

    @FindBy(css = ".quick-view-mobile")
    private WebElement quickView;

    @FindBy(css = "div>iframe")
    private WebElement iframe;

    @FindBy(css = "#thumbs_list_frame img")
    private List<WebElement> littlePicList;

    @FindBy(id = "bigpic")
    private WebElement bigPic;

    @Getter
    private static List<String> bigImgSrcList = new ArrayList<>();
    @Getter
    private static List<String> littleListSrcList = new ArrayList<>();

    public BlousesPage(WebDriver driver) { super(driver);}

    public BlousesPage shouldMoveToImage(){
        scrollToElement(blouse);
        moseHover(blouse);
        return this;
    }

    public BlousesPage shouldOpenQuickViewWindow(){
        moseHover(quickView);
        clickObject(quickView);
        return this;
    }

    public BlousesPage shouldIterateThroughImages(){
        wait.until(ExpectedConditions.visibilityOf(iframe));
        driver.switchTo().frame(iframe);
        for (WebElement e: littlePicList) {
            moseHover(e);
            String one = bigPic.getAttribute("src");
            bigImgSrcList.add(getPartOfText(one,"-",0));
            String two = e.getAttribute("src");
            littleListSrcList.add(getPartOfText(two,"-",0));
        }
        return this;
    }


}
