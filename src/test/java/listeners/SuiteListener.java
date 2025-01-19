package listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import utilities.FileManager;
import utilities.Logs;

public class SuiteListener implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        Logs.info("Comenzando Suite: %s",suite.getName());
        FileManager.borrarEvidenciaPrevia();
    }

    @Override
    public void onFinish(ISuite suite) {
        Logs.info("Finalizando Suite: %s",suite.getName());
    }
}
