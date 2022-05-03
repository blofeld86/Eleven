import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import page.objects.LandingPage;
import static org.assertj.core.api.Assertions.assertThat;
import static page.objects.BlousesPage.*;


public class QuickViewTest extends BaseTest{

    @DisplayName("Verification of the mouse hover")
    @Test
    void One() throws InterruptedException {
        LandingPage landingPage = new LandingPage(driver);
        landingPage
                   .shouldOpenBlousesSubCategory()
                   .shouldMoveToImage()
                   .shouldOpenQuickViewWindow()
                   .shouldIterateThroughImages();

        assertThat(getBigImgSrcList())
                                      .isEqualTo(getLittleListSrcList())
                                      .isNotNull()
                                      .isNotEmpty()
                                      .hasSize(3);
        Thread.sleep(1500);
    }

}
