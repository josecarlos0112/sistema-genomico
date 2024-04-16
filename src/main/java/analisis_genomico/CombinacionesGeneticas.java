package analisis_genomico;

public class CombinacionesGeneticas {

    public int calcularCombinaciones(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return n * calcularCombinaciones(n - 1);
        }
    }
}