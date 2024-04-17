package analisis_numerico;

public class SumatoriaNumeros {
    public static int sumatoria(int n) {
        if (n <= 0) {
            return 0;
        } else {
            return n + sumatoria(n - 1);
        }
    }
}