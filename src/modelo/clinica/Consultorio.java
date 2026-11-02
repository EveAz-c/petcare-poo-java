package modelo.clinica;

/**
 * Sala numerada de la clinica, pertenece a la clinica que la crea por composicion
 */
public class Consultorio {
    private int numero;
    private String descripcion;

    // Acceso de paquete (sin public ni private): solo las clases de modelo.clinica
    // pueden crear consultorios, por eso Main los pide con Clinica.crearConsultorio()
    /**
     * Crea un consultorio con numero y descripcion
     *
     * @param numero numero del consultorio, debe ser mayor a 0
     * @param descripcion descripcion del consultorio
     * @throws IllegalArgumentException si el numero no es mayor a 0
     */
    Consultorio (int numero, String descripcion ){
        this.descripcion = descripcion;
        setNumero(numero);
    }

    // Acceso de paquete: igual que el constructor anterior, solo la clinica lo usa
    /**
     * Crea un consultorio con descripcion general
     *
     * @param numero numero del consultorio, debe ser mayor a 0
     * @throws IllegalArgumentException si el numero no es mayor a 0
     */
    Consultorio (int numero){
        this(numero, "Consultorio general");
    }

    /**
     * Obtiene el numero del consultorio
     *
     * @return numero del consultorio
     */
    public int getNumero(){
        return numero;
    }

    /**
     * Establece el numero del consultorio
     *
     * @param numero nuevo numero
     * @throws IllegalArgumentException si el numero no es mayor a 0
     */
    public void setNumero(int numero){
        if(numero <= 0){
            throw new IllegalArgumentException("El numero de consultorio debe ser mayor a 0 ");
        }
        this.numero = numero;
    }

    /**
     * Obtiene la descripcion del consultorio
     *
     * @return descripcion del consultorio
     */
    public String getDescripcion(){
        return descripcion;
    }

    /**
     * Establece la descripcion del consultorio
     *
     * @param descripcion nueva descripcion
     */
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    /**
     * Devuelve el numero y la descripcion del consultorio en texto
     *
     * @return texto con los datos del consultorio
     */
    @Override
    public String toString() {
        return "Consultorio #" + numero + " - " + descripcion;
    }
}

