package myprojects.automation.assignment5.pages;

import myprojects.automation.assignment5.utils.Properties;
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
public class MainPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id="menu-icon")
    private WebElement mobileMenuIcon;

    @FindBy(css = "a.all-product-link.pull-xs-left.pull-md-right.h4")
    private WebElement allProductsLink;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public boolean isMobileVersion() {
        return mobileMenuIcon.isDisplayed();
    }

    public MainPage openMainPage() {
        driver.get(Properties.getBaseUrl());
        return this;
    }

    public AllProductsPage clickAllProductsLink() {
        scrollToElement(allProductsLink);
        wait.until(ExpectedConditions.elementToBeClickable(allProductsLink)).click();
        return PageFactory.initElements(driver, AllProductsPage.class);
    }

    private void scrollToElement(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
