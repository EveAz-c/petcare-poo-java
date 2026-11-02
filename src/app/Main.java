package app;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import modelo.clinica.Cita;
import modelo.clinica.Clinica;
import modelo.clinica.Consultorio;
import modelo.clinica.EstadoCita;
import modelo.mascotas.Ave;
import modelo.mascotas.Gato;
import modelo.mascotas.Mascota;
import modelo.mascotas.Perro;
import modelo.personas.Dueno;
import modelo.personas.Recepcionista;
import modelo.personas.Veterinario;
import modelo.servicios.Cirugia;
import modelo.servicios.Comprobante;
import modelo.servicios.ConsultaGeneral;
import modelo.servicios.Facturable;
import modelo.servicios.Vacunacion;

/**
 * Clase principal del programa, muestra el menu de consola y pide los datos al usuario
 * las reglas del negocio estan en las clases del modelo y aqui solo se llaman
 */
public class Main {
    private static final int OPCION_SALIR = 10;
    private static final Scanner ENTRADA = new Scanner(System.in);
    private static final DateTimeFormatter FORMATO_FECHA =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final Clinica CLINICA = new Clinica("PetCare");

    /**
     * Prepara la clinica y repite el menu hasta que el usuario elige salir
     *
     * @param args argumentos de la linea de comandos, no se usan
     */
    public static void main(String[] args) {
        prepararClinica();
        int opcion = -1;
        try {
            while (opcion != OPCION_SALIR) {
                mostrarMenu();
                opcion = leerEntero("Seleccione una opcion: ");
                ejecutarOpcion(opcion);
            }
        } catch (NoSuchElementException e) {
            System.out.println("\nSe termino la entrada de datos");
        }
        System.out.println("Hasta luego");
    }

    // Los consultorios no se crean con new porque su constructor es de paquete,
    // se piden a la clinica al iniciar el programa porque toda cita necesita uno
    private static void prepararClinica() {
        CLINICA.agregarRecepcionista(
            new Recepcionista("Laura", "Gomez", "5550001111", "recepcion@petcare.com", "Matutino"));
        CLINICA.crearConsultorio(1, "Consulta general");
        CLINICA.crearConsultorio(2, "Cirugia y vacunacion");
    }

    private static void mostrarMenu() {
        System.out.println("\n===== CLINICA VETERINARIA " + CLINICA.getNombre().toUpperCase()
            + " =====");
        System.out.println(" 1. Registrar dueno y mascota (perro, gato o ave)");
        System.out.println(" 2. Registrar veterinario y asignarlo a la clinica");
        System.out.println(" 3. Agendar una cita");
        System.out.println(" 4. Atender o cancelar una cita");
        System.out.println(" 5. Generar el comprobante de una cita atendida");
        System.out.println(" 6. Consultar el historial clinico de una mascota");
        System.out.println(" 7. Listar todas las mascotas y su sonido");
        System.out.println(" 8. Ver el total cobrado por veterinario");
        System.out.println(" 9. Dar de baja una mascota");
        System.out.println("10. Salir");
    }

