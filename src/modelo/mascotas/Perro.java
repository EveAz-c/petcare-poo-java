package modelo.mascotas;
import modelo.personas.Dueno;

/**
 * Mascota de tipo perro, agrega la raza y el nivel de energia
 */
public class Perro extends Mascota {
    private String raza;
    private String nivelEnergia;

    /**
     * Crea un perro
     *
     * @param raza raza del perro
     * @param nivelEnergia nivel de energia del perro
     * @param nombre nombre del perro
     * @param edad edad en anos
     * @param peso peso en kilogramos
     * @param dueno dueno del perro
     * @throws IllegalArgumentException si el nombre esta vacio o la edad o el peso no son validos
     */
    public Perro(String raza, String nivelEnergia, String nombre, int edad, double peso,
                 Dueno dueno) {
        super(nombre, edad, peso, dueno);
        this.raza = raza;
        this.nivelEnergia = nivelEnergia;
    }

    /**
     * Obtiene la raza del perro
     *
     * @return raza del perro
     */
    public String getRaza(){
        return raza;
    }

    /**
     * Establece la raza del perro
     *
     * @param raza nueva raza
     */
    public void setRaza(String raza){
        this.raza = raza;
    }

    /**
     * Obtiene el nivel de energia del perro
     *
     * @return nivel de energia
     */
    public String getNivelEnergia(){
        return nivelEnergia;
    }

    /**
     * Establece el nivel de energia del perro
     *
     * @param nivelEnergia nuevo nivel de energia
     */
    public void setNivelEnergia( String nivelEnergia){
        this.nivelEnergia = nivelEnergia;
    }

    /**
     * Muestra en pantalla el ladrido del perro
     */
    @Override
    public void hacerSonido(){
        System.out.println("Ladrido");
    }

}

