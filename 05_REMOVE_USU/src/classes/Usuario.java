/**
 * Autora: Ana Paula Rosales Olguin
 * Fecha: 07 de noviembre de 2025
 */

package classes;

public class Usuario {

    private String nombre;
    private String correo;

    public Usuario(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        // Esto se usa para guardar en archivo: "nombre,correo"
        return nombre + "," + correo;
    }

    // Convierte una línea del archivo en un Usuario
    public static Usuario fromString(String linea) {
        String[] partes = linea.split(",");
        if (partes.length == 2) {
            return new Usuario(partes[0].trim(), partes[1].trim());
        }
        return null;
    }
}
