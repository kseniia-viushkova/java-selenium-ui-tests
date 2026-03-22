package testBase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public Logger logger;
    public Properties properties;

    @BeforeClass
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) throws IOException {

        FileReader file = new FileReader(".//src//test//resources//config.properties");
        properties = new Properties();
        properties.load(file);

        logger = LogManager.getLogger(this.getClass());

        switch (browser) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                logger.warn("Browser type is unknown, defaulting to Chrome");
                driver = new ChromeDriver();
                break;
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(properties.getProperty("baseUrl"));
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
