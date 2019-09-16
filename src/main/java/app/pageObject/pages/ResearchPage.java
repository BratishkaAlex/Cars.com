package app.pageObject.pages;

import app.pageObject.forms.SelectCarMenu;
import framework.elements.Link;
import org.openqa.selenium.By;

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
        return new Link(sbsCompareLoc, "Link to side by side compare page");
    }

    public void clickOnSideBySideCompare() {
        getSBSCompareLink().click();
    }
}
