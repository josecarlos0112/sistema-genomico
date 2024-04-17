package gestion_informacion;

import java.io.*;
import java.util.*;

public class BusquedaTextos {
    public static boolean busquedaLineal(String palabra, String archivo) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(archivo));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains(palabra)) {
                return true;
            }
        }
        reader.close();
        return false;
    }

    public static boolean busquedaBinaria(String palabra, String archivo) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(archivo));
        ArrayList<String> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        Collections.sort(lines);
        int index = Collections.binarySearch(lines, palabra);
        reader.close();
        return (index >= 0);
    }
}