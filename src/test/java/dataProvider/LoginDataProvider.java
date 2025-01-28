package dataProvider;

import org.testng.annotations.DataProvider;

import data.DataGiven;
import modelos.CredencialesModel;

public class LoginDataProvider {
    public static final String DP_DATOSINVALIDOS = "CredencialesInvalidas"; 

    @DataProvider(name = DP_DATOSINVALIDOS)
    public Object[][] obtenerCredencialesInvalidas(){
        CredencialesModel inexistente = DataGiven.getCredencialInexistente(); 
        CredencialesModel invalida = DataGiven.getCredencialInvalida(); 

        return new Object[][]{
            {inexistente.getUsername(),inexistente.getPassword(),inexistente.getMessage()},
            {invalida.getUsername(),invalida.getPassword(),invalida.getMessage()}
        };
    }
}   
