package myprojects.automation.assignment5.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Pavel Holinko on 02.12.2017.
 */
public class PopupProductOrderPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "a.btn.btn-primary")
    private WebElement checkoutButton;


    public PopupProductOrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public CartPage clickCheckoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
        return PageFactory.initElements(driver, CartPage.class);
    }


}
