package utilities;

import org.openqa.selenium.WebDriver;

import pages.BarraNavegacionPage;
import pages.FormularioPagoPage;
import pages.InicioSesionPage;
import pages.RegistroPage;

public class CommonFlows {
    private String indexURL = "https://demoblaze.com/";

    public WebDriver getDriver(){
        return new WebdriverProvider().get(); 
    }

    public void goToRegisterPage(){
        Logs.info("Navegando a la pagina");
        getDriver().get(indexURL);
        new RegistroPage().waitPageToLoad();
    }

    public void goToLogIn(){
        Logs.info("Navegando al formulario de inicio sesion");
        getDriver().get(indexURL);
        new BarraNavegacionPage().clicLogin();
        new InicioSesionPage().waitPageToLoad();
    }

    public void goToCartPage(){
        goToRegisterPage();
        new BarraNavegacionPage().clicCart(); 
        new FormularioPagoPage().waitPageToLoad(); 
    }
}
