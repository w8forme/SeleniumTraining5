package myprojects.automation.assignment5.tests;

import myprojects.automation.assignment5.BaseTest;
import myprojects.automation.assignment5.model.ProductData;
import myprojects.automation.assignment5.model.UserData;
import myprojects.automation.assignment5.utils.Properties;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlaceOrderTest extends BaseTest {

    @Test
    public void checkSiteVersion() {
        // TODO open main page and validate website version
        driver.get(Properties.getBaseUrl());
        Assert.assertEquals(isMobileTesting, actions.isMobileVersion());
    }

    @Test(dependsOnMethods = "checkSiteVersion")
    public void createNewOrder() {
        // TODO implement order creation test

        // open random product
        actions.openRandomProduct();
        // save product parameters
        ProductData openedProductData = actions.getOpenedProductInfo();
        // add product to Cart and validate product information in the Cart
        actions.addProductToCart();
        ProductData cartProductData = actions.getCartProductInfo();
        int cartSizeExpected = 1;
        int cartSizeActual = actions.getCartSize();
        Assert.assertEquals(cartSizeActual, cartSizeExpected);
        Assert.assertEquals(cartProductData.getName().toLowerCase(), openedProductData.getName().toLowerCase());
        Assert.assertEquals(cartProductData.getPrice(), openedProductData.getPrice());
        // proceed to order creation, fill required information
        UserData userData = UserData.generate();
        actions.proceedToCheckout();
        actions.fillUserInformation(userData.getFirstname(), userData.getLastname(), userData.getEmail());
        // place new order and validate order summary
        String confirmationMsg = "ваш заказ подтверждён";
        int orderSizeExpected = 1;
        ProductData orderProductData = actions.getOrderProductInfo();
        Assert.assertEquals(actions.getOrderConfirmationMsg().toLowerCase(), confirmationMsg);
        Assert.assertEquals(actions.getOrderSize(), orderSizeExpected);
//        Assert.assertEquals(orderProductData.getName(), openedProductData.getName());
        Assert.assertEquals(orderProductData.getPrice(), openedProductData.getPrice());
        // check updated In Stock value
        Assert.assertEquals(actions.getTestedProductQty(), openedProductData.getQty() - 1);
    }

}
