package pageObject.forms;

import appClasses.Car;
import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static framework.elements.BaseElement.getListElements;
import static framework.utils.MathUtils.getRandomInt;

public class SelectCarMenu {
    private final String MAKE = "make";
    private final String MODEL = "model";
    private final String YEAR = "year";
    private final String PATTERN = "//select[contains(@name,'%s')]//option[not(@value='0')]";
    private By selectFormLoc = By.cssSelector(".oJ34o");
    private By submitBtnLoc = By.cssSelector("._3iP3L");

    public boolean isDisplayed() {
        return new Label(selectFormLoc, "Label for research cars model").isDisplayed();
    }


    private WebElement getRandomOption(String option) {
        By optionsLoc = By.xpath(String.format(PATTERN, option));
        return getListElements(optionsLoc).get(getRandomInt(getListElements(optionsLoc).size()));
    }

    private String chooseRandomOption(String name) {
        WebElement randomOption = getRandomOption(name);
        randomOption.click();
        return randomOption.getText();
    }

    private Button getSubmitBtn() {
        return new Button(submitBtnLoc, "Search car button");
    }

    public void searchCar() {
        getSubmitBtn().click();
    }

    public Car chooseRandomCar() {
        return new Car(chooseRandomOption(MAKE), chooseRandomOption(MODEL), chooseRandomOption(YEAR));
    }
}
