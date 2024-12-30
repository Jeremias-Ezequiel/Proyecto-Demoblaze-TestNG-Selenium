package automation;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import dataProvider.DataProviderRegistroUsuario;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import pages.RegistroPage;
import templates.ModeloRegistroUsuario;
import utilities.BaseTest;
import utilities.Logs;

@Feature("Registro de usuario - Chrome")
public class RegistroTests extends BaseTest {
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
    @Test(dataProvider = DataProviderRegistroUsuario.DP_POSITIVO,dataProviderClass = DataProviderRegistroUsuario.class,
    groups = {regression,smoke})
    public void registrarUsuarioConCredencialesValidasTest(ModeloRegistroUsuario usuario){
        Logs.debug("Caso de prueba : %s",usuario.getId());
        registroPage.rellenandoFormulario(usuario.getUsuario(), usuario.getPassword());
        registroPage.verificarMensaje(mensajeRegistroExistente);
    }

    @Description("Intentar registrar un usuario con caracteres limites en el input username")
    @Test(dataProvider = DataProviderRegistroUsuario.DP_LIMITEUSER, dataProviderClass = DataProviderRegistroUsuario.class, 
    groups = {regression,smoke})
    public void intentarRegistrarConUsernameLimitesTest(ModeloRegistroUsuario usuario){
        System.out.printf("Caso de prueba: %s",usuario.getId());
        registroPage.rellenandoFormulario(usuario.getUsuario(),usuario.getPassword());
        registroPage.verificarMensaje(mensajeRegistroVacio);
    }

    @Description("Intentar registrar un usuario con caracteres limites en el input password")
    @Test(dataProvider = DataProviderRegistroUsuario.DP_LIMITEPASSWORD,dataProviderClass = DataProviderRegistroUsuario.class,
    groups = {regression,smoke})
    public void intentarRegistrarConPasswordLimitesTest(ModeloRegistroUsuario usuario){
        System.out.printf("Caso de prueba: %s",usuario.getId());
        registroPage.rellenandoFormulario(usuario.getUsuario(), usuario.getPassword());
        registroPage.verificarMensaje(mensajeRegistroVacio);
    }

}
