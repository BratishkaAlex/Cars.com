package pageObject.forms;

import framework.elements.Button;
import framework.elements.Option;
import framework.utils.Waiter;
import org.openqa.selenium.By;

public class SideBySideCompareMenu {
    private final String pattern = "//select[@id='%s-dropdown']//option[text()='%s']";
    private final String patternForAnotherCar = "//h4[contains(text(), '%s') and contains(text(), '%s') and contains(text(), '%s')]";
    private By submitBtnLoc = By.cssSelector(".done-button");
    private By addAnotherCar = By.xpath("//button[@data-linkname='add-car-select']");


    private Option getOption(String option, String make) {
        return new Option(By.xpath(String.format(pattern, option, make)));
    }

    public void chooseOption(String option, String make) {
        getOption(option, make).click();
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

    public void addAnotherCar(String make, String model, String year) {
        getAddAnotherCarButton().click();
        Waiter.waitForClickAble(By.xpath(String.format(patternForAnotherCar, make, model, year)));
    }
}
