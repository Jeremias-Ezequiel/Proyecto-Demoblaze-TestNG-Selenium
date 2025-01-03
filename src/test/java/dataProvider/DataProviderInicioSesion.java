package dataProvider;

import org.testng.annotations.DataProvider;

import data.DataGiven;
import modelos.ModeloCredenciales;

public class DataProviderInicioSesion {
    public static final String DP_DATOSINVALIDOS = "CredencialesInvalidas"; 

    @DataProvider(name = DP_DATOSINVALIDOS)
    public Object[][] obtenerCredencialesInvalidas(){
        ModeloCredenciales inexistente = DataGiven.getCredencialInexistente(); 
        ModeloCredenciales invalida = DataGiven.getCredencialInvalida(); 

        return new Object[][]{
            {inexistente.getUsername(),inexistente.getPassword(),inexistente.getMessage()},
            {invalida.getUsername(),invalida.getPassword(),invalida.getMessage()}
        };
    }
}   
