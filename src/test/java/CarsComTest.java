import app.appClasses.Car;
import app.pageObject.pages.CompareSideBySidePage;
import app.pageObject.pages.CompareTrimsPage;
import app.pageObject.pages.ModelComparePage;
import app.pageObject.pages.ResearchPage;
import app.steps.CommonStep;
import framework.base.BaseTest;
import framework.browser.Browser;
import framework.utils.PropertyManager;
import org.testng.annotations.Test;

import static framework.utils.LoggerUtil.LOGGER;
import static framework.utils.LoggerUtil.step;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CarsComTest extends BaseTest {

    private final int COUNT_OF_TRYING = 3;
    private int counter = 1;

    @Test
    public void testCase() {
        step("Open https://www.cars.com/", counter++);
        Browser.enterUrl(PropertyManager.getConfigProperty("url"));

        Car firstCar = CommonStep.getCar("First", counter, COUNT_OF_TRYING);
        counter = 6;
        step("Navigate to the main page", counter++);
        new CompareTrimsPage().getNavigationMenu().navigateToMainPage();

        step("Repeat steps 2-5 for 1 more car model", counter++);
        counter = 2;
        Car secondCar = CommonStep.getCar("Second", counter, COUNT_OF_TRYING);
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
        assertTrue(modelComparePage.isDisplayedForCar(firstCar, 1), "This in not the model compare page for the first car");
        step("Click “Add Another Car” button -> add car 2 to the comparison", counter++);
        modelComparePage.clickOnAddAnotherCar();
        modelComparePage.getSideBySideCompareMenu().chooseCar(secondCar);
        modelComparePage.getSideBySideCompareMenu().addAnotherCar();
        LOGGER.info("Checking Car 2 is added to the “Model Compare” page");
        assertTrue(modelComparePage.isDisplayedForCar(secondCar, 2), "This in not the model compare page for the second car");
        Car readFirstCar = modelComparePage.getAddedCar(1);
        Car readSecondCar = modelComparePage.getAddedCar(2);
        step("Compare Car 1 and Car 2 Engine and Transmission values with the values saved", counter++);
        LOGGER.info("Checking values on the page “Model Compare” equals to the saved values");
        assertEquals(firstCar, readFirstCar, "Parameters for first chosen car and read first car are not the same");
        assertEquals(secondCar, readSecondCar, "Parameters for second chosen car and read second car are not the same");
    }
}
