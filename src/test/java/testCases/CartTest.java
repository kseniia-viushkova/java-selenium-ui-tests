package testCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.HeaderPage;
import testBase.BaseTest;

import static org.testng.Assert.assertEquals;

public class CartTest extends BaseTest {

    private HeaderPage headerPage;
    private CartPage cartPage;

    @BeforeClass
    public void setPages() {
        headerPage = new HeaderPage(driver);
        cartPage = new CartPage(driver);
    }

    @Test
    public void testEmptyCartMessage() {
        headerPage.clickOnCartLink();
        String expected = "No coffee, go add some.";
        String actual = cartPage.getEmptyCartMessage();
        assertEquals(actual, expected, "Empty cart message is unexpected");
    }
}
