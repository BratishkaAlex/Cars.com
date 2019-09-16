package pageObject.pages;

import framework.elements.Label;
import org.openqa.selenium.By;
import pageObject.forms.SideBySideCompareMenu;

public class CompareSideBySidePage {
    private SideBySideCompareMenu sideBySideCompareMenu;
    private By chooseCarFormLoc = By.cssSelector(".compare-head-h1-semi");

    public CompareSideBySidePage() {
        sideBySideCompareMenu = new SideBySideCompareMenu();
    }

    public SideBySideCompareMenu getSideBySideCompareMenu() {
        return sideBySideCompareMenu;
    }

    public boolean isCompareSBSPage() {
        return new Label(chooseCarFormLoc, "Label for side by side comparison").isDisplayed();
    }
}
