package analisis_numerico;

import java.util.ArrayList;
import java.util.List;

public class ListadoNumeros {
    public static List<Integer> listarNumeros(int inicio, int fin) {
        List<Integer> numeros = new ArrayList<>();
        for (int i = inicio; i <= fin; i++) {
            numeros.add(i);
        }
        return numeros;
    }
}