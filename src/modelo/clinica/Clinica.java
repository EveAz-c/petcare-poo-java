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


/**
 * Clase principal del sistema, guarda y valida duenos, mascotas, personal, consultorios,
 * citas y comprobantes, y calcula los cobros
 */
public class Clinica {
    private String nombre;
    private List<Dueno> duenos;
    private List<Veterinario> veterinarios;
    private List<Mascota> mascotas;
    private List<Recepcionista> recepcionistas;
    private List<Consultorio> consultorios;
    private List<Cita> citas;
    private List<Comprobante> comprobantes;

    /**
     * Crea una clinica vacia
     *
     * @param nombre nombre de la clinica
     * @throws IllegalArgumentException si el nombre esta vacio
     */
    public Clinica(String nombre){
        setNombre(nombre);
        this.duenos = new ArrayList<>();
        this.veterinarios = new ArrayList<>();
        this.mascotas = new ArrayList<>();
        this.recepcionistas = new ArrayList<>();
        this.consultorios = new ArrayList<>();
        this.citas = new ArrayList<>();
        this.comprobantes = new ArrayList<>();
    }

    /**
     * Registra un dueno en la clinica
     *
     * @param dueno dueno que se registra
     * @throws IllegalArgumentException si el dueno es nulo
     */
    public void registrarDueno(Dueno dueno){
        if(dueno == null){
            throw new IllegalArgumentException("El dueno no puede ser nulo");
        }
        duenos.add(dueno);
    }

    /**
     * Registra un veterinario en la clinica
     *
     * @param veterinario veterinario que se registra
     * @throws IllegalArgumentException si el veterinario es nulo
     */
    public void agregarVeterinario(Veterinario veterinario){
        if(veterinario == null){
            throw new IllegalArgumentException("El veterinario no puede ser nulo");
        }
        veterinarios.add(veterinario);
    }

    /**
     * Registra una mascota en la clinica y en la lista de su dueno
     *
     * @param mascota mascota que se registra
     * @throws IllegalArgumentException si la mascota es nula o no tiene dueno
     */
    public void registrarMascota(Mascota mascota){
        if(mascota == null){
            throw new IllegalArgumentException("La mascota no puede ser nula");
        }
        if(mascota.getDueno() == null){
            throw new IllegalArgumentException(
                "La mascota debe tener un dueno para poder registrarse");
        }
        mascotas.add(mascota);
        mascota.getDueno().agregarMascota(mascota);
    }

    /**
     * Da de baja una mascota, la quita de la clinica y de la lista de su dueno
     *
     * @param mascota mascota que se da de baja
     * @throws IllegalArgumentException si la mascota no esta registrada en la clinica
     */
    public void darDeBajaMascota(Mascota mascota){
        if(mascota == null || !mascotas.contains(mascota)){
            throw new IllegalArgumentException("La mascota no esta registrada en la clinica");
        }
        // Se quitan las referencias de las dos listas (clinica y dueno), cuando ya nada
        // apunta al objeto el recolector de basura de Java libera su memoria
        mascotas.remove(mascota);
        mascota.getDueno().eliminarMascota(mascota);
    }

    /**
     * Valida y registra una cita, aqui esta toda la logica de agendar
     *
     * @param fechaHora fecha y hora de la cita
     * @param mascota mascota que se atiende
     * @param veterinario veterinario asignado
     * @param consultorio consultorio asignado
     * @param servicio servicio medico que se aplica
     * @return cita creada
     * @throws IllegalStateException si el veterinario o el consultorio estan ocupados a esa hora
     */
    public Cita registrarCita(LocalDateTime fechaHora, Mascota mascota, Veterinario veterinario,
                              Consultorio consultorio, Facturable servicio) {
        for (Cita c : citas) {
            if (c.getEstado() != EstadoCita.CANCELADA && c.getFechaHora().equals(fechaHora)) {
                if (c.getVeterinario().equals(veterinario)) {
                    throw new IllegalStateException(
                        "El veterinario ya tiene una cita asignada a esta misma hora");
                }
                if (c.getConsultorio().equals(consultorio)) {
                    throw new IllegalStateException(
                        "El consultorio ya se encuentra ocupado en este horario");
                }
            }
        }
        Cita nuevaCita = new Cita(fechaHora, mascota, veterinario, consultorio, servicio);
        citas.add(nuevaCita);
        return nuevaCita;
    }

