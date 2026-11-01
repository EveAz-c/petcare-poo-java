package modelo.clinica;

import modelo.mascotas.Mascota;
import modelo.personas.Recepcionista;
import modelo.personas.Veterinario;
import modelo.servicios.Facturable;

import java.time.LocalDateTime;
import java.util.*;

public class Clinica {
    private String nombre;
    private List<Veterinario> veterinarios;
    private List<Mascota> mascotas;
    private List<Recepcionista> recepcionistas;
    private List<Consultorio> consultorios;
    private List<Cita> citas;

    public Clinica(String nombre){
        this.nombre = nombre;
        this.veterinarios = new ArrayList<>();
        this.recepcionistas = new ArrayList<>();
        this.consultorios = new ArrayList<>();
        this.citas = new ArrayList<>();
    }

    public void registrarCita(LocalDateTime fechaHora, Mascota mascota, Veterinario veterinario, Consultorio consultorio, Facturable servicio) {
        for (Cita c : citas) {
            if (!c.getEstado().equals("CANCELADA") && c.getFechaHora().equals(fechaHora)) {
                if (c.getVeterinario().equals(veterinario)) {
                    throw new IllegalStateException("El veterinario ya tiene una cita asignada a esta misma hora.");
                }
                if (c.getConsultorio().equals(consultorio)) {
                    throw new IllegalStateException("El consultorio ya se encuentra ocupado en este horario.");
                }
            }
        }
    Cita nuevaCita = new Cita(fechaHora, mascota, veterinario, consultorio, servicio);
        citas.add(nuevaCita);
    }

    public void darDeBajaMaascota(Mascota mascota){
        mascotas.remove(mascota);
    }

    public void agregarRecepcionista(Recepcionista recepcionista){
        if(recepcionista != null){
            this.recepcionistas.add(recepcionista);
        }
    }


    public void agregarConsultorio(Consultorio consultorio) {
    if (consultorio == null) {
        throw new IllegalArgumentException("El consultorio no puede ser nulo.");
    }
    
    for (Consultorio c : consultorios) {
        if (c.getNumero() == consultorio.getNumero()) {
            throw new IllegalArgumentException("Ya existe un consultorio registrado con el numero " + consultorio.getNumero());
        }
    }
    
    this.consultorios.add(consultorio);
}

    public double consultarCobrosPorVeterinario(Veterinario veterinario){
        double total = 0;
        for (Cita c : citas){
            if (c.getVeterinario().equals(veterinario) && "ATENDIDA".equals(c.getEstado())) {
                total += c.getServicio().calcularCosto();
            }
        }
        return total;

    }

    public double consultarCobrosPorMascota(Mascota mascota){
        double total = 0;
        for (Cita c : citas) {
            if (c.getMascota().equals(mascota) && "ATENDIDA".equals(c.getEstado())) {
                total += c.getServicio().calcularCosto();
            }
        }
        return total;
    }
    

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public List<Veterinario> getVeterinarios(){
        return veterinarios;
    }

    public List<Mascota> getMascotas(){
        return mascotas;
    }

    public List<Recepcionista> getRecepcionistas(){
        return recepcionistas;
    }

    public List<Consultorio> getConsultorios(){
        return consultorios;
    }

    public List<Cita> getCitas(){
        return citas;
    }


    
}
