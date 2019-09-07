package pageObject.pages;

import framework.elements.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageObject.forms.NavigationMenu;

import java.util.List;

public class CompareTrimsPage {
    private NavigationMenu navigationMenu;
    private By labelsRowLoc = By.id("labels-row");
    private By listLabelsLoc = By.xpath("child::div");
    private By trimCardLoc = By.xpath("(//div[@class='trim-card'])[2]");
    private By trimPageTitleLoc = By.cssSelector(".cui-heading-1");

    public CompareTrimsPage() {
        navigationMenu = new NavigationMenu();
    }

    public NavigationMenu getNavigationMenu() {
        return navigationMenu;
    }

    public boolean isCompareTrimsPage(String make, String model, String year) {
        String title = new Label(trimPageTitleLoc).getText().toLowerCase();
        return (title.contains(make.toLowerCase()) && title.contains(model.toLowerCase())
            && title.contains(year.toLowerCase()) && title.contains("trim"));
    }

    private Label getRow(By loc) {
        return new Label(loc);
    }

    private List<WebElement> getLabelsFromRow(By rowLoc, By labelsLoc) {
        return getRow(rowLoc).findElements(labelsLoc);
    }

    private int getNumberOfField(String name) {
        for (int i = 0; i < getLabelsFromRow(labelsRowLoc, listLabelsLoc).size(); i++) {
            if (getLabelsFromRow(labelsRowLoc, listLabelsLoc).get(i).getText().equals(name)) {
                return i;
            }
        }
        throw new IllegalStateException("There is no such field");
    }

    public String getCharacteristic(String name) {
        return getLabelsFromRow(trimCardLoc, listLabelsLoc).get(getNumberOfField(name)).getText();
    }
}
