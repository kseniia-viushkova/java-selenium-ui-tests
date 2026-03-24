package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderPage extends BasePage {

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "a[href='/cart']")
    WebElement cartLink;

    public int getCartCounter() {
        String counterText = cartLink.getText();
        return Integer.parseInt(
                counterText.substring(counterText.indexOf("(") + 1, counterText.indexOf(")")));
    }

    public void clickOnCartLink() {
        cartLink.click();
    }
}
