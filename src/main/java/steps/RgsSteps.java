package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class RgsSteps extends CommonSteps {

    private static final By openInsuranceNavBarButtonLocator = By.xpath("//*[@id='main-navbar-collapse']//a[contains(text(), 'Страхование')]");
    private static final By navBarLocator = By.className("container-rgs-main-menu-links");
    private static final String categoryFormat = "//*[@id='main-navbar-collapse']//a[contains(text(), '%s')]";
    private static final By openCalculateOnline = By.xpath("//div[@class='thumbnail-footer']/a[@href='https://www.rgs.ru/products/private_person/tour/strahovanie_turistov/calc/index.wbp']");
    private static final By headerXpath = By.xpath("//div[@class='container container-rgs-app-title']");
    private static final By checkboxPersonal = By.xpath("//label[@class = 'adaptive-checkbox-label' and contains(text(), ' Я согласен на обработку моих персональных данных в целях расчета страховой премии. ')]");
    private static final By severalTripsButton = By.xpath("//div[@class='step-body']//form/div[1]/btn-radio-group/div/button[2]");
    private static final By inputCountries = By.xpath("//input[@class = 'form-control-multiple-autocomplete-actual-input tt-input']");
    private static final By selectStartsCountry = By.id("ArrivalCountryList");
    private static final By inputTravelDate = By.cssSelector("[data-trip-count='MultipleTrips'] input");
    private static final By travelDuration = By.xpath("//label[@class = 'btn btn-attention']");
    private static final By inputNameLastname = By.xpath("//div[@class='panel panel-default']/div[2]/div[1]/div//div[@class='form-group']/input[@class='form-control']");
    private static final By checkLeisure = By.xpath("//*[contains(text(),' активный отдых или спорт ')]//preceding::div[contains(@class,'toggle')]//div[@class='toggle-group']//span[@class = 'toggle-handle']");
    private static final By submitButton = By.xpath("//button[@class = 'btn btn-primary btn-sm text-uppercase text-semibold' and contains(text(), ' Рассчитать ')]");

    // completed request field

    public RgsSteps openInsuranceNavBar() {
        clickXpath(openInsuranceNavBarButtonLocator);
        waitForVisible(navBarLocator);
        return this;
    }

    public void chooseCategory(String categoryName) {
        By categoryLocator = By.xpath(String.format(categoryFormat, categoryName));
        clickXpath(categoryLocator);
    }

    public void openCalculateOnlineWindow() {
        WebElement openCalculateOnlineWindow = findByXpath(openCalculateOnline);
        scrollToElement(openCalculateOnlineWindow);
        openCalculateOnlineWindow.click();
    }

    public void checkHeaderText(String expectedText) {
        WebElement header = findByXpath(headerXpath);
        checkElementText(header, expectedText);
    }

    public void clickOnCheckboxPersonal() {
        clickWithWaitOnElement(checkboxPersonal);
    }

    public void clickSeveralTripsButton() {
        clickWithWaitOnElement(severalTripsButton);
    }

    public void setInputCountries(String country) {
        waitForVisible(inputCountries);
        WebElement input = findByXpath(inputCountries);
        input.sendKeys(country);
        builder.sendKeys(Keys.DOWN).sendKeys(Keys.ENTER).build().perform();
    }

    public void selectStartCountyForTravel(String startCountry) {
        waitForVisible(selectStartsCountry);
        WebElement webElementForSelect = driver.findElement(selectStartsCountry);
        Select chooseCountry = new Select(webElementForSelect);
        chooseCountry.selectByVisibleText(startCountry);
        builder.sendKeys(Keys.TAB).build().perform();
    }

    public void inputDateOfTravel(String date) {
        //WebDriverWait wait = new WebDriverWait(driver, 5);
        //wait.pollingEvery(Duration.ofMillis(300))
        //        .until(ExpectedConditions.visibilityOfElementLocated(inputTravelDate));
        //WebElement dateElement = findByCss(inputTravelDate);
        //scrollToElement(dateElement);
        builder.sendKeys(date).build().perform();
    }

    public void chooseTravelDuration() {
        WebElement durationElement = findByXpath(travelDuration);
        scrollToElement(durationElement);
        durationElement.click();
    }

    public void inputNameAndLastname(String firstName, String lastname, String birthDate) {
        WebElement inputNameElement = findByXpath(inputNameLastname);
        inputNameElement.sendKeys(firstName + " " + lastname);
        builder.sendKeys(Keys.TAB).build().perform();
        builder.sendKeys(birthDate).build().perform();
    }


    public void setCheckLeisure() {
        WebElement leisureElement = findByXpath(checkLeisure);
        scrollToElement(leisureElement);
        builder.click(leisureElement).perform();
    }

    public void submitLeisureRequest() {
        WebElement submitElement = findByXpath(submitButton);
        scrollToElement(submitElement);
        submitElement.click();
    }


    public void checkComplitedRequest(String expectedMultiTripsText, String expecterInsuranceText,
                                      String expectedCountryText, String expectedDateOfBirthText,
                                      String expectedNameText, String expectedLeisureText) {

        checkElementAttribute(getNewElementByCss(By.cssSelector("[data-bind='with\\: Trips']")), expectedMultiTripsText);
        checkElementAttribute(getNewElementByXpath(By.xpath("//span[@class='summary-value']/div[1]/span[4]")), expecterInsuranceText);
        checkElementAttribute(getNewElementByCss(By.cssSelector("[data-bind='foreach\\: countries'] [data-bind]")), expectedCountryText);
        checkElementAttribute(getNewElementByCss(By.cssSelector("[data-bind=' text\\: BirthDay\\.repr\\(\\'moscowRussianDate\\'\\)']")), expectedDateOfBirthText);
        checkElementAttribute(getNewElementByCss(By.cssSelector("[data-bind='text\\: LastName\\(\\) \\+ \\' \\' \\+ FirstName\\(\\)']")), expectedNameText);
        checkElementAttribute(getNewElementByXpath(By.xpath("//div[@class='summary']/div[7]/div[1]/div[@class='summary-row']//small[.='(включая активный отдых)']")), expectedLeisureText);
    }
}