package myprojects.automation.assignment5.pages;

import org.apache.xpath.operations.Or;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by Pavel Holinko on 03.12.2017.
 */
public class OrderPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(name = "firstname")
    private WebElement firstNameInputField;
    @FindBy(name = "lastname")
    private WebElement lastNameInputField;
    @FindBy(name = "email")
    private WebElement emailInputField;
    @FindBy(name = "address1")
    private WebElement addressInputField;
    @FindBy(name = "postcode")
    private WebElement postcodeInputField;
    @FindBy(name = "city")
    private WebElement cityInputField;
    @FindBy(css = "#payment-option-2")
    private WebElement paymentByBankWire;
    @FindBy(id = "conditions_to_approve[terms-and-conditions]")
    private WebElement conditionsToApprove;
    @FindBy(css = "button.btn.btn-primary.center-block")
    private WebElement confirmOrderButton;
    @FindBy(css = "button.continue.btn.btn-primary.pull-xs-right")
    private List<WebElement> continueButtons;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public OrderPage fillUserNameField(String name) {
        firstNameInputField.sendKeys(name);
        return this;
    }

    public OrderPage fillLastNameField(String lastName) {
        lastNameInputField.sendKeys(lastName);
        return this;
    }

    public OrderPage fillEmailField(String email) {
        emailInputField.sendKeys(email);
        return this;
    }

    public OrderPage fillAddressField(String address){
        addressInputField.sendKeys(address);
        return this;
    }

    public OrderPage fillPostcodeField(String postcode){
        postcodeInputField.sendKeys(postcode);
        return this;
    }

    public OrderPage fillCityField(String city){
        cityInputField.sendKeys(city);
        return this;
    }

    public OrderPage confirmPaymentOption(){
        paymentByBankWire.click();
        return this;
    }

    public OrderPage checkTermsAndConditions(){
        conditionsToApprove.click();
        return this;
    }

    public OrderDetailsPage clickConfirmOrderButton() {
        confirmOrderButton.click();
        return PageFactory.initElements(driver, OrderDetailsPage.class);
    }

    public OrderPage clickContinueButton() {
        for (WebElement element :
                continueButtons) {
            if (element.isDisplayed()) {
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                return PageFactory.initElements(driver, OrderPage.class);
            }
        }
        return PageFactory.initElements(driver, OrderPage.class);
    }
}
