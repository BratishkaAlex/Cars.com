package app.pageObject.forms;

import app.appClasses.Car;
import framework.elements.Button;
import framework.elements.Option;
import org.openqa.selenium.By;

public class SideBySideCompareMenu {
    private final String MAKE = "make";
    private final String MODEL = "model";
    private final String YEAR = "year";
    private final String PATTERN = "//select[@id='%s-dropdown']//option[text()='%s']";
    private By submitBtnLoc = By.cssSelector(".done-button");
    private By addAnotherCar = By.xpath("//button[@data-linkname='add-car-select']");


    private Option getOption(String option, String value) {
        return new Option(By.xpath(String.format(PATTERN, option, value)), String.format("Option for option '%s' for chosen car", option));
    }

    private void chooseOption(String option, String value) {
        getOption(option, value).click();
    }

    public void chooseCar(Car car) {
        chooseOption(MAKE, car.getMake());
        chooseOption(MODEL, car.getModel());
        chooseOption(YEAR, car.getYear());
    }

    private Button getSubmitButton() {
        return new Button(submitBtnLoc, "Button for adding car to compare");
    }

    public void submitCar() {
        getSubmitButton().click();
    }

    private Button getAddAnotherCarButton() {
        return new Button(addAnotherCar, "Button for adding another car to compare");
    }

    public void addAnotherCar() {
        getAddAnotherCarButton().click();
    }
}
