package modelo.mascotas;
import modelo.personas.Dueno;

/**
 * Mascota de tipo ave, agrega la longitud de las alas
 */
public class Ave extends Mascota {
    private double longitudAlas;

    /**
     * Crea un ave
     *
     * @param longitudAlas longitud de las alas
     * @param nombre nombre del ave
     * @param edad edad en anos
     * @param peso peso en kilogramos
     * @param dueno dueno del ave
     * @throws IllegalArgumentException si el nombre esta vacio o la edad o el peso no son validos
     */
    public Ave (double longitudAlas, String nombre, int edad, double peso, Dueno dueno){
        super(nombre, edad, peso, dueno);
        this.longitudAlas = longitudAlas;
    }

    /**
     * Obtiene la longitud de las alas
     *
     * @return longitud de las alas
     */
    public double getLongitudAlas(){
        return longitudAlas;
    }

    /**
     * Establece la longitud de las alas
     *
     * @param longitudAlas nueva longitud de las alas
     */
    public void setLongitudAlas(double longitudAlas){
        this.longitudAlas = longitudAlas;
    }

    /**
     * Muestra en pantalla el canto del ave
     */
    @Override
    public void hacerSonido(){
        System.out.println("Canto");
    }

}
