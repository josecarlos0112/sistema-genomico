package gestion_informacion;

import java.util.*;

public class Gestionfch {
    private TreeSet<Date> fechas;

    public Gestionfch() {
        this.fechas = new TreeSet<>();
    }

    public void agregarFecha(Date fecha) {
        this.fechas.add(fecha);
    }

    public List<Date> listarFechas() {
        return new ArrayList<>(this.fechas);
    }
}