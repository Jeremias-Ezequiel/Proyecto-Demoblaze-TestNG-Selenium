package pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;
import utilities.BasePage;
import utilities.Logs;

public class InicioSesionPage extends BasePage{
    private By loginModal = By.id("logInModal");
    private By inputUsername = By.id("loginusername");
    private By inputPassword = By.id("loginpassword");
    private By buttonLogIn = By.xpath("//button[text()='Log in']"); 

    @Override
    @Step("Esperando a que cargue la pagina")
    public void waitPageToLoad() {
        Logs.info("Esperando a que cargue la pagina");
        waitPage(loginModal, this.getClass().getSimpleName());
    }

    @Override
    @Step("Verificando el formulario de LogIn")
    public void verifyPage() {
        Logs.info("Verificando el formulario de LogIn");
        softAssert.assertTrue(find(inputUsername).isDisplayed());
        softAssert.assertTrue(find(inputPassword).isDisplayed());
        softAssert.assertTrue(find(buttonLogIn).isDisplayed());
        softAssert.assertAll();
    }

    @Step("Rellenando el formulario de LogIn")
    public void rellenandoFormulario(String username, String password){
        Logs.info("Rellenando el formulario de LogIn");

        Logs.info("Escribiendo el username");
        find(inputUsername).sendKeys(username);

        Logs.info("Escribiendo el password");
        find(inputPassword).sendKeys(password);

        Logs.info("Haciendo clic en el boton LogIn");
        find(buttonLogIn).click();
    }

    @Step("Verificando el mensaje de inicio sesion")
    public void verificarMensaje(String mensaje){
        Logs.info("Verificando el mensaje de inicio sesion");

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(3));
        Logs.info("Esperando a que este presente el alert");
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        Logs.info("Obteniendo el mensaje del alert");
        softAssert.assertEquals(alert.getText(), mensaje,String.format("El mensaje esperado era: %s, y el mensaje obtenido fue: %s",mensaje,alert.getText()));
        softAssert.assertAll(); 
    }
}
