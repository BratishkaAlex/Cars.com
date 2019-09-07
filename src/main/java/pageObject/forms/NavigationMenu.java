package pageObject.forms;

import framework.elements.Button;
import org.openqa.selenium.By;

public class NavigationMenu {
    private By researchBtnLoc = By.xpath("(//a[@href='/research/'])[2]");
    private By homeBthLoc = By.xpath("//a[@data-linkname='header-home']");

    private Button getResearchButton() {
        return new Button(researchBtnLoc);
    }

    private Button getHomeButton() {
        return new Button(homeBthLoc);
    }

    public void navigateToResearch() {
        getResearchButton().click();
    }

    public void navigateToMainPage() {
        getHomeButton().click();
    }
}
