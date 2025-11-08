/**
 * Autora: Ana Paula Rosales Olguin
 * Fecha: 07 de noviembre de 2025
 */

package classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class UsuarioManager {

    private static final String ARCHIVO = "usuarios.txt";

    // Cargar la lista de usuarios desde el archivo
    public static List<Usuario> cargarUsuarios() {
        List<Usuario> lista = new ArrayList<>();

        File file = new File(ARCHIVO);

        if (!file.exists()) {
            // Crear archivo vacío si no existe
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return lista;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Usuario u = Usuario.fromString(linea);
                if (u != null) {
                    lista.add(u);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Guardar lista completa de usuarios en el archivo
    public static void guardarUsuarios(List<Usuario> listaUsuarios) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Usuario u : listaUsuarios) {
                bw.write(u.toString());
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
