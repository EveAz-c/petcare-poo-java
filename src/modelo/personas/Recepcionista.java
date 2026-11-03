package modelo.personas;

import modelo.clinica.Cita;
import modelo.clinica.Clinica;
import modelo.clinica.Consultorio;
import modelo.mascotas.Mascota;
import modelo.servicios.Facturable;
import java.time.LocalDateTime;

public class Recepcionista extends Persona {
    private String turno;

<<<<<<< Updated upstream
    public Recepcionista(String nombre, String apellido, String telefono, String correo, String turno) {
=======
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
>>>>>>> Stashed changes
        super(nombre, apellido, telefono, correo);
        this.turno = turno;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Cita agendarCita(Clinica clinica, LocalDateTime fechaHora, Mascota mascota, Veterinario veterinario, Consultorio consultorio, Facturable servicio) {
        return clinica.registrarCita(fechaHora, mascota, veterinario, consultorio, servicio);
    }

}
