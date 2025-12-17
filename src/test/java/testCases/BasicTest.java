package testCases;

import org.testng.annotations.Test;
import pageObjects.MenuPage;
import testBase.BaseTest;

import static org.testng.Assert.assertEquals;

public class BasicTest extends BaseTest {

    @Test
    public void testAddingOneCoffeeToCart() {
        int cupNumber = 0;
        MenuPage menuPage = new MenuPage(driver);
        float coffee_price = menuPage.get_nth_coffee_item_price(cupNumber);

        menuPage.click_on_nth_cup(cupNumber);

        assertEquals(menuPage.getCartCounter(), 1, "Cart counter is unexpected");
        assertEquals(menuPage.getTotalValue(), coffee_price, 0.001, "Total value is unexpected");
    }


}