    /**
     * Crea y guarda el comprobante de una cita atendida
     *
     * @param cita cita atendida que se cobra
     * @return comprobante creado
     * @throws IllegalArgumentException si la cita no pertenece a la clinica
     * @throws IllegalStateException si la cita no esta atendida o ya tiene comprobante
     */
    public Comprobante generarComprobante(Cita cita){
        if(cita == null || !citas.contains(cita)){
            throw new IllegalArgumentException("La cita no pertenece a la clinica");
        }
        if(cita.getEstado() != EstadoCita.ATENDIDA){
            throw new IllegalStateException(
                "Solo se puede generar comprobante de una cita atendida");
        }
        for (Comprobante c : comprobantes){
            if(c.getCitaAsociada() == cita){
                throw new IllegalStateException("La cita ya tiene un comprobante");
            }
        }
        Comprobante comprobante = new Comprobante(
            comprobantes.size() + 1, cita, cita.getServicio().calcularCosto());
        comprobantes.add(comprobante);
        return comprobante;
    }

    /**
     * Registra una recepcionista en la clinica
     *
     * @param recepcionista recepcionista que se registra
     */
    public void agregarRecepcionista(Recepcionista recepcionista){
        if(recepcionista != null){
            this.recepcionistas.add(recepcionista);
        }
    }

    /**
     * Crea un consultorio y lo agrega a la clinica, es el unico camino para crear consultorios
     *
     * @param numero numero del consultorio
     * @param descripcion descripcion del consultorio
     * @return consultorio creado
     * @throws IllegalArgumentException si el numero no es valido o ya existe
     */
    public Consultorio crearConsultorio(int numero, String descripcion) {
        Consultorio consultorio = new Consultorio(numero, descripcion);
        for (Consultorio c : consultorios) {
            if (c.getNumero() == consultorio.getNumero()) {
                throw new IllegalArgumentException(
                    "Ya existe un consultorio registrado con el numero " + consultorio.getNumero());
            }
        }
        this.consultorios.add(consultorio);
        return consultorio;
    }

    /**
     * Suma el costo de las citas atendidas de un veterinario
     *
     * @param veterinario veterinario que se consulta
     * @return total cobrado por el veterinario
     */
    public double consultarCobrosPorVeterinario(Veterinario veterinario){
        double total = 0;
        for (Cita c : citas){
            if (c.getVeterinario().equals(veterinario) && c.getEstado() == EstadoCita.ATENDIDA) {
                total += c.getServicio().calcularCosto();
            }
        }
        return total;
    }

    /**
     * Suma el costo de las citas atendidas de una mascota
     *
     * @param mascota mascota que se consulta
     * @return total cobrado por la mascota
     */
    public double consultarCobrosPorMascota(Mascota mascota){
        double total = 0;
        for (Cita c : citas) {
            if (c.getMascota().equals(mascota) && c.getEstado() == EstadoCita.ATENDIDA) {
                total += c.getServicio().calcularCosto();
            }
        }
        return total;
    }

    /**
     * Obtiene el nombre de la clinica
     *
     * @return nombre de la clinica
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Establece el nombre de la clinica
     *
     * @param nombre nuevo nombre
     * @throws IllegalArgumentException si el nombre esta vacio
     */
    public void setNombre(String nombre){
        if(nombre == null || nombre.isBlank()){
            throw new IllegalArgumentException("El nombre de la clinica no puede estar vacio");
        }
        this.nombre = nombre.trim();
    }

    /**
     * Obtiene los duenos registrados
     *
     * @return lista de solo lectura de duenos
     */
    public List<Dueno> getDuenos(){
        return Collections.unmodifiableList(duenos);
    }

    /**
     * Obtiene los veterinarios registrados
     *
     * @return lista de solo lectura de veterinarios
     */
    public List<Veterinario> getVeterinarios(){
        return Collections.unmodifiableList(veterinarios);
    }

    /**
     * Obtiene las mascotas registradas
     *
     * @return lista de solo lectura de mascotas
     */
    public List<Mascota> getMascotas(){
        return Collections.unmodifiableList(mascotas);
    }

    /**
     * Obtiene las recepcionistas registradas
     *
     * @return lista de solo lectura de recepcionistas
     */
    public List<Recepcionista> getRecepcionistas(){
        return Collections.unmodifiableList(recepcionistas);
    }

    /**
     * Obtiene los consultorios de la clinica
     *
     * @return lista de solo lectura de consultorios
     */
    public List<Consultorio> getConsultorios(){
        return Collections.unmodifiableList(consultorios);
    }

    /**
     * Obtiene las citas registradas
     *
     * @return lista de solo lectura de citas
     */
    public List<Cita> getCitas(){
        return Collections.unmodifiableList(citas);
    }

    /**
     * Obtiene los comprobantes generados
     *
     * @return lista de solo lectura de comprobantes
     */
    public List<Comprobante> getComprobantes(){
        return Collections.unmodifiableList(comprobantes);
    }

    /**
     * Devuelve el nombre de la clinica y la cantidad de datos que guarda
     *
     * @return texto con el resumen de la clinica
     */
    @Override
    public String toString() {
        return "Clinica " + nombre + " [" + duenos.size() + " duenos, "
            + mascotas.size() + " mascotas, "
            + veterinarios.size() + " veterinarios, " + consultorios.size() + " consultorios, "
            + citas.size() + " citas]";
    }
}