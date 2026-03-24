package pageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='app']/div[@class='list']/p")
    WebElement emptyCartMessage;

    public String getEmptyCartMessage() {
        try {
            return emptyCartMessage.getText();
        } catch (NoSuchElementException e) {
            return "";
        }
    }
}
