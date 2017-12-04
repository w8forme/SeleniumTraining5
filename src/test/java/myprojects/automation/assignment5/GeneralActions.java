package myprojects.automation.assignment5;


import myprojects.automation.assignment5.model.ProductData;
import myprojects.automation.assignment5.pages.*;
import myprojects.automation.assignment5.utils.logging.CustomReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Contains main script actions that may be used in scripts.
 */
public class GeneralActions {

    private WebDriver driver;
    private WebDriverWait wait;
    private String testProductLink;
    private MainPage mainPage;
    private AllProductsPage allProductsPage;
    private ProductDetailsPage productDetailsPage;
    private PopupProductOrderPage popupProductOrderPage;
    private CartPage cartPage;
    private OrderPage orderPage;
    private OrderDetailsPage orderDetailsPage;

    public GeneralActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
        mainPage = PageFactory.initElements(driver, MainPage.class);
    }

    public boolean isMobileVersion() {
        return mainPage.isMobileVersion();
    }

    public void openRandomProduct() {
        // TODO implement logic to open random product before purchase
        CustomReporter.logAction("Open main page");
        allProductsPage = mainPage.openMainPage().clickAllProductsLink();
        productDetailsPage = allProductsPage.clickOnRandomProduct();
    }

    /**
     * Extracts product information from opened product details page.
     *
     * @return
     */
    public ProductData getOpenedProductInfo() {
        CustomReporter.logAction("Get information about currently opened product");
        // TODO extract data from opened page
        testProductLink = productDetailsPage.getProductUrl();
        return productDetailsPage.getProductInfo();
    }

    public void addProductToCart() {
        popupProductOrderPage = productDetailsPage.clickAddToCartButton();
        cartPage = popupProductOrderPage.clickCheckoutButton();
    }

    public ProductData getCartProductInfo() {
        return cartPage.getCartProductInfo();
    }

    public int getCartSize() {
        return cartPage.countCartItems();
    }

    public void proceedToCheckout() {
        orderPage = cartPage.clickProceedToCheckoutButton();
    }


    public void fillUserInformation(String name, String lastName, String email) {
        orderPage.fillUserNameField(name)
                .fillLastNameField(lastName)
                .fillEmailField(email)
                .clickContinueButton();
        orderPage.fillAddressField("shevchenka 1")
                .fillPostcodeField("30000")
                .fillCityField("Kiyv")
                .clickContinueButton();
        orderPage.clickContinueButton();
        orderDetailsPage = orderPage.confirmPaymentOption()
                .checkTermsAndConditions()
                .clickConfirmOrderButton();
    }

    public ProductData getOrderProductInfo() {
        return new ProductData(orderDetailsPage.getProductName(),1, orderDetailsPage.getProductPrice());
    }

    public int getOrderSize() {
        return orderDetailsPage.countOrderItems();
    }

    public String getOrderConfirmationMsg() {
        return orderDetailsPage.getConfirmationMessage();
    }

    public int getTestedProductQty() {
        driver.get(testProductLink);
        productDetailsPage = PageFactory.initElements(driver, ProductDetailsPage.class);
        return productDetailsPage.getProductQty();
    }

}
