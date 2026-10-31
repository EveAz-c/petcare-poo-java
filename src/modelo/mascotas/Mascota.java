package modelo.mascotas;
import modelo.personas.Dueno;

public abstract class Mascota {
    protected String nombre;
    protected int edad;
    protected double peso;
    protected Dueno dueno;
    protected HistorialClinico historialClinico;

    public Mascota(String nombre, int edad, double peso){
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;

    }



}
