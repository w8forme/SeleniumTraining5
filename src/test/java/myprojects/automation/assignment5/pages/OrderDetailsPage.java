package myprojects.automation.assignment5.pages;

import myprojects.automation.assignment5.utils.DataConverter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

/**
 * Created by Pavel Holinko on 03.12.2017.
 */
public class OrderDetailsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "h3.h1.card-title")
    private WebElement confirmationMessage;
    @FindBy(css = "div.col-sm-4.col-xs-9.details")
    private WebElement productNameElement;
    @FindBy(css = "div.col-xs-5.text-sm-right.text-xs-left")
    private WebElement productPriceElement;
    @FindBy(css = "div.order-line.row")
    private List<WebElement> orderItems;

    public OrderDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public int countOrderItems() {
        return orderItems.size();
    }

    public String getConfirmationMessage() {
        wait.until(ExpectedConditions.visibilityOf(confirmationMessage));
        return confirmationMessage.getText().trim().replaceAll("[^\\p{L}\\p{Z}]", "");
    }

    public String getProductName() {
        return productNameElement.getText();
    }

    public float getProductPrice() {
        return DataConverter.parsePriceValue(productPriceElement.getText());
    }
}
