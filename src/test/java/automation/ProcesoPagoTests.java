package automation;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import dataProvider.DataProviderProcesoPago;
import io.qameta.allure.Description;
import pages.ProcesoPagoPage;
import templates.ModeloProcesoPago;
import utilities.BaseTest;
import utilities.Logs;

public class ProcesoPagoTests extends BaseTest {
    private final String URL = "https://demoblaze.com/cart.html"; 
    private final ProcesoPagoPage procesoPago = new ProcesoPagoPage(); 
    private final String mensajeCompraFallida = "Please fill out Name and Creditcard."; 

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        Logs.info("Navegando a la pagina");
        driver.get(URL);

        procesoPago.waitPageToLoad();
    }

    @Description("Verificar el formulario de compra")
    @Test
    public void verificarFormularioVisible(){
        procesoPago.verifyPage();
    }

    @Description("Rellenar el formulario de compra con datos validos")
    @Test(dataProvider = DataProviderProcesoPago.DP_POSITIVO,dataProviderClass = DataProviderProcesoPago.class,
    groups = {regression,smoke})
    public void realizarCompraConDatosValidosTest(ModeloProcesoPago usuario){
        Logs.info("Test: %s",usuario.getId());
        procesoPago.rellenarFormulario(usuario.getNombre() , usuario.getPais(), usuario.getCiudad(), usuario.getTarjeta(),
        usuario.getMes(), usuario.getYear());
        procesoPago.verificarModalCompraExitosa();
    }

    @Description("Rellenar el formulario de compra con datos invalidos")
    @Test(dataProvider = DataProviderProcesoPago.DP_NEGATIVO, dataProviderClass = DataProviderProcesoPago.class,
    groups = {regression,smoke})
    public void realizarCompraConDatosInvalidos(ModeloProcesoPago usuario){
        Logs.info("Test: %s",usuario.getId());
        procesoPago.rellenarFormulario(usuario.getNombre() , usuario.getPais(), usuario.getCiudad(), usuario.getTarjeta(),
        usuario.getMes(), usuario.getYear());
        procesoPago.verificarMensajeCompraFallida(mensajeCompraFallida);
    }
}
