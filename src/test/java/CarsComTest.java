import appClasses.Car;
import framework.browser.Browser;
import framework.utils.LoggerUtil;
import framework.utils.PropertyManager;
import framework.utils.Waiter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObject.pages.*;

import static framework.utils.LoggerUtil.LOGGER;
import static framework.utils.LoggerUtil.step;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CarsComTest {

    private final String ENGINE = "Engine";
    private final String TRANSMISSION = "Transmission";
    private final String TRANSMISSION_SMALL_NAME = "Trans";
    private int counter = 1;

    @BeforeTest
    public void setUpBrowser() {
        new LoggerUtil(this.getClass().getName());
        Browser.maximize();
        Waiter.implicitWait(Integer.parseInt(PropertyManager.getConfigProperty("timeout")));
    }

    @AfterTest
    public void closeBrowser() {
        Browser.closeBrowser();
    }

    public Car getCar(String number) {
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

    @Test
    public void testCase() {
        step("Open https://www.cars.com/", counter++);
        Browser.enterUrl(PropertyManager.getConfigProperty("url"));

        Car firstCar = getCar("First");
        Car readFirstCar = new Car(firstCar);
        step("Navigate to the main page", counter++);
        new CompareTrimsPage().getNavigationMenu().navigateToMainPage();

        step("Repeat steps 2-5 for 1 more car model", counter++);
        counter = 2;
        Car secondCar = getCar("Second");
        Car readSecondCar = new Car(secondCar);
        counter = 8;
        step("Select “Research” in the top menu", counter++);
        new CompareTrimsPage().getNavigationMenu().navigateToResearch();

        ResearchPage researchPage = new ResearchPage();
        LOGGER.info("Checking 'Research' page is opened");
        assertTrue(researchPage.getSelectCarMenu().isDisplayed(), "This is not the research page");
        step("Click 'Side-by-side comparisons'", counter++);
        researchPage.clickOnSideBySideCompare();

        CompareSideBySidePage compareSideBySidePage = new CompareSideBySidePage();
        LOGGER.info("Checking 'Compare Cars Side-by-Side' page is opened");
        assertTrue(compareSideBySidePage.isCompareSBSPage(), "This in not the compare side by side page");
        step("Select Car 1 Make, Model and Year -> click “Start Comparing” button", counter++);
        compareSideBySidePage.getSideBySideCompareMenu().chooseCar(firstCar);
        compareSideBySidePage.getSideBySideCompareMenu().submitCar();

        ModelComparePage modelComparePage = new ModelComparePage();
        LOGGER.info("Checking 'Model Compare' page is opened with Car 1 selected");
        assertTrue(modelComparePage.isDisplayedForCar(firstCar), "This in not the model compare page for the first car");
        step("Click “Add Another Car” button -> add car 2 to the comparison", counter++);
        modelComparePage.clickOnAddAnotherCar();
        modelComparePage.getSideBySideCompareMenu().chooseCar(secondCar);
        modelComparePage.getSideBySideCompareMenu().addAnotherCar();
        LOGGER.info("Checking Car 2 is added to the “Model Compare” page");
        assertTrue(modelComparePage.isDisplayedForCar(secondCar), "This in not the model compare page for the second car");
        readFirstCar.setEngine(modelComparePage.getValueForCar(ENGINE, 1));
        readSecondCar.setEngine(modelComparePage.getValueForCar(ENGINE, 2));
        readFirstCar.setTransmission(modelComparePage.getValueForCar(TRANSMISSION, 1));
        readSecondCar.setTransmission(modelComparePage.getValueForCar(TRANSMISSION, 2));
        step("Compare Car 1 and Car 2 Engine and Transmission values with the values saved", counter++);
        assertEquals(firstCar, readFirstCar, "Parameters for first chosen car and read first car are not the same");
        assertEquals(secondCar, readSecondCar, "Parameters for second chosen car and read second car are not the same");
    }
}
