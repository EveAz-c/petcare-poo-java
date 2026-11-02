package modelo.personas;

import modelo.clinica.Cita;
import modelo.clinica.Clinica;
import modelo.clinica.Consultorio;
import modelo.mascotas.Mascota;
import modelo.servicios.Facturable;
import java.time.LocalDateTime;

/**
 * Personal de recepcion, sus operaciones solo delegan en la clinica que valida y guarda los datos
 */
public class Recepcionista extends Persona {
    private String turno;

    /**
     * Crea una recepcionista
     *
     * @param nombre nombre de la recepcionista
     * @param apellido apellido de la recepcionista
     * @param telefono telefono de contacto
     * @param correo correo electronico
     * @param turno turno de trabajo
     * @throws IllegalArgumentException si el nombre o el apellido estan vacios
     */
    public Recepcionista(String nombre, String apellido, String telefono, String correo,
                         String turno) {
        super(nombre, apellido, telefono, correo);
        this.turno = turno;
    }

    /**
     * Obtiene el turno de trabajo
     *
     * @return turno de trabajo
     */
    public String getTurno() {
        return turno;
    }

    /**
     * Establece el turno de trabajo
     *
     * @param turno nuevo turno
     */
    public void setTurno(String turno) {
        this.turno = turno;
    }

    /**
     * Agenda una cita, solo delega en el metodo registrarCita de la clinica
     *
     * @param clinica clinica donde se agenda la cita
     * @param fechaHora fecha y hora de la cita
     * @param mascota mascota que se atiende
     * @param veterinario veterinario asignado
     * @param consultorio consultorio asignado
     * @param servicio servicio medico que se aplica
     * @return cita creada
     * @throws IllegalStateException si el veterinario o el consultorio estan ocupados
     */
    public Cita agendarCita(Clinica clinica, LocalDateTime fechaHora, Mascota mascota,
                            Veterinario veterinario, Consultorio consultorio,
                            Facturable servicio) {
        return clinica.registrarCita(fechaHora, mascota, veterinario, consultorio, servicio);
    }

    /**
     * Registra una mascota, solo delega en el metodo registrarMascota de la clinica
     *
     * @param clinica clinica donde se registra la mascota
     * @param mascota mascota que se registra
     * @throws IllegalArgumentException si la mascota es nula o no tiene dueno
     */
    public void registrarMascota(Clinica clinica, Mascota mascota) {
        clinica.registrarMascota(mascota);
    }

}
