package app.pageObject.pages;

import app.appClasses.Car;
import framework.elements.Label;
import framework.elements.Link;
import framework.utils.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class CarPage {
    private By labelForCar = By.cssSelector(".cui-page-section__title");
    private By compareTrimsLinkLoc = By.xpath("//a[@data-linkname='trim-compare']");

    private Label getLabelForCar() {
        return new Label(labelForCar, "Label for full car name");
    }

    public boolean isChosenCar(Car car) {
        String label = getLabelForCar().getText().toLowerCase();
        return (label.contains(car.getMake().toLowerCase()) && label.contains(car.getModel().toLowerCase())
            && label.contains(car.getYear().toLowerCase()));
    }

    private Link getCompareTrimsLink() {
        Waiter.waitForClickAble(compareTrimsLinkLoc);
        return new Link(compareTrimsLinkLoc, "Link to compare trims page");
    }

    public void clickOnCompareTrims() {
        getCompareTrimsLink().click();
    }

    public boolean isDisplayedCompareTrimsLink() {
        try {
            return getCompareTrimsLink().isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
