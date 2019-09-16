package app.pageObject.pages;

import app.appClasses.Car;
import app.pageObject.forms.NavigationMenu;
import framework.elements.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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

    public boolean isCompareTrimsPage(Car car) {
        String title = new Label(trimPageTitleLoc, "Label for compare trims page").getText().toLowerCase();
        return (title.contains(car.getMake().toLowerCase()) && title.contains(car.getModel().toLowerCase())
            && title.contains(car.getYear().toLowerCase()) && title.contains("trim"));
    }

    private Label getRow(By loc, String rowsName) {
        return new Label(loc, String.format("Row of labels for %s", rowsName));
    }

    private List<WebElement> getLabelsFromRow(By rowLoc, By labelsLoc, String rowsName) {
        return getRow(rowLoc, rowsName).findElementsFromCurrent(labelsLoc);
    }

    private int getNumberOfField(String name) {
        List<WebElement> row = getLabelsFromRow(labelsRowLoc, listLabelsLoc, "Trim card fields");
        for (int i = 0; i < row.size(); i++) {
            if (row.get(i).getText().equals(name)) {
                return i;
            }
        }
        throw new IllegalArgumentException("There is no such field");
    }

    public String getCharacteristic(String name) {
        return getLabelsFromRow(trimCardLoc, listLabelsLoc, "Trim card values").get(getNumberOfField(name)).getText();
    }
}
