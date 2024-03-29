package app.pageObject.pages;

import app.pageObject.forms.NavigationMenu;
import framework.elements.Label;
import org.openqa.selenium.By;

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
        return new Label(trendingLoc, "Label for trending cars");
    }

    public NavigationMenu getNavigationMenu() {
        return navigationMenu;
    }
}
