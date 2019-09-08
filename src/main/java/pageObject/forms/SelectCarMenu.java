package pageObject.forms;

import framework.elements.Button;
import framework.elements.Option;
import org.openqa.selenium.By;

import static appUtils.Utils.elementIsDisplayed;
import static appUtils.Utils.getListElements;
import static appUtils.Utils.getRandomInt;

public class SelectCarMenu {
    private By selectFormLoc = By.xpath("//div[@class='_1DN8b']");
    private final String pattern = "//select[contains(@name,'%s')]//option[not(@value='0')]";
    private By submitBtnLoc = By.cssSelector("._3iP3L");

    public boolean isDisplayed() {
        return elementIsDisplayed(selectFormLoc);
    }


    private Option getRandomOption(String option) {
        By optionsLoc = By.xpath(String.format(pattern, option));
        return new Option(getListElements(optionsLoc).get(getRandomInt(getListElements(optionsLoc).size())), optionsLoc);
    }

    public String chooseRandomOption(String name) {
        Option randomOption = getRandomOption(name);
        randomOption.click();
        return randomOption.getText();
    }

    private Button getSubmitBtn() {
        return new Button(submitBtnLoc);
    }

    public void searchCar() {
        getSubmitBtn().click();
    }
}
