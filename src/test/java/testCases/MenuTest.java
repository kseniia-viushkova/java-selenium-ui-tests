package testCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.HeaderPage;
import pageObjects.MenuPage;
import testBase.BaseTest;

import static org.testng.Assert.assertEquals;

public class MenuTest extends BaseTest {

    private HeaderPage headerPage;
    private MenuPage menuPage;

    @BeforeClass
    public void setPages() {
        headerPage = new HeaderPage(driver);
        menuPage = new MenuPage(driver);
    }

    @Test(priority = 1)
    public void testPageTitleAndUrl() {
        String expectedUrl = properties.getProperty("baseUrl");
        String actualUrl = driver.getCurrentUrl();
        String expectedTitle = "Coffee cart";
        String actualTitle = driver.getTitle();

        assertEquals(actualUrl, expectedUrl, "Page URL is unexpected");
        assertEquals(actualTitle, expectedTitle, "Page title is unexpected");
    }

    @Test(priority = 2)
    public void testCartIsEmptyOnPageLoad() {
        assertEquals(headerPage.getCartCounter(), 0, "Cart counter is unexpected");
        assertEquals(menuPage.getTotalValue(), 0.0, 0.001, "Total value is unexpected");
    }

    @Test(priority = 3)
    public void testAddingOneCoffeeToCart() {
        int cupNumber = 0;
        float coffee_price = menuPage.getNthCoffeeItemPrice(cupNumber);

        menuPage.clickOnNthCup(cupNumber);

        assertEquals(headerPage.getCartCounter(), 1, "Cart counter is unexpected");
        assertEquals(menuPage.getTotalValue(), coffee_price, 0.001, "Total value is unexpected");
    }


}
