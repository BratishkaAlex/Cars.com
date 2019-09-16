package framework.elements;

import org.openqa.selenium.By;

import static framework.utils.LoggerUtil.LOGGER;

public class Option extends BaseElement {

    public Option(By loc, String name) {
        super(loc, name);
    }

    public void click() {
        LOGGER.info(String.format("Click on custom %s", this.getClass().getSimpleName()));
        super.getWebElement().click();
    }
}
