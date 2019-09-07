package pageObject.forms;

import framework.elements.Button;
import framework.elements.Option;
import org.openqa.selenium.By;

public class SideBySideCompareMenu {
    private final String pattern = "//select[@id='%s-dropdown']//option[text()='%s']";
    private By submitBtnLoc = By.cssSelector(".done-button");
    private By addAnotherCar = By.xpath("//button[@data-linkname='add-car-select']");


    private Option getOption(String option, String value) {
        return new Option(By.xpath(String.format(pattern, option, value)));
    }

    public void chooseOption(String option, String value) {
        getOption(option, value).click();
    }

    private Button getSubmitButton() {
        return new Button(submitBtnLoc);
    }

    public void submitCar() {
        getSubmitButton().click();
    }

    private Button getAddAnotherCarButton() {
        return new Button(addAnotherCar);
    }

    public void addAnotherCar() {
        getAddAnotherCarButton().click();
    }
}
