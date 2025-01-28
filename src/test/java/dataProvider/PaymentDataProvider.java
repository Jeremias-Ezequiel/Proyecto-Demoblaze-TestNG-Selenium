package dataProvider;

import org.testng.annotations.DataProvider;

import data.ExcelReader;
import modelos.PaymentModel;

import java.util.List;

public class PaymentDataProvider {
    public static final String DP_COMPLETO = "CasosMixtos ";
    public static final String DP_NEGATIVO = "CasosNegativos";
    public static final String DP_POSITIVO = "CasoPositivos";

    @DataProvider(name = DP_COMPLETO)
    public Object[][] dataProvider(){
        List<PaymentModel> listaCompleta = ExcelReader.obtenerExcel(PaymentModel.class);
        return convertirListaAObjectArray(listaCompleta);
    }

    @DataProvider(name = DP_NEGATIVO)
    public Object[][] casosNegativos(){
        String casoNegativo = "NEG";
        List<PaymentModel> listaCompleta = ExcelReader.obtenerExcel(PaymentModel.class);
        List<PaymentModel> listaNegativos = ExcelReader.filtrarPorTipo(listaCompleta,casoNegativo);

        return convertirListaAObjectArray(listaNegativos);
    }

    @DataProvider(name = DP_POSITIVO)
    public Object[][] casosPositivos(){
        String casoPositivo = "POS";
        List<PaymentModel> listaCompleta = ExcelReader.obtenerExcel(PaymentModel.class);
        List<PaymentModel> listaPositivo = ExcelReader.filtrarPorTipo(listaCompleta,casoPositivo);

        return convertirListaAObjectArray(listaPositivo);
    }

    private Object[][] convertirListaAObjectArray(List<PaymentModel> lista){
        int tamanio = lista.size();
        Object[][] object = new Object[tamanio][];

        for(int i = 0; i < tamanio; i++){
            object[i] = new Object[]{lista.get(i)};
        }

        return object;
    }
}
