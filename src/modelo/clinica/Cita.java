package modelo.clinica;

import modelo.mascotas.Mascota;
import modelo.personas.Veterinario;
import modelo.servicios.Facturable;
import java.time.LocalDateTime;

/**
 * Cita que une una mascota, un veterinario, un consultorio y un servicio en una fecha y hora,
 * es la unica clase que cambia su propio estado
 */
public class Cita {

    private LocalDateTime fechaHora;
    private EstadoCita estado;
    private Mascota mascota;
    private Veterinario veterinario;
    private Consultorio consultorio;
    private Facturable servicio;

    /**
     * Crea una cita en estado programada
     *
     * @param fechaHora fecha y hora de la cita
     * @param mascota mascota que se atiende
     * @param veterinario veterinario asignado
     * @param consultorio consultorio asignado
     * @param servicio servicio medico que se aplica
     */
    public Cita (LocalDateTime fechaHora, Mascota mascota, Veterinario veterinario,
                 Consultorio consultorio, Facturable servicio){
        this.fechaHora = fechaHora;
        this.mascota = mascota;
        this.veterinario = veterinario;
        this.consultorio = consultorio;
        this.servicio = servicio;
        this.estado = EstadoCita.PROGRAMADA;
    }

    /**
     * Marca la cita como atendida y registra el servicio en el historial de la mascota
     *
     * @throws IllegalStateException si la cita esta cancelada o ya fue atendida
     */
    public void atender(){
        if(this.estado == EstadoCita.CANCELADA){
            throw new IllegalStateException(
                "ERROR! No se puede marcar una cita cancelada como atendida");
        }
        if(this.estado == EstadoCita.ATENDIDA){
            throw new IllegalStateException(
                "ERROR! No se puede marcar una cita que ya fue atendida");
        }
        this.estado = EstadoCita.ATENDIDA;
        this.mascota.getHistorialClinico().agregarServicio(this.servicio);
    }

    /**
     * Marca la cita como cancelada
     *
     * @throws IllegalStateException si la cita ya esta cancelada o ya fue atendida
     */
    public void cancelar(){
        if(this.estado == EstadoCita.CANCELADA){
            throw new IllegalStateException(
                "ERROR! No se puede marcar una cita que ya esta cancelada");
        }
        if(this.estado == EstadoCita.ATENDIDA){
            throw new IllegalStateException(
                "ERROR! No se puede marcar una cita que ya fue atendida");
        }
        this.estado = EstadoCita.CANCELADA;
    }

    /**
     * Obtiene la fecha y hora de la cita
     *
     * @return fecha y hora de la cita
     */
    public LocalDateTime getFechaHora(){
        return fechaHora;
    }

    /**
     * Establece la fecha y hora de la cita
     *
     * @param fechaHora nueva fecha y hora
     */
    public void setFechaHora(LocalDateTime fechaHora){
        this.fechaHora = fechaHora;
    }

    /**
     * Obtiene el estado de la cita, solo cambia con los metodos atender y cancelar
     *
     * @return estado de la cita
     */
    public EstadoCita getEstado(){
        return estado;
    }

    /**
     * Obtiene la mascota de la cita
     *
     * @return mascota de la cita
     */
    public Mascota getMascota(){
        return mascota;
    }

    /**
     * Establece la mascota de la cita
     *
     * @param mascota nueva mascota
     */
    public void setMascota(Mascota mascota){
        this.mascota = mascota;
    }

    /**
     * Obtiene el veterinario de la cita
     *
     * @return veterinario de la cita
     */
    public Veterinario getVeterinario(){
        return veterinario;
    }

    /**
     * Establece el veterinario de la cita
     *
     * @param veterinario nuevo veterinario
     */
    public void setVeterinario(Veterinario veterinario){
        this.veterinario = veterinario;
    }

    /**
     * Obtiene el consultorio de la cita
     *
     * @return consultorio de la cita
     */
    public Consultorio getConsultorio(){
        return consultorio;
    }

    /**
     * Establece el consultorio de la cita
     *
     * @param consultorio nuevo consultorio
     */
    public void setConsultorio(Consultorio consultorio){
        this.consultorio = consultorio;
    }

    /**
     * Obtiene el servicio medico de la cita
     *
     * @return servicio medico de la cita
     */
    public Facturable getServicio(){
        return servicio;
    }

    /**
     * Establece el servicio medico de la cita
     *
     * @param servicio nuevo servicio medico
     */
    public void setServicio(Facturable servicio){
        this.servicio = servicio;
    }

    /**
     * Devuelve los datos principales de la cita en texto
     *
     * @return texto con los datos de la cita
     */
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
