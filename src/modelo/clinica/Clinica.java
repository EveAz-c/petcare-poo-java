package modelo.clinica;

import modelo.mascotas.Mascota;
import modelo.personas.Dueno;
import modelo.personas.Recepcionista;
import modelo.personas.Veterinario;
import modelo.servicios.Comprobante;
import modelo.servicios.Facturable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Clinica {
    private String nombre;
    private List<Dueno> duenos;
    private List<Veterinario> veterinarios;
    private List<Mascota> mascotas;
    private List<Recepcionista> recepcionistas;
    private List<Consultorio> consultorios;
    private List<Cita> citas;
    private List<Comprobante> comprobantes;

    public Clinica(String nombre){
        this.nombre = nombre;
        this.duenos = new ArrayList<>();
        this.veterinarios = new ArrayList<>();
        this.mascotas = new ArrayList<>();
        this.recepcionistas = new ArrayList<>();
        this.consultorios = new ArrayList<>();
        this.citas = new ArrayList<>();
        this.comprobantes = new ArrayList<>();
    }

    public void registrarDueno(Dueno dueno){
        if(dueno == null){
            throw new IllegalArgumentException("El dueno no puede ser nulo");
        }
        duenos.add(dueno);
    }

    public void agregarVeterinario(Veterinario veterinario){
        if(veterinario == null){
            throw new IllegalArgumentException("El veterinario no puede ser nulo");
        }
        veterinarios.add(veterinario);
    }

    public void registrarMascota(Mascota mascota){
        if(mascota == null){
            throw new IllegalArgumentException("La mascota no puede ser nula");
        }
        if(mascota.getDueno() == null){
            throw new IllegalArgumentException("La mascota debe tener un dueno para poder registrarse");
        }
        mascotas.add(mascota);
        mascota.getDueno().agregarMascota(mascota);
    }

    public void darDeBajaMascota(Mascota mascota){
        if(mascota == null || !mascotas.contains(mascota)){
            throw new IllegalArgumentException("La mascota no esta registrada en la clinica");
        }
        mascotas.remove(mascota);
        mascota.getDueno().eliminarMascota(mascota);
    }

    public Cita registrarCita(LocalDateTime fechaHora, Mascota mascota, Veterinario veterinario, Consultorio consultorio, Facturable servicio) {
        for (Cita c : citas) {
            if (c.getEstado() != EstadoCita.CANCELADA && c.getFechaHora().equals(fechaHora)) {
                if (c.getVeterinario().equals(veterinario)) {
                    throw new IllegalStateException("El veterinario ya tiene una cita asignada a esta misma hora");
                }
                if (c.getConsultorio().equals(consultorio)) {
                    throw new IllegalStateException("El consultorio ya se encuentra ocupado en este horario");
                }
            }
        }
        Cita nuevaCita = new Cita(fechaHora, mascota, veterinario, consultorio, servicio);
        citas.add(nuevaCita);
        return nuevaCita;
    }

    public Comprobante generarComprobante(Cita cita){
        if(cita == null || !citas.contains(cita)){
            throw new IllegalArgumentException("La cita no pertenece a la clinica");
        }
        if(cita.getEstado() != EstadoCita.ATENDIDA){
            throw new IllegalStateException("Solo se puede generar comprobante de una cita atendida");
        }
        for (Comprobante c : comprobantes){
            if(c.getCitaAsociada() == cita){
                throw new IllegalStateException("La cita ya tiene un comprobante");
            }
        }
        Comprobante comprobante = new Comprobante(comprobantes.size() + 1, cita, cita.getServicio().calcularCosto());
        comprobantes.add(comprobante);
        return comprobante;
    }

    public void agregarRecepcionista(Recepcionista recepcionista){
        if(recepcionista != null){
            this.recepcionistas.add(recepcionista);
        }
    }

    public Consultorio crearConsultorio(int numero, String descripcion) {
        Consultorio consultorio = new Consultorio(numero, descripcion);
        for (Consultorio c : consultorios) {
            if (c.getNumero() == consultorio.getNumero()) {
                throw new IllegalArgumentException("Ya existe un consultorio registrado con el numero " + consultorio.getNumero());
            }
        }
        this.consultorios.add(consultorio);
        return consultorio;
    }

    public double consultarCobrosPorVeterinario(Veterinario veterinario){
        double total = 0;
        for (Cita c : citas){
            if (c.getVeterinario().equals(veterinario) && c.getEstado() == EstadoCita.ATENDIDA) {
                total += c.getServicio().calcularCosto();
            }
        }
        return total;
    }

    public double consultarCobrosPorMascota(Mascota mascota){
        double total = 0;
        for (Cita c : citas) {
            if (c.getMascota().equals(mascota) && c.getEstado() == EstadoCita.ATENDIDA) {
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

    public List<Dueno> getDuenos(){
        return Collections.unmodifiableList(duenos);
    }

    public List<Veterinario> getVeterinarios(){
        return Collections.unmodifiableList(veterinarios);
    }

    public List<Mascota> getMascotas(){
        return Collections.unmodifiableList(mascotas);
    }

    public List<Recepcionista> getRecepcionistas(){
        return Collections.unmodifiableList(recepcionistas);
    }

    public List<Consultorio> getConsultorios(){
        return Collections.unmodifiableList(consultorios);
    }

    public List<Cita> getCitas(){
        return Collections.unmodifiableList(citas);
    }

    public List<Comprobante> getComprobantes(){
        return Collections.unmodifiableList(comprobantes);
    }

    @Override
    public String toString() {
        return "Clinica " + nombre + " [" + duenos.size() + " duenos, " + mascotas.size() + " mascotas, "
            + veterinarios.size() + " veterinarios, " + consultorios.size() + " consultorios, "
            + citas.size() + " citas]";
    }
}