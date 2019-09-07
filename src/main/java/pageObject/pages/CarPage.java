package pageObject.pages;

import framework.elements.Label;
import framework.elements.Link;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class CarPage {
    private By labelForCar = By.cssSelector(".cui-page-section__title");
    private By compareTrimsLinkLoc = By.xpath("//a[@data-linkname='trim-compare']");

    private Label getLabelForCar() {
        return new Label(labelForCar);
    }

    public boolean isChosenCar(String make, String model, String year) {
        String label = getLabelForCar().getText().toLowerCase();
        return (label.contains(make.toLowerCase()) && label.contains(model.toLowerCase())
            && label.contains(year.toLowerCase()));
    }

    private Link getCompareTrimsLink() {
        Link linkToCompareTrims = null;
        try {
            linkToCompareTrims = new Link(compareTrimsLinkLoc);
        } catch (NoSuchElementException e) {
            System.out.println("There is no link for compare trims");
        }
        return linkToCompareTrims;
    }

    public void clickOnCompareTrims() {
        getCompareTrimsLink().click();
    }
}
