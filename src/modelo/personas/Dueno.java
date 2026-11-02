package modelo.personas;

import modelo.mascotas.Mascota;
import java.util.ArrayList;
import java.util.List;

public class Dueno extends Persona {
    private String direccion;
    private List<Mascota> mascotas;

    public Dueno (String nombre, String apellido, String telefono, String correo, String direccion, Mascota mascota) {
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
