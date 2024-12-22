package listeners;

import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;
import utilities.FileManager;
import utilities.Logs;

public class AllureLIstener implements TestLifecycleListener {

    @Override
    public void beforeTestStop(TestResult result) {
        Logs.debug("Before test stop Allure Listener");
        Status resultado = result.getStatus();

         switch (resultado){
             case BROKEN,FAILED:{
                 FileManager.getScreenshot(); 
             }
         }
    }
}
