package myprojects.automation.assignment5.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

/**
 * Created by Pavel Holinko on 02.12.2017.
 */
public class AllProductsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "h1[itemprop=name]")
    private List<WebElement> productNameElements;

    public AllProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public ProductDetailsPage clickOnRandomProduct() {
        Random random = new Random();
        int randomElement = random.nextInt(productNameElements.size());
        productNameElements.get(randomElement).click();
        return PageFactory.initElements(driver, ProductDetailsPage.class);
    }
}
