package utilities;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class FileManager {
    private static final String screenshotPath = "src/test/resources/screenshots";

    public static void getScreenshot(String screenshotName){
        Logs.debug("Tomando Screenshot");

        File screenshotFile = ((TakesScreenshot)new WebdriverProvider().get()).getScreenshotAs(OutputType.FILE);

        String path = String.format("%s/%s.png",screenshotPath,screenshotName);

        try{
            FileUtils.copyFile(screenshotFile, new File(path));
        } catch (IOException e) {
            Logs.error("Error al tomar el screenshot: %s",e.getLocalizedMessage());
        }
    }

    public static void borrarEvidenciaPrevia(){
        try{
            FileUtils.deleteDirectory(new File(screenshotPath));
        } catch (IOException e) {
            Logs.error("Error al borrar la evidencia previa: %s",e.getLocalizedMessage());
        }
    }

    // Screenshot en Allure

    // @Attachment(value = "screenshot", type = "img/png")
    // public static byte[] getScreenshot(){
    //     return ((TakesScreenshot)new WebdriverProvider().get()).getScreenshotAs(OutputType.BYTES);
    // }

}
