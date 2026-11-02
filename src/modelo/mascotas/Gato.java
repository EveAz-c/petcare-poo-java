package modelo.mascotas;
import modelo.personas.Dueno;


/**
 * Mascota de tipo gato, agrega si es domestico o no
 */
public class Gato extends Mascota{
    private boolean domestico;

    /**
     * Crea un gato
     *
     * @param esDomestico indica si el gato es domestico
     * @param nombre nombre del gato
     * @param edad edad en anos
     * @param peso peso en kilogramos
     * @param dueno dueno del gato
     * @throws IllegalArgumentException si el nombre esta vacio o la edad o el peso no son validos
     */
    public Gato(boolean esDomestico, String nombre, int edad, double peso, Dueno dueno){
        super(nombre, edad, peso, dueno);
        this.domestico = esDomestico;
    }

    /**
     * Indica si el gato es domestico
     *
     * @return verdadero si el gato es domestico
     */
    public boolean isDomestico(){
        return domestico;
    }

    /**
     * Establece si el gato es domestico
     *
     * @param domestico verdadero si el gato es domestico
     */
    public void setDomestico(boolean domestico){
        this.domestico = domestico;
    }

    /**
     * Muestra en pantalla el maullido del gato
     */
    @Override
    public void hacerSonido(){
        System.out.println("Miau");
    }

}
