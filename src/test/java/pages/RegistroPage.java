package pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;
import utilities.BasePage;
import utilities.Logs;

public class RegistroPage extends BasePage {
    private final By btnRegistro = By.id("signin2");
    private final By formulario = By.id("signInModal");
    private final By inputUsername = By.id("sign-username");
    private final By inputPassword = By.id("sign-password");
    private final By btnRegitrarse = By.xpath("//button[text()='Sign up']"); 

    @Override
    @Step("Esperando que cargue la pagina")
    public void waitPageToLoad() {
        waitPage(btnRegistro,this.getClass().getSimpleName()); 
    } 

    @Override
    @Step("Verificando la pagina de Registro")
    public void verifyPage() {
        new BarraNavegacionPage().clicSignUp();
        Logs.info("Verificando la visibilidad del formulario");
        softAssert.assertTrue(find(btnRegistro).isDisplayed());
        softAssert.assertTrue(find(inputUsername).isDisplayed());
        softAssert.assertTrue(find(inputPassword).isDisplayed());
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(3)); 
        wait.until(ExpectedConditions.elementToBeClickable(btnRegistro));
        softAssert.assertAll();
    }

    @Step("Rellenando el formulario de registro")
    public void rellenandoFormulario(String username, String password){
        Logs.info("Haciendo clic en 'Sign Up'");
        find(btnRegistro).click();

        Logs.info("Escribiendo el username");
        find(inputUsername).sendKeys(username);

        Logs.info("Escribiendo el password");
        find(inputPassword).sendKeys(password);

        Logs.info("Haciendo clic en boton registrarse");
        find(btnRegitrarse).click();
    }

    @Step("Verificar mensaje de Alert")
    public void verificarMensaje(String mensaje){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent()); 
        Logs.info("Obteniendo mensaje del alert"); 
        softAssert.assertEquals(alert.getText(),mensaje,String.format("El mensaje obtenido fue: %s y el mensaje esperado era: %s",
        alert.getText(),mensaje));
        softAssert.assertAll();
    }
}
