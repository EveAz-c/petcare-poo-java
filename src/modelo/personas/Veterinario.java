package modelo.personas;
import modelo.clinica.Cita;

public class Veterinario extends Persona {
    private String cedulaProfesional;
    private String especialidad;
    private Cita cita;

<<<<<<< Updated upstream
    public Veterinario(String nombre, String apellido, String telefono, String correo, String especialidad, String cedulaProfesional, String horario) {
=======
    /**
     * Crea un veterinario
     *
     * @param nombre nombre del veterinario
     * @param apellido apellido del veterinario
     * @param telefono telefono de contacto
     * @param correo correo electronico
     * @param especialidad especialidad medica
     * @param cedulaProfesional cedula profesional
     * @throws IllegalArgumentException si el nombre o el apellido estan vacios
     */
    public Veterinario(String nombre, String apellido, String telefono, String correo,
                String especialidad, String cedulaProfesional) {
>>>>>>> Stashed changes
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
