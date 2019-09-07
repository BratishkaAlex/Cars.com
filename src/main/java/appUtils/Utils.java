package appUtils;

import framework.browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class Utils {
    public static boolean elementIsDisplayed(By loc) {
        return Browser.getDriver().findElement(loc).isDisplayed();
    }

    public static int getRandomInt(int maxInt) {
        return new Random().nextInt(maxInt);
    }

    public static List<WebElement> getListElements(By loc) {
        return Browser.getDriver().findElements(loc);
    }
}
