package utilities;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public abstract class BasePage {
    private final static int defaulTimeOut = 5; 
    protected final SoftAssert softAssert;
    private final int timeOut; 
    
    // Generamos dos constructores
    public BasePage(int timeOut){
        softAssert = new SoftAssert(); 
        this.timeOut = timeOut; 
    }

    public BasePage(){
        this(defaulTimeOut); 
    }

    // Obtenemos el driver
    protected WebDriver getDriver(){
        return new WebdriverProvider().get(); 
    }

    // Esperamos a que la pagina cargue con un locator especifico
    protected void waitPage(By locator,String pageName){
        final var wait = new WebDriverWait(getDriver(),Duration.ofSeconds(this.timeOut)); 
        Logs.info("Esperando a que la pagina cargue: %s",pageName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Logs.info("%s se ha cargado correctamente",pageName); 
    }

    // Buscamos un elemento
    protected WebElement find(By locator){
        return getDriver().findElement(locator); 
    }

    // Buscamos mas de un elemento
    protected List<WebElement> findAll(By locator){
        return getDriver().findElements(locator); 
    }

    
    public abstract void waitPageToLoad(); 
    public abstract void verifyPage(); 
}
