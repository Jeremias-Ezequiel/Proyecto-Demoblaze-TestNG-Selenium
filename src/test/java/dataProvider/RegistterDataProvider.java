package dataProvider;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

import data.ExcelReader;
import modelos.RegisterModel;

public class RegistterDataProvider {
    public static final String DP_COMPLETO = "CasosMixtos"; 
    public static final String DP_POSITIVO = "CasosPositivos";
    public static final String DP_NEGATIVO = "CasosNegativos"; 
    public static final String DP_LIMITEUSER = "CasosLimitesUsername"; 
    public static final String DP_LIMITEPASSWORD = "CasosLimitesPassword"; 
    private final int minCharUsername = 6; 
    private final int maxCharUsername = 12;
    private final int minCharPassword = 5; 
    private final int maxCharPassword = 10;  

    @DataProvider(name = DP_COMPLETO)
    public Object[][] dataProvider(){
        List<RegisterModel> listaCompleta = ExcelReader.obtenerExcel(RegisterModel.class); 
        return convertirListaAObjectArray(listaCompleta); 
    }

    @DataProvider(name = DP_NEGATIVO)
    public Object[][] casosNegativos(){
        String casoNegativos = "NEG"; 
        List<RegisterModel> listaCompleta = ExcelReader.obtenerExcel(RegisterModel.class); 
        List<RegisterModel> listaNegativos = ExcelReader.filtrarPorTipo(listaCompleta, casoNegativos); 
        return convertirListaAObjectArray(listaNegativos); 
    }

    @DataProvider(name = DP_POSITIVO)
    public Object[][] casosPositivos(){
        String casosPositivos = "POS"; 
        List<RegisterModel> listaCompleta = ExcelReader.obtenerExcel(RegisterModel.class); 
        List<RegisterModel> listaFiltrada = ExcelReader.filtrarPorTipo(listaCompleta, casosPositivos); 
        return convertirListaAObjectArray(listaFiltrada); 
    }

    @DataProvider(name = DP_LIMITEUSER) 
    public Object[][] casosLimitesUser(){
        String username = "username"; 
        List<RegisterModel> listaCompleta = ExcelReader.obtenerExcel(RegisterModel.class); 
        List<RegisterModel> listaFiltrada = filtrarPorLimites(listaCompleta,username); 
        return convertirListaAObjectArray(listaFiltrada); 
    }

    @DataProvider(name = DP_LIMITEPASSWORD) 
    public Object[][] casosLimitesPassword(){
        String password = "password"; 
        List<RegisterModel> listaCompleta = ExcelReader.obtenerExcel(RegisterModel.class); 
        List<RegisterModel> listaFiltrada = filtrarPorLimites(listaCompleta,password); 
        return convertirListaAObjectArray(listaFiltrada); 
    }

    private Object[][] convertirListaAObjectArray(List<RegisterModel> listaCompleta){
        int tamanio = listaCompleta.size(); 
        Object[][] object = new Object[tamanio][]; 
        
        for(int i = 0; i < tamanio; i++){
            object[i] = new Object[]{listaCompleta.get(i)}; 
        }

        return object; 
    }

    private List<RegisterModel> filtrarPorLimites(List<RegisterModel> listaCompleta,String propiedad){
        List<RegisterModel> listaFiltrada = new ArrayList<>(); 
        if(propiedad.equalsIgnoreCase("username")){
            for(RegisterModel dato : listaCompleta){
                if(dato.getUsuario().length() < minCharUsername || dato.getUsuario().length() > maxCharUsername){
                    listaFiltrada.add(dato); 
                }
            }
        }

        if(propiedad.equalsIgnoreCase("password")){
            for(RegisterModel dato : listaCompleta){
                if(dato.getPassword().length() < minCharPassword || dato.getPassword().length() > maxCharPassword){
                    listaFiltrada.add(dato); 
                }
            }
        }

        return listaFiltrada;

        // List<ModeloRegistroUsuario> listaFiltrada = new ArrayList<>();  
        // for(ModeloRegistroUsuario dato : listaCompleta){
        //     if(dato.getPassword().length() > maxCharPassword || dato.getPassword().length() < minCharPassword || dato.getUsuario().length()
        //     > maxCharUsername || dato.getUsuario().length() < minCharUsername){
        //         listaFiltrada.add(dato); 
        //     }
        // }
        // return listaFiltrada; 
    }
}
