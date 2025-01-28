package automation;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import io.qameta.allure.testng.AllureTestNg;
import modelos.PaymentModel;
import dataProvider.PaymentDataProvider;
import io.qameta.allure.Description;
import pages.FormularioPagoPage;
import utilities.BaseTest;
import utilities.Logs;

@Listeners(AllureTestNg.class)
public class FormularioPagoTests extends BaseTest { 
    private final FormularioPagoPage procesoPago = new FormularioPagoPage(); 
    private final String mensajeCompraFallida = "Please fill out Name and Creditcard."; 

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        commonFlows.goToCartPage();
    }

    @Description("Verificar el formulario de compra")
    @Test(groups = {"smoke"})
    public void verificarFormularioVisible(){
        procesoPago.verifyPage();
    }

    @Description("Rellenar el formulario de compra con datos validos")
    @Test(dataProvider = PaymentDataProvider.DP_POSITIVO,dataProviderClass = PaymentDataProvider.class,
    groups = {"regression","smoke"})
    public void realizarCompraConDatosValidosTest(PaymentModel usuario){
        Logs.info("Test: %s",usuario.getId());
        procesoPago.rellenarFormulario(usuario.getNombre() , usuario.getPais(), usuario.getCiudad(), usuario.getTarjeta(),
        usuario.getMes(), usuario.getYear());
        procesoPago.verificarModalCompraExitosa();
    }

    @Description("Rellenar el formulario de compra con datos invalidos")
    @Test(dataProvider = PaymentDataProvider.DP_NEGATIVO, dataProviderClass = PaymentDataProvider.class,
    groups = {"regression"})
    public void realizarCompraConDatosInvalidos(PaymentModel usuario){
        Logs.info("Test: %s",usuario.getId());
        procesoPago.rellenarFormulario(usuario.getNombre() , usuario.getPais(), usuario.getCiudad(), usuario.getTarjeta(),
        usuario.getMes(), usuario.getYear());
        procesoPago.verificarMensajeCompraFallida(mensajeCompraFallida);
    }
}
