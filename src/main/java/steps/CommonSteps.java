package steps;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class CommonSteps {
    WebDriver driver;


    protected Actions builder;
    String BASE_URL = "https://www.rgs.ru";

    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.get(BASE_URL);
        builder = new Actions(driver);

    }

    public void tearDown() {
        driver.close();
    }

    protected WebElement findByXpath(By xpath) {
        return driver.findElement(xpath);
    }

    protected WebElement findByCss(By css) {
        return driver.findElement(css);
    }


    void clickXpath(By xpath) {
        findByXpath(xpath).click();
    }

    void checkElementText(WebElement element, String expectedText) {
        Assert.assertEquals("Значения текст не соотвествует ожидаемому",
                expectedText, element.getText());

    }

    void checkElementAttribute(WebElement element, String expectedText) {
        Assert.assertEquals("Значения текст не соотвествует ожидаемому",
                expectedText, element.getAttribute("textContent").trim());
    }

    void waitForVisible(By xpath) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.pollingEvery(Duration.ofMillis(300))
                .until(ExpectedConditions.visibilityOfElementLocated(xpath));
    }

    void clickWithWaitOnElement(By xpath) {
        waitForVisible(xpath);
        WebElement element = findByXpath(xpath);
        element.click();
    }

    void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    WebElement getNewElementByXpath(By xpath) {
        WebElement element = findByXpath(xpath);
        return element;
    }

    WebElement getNewElementByCss(By css) {
        WebElement element = findByXpath(css);
        return element;
    }


}
