package automation;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import io.qameta.allure.testng.AllureTestNg;

import dataProvider.DataProviderProcesoPago;
import io.qameta.allure.Description;
import pages.ProcesoPagoPage;
import templates.ModeloProcesoPago;
import utilities.BaseTest;
import utilities.Logs;

@Listeners(AllureTestNg.class)
public class ProcesoPagoTests extends BaseTest { 
    private final ProcesoPagoPage procesoPago = new ProcesoPagoPage(); 
    private final String mensajeCompraFallida = "Please fill out Name and Creditcard."; 

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        commonFlows.goToCartPage();
    }

    @Description("Verificar el formulario de compra")
    @Test(groups = {smoke})
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
    groups = {regression})
    public void realizarCompraConDatosInvalidos(ModeloProcesoPago usuario){
        Logs.info("Test: %s",usuario.getId());
        procesoPago.rellenarFormulario(usuario.getNombre() , usuario.getPais(), usuario.getCiudad(), usuario.getTarjeta(),
        usuario.getMes(), usuario.getYear());
        procesoPago.verificarMensajeCompraFallida(mensajeCompraFallida);
    }
}
