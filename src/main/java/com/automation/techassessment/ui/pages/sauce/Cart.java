package com.automation.techassessment.ui.pages.sauce;

import com.automation.techassessment.ui.lib.UIThreadManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


public class Cart {

    public boolean isProductPresent(String product) {
        boolean flag = false;
        List<WebElement> products = UIThreadManager.getBrowser().getDriver().findElements(By.cssSelector("div.inventory_item_name"));
        for (WebElement element : products) {
            if (element.getText().equals(product)) {
                flag = true;
            }
        }
        return flag;
    }
}

