package modelo.clinica;

import modelo.mascotas.Mascota;
import modelo.personas.Veterinario;
import modelo.servicios.Facturable;
import java.time.LocalDateTime;

public class Cita {

    private LocalDateTime fechaHora;
    private String estado;
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
        this.estado = "PROGRAMADA";
    }

    public void atender(){
        if("CANCELADA".equals(this.estado)){
            throw new IllegalStateException("ERROR! No se puede marcar una cita cancelada como atendida");
        }
        if("ATENDIDA".equals(this.estado)){
            throw new IllegalStateException("ERROR! No se puede marcar una cita que ya fue atendida");
        }
        this.estado = "ATENDIDA";
    }

    public void cancelar(){
        if("CANCELADA".equals(this.estado)){
            throw new IllegalStateException("ERROR! No se puede marcar una cita que ya esta cancelada");
        }
        if("ATENDIDA".equals(this.estado)){
            throw new IllegalStateException("ERROR! No se puede marcar una cita que ya fue atendida");
        }
        this.estado = "CANCELADA"; 
    }

    public LocalDateTime getFechaHora(){
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora){
        this.fechaHora = fechaHora;
    }

    public String getEstado(){
        return estado;
    }

    public void setEstado(String estado){
        this.estado = estado;
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

}
