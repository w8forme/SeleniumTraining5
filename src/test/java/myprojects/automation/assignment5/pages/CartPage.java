package myprojects.automation.assignment5.pages;

import myprojects.automation.assignment5.model.ProductData;
import myprojects.automation.assignment5.utils.DataConverter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by Pavel Holinko on 03.12.2017.
 */
public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "li.cart-item")
    private List<WebElement> cartItems;
    @FindBy(css = "div.product-line-info > a.label")
    private WebElement productNameElement;
    @FindBy(css = "div.product-line-info:nth-child(2) > span.value")
    private WebElement productPriceElement;
    @FindBy(css = "a.btn.btn-primary")
    private WebElement proceedToCheckoutButton;


    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public int countCartItems() {
        return cartItems.size();
    }

    public ProductData getCartProductInfo() {
        String productName = productNameElement.getText();
        float productPrice = DataConverter.parsePriceValue(productPriceElement.getText());
        return new ProductData(productName, 1, productPrice);
    }

    public OrderPage clickProceedToCheckoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButton)).click();
        return PageFactory.initElements(driver, OrderPage.class);
    }

}
