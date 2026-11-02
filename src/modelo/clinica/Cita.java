package modelo.clinica;

import modelo.mascotas.Mascota;
import modelo.personas.Veterinario;
import modelo.servicios.Facturable;
import java.time.LocalDateTime;

public class Cita {

    private LocalDateTime fechaHora;
    private EstadoCita estado;
    private Mascota mascota;
    private Veterinario veterinario;
    private Consultorio consultorio;
    private Facturable servicio;

    public Cita (LocalDateTime fechaHora, Mascota mascota, Veterinario veterinario, Consultorio consultorio, Facturable servicio){
        this.fechaHora = fechaHora;
        this.mascota = mascota;
        this.veterinario = veterinario;
        this.consultorio = consultorio;
        this.servicio = servicio;
        this.estado = EstadoCita.PROGRAMADA;
;
    }

    public void atender(){
        if(this.estado == EstadoCita.CANCELADA){
            throw new IllegalStateException("ERROR! No se puede marcar una cita cancelada como atendida");
        }
        if(this.estado == EstadoCita.ATENDIDA){
            throw new IllegalStateException("ERROR! No se puede marcar una cita que ya fue atendida");
        }
        this.estado = EstadoCita.ATENDIDA;
        this.mascota.getHistorialClinico().agregarServicio(this.servicio);
    }

    public void cancelar(){
        if(this.estado == EstadoCita.CANCELADA){
            throw new IllegalStateException("ERROR! No se puede marcar una cita que ya esta cancelada");
        }
        if(this.estado == EstadoCita.ATENDIDA){
            throw new IllegalStateException("ERROR! No se puede marcar una cita que ya fue atendida");
        }
        this.estado = EstadoCita.CANCELADA;
    }

    public LocalDateTime getFechaHora(){
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora){
        this.fechaHora = fechaHora;
    }

    public EstadoCita getEstado(){
        return estado;
    }

    public Mascota getMascota(){
        return mascota;
    }

    public void setMascota(Mascota mascota){
        this.mascota = mascota;
    }

    public Veterinario getVeterinario(){
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario){
        this.veterinario = veterinario;
    }

    public Consultorio getConsultorio(){
        return consultorio;
    }

    public void setConsultorio(Consultorio consultorio){
        this.consultorio = consultorio;
    }

    public Facturable getServicio(){
        return servicio;
    }

    public void setServicio(Facturable servicio){
        this.servicio = servicio;
    }

    @Override 
    public String toString(){
        return "Cita{" +
                "fechaHora=" + fechaHora +
                ", estado=" + estado +
                ", mascota=" + mascota.getNombre() +
                ", veterinario=" + veterinario.getNombre() + " " + veterinario.getApellido() +
                ", consultorio=" + consultorio.getNumero() +
                ", servicio=" + servicio.getClass().getSimpleName() +
                '}';
    }

}
