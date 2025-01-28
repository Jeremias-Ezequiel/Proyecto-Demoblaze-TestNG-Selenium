package automation;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import io.qameta.allure.testng.AllureTestNg;
import modelos.RegisterModel;
import dataProvider.RegistterDataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import pages.RegistroPage;
import utilities.BaseTest;
import utilities.Logs;

@Listeners(AllureTestNg.class)
public class RegistroTests extends BaseTest{
    private final RegistroPage registroPage = new RegistroPage(); 
    private String mensajeRegistroExitoso = "Sign up successful.";
    private String mensajeRegistroExistente = "This user already exist.";
    private String mensajeRegistroVacio = "Please fill out Username and Password.";

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        commonFlows.goToRegisterPage();
    }

    @Description("Verificar el formulario de registro")
    @Test(groups = {regression,smoke})
    public void verificarFormularioTest(){
        registroPage.verifyPage();
    }

    @Description("Registrar un usuario con username y password valido")
    @Test(dataProvider = RegistterDataProvider.DP_POSITIVO,dataProviderClass = RegistterDataProvider.class,
    groups = {regression,smoke})
    public void registrarUsuarioConCredencialesValidasTest(RegisterModel usuario){
        Logs.debug("Caso de prueba : %s",usuario.getId());
        registroPage.rellenandoFormulario(usuario.getUsuario(), usuario.getPassword());
        registroPage.verificarMensaje(mensajeRegistroExistente);
    }

    @Description("Intentar registrar un usuario con caracteres limites en el input username")
    @Test(dataProvider = RegistterDataProvider.DP_LIMITEUSER, dataProviderClass = RegistterDataProvider.class, 
    groups = {regression})
    public void intentarRegistrarConUsernameLimitesTest(RegisterModel usuario){
        System.out.printf("Caso de prueba: %s",usuario.getId());
        registroPage.rellenandoFormulario(usuario.getUsuario(),usuario.getPassword());
        registroPage.verificarMensaje(mensajeRegistroVacio);
    }

    @Description("Intentar registrar un usuario con caracteres limites en el input password")
    @Test(dataProvider = RegistterDataProvider.DP_LIMITEPASSWORD,dataProviderClass = RegistterDataProvider.class,
    groups = {regression})
    public void intentarRegistrarConPasswordLimitesTest(RegisterModel usuario){
        System.out.printf("Caso de prueba: %s",usuario.getId());
        registroPage.rellenandoFormulario(usuario.getUsuario(), usuario.getPassword());
        registroPage.verificarMensaje(mensajeRegistroVacio);
    }

}
