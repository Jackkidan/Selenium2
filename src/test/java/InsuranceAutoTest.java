import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import steps.RgsSteps;

public class InsuranceAutoTest {
    RgsSteps user = new RgsSteps();

    @Before
    public void setUp() {
        user.setUp();
    }

    @After
    public void tearDown() {
        //  user.tearDown();
    }

    @Test
    public void checkCorrectFormFilling() {
        user.openInsuranceNavBar().chooseCategory("Выезжающим за рубеж");
        user.openCalculateOnlineWindow();
        user.checkHeaderText("Калькулятор страхования путешественников онлайн");
        user.clickOnCheckboxPersonal();
        user.clickSeveralTripsButton();
        user.setInputCountries("Шенген");
        user.selectStartCountyForTravel("Испания");
        user.inputDateOfTravel("11112018");
        user.chooseTravelDuration();
        user.inputNameAndLastname("IVAN", "IVANOV", "12121985");
        user.setCheckLeisure();
        user.submitLeisureRequest();
        //user.checkComplitedRequest(
        //        "Многократные поездки в течение года",
        //"(количество дней суммарно: 90)",
        //        "Шенген",
        //        "12.12.1985",
        //        "IVAN IVANOV",
        //        "(включая активный отдых)");

    }
}
