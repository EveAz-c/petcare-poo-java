# petcare-poo-java

Sistema de consola en Java para una clínica veterinaria (examen de Diseño y POO).

PetCare administra una clínica veterinaria desde un menú de consola. Permite registrar dueños, veterinarios y mascotas (perros, gatos y aves), agendar y atender citas, generar comprobantes de pago, consultar cobros y llevar el historial clínico de cada mascota. Los datos viven en memoria mientras el programa está abierto.

## Contenido

- [Tecnologías](#tecnologías)
- [Modelo de la solución](#modelo-de-la-solución)
- [Estructura del proyecto](#estructura-del-proyecto)
- [Conceptos aplicados](#conceptos-aplicados)
- [Instalación y ejecución](#instalación-y-ejecución)
- [Ejemplo de uso](#ejemplo-de-uso)
- [Reglas de negocio](#reglas-de-negocio)
- [Reflexión](#reflexión)
- [Autor](#autor)

## Tecnologías

| Herramienta | Uso |
| --- | --- |
| Java 17 o superior (probado con Java 21) | Lenguaje del proyecto |
| **(Draw.io)** | Diagrama de clase |
| **(VS Code)** | Edición y ejecución del código |
| Git,GitHub y Git Desktop| Control de versiones |

## Modelo de la solución

![Diagrama de clases de PetCare](docs/diagramas/diagrama-clases.png)

El análisis completo (tabla de clases, objetos de ejemplo, clases descartadas y justificación de relaciones) está en [`docs/analisis.md`](docs/analisis.md). Estas son las decisiones principales:

**Herencia.** Hay dos jerarquías independientes:

- `Persona` (abstracta) es el padre de `Dueno`, `Veterinario` y `Recepcionista`. Los tres comparten nombre, apellido, teléfono y correo, y cada uno agrega lo suyo (dirección, cédula y especialidad, turno).
- `Mascota` (abstracta) es el padre de `Perro`, `Gato` y `Ave`. Comparten nombre, edad, peso, dueño e historial, y cada hija agrega su dato propio y su sonido.

`Persona` y `Mascota` son abstractas para que no se puedan crear objetos incompletos: una persona sin rol o una mascota sin tipo. Solo se instancian sus hijas, y la clase padre guarda lo que comparten. `Mascota` además declara `hacerSonido()` como método abstracto, porque no existe un sonido genérico: cada clase hija define el suyo, y eso permite llamarlo desde una `List<Mascota>` sin importar el animal.

**Interfaz.** `Facturable` define un solo contrato, `calcularCosto()`. `ConsultaGeneral`, `Vacunacion` y `Cirugia` no son "parientes" (no comparten atributos), solo comparten la capacidad de cobrarse, por eso se eligió una interfaz y no una clase padre. Gracias a ella, `Cita` e `HistorialClinico` suman costos sin saber de qué servicio se trata.

**Asociación.** `Cita` relaciona objetos independientes: una `Mascota`, un `Veterinario`, un `Consultorio` y un servicio `Facturable`. La cita solo guarda referencias; ninguno de esos objetos depende de ella. `Dueno` y `Mascota` también se asocian entre sí: dar de baja la mascota no elimina al dueño.

**Agregación.** `Clinica` agrupa veterinarios (también dueños, mascotas y recepcionistas) que se crean fuera de ella y se le entregan ya hechos. Si la clínica desaparece, el veterinario sigue existiendo y podría trabajar en otra.

**Composición.** 

- `Clinica` crea y posee sus `Consultorio` (con `crearConsultorio()`); sus constructores son de paquete para que nadie más pueda crearlos.
- `Mascota` crea y posee su `HistorialClinico` dentro de su constructor. Al dar de baja la mascota, el historial se va con ella.

## Estructura del proyecto

```text
petcare-poo-java/
├── README.md
├── .gitignore
├── docs/
│   ├── analisis.md
│   ├── diagramas/
│   └── capturas/
└── src/
    ├── app/
    │   └── Main.java
    └── modelo/
        ├── personas/
        │   ├── Persona.java
        │   ├── Dueno.java
        │   ├── Veterinario.java
        │   └── Recepcionista.java
        ├── mascotas/
        │   ├── Mascota.java
        │   ├── Perro.java
        │   ├── Gato.java
        │   ├── Ave.java
        │   └── HistorialClinico.java
        ├── servicios/
        │   ├── Facturable.java
        │   ├── ConsultaGeneral.java
        │   ├── Vacunacion.java
        │   ├── Cirugia.java
        │   └── Comprobante.java
        └── clinica/
            ├── Clinica.java
            ├── Cita.java
            ├── EstadoCita.java
            └── Consultorio.java
```

| Paquete | Propósito |
| --- | --- |
| `app` | Contiene `Main`: muestra el menú, pide los datos y llama al modelo. No tiene reglas de negocio. |
| `modelo.personas` | Las personas de la clínica: `Persona` y sus hijas `Dueno`, `Veterinario` y `Recepcionista`. |
| `modelo.mascotas` | Las mascotas (`Mascota`, `Perro`, `Gato`, `Ave`) y su `HistorialClinico`. |
| `modelo.servicios` | Los servicios que se cobran (interfaz `Facturable` y sus tres implementaciones) y el `Comprobante`. |
| `modelo.clinica` | La `Clinica` (que concentra las reglas), las `Cita`, su `EstadoCita` y los `Consultorio`. |

## Conceptos aplicados

| Concepto | Dónde se aplica (archivo:línea) |
| --- | --- |
| Herencia (`Persona`) | `Persona.java:6`; hijas en `Dueno.java:10`, `Veterinario.java:7`, `Recepcionista.java:13` |
| Herencia (`Mascota`) | `Mascota.java:7`; hijas en `Perro.java:7`, `Gato.java:8`, `Ave.java:7` |
| Clase abstracta | `Persona.java:6` y `Mascota.java:7` |
| Método abstracto | `Mascota.java:121` (`hacerSonido()`), implementado en `Perro.java:69`, `Gato.java:48` y `Ave.java:47` |
| Interfaz | `Facturable.java:6`; implementada en `ConsultaGeneral.java:7`, `Vacunacion.java:7` y `Cirugia.java:7` |
| Polimorfismo con `List<Mascota>` | `Main.java:241-250` (opción 7, llama a `hacerSonido()`) |
| Polimorfismo con `List<Facturable>` | `HistorialClinico.java:77-83` y `Clinica.java:201-209`, suman costos sin usar `instanceof` |
| Sobreescritura (`@Override`) | `toString()` en `Persona.java:113`, `Mascota.java:128`, `Cita.java:179`, `Comprobante.java:132`; `calcularCosto()` en `Cirugia.java:29` |
| Sobrecarga | Constructores de `HistorialClinico.java:18` y `:27`; método `mostrarHistorial()` en `HistorialClinico.java:50` y `:59`; constructores de `Consultorio.java:19` y `:31` |
| Constructor por defecto y constructor con datos | `HistorialClinico.java:18` (vacío) y `:27` (recibe la lista) |
| Constructores con `super(...)` | `Perro.java:24`, `Gato.java:22`, `Ave.java:21`, `Dueno.java:26`, `Veterinario.java:24`, `Recepcionista.java:28` |
| Asociación | `Cita.java:16-19` (mascota, veterinario, consultorio, servicio); `Dueno.java:12` |
| Agregación | `Clinica.java:66-71` (`agregarVeterinario` recibe el objeto ya creado) |
| Composición | `Mascota.java:30` (crea su `HistorialClinico`) y `Clinica.java:183-193` (crea sus consultorios) |
| Variable `static` | `Comprobante.java:11` (contador), `:30` (se incrementa) y `:38` (`getTotalGenerados()`) |
| Enumeración | `EstadoCita.java:6` |
| Encapsulamiento y setters con validación | `Mascota.java:70-96`, `Persona.java:45` y `:67`, `ConsultaGeneral.java:54`, `Vacunacion.java:50`, `Cirugia.java:81` |
| Niveles de acceso | `protected` en `Mascota.java:10-14` y `Persona.java:7-10`; `private` en `Cita.java:14-19`; de paquete en `Consultorio.java:19` y `:31`; `public` en el resto. Comentarios que explican la elección: `Mascota.java:8-9` y `Consultorio.java:10-11` |
| Eliminación de objetos y recolector de basura | `Clinica.java:97-105` (`darDeBajaMascota`) |
| Excepciones | `IllegalArgumentException` en los setters y en `Clinica.java:79-89`; `IllegalStateException` en `Clinica.java:120-131` y `Cita.java:45-72`; captura en `Main.java:99` |
| Paquetes e importaciones explícitas | `app` y `modelo.*`, con `import` por clase en `Main.java:3-24` |
| Menú que se repite hasta salir | `Main.java:44-56` |

## Instalación y ejecución

**Requisito:** tener instalado el JDK 17 o superior. Para comprobarlo:

```bash
java -version
javac -version
```

**1. Clonar el repositorio**

```bash
git clone https://github.com/<tu-usuario>/petcare-poo-java.git
cd petcare-poo-java
```

**2. Compilar** (crea la carpeta `out/` con los archivos `.class`)

Linux o macOS:

```bash
mkdir -p out
javac -d out $(find src -name "*.java")
```

Windows (PowerShell):

```powershell
mkdir out
javac -d out (Get-ChildItem -Recurse -Filter *.java src).FullName
```

**3. Ejecutar**

```bash
java -cp out app.Main
```

Debe aparecer el menú de la clínica. Para terminar, elige la opción **10**.

> Si usas un IDE, también puedes abrir la carpeta `src/` como carpeta de código fuente y ejecutar la clase `app.Main`.

## Ejemplo de uso

Esta es la ejecución del escenario de demostración. La clínica arranca con una recepcionista y dos consultorios ya creados (consultorio 1, "Consulta general", y consultorio 2, "Cirugia y vacunacion"). Las capturas están en [`docs/capturas/`](docs/capturas/).

**Menú principal**

![Menú principal](docs/capturas/01_menu_principal.png)

**Validación de entradas incorrectas.** Texto donde se espera un número, una opción fuera del menú, un dato vacío y una edad inválida; el programa muestra el mensaje al final y sigue funcionando.

![Validación de entradas](docs/capturas/02_validacion_de_entradas.png)

### 1. Registrar dos dueños y tres mascotas (perro, gato y ave)

Carlos Ramirez registra a Max (perro) y a Misu (gato); Ana Torres registra a Pico (ave).

![Dueño con perro y gato](docs/capturas/03_registro_dueno_max_y_misu.png)

![Dueño con ave](docs/capturas/04_registro_dueno_pico.png)

### 2. Registrar dos veterinarios

![Registro de veterinarios](docs/capturas/05_registro_veterinarios.png)

### 3. Agendar tres citas y atender dos

Se agenda una consulta general, una vacunación y una cirugía.

![Cita de consulta general](docs/capturas/06_agendar_cita_consulta.png)

![Cita de vacunación](docs/capturas/07_agendar_cita_vacunacion.png)

![Cita de cirugía](docs/capturas/08_agendar_cita_cirugia.png)

Se atienden la consulta de Max y la cirugía de Pico, en cada servicio calcula su costo de forma distinta. La consulta cobra el costo capturado $350.0 y la cirugía depende de las horas de quirófano y de la complejidad (1.5 horas × $1,200 × 2.0 por complejidad ALTA = $3,600.0).

![Atender citas](docs/capturas/12_atender_citas.png)

Al atender una cita se genera su comprobante con el total:

![Comprobantes](docs/capturas/13_comprobantes.png)

### 4. Intentar agendar una cita que viole una regla de negocio

Veterinario con dos citas a la misma hora:

![Error: veterinario ocupado](docs/capturas/09_error_veterinario_ocupado.png)

Consultorio usado en dos citas a la vez:

![Error: consultorio ocupado](docs/capturas/10_error_consultorio_ocupado.png)

Costo negativo:

![Error: costo negativo](docs/capturas/11_error_costo_negativo.png)

### 5. Listar las mascotas llamando a `hacerSonido()` (polimorfismo)

![Listar mascotas y su sonido](docs/capturas/14_listar_mascotas_y_sonido.png)

### 6. Mostrar el total cobrado y consultar un historial clínico

![Total cobrado por veterinario](docs/capturas/15_total_por_veterinario.png)

![Historial clínico](docs/capturas/16_historial_clinico.png)

### Extras

Se atiende la tercera cita (vacunación $250.0), con lo que se comprueban los tres tipos de servicio, y el total de la Dra. Mendez sube a $600.0.

![Atender la vacunación](docs/capturas/17_extra_atender_vacunacion.png)

Dar de baja una mascota (Misu) la quita de la clínica y de la lista de su dueño; después ya no aparece al listar.

![Dar de baja una mascota](docs/capturas/18_dar_de_baja_mascota.png)

Salir del programa:

![Salir](docs/capturas/19_salir.png)

## Reglas de negocio

| # | Regla | Dónde se valida |
| --- | --- | --- |
| 1 | El peso y la edad de una mascota no pueden ser negativos ni cero | `Mascota.setEdad()` (`Mascota.java:72-74`) y `Mascota.setPeso()` (`Mascota.java:94-96`). El constructor usa estos setters (`Mascota.java:26-28`), por lo que también se valida al crear la mascota. Lanza `IllegalArgumentException`. |
| 2 | Un veterinario no puede tener dos citas programadas a la misma hora | `Clinica.registrarCita()` (`Clinica.java:120-125`). Lanza `IllegalStateException`. Las citas canceladas no cuentan. |
| 3 | Una cita cancelada no puede marcarse como atendida | `Cita.atender()` (`Cita.java:45-50`). Lanza `IllegalStateException`. Desde el menú solo se ofrecen citas programadas, por lo que esta regla es una segunda barrera dentro del modelo. |
| 4 | Un consultorio no puede usarse en dos citas al mismo tiempo | `Clinica.registrarCita()` (`Clinica.java:126-129`). Lanza `IllegalStateException`. |
| 5 | Los costos nunca pueden ser negativos | `ConsultaGeneral.setCosto()` (`ConsultaGeneral.java:52-56`), `Vacunacion.setCostoVacuna()` (`Vacunacion.java:48-52`) y `Cirugia.setHorasQuirofano()` (`Cirugia.java:79-83`; el costo de la cirugía se calcula con las horas, por lo que no puede ser negativo). Lanzan `IllegalArgumentException`. |
| 6 | Todo cobro debe poder consultarse por mascota y por veterinario | `Clinica.consultarCobrosPorVeterinario()` (`Clinica.java:201`, opción 8 del menú) y `Clinica.consultarCobrosPorMascota()` (`Clinica.java:217`). El costo por mascota también se ve en el historial clínico (opción 6). |

Además, `Main` captura las excepciones del modelo en `ejecutarOpcion()` (`Main.java:99-101`) y muestra el mensaje con el prefijo `ERROR:` sin cerrar el programa. Las entradas incorrectas (texto en lugar de número, datos vacíos, opciones fuera de rango, formato de fecha inválido) se validan en los métodos de lectura de `Main.java:325-390`.

## Reflexión

> Es un sistema bastante complejo, lo vi bastante sencillo en un inicio pero al final no lo fue. Requirio investigar bastante ya que no sabía como desarrollar varias cosas. Pero fue bastante entretenido ver como se iban acoplando unas con otras cosas mientras las iba desarrollando, tambien fue muy estresante ver que por más que intentará seguia saliendo algunos errores. Todavia quedo bastante por corregir, pero creo que cumple lo esperado.

**Uso de la IA.** Usé la IA (Claude) como apoyo para poder organizar el como iba a desarrollar el proyecto/examen, tambien para que me fuera diciendo que me iba faltando segun como iba avanzando en la creación ya que me iba poniendo pruebas y así tambien me ayudaba diciendome que estaba fallando. Tambien lo use en la busqueda de información como el como tambien poner la información de los documentos y que se viera más presentable y que se entendiera mejor la información añadiendole algunas cosas caracteristicas.

**Lo que aprendí.** *Creo que una de las cosas que más aprendí aquí fue el uso de las listas, ya que las creaba en una clase pero en pruebas aparecía que no guardaban nada, que hay veces que pongo validación pero que no solo se debe poner ahí.*

**Lo más difícil.** *(Entre lo mas dificil para mi, fue la parte de organización en lo que tuve que ocupar bastante ayuda, ya que se me olvidaba que me iba faltando, tambien varias cosas que iba agregando y que en las pruebas que le pedia al asistente que hiciera me decia que estaba mal. por lo que ver esos "pequeños detalles", que realmente terminaron siendo más grandes y que varias de esas cosas no las sabia hacer y tenia que estar buscando como hacerlas y me quitaba bastante tiempo el estar buscando como tenia que quedar. Creo que fue lo más dificil.)*

**Qué mejoraría.** *(Mejoraria el orden en el que fui desarrollando las cosas, ya que a pesar de que al inicio si entendia e iba bastante bien siguiendo mis reglas, a veces me perdia en lo que iba. Entonces creo que buscaria mejor el modo ver de que manera iniciar a poner mi codigo para buscar lo siguiente que haré.)*

## Autor

**Eveln Perez Uscanga** — [https://github.com/EveAz-c](https://github.com/EveAz-c)
