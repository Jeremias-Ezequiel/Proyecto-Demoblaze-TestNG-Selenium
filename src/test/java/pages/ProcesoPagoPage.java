package pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;
import utilities.BasePage;
import utilities.Logs;

public class ProcesoPagoPage extends BasePage {
    private final By btnPlaceOrder = By.cssSelector("button[data-target='#orderModal']"); 
    private final By modalOrder = By.cssSelector("#orderModal .modal-dialog");
    private final By campoNombre = By.cssSelector("#orderModal input[id='name']");
    private final By campoPais = By.cssSelector("#orderModal input[id='country']");
    private final By campoCiudad = By.cssSelector("#orderModal input[id='city']");
    private final By campoTarjeta = By.cssSelector("#orderModal input[id='card']");
    private final By campoMes = By.cssSelector("#orderModal input[id='month']");
    private final By campoYear = By.cssSelector("#orderModal input[id='year']");
    private final By btnPurchase = By.xpath("//button[text()='Purchase']"); 
    private final By modalCompraExitosa = By.className("sweet-overlay");
    private final By mensajeCompraExitosa = By.xpath("//h2[text()='Thank you for your purchase!']");

    @Override
    @Step("Esperando a que la pagina cargue")
    public void waitPageToLoad() {
       waitPage(btnPlaceOrder, this.getClass().getSimpleName());
    }

    @Override
    @Step("Verificando el formulario de compra")
    public void verifyPage() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(btnPlaceOrder));
        Logs.info("Haciendo clic en 'Place Order'");
        find(btnPlaceOrder).click();
        Logs.info("Verificando la visibilidad del formulario de compra");
        softAssert.assertTrue(find(campoNombre).isDisplayed());
        softAssert.assertTrue(find(campoPais).isDisplayed());
        softAssert.assertTrue(find(campoCiudad).isDisplayed());
        softAssert.assertTrue(find(campoTarjeta).isDisplayed());
        softAssert.assertTrue(find(campoMes).isDisplayed());
        softAssert.assertTrue(find(campoYear).isDisplayed());
        softAssert.assertTrue(find(btnPurchase).isDisplayed());
        wait.until(ExpectedConditions.elementToBeClickable(btnPurchase)); 
        softAssert.assertAll();
    }

    @Step("Rellenando el formulario de compra")
    public void rellenarFormulario(String nombre, String pais, String ciudad, String tarjeta, String mes, String year){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(4)); 
        
        Logs.info("Haciendo clic en 'Place order'");
        find(btnPlaceOrder).click();

        Logs.info("Esperando a que el formulario sea visible");
        wait.until(ExpectedConditions.visibilityOfElementLocated(modalOrder));

        Logs.info("Escribiendo el nombre: %s",nombre);
        find(campoNombre).sendKeys(nombre);

        Logs.info("Escribiendo el pais: %s",pais);
        find(campoPais).sendKeys(pais);

        Logs.info("Escribiendo la ciudad: %s", ciudad);
        find(campoCiudad).sendKeys(ciudad);

        Logs.info("Escribiendo la tarjeta: %s",tarjeta);
        find(campoTarjeta).sendKeys(tarjeta);

        Logs.info("Escribiendo el mes: %s",mes);
        find(campoMes).sendKeys(mes);

        Logs.info("Escribiendo el año: %s",year);
        find(campoYear).sendKeys(year);

        Logs.info("Haciendo clic en boton 'Purchase'"); 
        find(btnPurchase).click();
    }

    @Step("Verificar modal de compra exitosa")
    public void verificarModalCompraExitosa(){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(3));
        Logs.info("Verificando la visibilidad de compra exitosa");
        wait.until(ExpectedConditions.visibilityOfElementLocated(modalCompraExitosa)); 
        softAssert.assertTrue(find(modalCompraExitosa).isDisplayed());
        softAssert.assertTrue(find(mensajeCompraExitosa).isDisplayed());
        softAssert.assertAll();
    }

    @Step("Verificar mensaje de compra fallida")
    public void verificarMensajeCompraFallida(String mensaje){
        Logs.info("Esperando a que aparezca el alert");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(3));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Logs.info("Obteniendo el mensaje del alert");
        softAssert.assertEquals(alert.getText(),mensaje,String.format("El mensaje obtenido fue: %s y el mensaje esperado era: %s",
        alert.getText(),mensaje));
        softAssert.assertAll();
    }
}
