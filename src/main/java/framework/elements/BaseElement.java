package framework.elements;

import framework.browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static framework.utils.LoggerUtil.LOGGER;

public abstract class BaseElement {
    private String name;
    private By loc;
    private WebElement webElement;

    public BaseElement(By loc, String name) {
        LOGGER.info(String.format("Creating instance of %s", name));
        this.loc = loc;
        this.name = name;
        this.webElement = Browser.getDriver().findElement(loc);
    }

    public static List<WebElement> getListElements(By loc) {
        return Browser.getDriver().findElements(loc);
    }

    public boolean isDisplayed() {
        LOGGER.info(String.format("Checking displaying of %s", name));
        return webElement.isDisplayed();
    }

    public String getText() {
        LOGGER.info(String.format("Getting text of %s", name));
        return webElement.getText();
    }

    public List<WebElement> findElements(By loc) {
        return webElement.findElements(loc);
    }

    protected WebElement getWebElement() {
        return this.webElement;
    }
}
