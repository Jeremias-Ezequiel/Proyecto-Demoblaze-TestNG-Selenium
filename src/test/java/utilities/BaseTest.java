package utilities;

import listeners.SuiteListener;
import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

@Listeners({SuiteListener.class, TestListener.class})
public class BaseTest {
    protected SoftAssert softAssert;

    protected final String smoke = "smoke";
    protected final String regression = "regression";

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        Logs.debug("Inicializando el softAssert");
        softAssert = new SoftAssert();

        Logs.debug("Inicializando el driver");
        driver = new ChromeDriver();

        Logs.debug("Maximizando pantalla");
        driver.manage().window().maximize();

        Logs.debug("Borrando las cookies");
        driver.manage().deleteAllCookies();

        Logs.debug("Estableciendo un implicit wait de 3 segundos");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        Logs.debug("Asignando el driver al web driver provider");
        new WebdriverProvider().set(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        Logs.debug("Matando al driver");
        driver.quit();
    }
}
