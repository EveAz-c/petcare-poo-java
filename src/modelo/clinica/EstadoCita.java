package modelo.clinica;

/**
 * Estados posibles de una cita
 */
public enum EstadoCita {
    /** La cita esta agendada y todavia no se atiende */
    PROGRAMADA,
    /** La cita ya fue atendida por el veterinario */
    ATENDIDA,
    /** La cita fue cancelada y no genera cobro */
    CANCELADA
}