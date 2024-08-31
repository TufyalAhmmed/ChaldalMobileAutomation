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
    public void wait_for_existence_of(By locator){
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void wait_for_existence_of_all(By locator){
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }
    public void wait_for_invisibility_of(By locator){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    public void wait_for_invisibility_of(WebElement element){
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void wait_for_clickable_of(By locator){
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement getElement(By locator){
        wait_for_existence_of(locator);
        return getDriver().findElement(locator);
    }

    public List<WebElement> getElements(By locator){
        wait_for_existence_of_all(locator);
        return getDriver().findElements(locator);
    }

    public String getAttribute(By locator, String attribute){
        wait_for_existence_of(locator);
        return getDriver().findElement(locator).getAttribute(attribute);
    }

    public String getAttribute(WebElement element, String attribute){
        return element.getAttribute(attribute);
    }


    public String get_text_of(By locator){
        wait_for_existence_of(locator);
        return getDriver().findElement(locator).getText();
    }

    public String get_text_of(WebElement element){
        return element.getText();
    }

    public List<String> get_list_of_text_of(By locator){
        wait_for_existence_of_all(locator);
        List<WebElement> elements = getDriver().findElements(locator);
        List<String> textList = new ArrayList<>();
        for (WebElement element : elements) {
            textList.add(element.getText());
        }
        return textList;
    }

    public List<String> get_list_of_text_of(List<WebElement> elements){
        List<String> textList = new ArrayList<>();
        for (WebElement element : elements) {
            textList.add(element.getText());
        }
        return textList;
    }

    public void click_on(By locator){
        wait_for_clickable_of(locator);
        getDriver().findElement(locator).click();
    }
    public void click_on(WebElement element){
        element.click();
    }

    public void click_and_wait_for_invisibility_of(By locator){
        wait_for_existence_of(locator);
        getDriver().findElement(locator).click();
        wait_for_invisibility_of(locator);
    }

    public void click_and_wait_for_invisibility_of(WebElement element){
        element.click();
        wait_for_invisibility_of(element);
    }


    public void enter_text_at(WebElement element, String text){
        element.clear();
        element.sendKeys(text);
    }

    public void scrollInToViewText(String text){
        getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(text(\"" + text + "\"));"));
    }

    public void scrollInSpecificAreaByHorizontal(By scrollArea, String text){
        String scrollAreaResourceID = getAttribute(scrollArea, "resource-id");
        getDriver().findElement(AppiumBy
                .androidUIAutomator("new UiScrollable(new UiSelector().resourceId(\""+scrollAreaResourceID+"\")).setAsHorizontalList().scrollIntoView("
                        + "new UiSelector().text(\""+text+"\"));"));
    }

    public By get_element_locator_by_text(String text){
        return By.xpath("//*[@text=\'" + text + "\']");
    }
    public void scrollInSpecificAreaByVertical(By scrollArea, String text){
        String scrollAreaResourceID = getAttribute(scrollArea, "resource-id");
        getDriver().findElement(AppiumBy
                .androidUIAutomator("new UiScrollable(new UiSelector().resourceId(\""+scrollAreaResourceID+"\")).setAsVerticalList().scrollIntoView("
                        + "new UiSelector().text(\""+text+"\"));"));
    }
    public void scrollUp() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollBy(0,-350)", "");
    }

    public void swipeVerticalUp(AppiumDriver appiumDriver, int anchor, int startY, int endY) throws Exception {
        Dimension size = appiumDriver.manage().window().getSize();
        int startPoint = (int) (size.width * anchor);
        int endPoint = (int) (size.width * anchor);
        TouchAction action = new TouchAction((PerformsTouchActions) appiumDriver);
        action.press(PointOption.point(startPoint, startY)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
                .moveTo(PointOption.point(endPoint, endY)).release().perform();
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

//    public void scrollToElement(String xpath) {
//        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
//        boolean isElementFound = false;
//
//        // Loop until the element is found or maximum scroll attempts are reached
//        for (int i = 0; i < 10; i++) { // Adjust the number of attempts as necessary
//            try {
//                // Try to find the element
//                MobileElement element = getDriver().findElement(By.xpath(xpath));
//                if (element.isDisplayed()) {
//                    isElementFound = true;
//                    break; // Exit the loop if found
//                }
//            } catch (Exception e) {
//                // Element not found, scroll down
//                getDriver().swipe(
//                        getDriver().manage().window().getSize().getWidth() / 2,
//                        getDriver().manage().window().getSize().getHeight() * 3 / 4,
//                        getDriver().manage().window().getSize().getWidth() / 2,
//                        getDriver().manage().window().getSize().getHeight() / 4,
//                        1000 // Duration of the swipe
//                );
//            }
//        }
//
//        if (!isElementFound) {
//            System.out.println("Element not found after scrolling.");
//        }
//    }

//    public void scrollToElementUsingMobileCommand(String xpath) {
//        // Use mobile: scroll command
//        getDriver().executeScript("mobile: scroll", ImmutableMap.of("direction", "down"));
//    }
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
