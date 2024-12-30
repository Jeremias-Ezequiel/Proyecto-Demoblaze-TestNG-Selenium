package utilities;

import listeners.SuiteListener;
import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({SuiteListener.class, TestListener.class})
public class BaseTest {
    protected final String smoke = "smoke";
    protected final String regression = "regression";
    protected final CommonFlows commonFlows = new CommonFlows(); 

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void masterSetUp() {
        Logs.debug("Inicializando el driver");
        driver = new ChromeDriver();

        Logs.debug("Maximizando pantalla");
        driver.manage().window().maximize();

        Logs.debug("Borrando las cookies");
        driver.manage().deleteAllCookies();

        Logs.debug("Asignando el driver al web driver provider");
        new WebdriverProvider().set(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void masterTearDown() {
        Logs.debug("Matando al driver");
        driver.quit();
    }
}
