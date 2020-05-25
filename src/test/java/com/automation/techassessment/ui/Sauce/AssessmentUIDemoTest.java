package com.automation.techassessment.ui.Sauce;

import com.automation.techassessment.ui.pages.sauce.SauceDemo;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AssessmentUIDemoTest extends BaseUITest {

    @DataProvider(name = "listOfProducts")
    public Object[][] dataProvider() {
        return new Object[][] { { "Sauce Labs Onesie", "Sauce Labs Bike Light" }, { "Onesie Labs", "Sauce Labs Bike Light" } };
    }

    @Test (dataProvider = "listOfProducts")
    public void verifyAddedProductsInCart(String product1, String product2) throws Exception {

        SoftAssert softAssert = new SoftAssert();
        SauceDemo.Login.login("standard_user", "secret_sauce");
        SauceDemo.Dashboard.addProductsToCart(product1);
        SauceDemo.Dashboard.addProductsToCart(product2);
        SauceDemo.MenuBar.clickShopingCart();
        softAssert.assertTrue(SauceDemo.Cart.isProductPresent(product1), "Unable to find Product " + product1 + " in the shopping cart");
        softAssert.assertTrue(SauceDemo.Cart.isProductPresent(product2), "Unable to find Product " + product2 + " in the shopping cart");
        softAssert.assertAll();
    }
}
