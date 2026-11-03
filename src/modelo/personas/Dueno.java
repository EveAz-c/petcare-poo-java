package modelo.personas;

import modelo.mascotas.Mascota;
import java.util.ArrayList;
import java.util.List;

public class Dueno extends Persona {
    private String direccion;
    private List<Mascota> mascotas;

<<<<<<< Updated upstream
    public Dueno (String nombre, String apellido, String telefono, String correo, String direccion, Mascota mascota) {
=======
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
>>>>>>> Stashed changes
        super(nombre, apellido, telefono, correo);
        this.direccion = direccion;
        this.mascotas = new ArrayList<>();
    }
    
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void agregarMascota(Mascota mascota) {
        if (mascota != null && !mascotas.contains(mascota)) {
            mascotas.add(mascota);
        }
    }

    public List<Mascota> listarMascota() {
        return mascotas;
    }

    public void eliminarMascota(Mascota mascota) {
        mascotas.remove(mascota);
    }
    
}
