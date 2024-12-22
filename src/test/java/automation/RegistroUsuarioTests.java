package automation;


import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import utils.Mensajes;
import utilities.BaseTest;
import reader.ExcelReader;
import utilities.Logs;
import templates.ModeloRegistroUsuario;

import java.time.Duration;
import java.util.List;

@Feature("Registro de usuario - Chrome")
public class RegistroUsuarioTests extends BaseTest {
    List<ModeloRegistroUsuario> listaCompleta = ExcelReader.obtenerExcel(ModeloRegistroUsuario.class);
    private String URL = "https://demoblaze.com/";
    private final int maxCharUsername = 12;
    private final int minCharUsername = 6;
    private final int maxCharPassword = 10;
    private final int minCharPassword = 5;
    Mensajes mensajes = new Mensajes();
    private String username = "username";
    private String password = "password";


    @Description("Caso de prueba T44 : Registrar un usuario con username y password valido")
    @Test(groups = {regression,smoke})
    public void registrarUsuarioConCredencialesValidas() {
        // Datos
        String casoDePrueba = "T44";
        Logs.debug("Obteniendo datos del caso T44");
        ModeloRegistroUsuario dato = ExcelReader.filtrarPorId(listaCompleta,casoDePrueba);

        // Verificacion
        rellenarFormularioRegistro(dato.getUsuario(),dato.getPassword());

        String mensaje = mensajes.obtenerMensajeModal(driver);

        Assert.assertEquals(mensaje,mensajes.getMensajeRegistroExitoso(),String.format("El mensajes esperad era: %s, y el mensaje obtenido fue: %s",mensajes.getMensajeRegistroExitoso(),mensaje));
    }

    @Description("Caso de prueba T45 : Intentar registrar usuario con username de 5 caracteres")
    @Test(groups = {regression,smoke})
    public void noPermitirRegistroConUsernameCorto() {
        // Datos
        int cantidadChar = minCharUsername - 1;
        Logs.debug("Obteniendo datos con username de: %d caracteres",cantidadChar);
        ModeloRegistroUsuario dato = filtrarPorCaracter(listaCompleta,cantidadChar,username);

        // Verificacion
        rellenarFormularioRegistro(dato.getUsuario(),dato.getPassword());
        String mensaje = mensajes.obtenerMensajeModal(driver);

        Assert.assertEquals(mensaje,mensajes.getMensajeRegistroVacio(),String.format("El mensajes esperad era: %s, y el mensaje obtenido fue: %s",mensajes.getMensajeRegistroVacio(),mensaje));
    }

    @Description("Caso de prueba T55 : Intentar registrar un usuario con password de 4 caracteres")
    @Test(groups = {regression,smoke})
    public void noPermitirRegistroConPasswordCorto() {
        //Datos
        int cantidadChar = minCharPassword - 1;
        Logs.debug("Obteniendo datos con password de : %d caracteres",cantidadChar);
        ModeloRegistroUsuario dato = filtrarPorCaracter(listaCompleta,cantidadChar,password);

        // Verificacion
        rellenarFormularioRegistro(dato.getUsuario(),dato.getPassword());
        String mensaje = mensajes.obtenerMensajeModal(driver);

        Assert.assertEquals(mensaje,mensajes.getMensajeRegistroVacio(),String.format("El mensajes esperad era: %s, y el mensaje obtenido fue: %s",mensajes.getMensajeRegistroVacio(),mensaje));
    }

    @Description("Caso de prueba : Intentar registrar un usuario con username de caracteres")
    @Test(groups = {regression,smoke})
    public void noPermitirRegistroConUsernameLargo(){
        //Datos
        int cantidadChar = maxCharUsername + 1; 
        Logs.debug("Obteniendo datos con username de : %s caracteres", cantidadChar);
        ModeloRegistroUsuario dato = filtrarPorCaracter(listaCompleta, cantidadChar, password); 

        //Verificacion
        rellenarFormularioRegistro(dato.getUsuario(), dato.getPassword());
        Logs.debug("Obteniendo mensaje de registro fallido del modal");
        String mensaje = mensajes.obtenerMensajeModal(driver); 

        Assert.assertEquals(mensaje, mensajes.getMensajeRegistroVacio(), String.format("El mensaje esperado era : %s y el mensaje obtenido fue: %s", mensajes.getMensajeRegistroVacio(),mensaje));
    }

    @Description("Caso de prueba  : Intentar registrar un usuario con password de  caracteres")
    @Test(groups = {regression,smoke})
    public void noPermitirRegistroConPasswordLargo() {
        //Datos
        int cantidadChar = maxCharPassword + 1;
        Logs.debug("Obteniendo datos con password de : %d caracteres",cantidadChar);
        ModeloRegistroUsuario dato = filtrarPorCaracter(listaCompleta,cantidadChar,password);

        // Verificacion
        rellenarFormularioRegistro(dato.getUsuario(),dato.getPassword());
        Logs.debug("Obteniendo mensaje de registro fallido del modal");
        String mensaje = mensajes.obtenerMensajeModal(driver);

        Assert.assertEquals(mensaje,mensajes.getMensajeRegistroVacio(),String.format("El mensajes esperad era: %s, y el mensaje obtenido fue: %s",mensajes.getMensajeRegistroVacio(),mensaje));
    }

    //Funciones
    private void rellenarFormularioRegistro(String username, String password){
        Logs.info("Ingresando a la pagina");
        driver.get(URL);
        
        Logs.debug("Buscando botón : 'Sign Up'");
        WebElement signupNavbar = driver.findElement(By.id("signin2"));
        signupNavbar.click();

        Logs.debug("Esperando a que el modal sea visible");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3)); 
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signInModal"))); 

        Logs.debug("Buscando campos del formulario");
        WebElement campoUsuario = driver.findElement(By.id("sign-username"));
        WebElement campoPassword = driver.findElement(By.id("sign-password"));
        
        Logs.debug("Ingresando datos en el formulario");
        Logs.info("Ingresando campo username: %s",username);
        campoUsuario.sendKeys(username);
        
        Logs.info("Ingresando campo password: %s",password);
        campoPassword.sendKeys(password);
        
        WebElement botonSignUp = driver.findElement(By.xpath("//button[text()='Sign up']"));
        botonSignUp.click();
    }

    private ModeloRegistroUsuario filtrarPorCaracter(List<ModeloRegistroUsuario> listaCompleta, int cantidad, String tipo){
        ModeloRegistroUsuario encontrado = null;

        if(tipo.equalsIgnoreCase("username")){
            for(ModeloRegistroUsuario dato : listaCompleta){
                if(dato.getUsuario().length() == cantidad) {
                    encontrado = dato;
                    break;
                }
            }
        }else if(tipo.equalsIgnoreCase("password") ){
            for(ModeloRegistroUsuario dato : listaCompleta){
                if(dato.getPassword().length() == cantidad) {
                    encontrado = dato;
                    break;
                }
            }
        }

        return encontrado;
    }
}
