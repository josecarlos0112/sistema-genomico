package gestion_informacion;

import java.io.*;
import java.util.*;

public class OrganizacionDocs {
    public static void ordenarArchivo(String archivoEntrada, String archivoSalida) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(archivoEntrada));
        ArrayList<String> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        Collections.sort(lines);
        BufferedWriter writer = new BufferedWriter(new FileWriter(archivoSalida));
        for (String sortedLine : lines) {
            writer.write(sortedLine);
            writer.newLine();
        }
        reader.close();
        writer.close();
    }
}