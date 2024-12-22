package utilities;

import org.testng.annotations.DataProvider;
import reader.ExcelReader;
import templates.ModeloProcesoPago;

import java.util.List;

public class DataProviderProcesoPago {
    public static final String DP_COMPLETO = "CasosMixtos ";
    public static final String DP_NEGATIVO = "CasosNegativos";
    public static final String DP_POSITIVO = "CasoPositivos";

    @DataProvider(name = DP_COMPLETO)
    public Object[][] dataProvider(){
        List<ModeloProcesoPago> listaCompleta = ExcelReader.obtenerExcel(ModeloProcesoPago.class);
        return convertirListaAObjectArray(listaCompleta);
    }

    @DataProvider(name = DP_NEGATIVO)
    public Object[][] casosNegativos(){
        String casoNegativo = "NEG";
        List<ModeloProcesoPago> listaCompleta = ExcelReader.obtenerExcel(ModeloProcesoPago.class);
        List<ModeloProcesoPago> listaNegativos = ExcelReader.filtrarPorTipo(listaCompleta,casoNegativo);

        return convertirListaAObjectArray(listaNegativos);
    }

    @DataProvider(name = DP_POSITIVO)
    public Object[][] casosPositivos(){
        String casoPositivo = "POS";
        List<ModeloProcesoPago> listaCompleta = ExcelReader.obtenerExcel(ModeloProcesoPago.class);
        List<ModeloProcesoPago> listaPositivo = ExcelReader.filtrarPorTipo(listaCompleta,casoPositivo);

        return convertirListaAObjectArray(listaPositivo);
    }

    private Object[][] convertirListaAObjectArray(List<ModeloProcesoPago> lista){
        int tamanio = lista.size();
        Object[][] object = new Object[tamanio][];

        for(int i = 0; i < tamanio; i++){
            object[i] = new Object[]{lista.get(i)};
        }

        return object;
    }
}
