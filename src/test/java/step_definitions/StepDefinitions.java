package step_definitions;

import business.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.*;

import java.time.Duration;

import static utilities.DriverSetup.getDriver;

public class StepDefinitions {
    BasePage base = new BasePage();

    @Given("Search for the item toothbrush")
    public void searchForTheItemToothbrush() throws InterruptedException {
        By allowButton = By.xpath("//android.widget.Button[@text='While using the app']");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(allowButton));

            // Click the desired button (e.g., "While using the app")
            WebElement buttonToClick = getDriver().findElement(allowButton);
            buttonToClick.click();
        } catch (Exception e) {
            System.out.println("Permission dialog not found or error occurred: " + e.getMessage());
        }
        base.wait_for_clickable_of(By.xpath(PageObjects.Search_Button_xpath));
        base.click_on(By.xpath(PageObjects.Search_Button_xpath));
        base.wait_for_clickable_of(By.xpath(PageObjects.Search_Products_Input_xpath));
        base.sendKeysToElement(By.xpath(PageObjects.Search_Products_Input_xpath), "toothbrush");
        Thread.sleep(2000);
        getDriver().hideKeyboard();
        Thread.sleep(3000);


    }

    @When("Scroll down to an item and Open the Item screen")
    public void scrollDownToAnItemAndOpenTheItemScreen() throws InterruptedException {
        Thread.sleep(15000);
        base.scrollToElementExample(PageObjects.Item_Toothbrush_xpath);
    }

    @And("Click the Plus icon three times to add to the cart")
    public void clickThePlusIconThreeTimesToAddToTheCart() throws InterruptedException {
        Thread.sleep(3000);
        base.click_on(By.xpath(PageObjects.Add_Button_xpath));
        Thread.sleep(1000);
        base.click_on(By.xpath(PageObjects.Add_Button_xpath));
        Thread.sleep(1000);
        base.click_on(By.xpath(PageObjects.Add_Button_xpath));

    }

    @And("Go to the cart screen from the top")
    public void goToTheCartScreenFromTheTop() throws InterruptedException {

        Thread.sleep(5000);
        base.wait_for_clickable_of(By.xpath(PageObjects.Checkout_Button_xpath));
        base.click_on(By.xpath(PageObjects.Checkout_Button_xpath));
    }

    @And("Click the Minus icon to empty the card")
    public void clickTheMinusIconToEmptyTheCard() throws InterruptedException {
        Thread.sleep(5000);
        base.wait_for_clickable_of(By.xpath(PageObjects.minas_Button_xpath));
        base.click_on(By.xpath(PageObjects.minas_Button_xpath));
        Thread.sleep(2000);
        base.wait_for_clickable_of(By.xpath(PageObjects.minas_Button_xpath));
        base.click_on(By.xpath(PageObjects.minas_Button_xpath));
        Thread.sleep(2000);
        base.wait_for_clickable_of(By.xpath(PageObjects.minas_Button_xpath));
        base.click_on(By.xpath(PageObjects.minas_Button_xpath));
    }

    @Then("Verify Text Nothing to see here on cart screen after removing items")
    public void verifyTextNothingToSeeHereOnCartScreenAfterRemovingItems() throws InterruptedException {
        Thread.sleep(2000);
        base.wait_for_existence_of(By.xpath(PageObjects.Nothing_to_see_message_xpath));
        String expectedMessage = "Nothing to see here";
        Thread.sleep(2000);
        String finalMessage = base.get_text_of(By.xpath(PageObjects.Nothing_to_see_message_xpath));
        Thread.sleep(2000);
        Assert.assertEquals(finalMessage, expectedMessage, "The message does not match!");
    }
}
