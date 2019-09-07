package pageObject.pages;

import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;
import pageObject.forms.SideBySideCompareMenu;

public class ModelComparePage {
    private SideBySideCompareMenu sideBySideCompareMenu;
    private By addCarBtnLoc = By.cssSelector(".add-car-icon");
    private By mainLabelLoc = By.cssSelector(".compare-subhead-h2");
    private final String patternForValues = "(//cars-compare-compare-info[@header = '%s']//div[@ng-switch-when='simple-list'])[%d]";

    public ModelComparePage() {
        sideBySideCompareMenu = new SideBySideCompareMenu();
    }

    public SideBySideCompareMenu getSideBySideCompareMenu() {
        return sideBySideCompareMenu;
    }

    private Label getMainLabel() {
        return new Label(mainLabelLoc);
    }

    public boolean isDisplayedForCar(String make, String model, String year) {
        String mainLabel = getMainLabel().getText().toLowerCase();
        return (mainLabel.contains(make.toLowerCase()) && mainLabel.contains(model.toLowerCase())
            && mainLabel.contains(year.toLowerCase()));
    }

    private Button getAddNewCarButton() {
        return new Button(addCarBtnLoc);
    }

    public void addAnotherCar() {
        getAddNewCarButton().click();
    }

    private Label getLabelForValue(String name, int number) {
        return new Label(By.xpath(String.format(patternForValues, name, number)));
    }

    public String getValueForCar(String name, int number) {
        return getLabelForValue(name, number).getText();
    }
}
