package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.FileManager;
import utilities.Logs;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        Logs.info("Empezando Test: %s",result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Logs.info("Test exitoso: %s",result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Logs.info("Test Fallido: %s",result.getName());
        FileManager.getScreenshot(result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Logs.info("Test Saltado: %s",result.getName());
    }
}
