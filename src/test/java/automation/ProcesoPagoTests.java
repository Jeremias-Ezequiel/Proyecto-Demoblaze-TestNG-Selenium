package automation;

import io.qameta.allure.Feature;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import templates.ModeloProcesoPago;
import utilities.BaseTest;
import utilities.DataProviderProcesoPago;
import utilities.Logs;
import utils.Mensajes;

import java.time.Duration;

@Feature("Proceso de pago - Chrome")
public class ProcesoPagoTests extends BaseTest {
    private String URL = "https://demoblaze.com/";
    Mensajes mensajes = new Mensajes();

    @Test(dataProvider = DataProviderProcesoPago.DP_NEGATIVO,dataProviderClass = DataProviderProcesoPago.class, groups = {regression,smoke})
    public void validarCamposVacios(ModeloProcesoPago datos) {

        Logs.debug("Ejecutando caso de prueba: %s",datos.getId());
        rellenarFormularioCompra(datos);
        String mensaje = mensajes.obtenerMensajeModal(driver);
        softAssert.assertEquals(mensaje,mensajes.getMensajePagoFallo(),String.format("Error : El mensajes espera era: %s, y el mensaje obtenido fue: %s",mensajes.getMensajePagoFallo(),mensaje));

        softAssert.assertAll();
    }

    @Test(dataProvider = DataProviderProcesoPago.DP_POSITIVO, dataProviderClass = DataProviderProcesoPago.class)
    public void validarCompraExitosa(ModeloProcesoPago datos){
        Logs.debug("Ejecutando caso de prueba : %s", datos.getId());
        rellenarFormularioCompra(datos);

        Logs.debug("Esperando a que sea visible el mensaje de exito");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3)); 
        WebElement modalExito = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("orderModal"))); 

        softAssert.assertTrue(modalExito.isDisplayed(),"El mensaje de compra exitosa no es viisble");

        softAssert.assertAll();
    }

    private void rellenarFormularioCompra(ModeloProcesoPago datos){
        Logs.info("Ingresando a la url: %s",URL);
        driver.get(URL);

        // Haciendo clic en el botón para ir al carrito
        WebElement btnCart = driver.findElement(By.id("cartur")); 
        btnCart.click();

        Logs.debug("Buscando botón : 'Place Order' ");
        WebElement btnPlaceOrder = driver.findElement(By.cssSelector("#page-wrapper button[type='button']"));
        btnPlaceOrder.click();

        Logs.debug("Esperando a que el modal sea visible");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("orderModal")));

        Logs.debug("Modal visible. Buscando campos del formulario");
        WebElement campoNombre = driver.findElement(By.cssSelector("#orderModal input[id='name']"));
        WebElement campoPais = driver.findElement(By.cssSelector("#orderModal input[id='country']"));
        WebElement campoCiudad = driver.findElement(By.cssSelector("#orderModal input[id='city']"));
        WebElement campoTarjeta = driver.findElement(By.cssSelector("#orderModal input[id='card']"));
        WebElement campoMes = driver.findElement(By.cssSelector("#orderModal input[id='month']"));
        WebElement campoYear = driver.findElement(By.cssSelector("#orderModal input[id='year']"));
        
        Logs.info("Ingresando datos en el formulario");
        campoNombre.sendKeys(datos.getNombre());
        campoPais.sendKeys(datos.getPais());
        campoCiudad.sendKeys(datos.getCiudad());
        campoTarjeta.sendKeys(datos.getTarjeta());
        campoMes.sendKeys(datos.getMes());
        campoYear.sendKeys(datos.getYear());
        
        WebElement btnPurchase = driver.findElement(By.xpath("//button[text()='Purchase']"));
        btnPurchase.click();
    }
}
