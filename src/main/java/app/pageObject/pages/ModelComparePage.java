package app.pageObject.pages;

import app.appClasses.Car;
import app.pageObject.forms.SideBySideCompareMenu;
import framework.elements.Button;
import framework.elements.Label;
import framework.utils.Waiter;
import org.openqa.selenium.By;

public class ModelComparePage {
    private final String PATTERN_FOR_CARS = "//h4[contains(text(), '%s')]";
    private final String PATTERN_FOR_FULL_CAR_NAME = "(//h4[@class='listing-name'])[%d]";
    private final String PATTERN_FOR_VALUES = "(//cars-compare-compare-info[@header = '%s']//div[@ng-switch-when='simple-list'])[%d]";
    private final String ENGINE = "Engine";
    private final String TRANSMISSION = "Transmission";
    private SideBySideCompareMenu sideBySideCompareMenu;
    private By addCarBtnLoc = By.cssSelector(".add-car-icon");


    public ModelComparePage() {
        sideBySideCompareMenu = new SideBySideCompareMenu();
    }

    public SideBySideCompareMenu getSideBySideCompareMenu() {
        return sideBySideCompareMenu;
    }

    public boolean isDisplayedForCar(Car car, int numberOfCar) {
        Waiter.waitForClickAble(By.xpath(String.format(PATTERN_FOR_CARS, car.getFullName())));
        return getAddedCar(numberOfCar).equals(car);
    }

    private Label getLabelForCar(int number) {
        return new Label(By.xpath(String.format(PATTERN_FOR_FULL_CAR_NAME, number)), "Label with fullName of car");
    }

    private String getFullNameOfCar(int number) {
        return getLabelForCar(number).getText();
    }

    public Car getAddedCar(int number) {
        Car car = new Car(getFullNameOfCar(number));
        car.setEngine(getValueForCar(ENGINE, number));
        car.setTransmission(getValueForCar(TRANSMISSION, number));
        return car;
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

    private String getValueForCar(String name, int number) {
        return getLabelForValue(name, number).getText();
    }
}
