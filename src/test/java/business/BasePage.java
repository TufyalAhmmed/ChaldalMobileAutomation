package business;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static utilities.DriverSetup.getDriver;

public class BasePage {
    public WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));

    public void wait_for_existence_of(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void wait_for_existence_of_all(By locator) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public void wait_for_invisibility_of(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void wait_for_invisibility_of(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void wait_for_clickable_of(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }


    public String getAttribute(By locator, String attribute) {
        wait_for_existence_of(locator);
        return getDriver().findElement(locator).getAttribute(attribute);
    }

    public String get_text_of(By locator) {
        wait_for_existence_of(locator);
        return getDriver().findElement(locator).getText();
    }


    public void click_on(By locator) {
        wait_for_clickable_of(locator);
        getDriver().findElement(locator).click();
    }


    public void sendKeysToElement(By locator, String text) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        try {
            // Wait for the element to be visible
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.clear(); // Clear any existing text in the input field
            element.sendKeys(text); // Send the specified text
        } catch (Exception e) {
            System.out.println("Error sending keys to element: " + e.getMessage());
        }
    }


    public void scrollToElementUsingMobileCommand(String direction) {
        // Use mobile: scroll command
        getDriver().executeScript("mobile: scroll", ImmutableMap.of("direction", direction));
    }

    public void scrollToElementExample(String xpath) {
        // Attempt to scroll to the element by trying to locate it
        boolean isElementFound = false;
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        while (!isElementFound) {
            try {
                WebElement element = getDriver().findElement(By.xpath(xpath));
                if (element.isDisplayed()) {
                    isElementFound = true;
                }
            } catch (NoSuchElementException e) {
                // Scroll down if the element is not found
                scrollToElementUsingMobileCommand("down");
            }
        }

        if (!isElementFound) {
            System.out.println("Element not found after scrolling down.");
        }
    }

    public void clickByCoordinates(int x, int y) {
        Actions action = new Actions(getDriver());
        action.moveByOffset(x, y).click().perform();
    }


}
