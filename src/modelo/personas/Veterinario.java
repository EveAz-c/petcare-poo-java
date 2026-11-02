package modelo.personas;
import modelo.clinica.Cita;

/**
 * Medico veterinario que atiende citas, no guarda citas porque estas viven en la clinica
 */
public class Veterinario extends Persona {
    private String cedulaProfesional;
    private String especialidad;

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
        super(nombre, apellido, telefono, correo);
        this.especialidad = especialidad;
        this.cedulaProfesional = cedulaProfesional;
    }

    /**
     * Obtiene la cedula profesional
     *
     * @return cedula profesional
     */
    public String getCedulaProfesional() {
        return cedulaProfesional;
    }

    /**
     * Establece la cedula profesional
     *
     * @param cedulaProfesional nueva cedula profesional
     */
    public void setCedulaProfesional(String cedulaProfesional) {
        this.cedulaProfesional = cedulaProfesional;
    }

    /**
     * Obtiene la especialidad medica
     *
     * @return especialidad medica
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Establece la especialidad medica
     *
     * @param especialidad nueva especialidad
     */
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * Atiende una cita, solo verifica que la cita sea suya y delega en la cita el cambio de estado
     *
     * @param cita cita que se atiende
     * @throws IllegalArgumentException si la cita esta asignada a otro veterinario
     * @throws IllegalStateException si la cita ya fue atendida o esta cancelada
     */
    public void atenderCita(Cita cita) {
        if (cita.getVeterinario() != this) {
            throw new IllegalArgumentException("La cita no esta asignada a este veterinario.");
        }
        cita.atender();
    }

}
