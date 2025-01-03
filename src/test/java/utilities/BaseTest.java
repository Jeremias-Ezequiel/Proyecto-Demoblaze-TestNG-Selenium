package utilities;

import listeners.SuiteListener;
import listeners.TestListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({SuiteListener.class, TestListener.class})
public class BaseTest {
    protected final String smoke = "smoke";
    protected final String regression = "regression";
    protected final CommonFlows commonFlows = new CommonFlows(); 
    private final DriverManager driverManager = new DriverManager();  

    @BeforeMethod(alwaysRun = true)
    public void masterSetUp() {
        driverManager.buildDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void masterTearDown() {
        driverManager.killDriver();
    }
}
