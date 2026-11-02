package modelo.personas;
import modelo.clinica.Cita;

public class Veterinario extends Persona {
    private String cedulaProfesional;
    private String especialidad;
    private Cita cita;

    public Veterinario(String nombre, String apellido, String telefono, String correo, String especialidad, String cedulaProfesional, String horario) {
        super(nombre, apellido, telefono, correo);
        this.especialidad = especialidad;
        this.cedulaProfesional = cedulaProfesional;
    }

    public String getCedulaProfesional() {
        return cedulaProfesional;
    }

    public void setCedulaProfesional(String cedulaProfesional) {
        this.cedulaProfesional = cedulaProfesional;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void atenderCita(Cita cita) {
        if (cita.getVeterinario() != this) {
            throw new IllegalArgumentException("La cita no esta asignada a este veterinario.");
        }
        cita.atender();
    }
    
}
