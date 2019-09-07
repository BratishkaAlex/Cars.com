package framework.elements;

import framework.utils.Waiter;
import org.openqa.selenium.By;

import static framework.utils.LoggerUtil.LOGGER;

public class Link extends BaseElement {
    public Link(By loc) {
        super(loc);
    }

    public void click() {
        LOGGER.info(String.format("Click on custom %s", this.getClass().getSimpleName()));
        Waiter.waitForClickAble(super.getLoc());
        super.getWebElement().click();
    }
}
