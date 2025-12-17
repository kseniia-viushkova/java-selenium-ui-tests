package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MenuPage extends BasePage {

    public MenuPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[contains(@class, 'cup')]/ancestor::li//small")
    List<WebElement> coffeePrices;

    @FindBy(css = "ul .cup")
    List<WebElement> coffeeCups;

    @FindBy(css = "a[href=\"/cart\"]")
    WebElement cartLink;

    @FindBy(className = "pay")
    WebElement total;

    public float get_nth_coffee_item_price(int cupNumber) {
        return Float.parseFloat(coffeePrices.get(cupNumber).getText().replace("$", ""));
    }

    public void click_on_nth_cup(int cupNumber) {
        coffeeCups.get(cupNumber).click();
    }

    public int getCartCounter() {
        String counterText = cartLink.getText();
        return Integer.parseInt(
                counterText.substring(counterText.indexOf("(") + 1, counterText.indexOf(")")));
    }

    public float getTotalValue() {
        return Float.parseFloat(total.getText().replace("Total: $", ""));
    }
}
