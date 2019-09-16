package app.steps;

import app.appClasses.Car;
import app.pageObject.pages.CarPage;
import app.pageObject.pages.CompareTrimsPage;
import app.pageObject.pages.HomePage;
import app.pageObject.pages.ResearchPage;
import framework.browser.Browser;
import framework.utils.PropertyManager;

import static framework.utils.LoggerUtil.LOGGER;
import static framework.utils.LoggerUtil.step;
import static org.testng.Assert.assertTrue;

public class CommonStep {
    private static final String TRANSMISSION_SMALL_NAME = "Trans";
    private static final String ENGINE = "Engine";

    public static Car getCar(String number, int counter, int countOfTrying) {
        int i = 3;
        while (i > 0) {
            try {
                HomePage homePage = new HomePage();
                LOGGER.info("Checking Application main page is opened");
                assertTrue(homePage.isDisplayed(), "This is not the main page");
                step("Select 'Research' in the top menu", counter++);
                homePage.getNavigationMenu().navigateToResearch();

                ResearchPage researchPage = new ResearchPage();
                LOGGER.info("Checking 'Research' page is opened");
                assertTrue(researchPage.getSelectCarMenu().isDisplayed(), "This is not the research page");
                step("Search car by random selected parameters: Make, Model, Year", counter++);
                Car car = researchPage.getSelectCarMenu().chooseRandomCar();
                researchPage.getSelectCarMenu().searchCar();

                CarPage carPage = new CarPage();
                LOGGER.info("Checking Parameters are selected successfully and " +
                        "Page with details on the car with selected make + Model + Year is opened");
                assertTrue(carPage.isChosenCar(car), String.format("This is not the page for %s chosen car", number));
                step("Click “Compare trims” link", counter++);
                carPage.clickOnCompareTrims();

                CompareTrimsPage compareTrimsPage = new CompareTrimsPage();
                LOGGER.info("Checking 'Compare trims' page is opened for the selected car");
                assertTrue(compareTrimsPage.isCompareTrimsPage(car), String.format("This is not the compare trims page for the %s car", number));
                step("Save the following parameters for the base (first) trim: Engine, Trans", counter++);
                car.setEngine(compareTrimsPage.getCharacteristic(ENGINE));
                car.setTransmission(compareTrimsPage.getCharacteristic(TRANSMISSION_SMALL_NAME));
                LOGGER.info(String.format("Checking %s car parameters are saved", number));
                assertTrue(car.areEngineAndTransSaved(), String.format("Engine and trans did not saved for the %s car", number));
                return car;
            } catch (IllegalStateException e) {
                i--;
                Browser.enterUrl(PropertyManager.getConfigProperty("url"));
                counter = 2;
            }
        }
        throw new IllegalStateException("There was no compare trim link for three times");
    }
}
