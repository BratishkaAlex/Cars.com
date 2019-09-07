package framework.elements;

import framework.browser.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static framework.utils.LoggerUtil.LOGGER;

public abstract class BaseElement {
    private String name = this.getClass().getSimpleName();
    private By loc;
    private WebElement webElement;

    public BaseElement(By loc) {
        LOGGER.info(String.format("Creating instance of custom %s", name));
        this.loc = loc;
        this.webElement = Browser.getDriver().findElement(loc);
    }

    protected void setWebElement(WebElement webElement) {
        this.webElement = webElement;
    }

    public boolean isDisplayed() {
        LOGGER.info(String.format("Checking displaying of custom %s", name));
        return webElement.isDisplayed();
    }

    public String getText() {
        LOGGER.info(String.format("Getting text of custom %s", name));
        return webElement.getText();
    }

    public WebElement findElement(By loc) {
        return webElement.findElement(loc);
    }

    public List<WebElement> findElements(By loc) {
        return webElement.findElements(loc);
    }

    protected WebElement getWebElement() {
        return this.webElement;
    }

    protected By getLoc() {
        return this.loc;
    }

}
