import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.objects.BlousesPage;
import page.objects.LandingPage;

import static org.assertj.core.api.Assertions.assertThat;
import static page.objects.BlousesPage.getBigImgSrcList;
import static page.objects.BlousesPage.getLittleSrcList;

public class QuickViewTest extends BaseTest{

    @DisplayName("Verification of the mouse hover")
    @Test
    void One() {
        LandingPage landingPage = new LandingPage(driver);
        landingPage
                   .openBlousesSubCategory()
                   .moveToImage()
                   .openQuickViewWindow()
                   .iterateThroughImages();
        assertThat(getBigImgSrcList())
                                      .isNotEmpty()
                                      .hasSize(new BlousesPage(driver).getLittleImgListSize())
                                      .isEqualTo(getLittleSrcList());
    }

}
