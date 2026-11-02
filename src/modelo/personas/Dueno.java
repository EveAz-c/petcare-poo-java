package modelo.personas;

import modelo.mascotas.Mascota;
import java.util.ArrayList;
import java.util.List;

/**
 * Persona duena de una o varias mascotas, se crea sin mascotas y estas se agregan despues
 */
public class Dueno extends Persona {
    private String direccion;
    private List<Mascota> mascotas;

    /**
     * Crea un dueno sin mascotas
     *
     * @param nombre nombre del dueno
     * @param apellido apellido del dueno
     * @param telefono telefono de contacto
     * @param correo correo electronico
     * @param direccion direccion del dueno
     * @throws IllegalArgumentException si el nombre o el apellido estan vacios
     */
    public Dueno (String nombre, String apellido, String telefono, String correo,
                  String direccion) {
        super(nombre, apellido, telefono, correo);
        this.direccion = direccion;
        this.mascotas = new ArrayList<>();
    }

    /**
     * Obtiene la direccion del dueno
     *
     * @return direccion del dueno
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la direccion del dueno
     *
     * @param direccion nueva direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Agrega una mascota a la lista del dueno si no estaba agregada
     *
     * @param mascota mascota que se agrega
     */
    public void agregarMascota(Mascota mascota) {
        if (mascota != null && !mascotas.contains(mascota)) {
            mascotas.add(mascota);
        }
    }

    /**
     * Obtiene la lista de mascotas del dueno
     *
     * @return lista de mascotas del dueno
     */
    public List<Mascota> listarMascotas() {
        return mascotas;
    }

    /**
     * Quita una mascota de la lista del dueno
     *
     * @param mascota mascota que se quita
     */
    public void eliminarMascota(Mascota mascota) {
        mascotas.remove(mascota);
    }

}
