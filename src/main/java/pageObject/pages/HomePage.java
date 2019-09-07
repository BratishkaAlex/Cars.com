package pageObject.pages;

import framework.elements.Label;
import org.openqa.selenium.By;
import pageObject.forms.NavigationMenu;

public class HomePage {
    private NavigationMenu navigationMenu;
    private By trendingLoc = By.xpath("//div[contains(@data-analytics-id, 'trending-content-rotating')]");

    public HomePage() {
        navigationMenu = new NavigationMenu();
    }

    public boolean isDisplayed() {
        return getLabelForTrending().isDisplayed();
    }

    private Label getLabelForTrending() {
        return new Label(trendingLoc);
    }

    public NavigationMenu getNavigationMenu() {
        return navigationMenu;
    }
}
