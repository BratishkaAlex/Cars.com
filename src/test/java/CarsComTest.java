import appClasses.Car;
import framework.browser.Browser;
import framework.utils.LoggerUtil;
import framework.utils.PropertyManager;
import framework.utils.Waiter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObject.pages.CarPage;
import pageObject.pages.CompareSideBySidePage;
import pageObject.pages.CompareTrimsPage;
import pageObject.pages.HomePage;
import pageObject.pages.ModelComparePage;
import pageObject.pages.ResearchPage;

import static org.testng.Assert.assertTrue;

public class CarsComTest {

    private final String make = "make";
    private final String model = "model";
    private final String year = "year";
    private final String engine = "Engine";
    private final String transmission = "Transmission";

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

    @Test
    public void testCase() {
        Browser.enterUrl(PropertyManager.getConfigProperty("url"));

        HomePage homePage = new HomePage();
        assertTrue(homePage.isDisplayed(), "This is note the main page");
        homePage.getNavigationMenu().navigateToResearch();

        ResearchPage researchPage = new ResearchPage();
        assertTrue(researchPage.getSelectCarMenu().isDisplayed(), "This is not the research page");
        Car firstCar = new Car();
        firstCar.setMake(researchPage.getSelectCarMenu().chooseRandomOption(make));
        firstCar.setModel(researchPage.getSelectCarMenu().chooseRandomOption(model));
        firstCar.setYear(researchPage.getSelectCarMenu().chooseRandomOption(year));
        researchPage.getSelectCarMenu().searchCar();

        CarPage carPage = new CarPage();
        assertTrue(carPage.isChosenCar(firstCar.getMake(), firstCar.getModel(), firstCar.getYear()), "This is not the page for first chosen car");
        carPage.clickOnCompareTrims();

        CompareTrimsPage compareTrimsPage = new CompareTrimsPage();
        assertTrue(compareTrimsPage.isCompareTrimsPage(firstCar.getMake(), firstCar.getModel(), firstCar.getYear()), "This is not the compare trims page for the first car");
        firstCar.setEngine(compareTrimsPage.getCharacteristic(engine));
        firstCar.setTransmission(compareTrimsPage.getCharacteristic(transmission.substring(0, 5)));
        assertTrue(firstCar.areEngineAndTransSaved(), "Engine and trans did not saved for the first car");
        compareTrimsPage.getNavigationMenu().navigateToMainPage();

        HomePage secondInstance = new HomePage();
        assertTrue(secondInstance.isDisplayed(), "This is note the main page");
        secondInstance.getNavigationMenu().navigateToResearch();

        ResearchPage secondResearchPage = new ResearchPage();
        assertTrue(secondResearchPage.getSelectCarMenu().isDisplayed(), "This is not the research page");
        Car secondCar = new Car();
        secondCar.setMake(secondResearchPage.getSelectCarMenu().chooseRandomOption(make));
        secondCar.setModel(secondResearchPage.getSelectCarMenu().chooseRandomOption(model));
        secondCar.setYear(secondResearchPage.getSelectCarMenu().chooseRandomOption(year));
        secondResearchPage.getSelectCarMenu().searchCar();

        CarPage secondCarPage = new CarPage();
        assertTrue(secondCarPage.isChosenCar(secondCar.getMake(), secondCar.getModel(), secondCar.getYear()), "This is not the page for second chosen car");
        secondCarPage.clickOnCompareTrims();

        CompareTrimsPage secondCompareTrimsPage = new CompareTrimsPage();
        assertTrue(secondCompareTrimsPage.isCompareTrimsPage(secondCar.getMake(), secondCar.getModel(), secondCar.getYear()), "This is note hte compare trims page for the second car");
        secondCar.setEngine(secondCompareTrimsPage.getCharacteristic(engine));
        secondCar.setTransmission(secondCompareTrimsPage.getCharacteristic(transmission.substring(0, 5)));
        assertTrue(secondCar.areEngineAndTransSaved(), "Engine and trans did not saved for the second car");
        secondCompareTrimsPage.getNavigationMenu().navigateToResearch();

        ResearchPage thirdResearchPage = new ResearchPage();
        assertTrue(thirdResearchPage.getSelectCarMenu().isDisplayed(), "This is not the research page");
        thirdResearchPage.clickOnSideBySideCompare();

        CompareSideBySidePage compareSideBySidePage = new CompareSideBySidePage();
        assertTrue(compareSideBySidePage.isCompareSBSPage(), "This in not the compare side by side page");
        compareSideBySidePage.getSideBySideCompareMenu().chooseOption(make, firstCar.getMake());
        compareSideBySidePage.getSideBySideCompareMenu().chooseOption(model, firstCar.getModel());
        compareSideBySidePage.getSideBySideCompareMenu().chooseOption(year, firstCar.getYear());
        compareSideBySidePage.getSideBySideCompareMenu().submitCar();

        ModelComparePage modelComparePage = new ModelComparePage();
        assertTrue(modelComparePage.isDisplayedForCar(firstCar.getMake(), firstCar.getModel(), firstCar.getYear()), "This in not the model compare page for the first car");
        modelComparePage.clickOnAddAnotherCar();
        modelComparePage.getSideBySideCompareMenu().chooseOption(make, secondCar.getMake());
        modelComparePage.getSideBySideCompareMenu().chooseOption(model, secondCar.getModel());
        modelComparePage.getSideBySideCompareMenu().chooseOption(year, secondCar.getYear());
        modelComparePage.getSideBySideCompareMenu().addAnotherCar();
        assertTrue(modelComparePage.isDisplayedForCar(secondCar.getMake(), secondCar.getModel(), secondCar.getYear()), "This in not the model compare page for the second car");
        String readEngineForFirstCar = modelComparePage.getValueForCar(engine, 1);
        String readEngineForSecondCar = modelComparePage.getValueForCar(engine, 2);
        String readTransmissionForFirstCar = modelComparePage.getValueForCar(transmission, 1);
        String readTransmissionForSecondCar = modelComparePage.getValueForCar(transmission, 2);
        assertTrue(readEngineForFirstCar.toLowerCase().contains(firstCar.getEngine().toLowerCase()), "Engines for the first car are not the same");
        assertTrue(readEngineForSecondCar.toLowerCase().contains(secondCar.getEngine().toLowerCase()), "Engines for the second car are not the same");
        assertTrue(readTransmissionForFirstCar.toLowerCase().contains(firstCar.getTransmission().toLowerCase()), "Transmissions for the first car are not the same");
        assertTrue(readTransmissionForSecondCar.toLowerCase().contains(secondCar.getTransmission().toLowerCase()), "Transmissions for the second car are not the same");
    }
}
