package automation;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import io.qameta.allure.testng.AllureTestNg;

import data.DataGiven;
import dataProvider.DataProviderInicioSesion;
import io.qameta.allure.Description;
import modelos.ModeloCredenciales;
import pages.BarraNavegacionPage;
import pages.InicioSesionPage;
import utilities.BaseTest;

@Listeners(AllureTestNg.class)
public class InicioSesionTests extends BaseTest{
    private final InicioSesionPage inicioSesionPage = new InicioSesionPage(); 

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        commonFlows.goToLogIn();
    }

    @Description("Verificando la pagina de inicio sesion")
    @Test(groups = {smoke})
    public void verificarPaginaTest(){
        inicioSesionPage.verifyPage();
    }

    @Description("Iniciar sesion con credenciales validas")
    @Test(groups = {smoke})
    public void iniciarSesionConCredencialesValidasTest(){
        ModeloCredenciales credenciales = DataGiven.getCredencialValida(); 
        inicioSesionPage.rellenandoFormulario(credenciales.getUsername(), credenciales.getPassword());
        new BarraNavegacionPage().verificarInicioSesionExitoso(); 
    }

    @Description("Intentar iniciar sesion con credenciales invalidas")
    @Test(dataProvider = DataProviderInicioSesion.DP_DATOSINVALIDOS,dataProviderClass = DataProviderInicioSesion.class,
    groups = {regression})
    public void intentarIniciarSesionConCredencialesInvalidas(String username, String password, String message){ 
        inicioSesionPage.rellenandoFormulario(username,password);
        inicioSesionPage.verificarMensaje(message);
    }
}
