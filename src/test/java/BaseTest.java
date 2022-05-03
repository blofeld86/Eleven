import configuration.DriverFactory;
import configuration.YamlReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static configuration.YamlReader.*;

public class BaseTest {

    private static final Logger logger = LoggerFactory.getLogger("BaseTest.class");
    protected DriverFactory driverFactory = new DriverFactory();
    protected WebDriver driver;

    @BeforeAll
    static void beforeAll() { YamlReader.getInstance();}

    @BeforeEach
    void setUp() {
        driver = driverFactory.getDriver(driverFactory.getBrowserFromYaml());
        driver.get(System.getProperty("appUrl"));
        logger.info("Driver initiated properly");
    }

    @AfterEach
    void tearDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
        logger.info("Driver closed properly");
    }
}