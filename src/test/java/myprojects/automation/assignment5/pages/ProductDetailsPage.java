package myprojects.automation.assignment5.pages;

import myprojects.automation.assignment5.model.ProductData;
import myprojects.automation.assignment5.utils.DataConverter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Pavel Holinko on 02.12.2017.
 */
public class ProductDetailsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "h1[itemprop=name]")
    private WebElement productNameElement;
    @FindBy(css = "span[itemprop=price]")
    private WebElement productPriceElement;
    @FindBy(css = "div.product-quantities > span")
    private WebElement productQtyElement;
    @FindBy(css = "li.nav-item:nth-child(2)")
    private WebElement productAdditionalInfoTab;
    @FindBy(css = "button.btn.btn-primary.add-to-cart")
    private WebElement addToCartButton;


    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public ProductData getProductInfo() {
        String productName = productNameElement.getText();
        float productPrice = DataConverter.parsePriceValue(productPriceElement.getText());
        int productQty = getProductQty();
        return new ProductData(productName, productQty, productPrice);
    }

    public PopupProductOrderPage clickAddToCartButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
        return PageFactory.initElements(driver, PopupProductOrderPage.class);
    }

    public String getProductUrl() {
        return driver.getCurrentUrl();
    }

    public int getProductQty() {
        scrollToElement(productAdditionalInfoTab);
        productAdditionalInfoTab.click();
        wait.until(ExpectedConditions.visibilityOf(productQtyElement));
        return DataConverter.parseStockValue(productQtyElement.getText());
    }

    private void scrollToElement(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
