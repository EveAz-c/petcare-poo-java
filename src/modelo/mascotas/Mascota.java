package modelo.mascotas;
import modelo.personas.Dueno;

/**
 * Clase base de las mascotas, agrupa los datos y las validaciones comunes de edad y peso
 */
public abstract class Mascota {
    // protected: solo las subclases Perro, Gato y Ave y las clases del mismo paquete
    // pueden usar estos atributos, el resto del programa los consulta con los getters
    protected String nombre;
    protected int edad;
    protected double peso;
    protected Dueno dueno;
    protected HistorialClinico historialClinico;

    /**
     * Crea una mascota con su historial clinico vacio
     *
     * @param nombre nombre de la mascota
     * @param edad edad en anos, debe ser mayor a 0
     * @param peso peso en kilogramos, debe ser mayor a 0
     * @param dueno dueno de la mascota
     * @throws IllegalArgumentException si el nombre esta vacio o la edad o el peso no son validos
     */
    public Mascota(String nombre, int edad, double peso, Dueno dueno){
        setNombre(nombre);
        setEdad(edad);
        setPeso(peso);
        this.dueno = dueno;
        this.historialClinico = new HistorialClinico();

    }

    /**
     * Obtiene el nombre de la mascota
     *
     * @return nombre de la mascota
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Establece el nombre de la mascota
     *
     * @param nombre nuevo nombre
     * @throws IllegalArgumentException si el nombre esta vacio
     */
    public void setNombre(String nombre){
        if(nombre == null || nombre.isBlank()){
            throw new IllegalArgumentException("El nombre de la mascota no puede estar vacio");
        }
        this.nombre = nombre.trim();
    }

    /**
     * Obtiene la edad de la mascota
     *
     * @return edad en anos
     */
    public int getEdad(){
        return edad;
    }

    /**
     * Establece la edad de la mascota
     *
     * @param edad nueva edad en anos
     * @throws IllegalArgumentException si la edad no es mayor a 0
     */
    public void setEdad(int edad){
        if(edad <= 0){
            throw new IllegalArgumentException("La edad debe ser mayor a 0");
        }
        this.edad = edad;
    }

    /**
     * Obtiene el peso de la mascota
     *
     * @return peso en kilogramos
     */
    public double getPeso(){
        return peso;
    }

    /**
     * Establece el peso de la mascota
     *
     * @param peso nuevo peso en kilogramos
     * @throws IllegalArgumentException si el peso no es mayor a 0
     */
    public void setPeso(double peso){
        if(peso <= 0){
            throw new IllegalArgumentException("El peso debe ser mayor a 0");
        }
        this.peso = peso;
    }

    /**
     * Obtiene el dueno de la mascota
     *
     * @return dueno de la mascota
     */
    public Dueno getDueno(){
        return dueno;
    }

    /**
     * Obtiene el historial clinico de la mascota
     *
     * @return historial clinico de la mascota
     */
    public HistorialClinico getHistorialClinico() {
        return historialClinico;
    }

    /**
     * Muestra en pantalla el sonido de la mascota, cada tipo de mascota lo define a su manera
     */
    public abstract void hacerSonido();

    /**
     * Devuelve el nombre, la edad, el peso y el dueno de la mascota en texto
     *
     * @return texto con los datos de la mascota
     */
    @Override
    public String toString(){
        return "Mascota{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", peso=" + peso +
                ", dueno=" + dueno.getNombre() + " " + dueno.getApellido() +
                '}';
    }

}
