package modelo.personas;

public class Dueno extends Persona {
    private String direccion;
    private Mascota mascota;

    public Dueno (String nombre, String apellido, String telefono, String correo, String direccion, Mascota mascota) {
        super(nombre, apellido, telefono, correo);
        this.direccion = direccion;
        this.mascota = mascota;
    }
    
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Mascota agregarMascota(Mascota mascota) {
        this.mascota = mascota;
        return mascota;
    }

    public Mascota listarMascota() {
        return mascota;
    }

    public void eliminarMascota() {
        this.mascota = null;
    }
    
}
