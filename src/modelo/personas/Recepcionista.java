package modelo.personas;

import modelo.clinica.Cita;
import modelo.clinica.Clinica;
import modelo.clinica.Consultorio;
import modelo.mascotas.Mascota;
import modelo.servicios.Facturable;
import java.time.LocalDateTime;

public class Recepcionista extends Persona {
    private String turno;

    public Recepcionista(String nombre, String apellido, String telefono, String correo, String turno) {
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
