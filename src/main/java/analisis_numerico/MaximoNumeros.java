package analisis_numerico;

import java.util.List;

public class MaximoNumeros {
    public static int maximo(List<Integer> numeros) {
        int maximo = Integer.MIN_VALUE;
        for (int numero : numeros) {
            if (numero > maximo) {
                maximo = numero;
            }
        }
        return maximo;
    }
}