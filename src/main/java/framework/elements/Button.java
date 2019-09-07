package framework.elements;

import framework.utils.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static framework.utils.LoggerUtil.LOGGER;

public class Button extends BaseElement {
    public Button(By loc) {
        super(loc);
    }

    public Button(WebElement webElement, By loc) {
        super(loc);
        super.setWebElement(webElement);
    }

    public void click() {
        LOGGER.info(String.format("Click on custom %s", this.getClass().getSimpleName()));
        Waiter.waitForClickAble(super.getLoc());
        super.getWebElement().click();
    }
}
