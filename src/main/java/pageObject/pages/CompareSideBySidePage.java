package pageObject.pages;

import appUtils.Utils;
import org.openqa.selenium.By;
import pageObject.forms.SideBySideCompareMenu;

public class CompareSideBySidePage {
    private SideBySideCompareMenu sideBySideCompareMenu;
    private By chooseCarFormLoc = By.id("mainAddCarForm");

    public CompareSideBySidePage() {
        sideBySideCompareMenu = new SideBySideCompareMenu();
    }

    public SideBySideCompareMenu getSideBySideCompareMenu() {
        return sideBySideCompareMenu;
    }

    public boolean isCompareSBSPage() {
        return Utils.elementIsDisplayed(chooseCarFormLoc);
    }
}
