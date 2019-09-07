package pageObject.pages;

import framework.elements.Link;
import org.openqa.selenium.By;
import pageObject.forms.SelectCarMenu;

public class ResearchPage {
    private SelectCarMenu selectCarMenu;
    private By sbsCompareLoc = By.cssSelector("._1dRAW");

    public ResearchPage() {
        selectCarMenu = new SelectCarMenu();
    }

    public SelectCarMenu getSelectCarMenu() {
        return selectCarMenu;
    }

    private Link getSBSCompareLink() {
        return new Link(sbsCompareLoc);
    }

    public void clickOnSideBySideCompare() {
        getSBSCompareLink().click();
    }
}
