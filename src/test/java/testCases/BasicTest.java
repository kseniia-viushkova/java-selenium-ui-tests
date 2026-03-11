package testCases;

import org.testng.annotations.Test;
import pageObjects.MenuPage;
import testBase.BaseTest;

import static org.testng.Assert.assertEquals;

public class BasicTest extends BaseTest {

    @Test(priority = 1)
    public void testPageTitleAndUrl() {
        String expectedUrl = "https://coffee-cart.app/";
        String actualUrl = driver.getCurrentUrl();
        String expectedTitle = "Coffee cart";
        String actualTitle = driver.getTitle();

        assertEquals(actualUrl, expectedUrl, "Page URL is unexpected");
        assertEquals(actualTitle, expectedTitle, "Page title is unexpected");
    }

    @Test(priority = 2)
    public void testCartIsEmptyOnPageLoad() {
        MenuPage menuPage = new MenuPage(driver);

        assertEquals(menuPage.getCartCounter(), 0, "Cart counter is unexpected");
        assertEquals(menuPage.getTotalValue(), 0.0, 0.001, "Total value is unexpected");
    }

    @Test(priority = 3)
    public void testAddingOneCoffeeToCart() {
        int cupNumber = 0;
        MenuPage menuPage = new MenuPage(driver);
        float coffee_price = menuPage.get_nth_coffee_item_price(cupNumber);

        menuPage.click_on_nth_cup(cupNumber);

        assertEquals(menuPage.getCartCounter(), 1, "Cart counter is unexpected");
        assertEquals(menuPage.getTotalValue(), coffee_price, 0.001, "Total value is unexpected");
    }


}
