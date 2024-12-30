package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pages.ProcesoPagoPage;
import pages.RegistroPage;

public class CommonFlows {
    private String indexURL = "https://demoblaze.com/";
    private String cartURL = "https://demoblaze.com/cart.html"; 

    public WebDriver getDriver(){
        return new WebdriverProvider().get(); 
    }

    public void goToRegisterPage(){
        Logs.info("Navegando a la pagina");
        getDriver().get(indexURL);

        new RegistroPage().waitPageToLoad();
    }

    public void goToCartPage(){
        goToRegisterPage();
        getDriver().findElement(By.id("cartur")).click();
        new ProcesoPagoPage().waitPageToLoad(); 
    }
}
