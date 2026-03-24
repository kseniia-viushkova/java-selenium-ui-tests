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

    @FindBy(className = "pay")
    WebElement total;

    public float getNthCoffeeItemPrice(int cupNumber) {
        return Float.parseFloat(coffeePrices.get(cupNumber).getText().replace("$", ""));
    }

    public void clickOnNthCup(int cupNumber) {
        coffeeCups.get(cupNumber).click();
    }

    public float getTotalValue() {
        return Float.parseFloat(total.getText().replace("Total: $", ""));
    }
}
