package pages;

import org.openqa.selenium.By;

import io.qameta.allure.Step;
import utilities.BasePage;
import utilities.Logs;

public class BarraNavegacionPage extends BasePage{
    private final By btnLogin = By.id("login2");
    private final By btnSignUp = By.id("signin2"); 
    private final By btnLogInExitoso = By.id("nameofuser");

    @Step("Haciendo clic en 'Log in'")
    public void clicLogin(){
        Logs.info("Haciendo clic en 'Log in'");
        find(btnLogin).click();
    }

    @Step("Haciendo clic en 'Sign up'")
    public void clicSignUp(){
        Logs.info("Haciendo clic en 'Sign up'");
        find(btnSignUp).click();
    }

    @Step("Haciendo clic en 'Cart'")
    public void clicCart(){
        Logs.info("Haciendo clic en 'Cart'");
        find(By.id("cartur")).click();
    }

    @Override
    @Step("Esperando a que cargue la navegacion principal")
    public void waitPageToLoad() {
        Logs.info("Esperando a que cargue la navegacion principal");
        waitPage(btnLogin,this.getClass().getSimpleName());
    }

    @Override
    @Step("Verificando la visibilidad de los botones")
    public void verifyPage() {
        Logs.info("Verificando la visibilidad de los botones");
        softAssert.assertTrue(find(btnLogin).isDisplayed());
        softAssert.assertTrue(find(btnSignUp).isDisplayed());
        softAssert.assertAll();
    }

    @Step("Verificando el inicio de sesion exitoso")
    public void verificarInicioSesionExitoso(){
        waitPage(btnLogInExitoso, this.getClass().getSimpleName());
        Logs.info("Verificando el inicio de sesion exitoso");
        softAssert.assertTrue(find(btnLogInExitoso).isDisplayed());
        softAssert.assertAll();
    }

}
