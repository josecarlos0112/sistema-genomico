package analisis_genomico;

public class ConteoGenes {

    public int contarGenes(String secuenciaADN) {
        if (secuenciaADN.isEmpty()) {
            return 0;
        } else if (secuenciaADN.startsWith("ATG")) {
            return 1 + contarGenes(secuenciaADN.substring(3));
        } else {
            return contarGenes(secuenciaADN.substring(1));
        }
    }
}