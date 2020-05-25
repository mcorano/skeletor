package com.automation.techassessment.ui.pages.sauce;

import com.automation.techassessment.ui.lib.UIThreadManager;
import com.slickqa.webdriver.FindBy;
import com.slickqa.webdriver.PageElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


public class DashboardPage {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private PageElement productContainer = new PageElement("Product container", FindBy.id("inventory_container"));

    public boolean productContainerIsVisible() {

        return UIThreadManager.getBrowser().exists(productContainer);
    }

    public void addProductsToCart(String product) {

        List<WebElement> products = UIThreadManager.getBrowser().getDriver().findElements(By.cssSelector("div.inventory_item_name"));
        for (WebElement element : products) {
            if (element.getText().equals(product)) {
                element.findElement(By.xpath("../../..")).findElement(By.tagName("button")).click();
            }
        }
    }







}

