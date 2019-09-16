package pageObject.pages;

import appClasses.Car;
import framework.elements.Button;
import framework.elements.Label;
import framework.elements.Link;
import framework.utils.Waiter;
import org.openqa.selenium.By;
import pageObject.forms.SideBySideCompareMenu;

public class ModelComparePage {
    private final String PATTERN_FOR_CARS = "//h4[contains(text(), '%s') and contains(text(), '%s') and contains(text(), '%s')]";
    private final String PATTERN_FOR_VALUES = "(//cars-compare-compare-info[@header = '%s']//div[@ng-switch-when='simple-list'])[%d]";
    private SideBySideCompareMenu sideBySideCompareMenu;
    private By addCarBtnLoc = By.cssSelector(".add-car-icon");


    public ModelComparePage() {
        sideBySideCompareMenu = new SideBySideCompareMenu();
    }

    public SideBySideCompareMenu getSideBySideCompareMenu() {
        return sideBySideCompareMenu;
    }

    private Link getLinkForAddedCar(String make, String model, String year) {
        return new Link(By.xpath(String.format(PATTERN_FOR_CARS, make, model, year)), "Link with name of added car");
    }

    public boolean isDisplayedForCar(Car car) {
        Waiter.waitForClickAble(By.xpath(String.format(PATTERN_FOR_CARS, car.getMake(), car.getModel(), car.getYear())));
        return getLinkForAddedCar(car.getMake(), car.getModel(), car.getYear()).isDisplayed();
    }

    private Button getAddNewCarButton() {
        return new Button(addCarBtnLoc, "Button for add car to model compare page");
    }

    public void clickOnAddAnotherCar() {
        getAddNewCarButton().click();
    }

    private Label getLabelForValue(String name, int number) {
        return new Label(By.xpath(String.format(PATTERN_FOR_VALUES, name, number)), String.format("Label with %s for car", name));
    }

    public String getValueForCar(String name, int number) {
        return getLabelForValue(name, number).getText();
    }
}
