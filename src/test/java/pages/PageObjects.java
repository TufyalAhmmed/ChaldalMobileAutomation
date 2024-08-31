package pages;

import business.BasePage;

public class PageObjects extends BasePage {
    //search icon
    public static final String Search_Button_xpath = "//android.widget.TextView[@text='Search']";
    public static final String Search_Products_Input_xpath = "//android.widget.EditText[@text='Search Products']";
    public static final String Item_Toothbrush_xpath = "//android.widget.TextView[@text=\"Trisa Toothbrush Perfect White Medium (Assorted colour)\"]";
    public static final String Add_Button_xpath = "(//android.widget.TextView[contains(@text, 'Toothbrush')] /following-sibling::android.view.ViewGroup[last()])[4]";
    public static final String Checkout_Button_xpath = "//android.widget.TextView[@text='\uF1D8']/parent::android.view.ViewGroup";
    public static final String minas_Button_xpath = "(//android.widget.TextView[contains(@text, 'Toothbrush')]/following-sibling::android.view.ViewGroup[2])[last()]";
    public static final String Nothing_to_see_message_xpath = "//android.widget.TextView[@text=\"Nothing to see here\"]";


}

