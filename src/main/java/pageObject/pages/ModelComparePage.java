package pageObject.pages;

import framework.elements.Button;
import framework.elements.Label;
import framework.elements.Link;
import framework.utils.Waiter;
import org.openqa.selenium.By;
import pageObject.forms.SideBySideCompareMenu;

public class ModelComparePage {
    private SideBySideCompareMenu sideBySideCompareMenu;
    private final String patternForCar = "//h4[contains(text(), '%s') and contains(text(), '%s') and contains(text(), '%s')]";
    private By addCarBtnLoc = By.cssSelector(".add-car-icon");
    private By mainLabelLoc = By.cssSelector(".compare-subhead-h2");
    private final String patternForValues = "(//cars-compare-compare-info[@header = '%s']//div[@ng-switch-when='simple-list'])[%d]";

    public ModelComparePage() {
        sideBySideCompareMenu = new SideBySideCompareMenu();
    }

    public SideBySideCompareMenu getSideBySideCompareMenu() {
        return sideBySideCompareMenu;
    }

    private Link getLinkForAddedCar(String make, String model, String year) {
        return new Link(By.xpath(String.format(patternForCar, make, model, year)));
    }

    public boolean isDisplayedForCar(String make, String model, String year) {
        Waiter.waitForClickAble(By.xpath(String.format(patternForCar, make, model, year)));
        return getLinkForAddedCar(make, model, year).isDisplayed();
    }

    private Button getAddNewCarButton() {
        return new Button(addCarBtnLoc);
    }

    public void clickOnAddAnotherCar() {
        getAddNewCarButton().click();
    }

    private Label getLabelForValue(String name, int number) {
        return new Label(By.xpath(String.format(patternForValues, name, number)));
    }

    public String getValueForCar(String name, int number) {
        return getLabelForValue(name, number).getText();
    }
}