    // Cualquier error de las clases del modelo se muestra y el menu sigue funcionando
    private static void ejecutarOpcion(int opcion) {
        try {
            switch (opcion) {
                case 1 -> registrarDuenoYMascota();
                case 2 -> registrarVeterinario();
                case 3 -> agendarCita();
                case 4 -> atenderOCancelarCita();
                case 5 -> generarComprobante();
                case 6 -> consultarHistorial();
                case 7 -> listarMascotas();
                case 8 -> verTotalPorVeterinario();
                case 9 -> darDeBajaMascota();
                case OPCION_SALIR -> { }
                default -> System.out.println("Opcion no valida");
            }
        } catch (IllegalArgumentException | IllegalStateException | DateTimeException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    // Opcion 1: primero se crean los dos objetos, asi un dato invalido de la mascota
    // no deja al dueno registrado a medias, despues se guardan en la clinica
    private static void registrarDuenoYMascota() {
        String nombre = leerTexto("Nombre del dueno: ");
        String apellido = leerTexto("Apellido: ");
        String telefono = leerTexto("Telefono: ");
        String correo = leerTexto("Correo: ");
        String direccion = leerTexto("Direccion: ");
        Dueno dueno = new Dueno(nombre, apellido, telefono, correo, direccion);
        Mascota mascota = leerMascota(dueno);

        CLINICA.registrarDueno(dueno);
        registrarMascotaEnClinica(mascota);
        System.out.println("Dueno registrado: " + dueno);
        System.out.println("Mascota registrada: " + mascota);

        while (leerSiNo("Desea registrar otra mascota para este dueno? (s/n): ")) {
            Mascota otra = leerMascota(dueno);
            registrarMascotaEnClinica(otra);
            System.out.println("Mascota registrada: " + otra);
        }
    }

    // La recepcionista registra la mascota, si no hay recepcionista la registra la clinica
    private static void registrarMascotaEnClinica(Mascota mascota) {
        Recepcionista recepcionista = primeraRecepcionista();
        if (recepcionista != null) {
            recepcionista.registrarMascota(CLINICA, mascota);
        } else {
            CLINICA.registrarMascota(mascota);
        }
    }

    private static Mascota leerMascota(Dueno dueno) {
        System.out.println("Tipo de mascota: 1) Perro  2) Gato  3) Ave");
        int tipo = leerEnteroEnRango("Tipo: ", 1, 3);
        String nombre = leerTexto("Nombre de la mascota: ");
        int edad = leerEntero("Edad en anos: ");
        double peso = leerDecimal("Peso en kg: ");

        if (tipo == 1) {
            String raza = leerTexto("Raza: ");
            String energia = leerTexto("Nivel de energia (ALTO, MEDIO o BAJO): ");
            return new Perro(raza, energia, nombre, edad, peso, dueno);
        }
        if (tipo == 2) {
            boolean domestico = leerSiNo("Es domestico? (s/n): ");
            return new Gato(domestico, nombre, edad, peso, dueno);
        }
        double alas = leerDecimal("Longitud de alas en cm: ");
        return new Ave(alas, nombre, edad, peso, dueno);
    }

    // Opcion 2: el veterinario queda asignado a la clinica con Clinica.agregarVeterinario()
    private static void registrarVeterinario() {
        String nombre = leerTexto("Nombre: ");
        String apellido = leerTexto("Apellido: ");
        String telefono = leerTexto("Telefono: ");
        String correo = leerTexto("Correo: ");
        String especialidad = leerTexto("Especialidad: ");
        String cedula = leerTexto("Cedula profesional: ");
        Veterinario veterinario =
            new Veterinario(nombre, apellido, telefono, correo, especialidad, cedula);
        CLINICA.agregarVeterinario(veterinario);
        System.out.println("Veterinario registrado y asignado a la clinica: " + veterinario);
    }

    // Opcion 3: la recepcionista agenda y delega en Clinica.registrarCita(),
    // si la clinica no tiene recepcionistas se llama directo a la clinica
    private static void agendarCita() {
        Mascota mascota = seleccionar("Mascota", CLINICA.getMascotas());
        if (mascota == null) {
            return;
        }
        Veterinario veterinario = seleccionar("Veterinario", CLINICA.getVeterinarios());
        if (veterinario == null) {
            return;
        }
        Consultorio consultorio = seleccionar("Consultorio", CLINICA.getConsultorios());
        if (consultorio == null) {
            return;
        }
        LocalDateTime fechaHora = leerFechaHora("Fecha y hora (yyyy-MM-dd HH:mm): ");
        Facturable servicio = leerServicio();

        Recepcionista recepcionista = primeraRecepcionista();
        Cita cita;
        if (recepcionista != null) {
            cita = recepcionista.agendarCita(
                CLINICA, fechaHora, mascota, veterinario, consultorio, servicio);
        } else {
            cita = CLINICA.registrarCita(fechaHora, mascota, veterinario, consultorio, servicio);
        }
        System.out.println("Cita agendada: " + cita);
    }

    // Opcion 4: se elige una cita programada y despues si se atiende o se cancela,
    // atender lo hace el veterinario de la cita, que delega en Cita.atender()
    private static void atenderOCancelarCita() {
        Cita cita = seleccionar("Cita programada", citasConEstado(EstadoCita.PROGRAMADA));
        if (cita == null) {
            return;
        }
        System.out.println("Accion: 1) Atender  2) Cancelar");
        int accion = leerEnteroEnRango("Accion: ", 1, 2);
        if (accion == 1) {
            cita.getVeterinario().atenderCita(cita);
            System.out.println("Cita atendida y servicio anotado en el historial clinico");
        } else {
            cita.cancelar();
            System.out.println("Cita cancelada");
        }
    }

    // Opcion 5: Clinica.generarComprobante() crea el comprobante y lo guarda
    private static void generarComprobante() {
        Cita cita = seleccionar("Cita atendida", citasConEstado(EstadoCita.ATENDIDA));
        if (cita == null) {
            return;
        }
        Comprobante comprobante = CLINICA.generarComprobante(cita);
        System.out.println(comprobante.mostrarDetalle());
    }

    // Opcion 6: polimorfismo con List<Facturable>, calcularCostoTotal() suma los costos
    // sin preguntar el tipo de cada servicio
    private static void consultarHistorial() {
        Mascota mascota = seleccionar("Mascota", CLINICA.getMascotas());
        if (mascota == null) {
            return;
        }
        mascota.getHistorialClinico().mostrarHistorial();
        System.out.println("Costo total de los servicios: $"
            + mascota.getHistorialClinico().calcularCostoTotal());
    }

    // Opcion 7: polimorfismo con List<Mascota>, cada mascota hace su propio sonido
    private static void listarMascotas() {
        List<Mascota> mascotas = CLINICA.getMascotas();
        if (mascotas.isEmpty()) {
            System.out.println("No hay mascotas registradas");
            return;
        }
        for (Mascota m : mascotas) {
            System.out.print(m.getClass().getSimpleName() + " " + m.getNombre() + ": ");
            m.hacerSonido();
        }
    }

    // Opcion 8: muestra el total cobrado de cada veterinario registrado
    private static void verTotalPorVeterinario() {
        List<Veterinario> veterinarios = CLINICA.getVeterinarios();
        if (veterinarios.isEmpty()) {
            System.out.println("No hay veterinarios registrados");
            return;
        }
        for (Veterinario v : veterinarios) {
            System.out.println(v.getNombre() + " " + v.getApellido() + ": $"
                + CLINICA.consultarCobrosPorVeterinario(v));
        }
    }

    // Opcion 9: Clinica.darDeBajaMascota() la quita de la clinica y de su dueno
    private static void darDeBajaMascota() {
        Mascota mascota = seleccionar("Mascota a dar de baja", CLINICA.getMascotas());
        if (mascota == null) {
            return;
        }
        CLINICA.darDeBajaMascota(mascota);
        System.out.println("Mascota dada de baja: " + mascota.getNombre());
    }

    private static Recepcionista primeraRecepcionista() {
        if (CLINICA.getRecepcionistas().isEmpty()) {
            return null;
        }
        return CLINICA.getRecepcionistas().get(0);
    }

    private static List<Cita> citasConEstado(EstadoCita estado) {
        List<Cita> resultado = new ArrayList<>();
        for (Cita c : CLINICA.getCitas()) {
            if (c.getEstado() == estado) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    private static Facturable leerServicio() {
        System.out.println("Servicio: 1) Consulta general  2) Vacunacion  3) Cirugia");
        int tipo = leerEnteroEnRango("Servicio: ", 1, 3);
        if (tipo == 1) {
            String diagnostico = leerTexto("Diagnostico o motivo: ");
            double costo = leerDecimal("Costo: ");
            return new ConsultaGeneral(costo, diagnostico, "");
        }
        if (tipo == 2) {
            String vacuna = leerTexto("Nombre de la vacuna: ");
            double costo = leerDecimal("Costo de la vacuna: ");
            return new Vacunacion(costo, vacuna);
        }
        String complejidad = leerTexto("Complejidad (BAJA, MEDIA o ALTA): ");
        double horas = leerDecimal("Horas de quirofano: ");
        return new Cirugia(complejidad, horas);
    }

    // Muestra una lista numerada y devuelve el elemento elegido, null si la lista esta vacia
    private static <T> T seleccionar(String titulo, List<T> elementos) {
        if (elementos.isEmpty()) {
            System.out.println("No hay elementos disponibles para: " + titulo);
            return null;
        }
        System.out.println(titulo + ":");
        for (int i = 0; i < elementos.size(); i++) {
            System.out.println("  " + (i + 1) + ") " + elementos.get(i));
        }
        int indice = leerEnteroEnRango("Elija un numero: ", 1, elementos.size());
        return elementos.get(indice - 1);
    }

    private static String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = ENTRADA.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("El dato no puede estar vacio");
        }
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(ENTRADA.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Escriba un numero entero");
            }
        }
    }

    private static int leerEnteroEnRango(String mensaje, int minimo, int maximo) {
        while (true) {
            int valor = leerEntero(mensaje);
            if (valor >= minimo && valor <= maximo) {
                return valor;
            }
            System.out.println("Elija un numero entre " + minimo + " y " + maximo);
        }
    }

    private static double leerDecimal(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(ENTRADA.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Escriba un numero, por ejemplo 18.5");
            }
        }
    }

    private static boolean leerSiNo(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String respuesta = ENTRADA.nextLine().trim().toLowerCase();
            if (respuesta.equals("s")) {
                return true;
            }
            if (respuesta.equals("n")) {
                return false;
            }
            System.out.println("Responda s o n");
        }
    }

    private static LocalDateTime leerFechaHora(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return LocalDateTime.parse(ENTRADA.nextLine().trim(), FORMATO_FECHA);
            } catch (DateTimeParseException e) {
                System.out.println("Formato invalido, ejemplo: 2026-11-05 10:30");
            }
        }
    }
}