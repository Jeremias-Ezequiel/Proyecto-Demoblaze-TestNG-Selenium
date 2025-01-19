package utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Mensajes {

    // Registro de usuario
    private String mensajeRegistroExitoso = "Sign up successful.";
    private String mensajeRegistroExistente = "This user already exist.";
    private String mensajeRegistroVacio = "Please fill out Username and Password.";

    // Proceso dep ago
    private String mensajePagoFallo = "Please fill out Name and Creditcard.";

    public Mensajes() {}

    public String getMensajeRegistroExitoso() {
        return mensajeRegistroExitoso;
    }

    public String getMensajeRegistroExistente() {
        return mensajeRegistroExistente;
    }

    public String getMensajeRegistroVacio() {
        return mensajeRegistroVacio;
    }

    public String getMensajePagoFallo() {
        return mensajePagoFallo;
    }

    public String obtenerMensajeModal(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        String mensaje = alert.getText();
        alert.accept();

        return mensaje;
    }
}
