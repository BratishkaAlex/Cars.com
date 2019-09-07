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
        String firstCarMake = researchPage.getSelectCarMenu().chooseRandomOption(make);
        String firstCarModel = researchPage.getSelectCarMenu().chooseRandomOption(model);
        String firstCarYear = researchPage.getSelectCarMenu().chooseRandomOption(year);
        researchPage.getSelectCarMenu().searchCar();

        CarPage carPage = new CarPage();
        assertTrue(carPage.isChosenCar(firstCarMake, firstCarModel, firstCarYear), "This is not the page for first chosen car");
        carPage.clickOnCompareTrims();

        CompareTrimsPage compareTrimsPage = new CompareTrimsPage();
        assertTrue(compareTrimsPage.isCompareTrimsPage(firstCarMake, firstCarModel, firstCarYear), "This is note hte compare trims page for the first car");
        String firstCarEngine = compareTrimsPage.getCharacteristic(engine);
        String firstCarTrans = compareTrimsPage.getCharacteristic(transmission.substring(0, 5));
        compareTrimsPage.getNavigationMenu().navigateToMainPage();

        HomePage secondInstance = new HomePage();
        assertTrue(secondInstance.isDisplayed(), "This is note the main page");
        secondInstance.getNavigationMenu().navigateToResearch();

        ResearchPage secondResearchPage = new ResearchPage();
        assertTrue(secondResearchPage.getSelectCarMenu().isDisplayed(), "This is not the research page");
        String secondCarMake = secondResearchPage.getSelectCarMenu().chooseRandomOption(make);
        String secondCarModel = secondResearchPage.getSelectCarMenu().chooseRandomOption(model);
        String secondCarYear = secondResearchPage.getSelectCarMenu().chooseRandomOption(year);
        secondResearchPage.getSelectCarMenu().searchCar();

        CarPage secondCarPage = new CarPage();
        assertTrue(secondCarPage.isChosenCar(secondCarMake, secondCarModel, secondCarYear), "This is not the page for second chosen car");
        secondCarPage.clickOnCompareTrims();

        CompareTrimsPage secondCompareTrimsPage = new CompareTrimsPage();
        assertTrue(secondCompareTrimsPage.isCompareTrimsPage(secondCarMake, secondCarModel, secondCarYear), "This is note hte compare trims page for the second car");
        String secondCarEngine = secondCompareTrimsPage.getCharacteristic(engine);
        String secondCarTrans = secondCompareTrimsPage.getCharacteristic(transmission.substring(0, 5));
        secondCompareTrimsPage.getNavigationMenu().navigateToResearch();

        ResearchPage thirdResearchPage = new ResearchPage();
        assertTrue(thirdResearchPage.getSelectCarMenu().isDisplayed(), "This is not the research page");
        thirdResearchPage.clickOnSideBySideCompare();

        CompareSideBySidePage compareSideBySidePage = new CompareSideBySidePage();
        assertTrue(compareSideBySidePage.isCompareSBSPage(), "This in not the compare side by side page");
        compareSideBySidePage.getSideBySideCompareMenu().chooseOption(make, firstCarMake);
        compareSideBySidePage.getSideBySideCompareMenu().chooseOption(model, firstCarModel);
        compareSideBySidePage.getSideBySideCompareMenu().chooseOption(year, firstCarYear);
        compareSideBySidePage.getSideBySideCompareMenu().submitCar();

        ModelComparePage modelComparePage = new ModelComparePage();
        assertTrue(modelComparePage.isDisplayedForCar(firstCarMake, firstCarModel, firstCarYear), "This in not the model compare page for the first car");
        modelComparePage.addAnotherCar();
        modelComparePage.getSideBySideCompareMenu().chooseOption(make, secondCarMake);
        modelComparePage.getSideBySideCompareMenu().chooseOption(model, secondCarModel);
        modelComparePage.getSideBySideCompareMenu().chooseOption(year, secondCarYear);
        modelComparePage.getSideBySideCompareMenu().addAnotherCar(secondCarMake, secondCarModel, secondCarYear);
        assertTrue(modelComparePage.isDisplayedForCar(secondCarMake, secondCarModel, secondCarYear), "This in not the model compare page for the second car");
        String readEngineForFirstCar = modelComparePage.getValueForCar(engine, 1);
        String readEngineForSecondCar = modelComparePage.getValueForCar(engine, 2);
        String readTransmissionForFirstCar = modelComparePage.getValueForCar(transmission, 1);
        String readTransmissionForSecondCar = modelComparePage.getValueForCar(transmission, 2);
        assertTrue(readEngineForFirstCar.toLowerCase().contains(firstCarEngine.toLowerCase()), "Engines for the first car are not the same");
        assertTrue(readEngineForSecondCar.toLowerCase().contains(secondCarEngine.toLowerCase()), "Engines for the second car are not the same");
        assertTrue(readTransmissionForFirstCar.toLowerCase().contains(firstCarTrans.toLowerCase()), "Transmissions for the first car are not the same");
        assertTrue(readTransmissionForSecondCar.toLowerCase().contains(secondCarTrans.toLowerCase()), "Transmissions for the second car are not the same");
    }
}
