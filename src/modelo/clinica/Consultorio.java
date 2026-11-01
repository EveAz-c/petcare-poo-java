package modelo.clinica;

public class Consultorio {
    private int numero;
    private String descripcion;

    public Consultorio (int numero, String descripcion ){
        this.descripcion = descripcion;
    }
    
    public int getNumero(){
        return numero;
    }

    public void setNumero(int numero){
        if(numero <= 0){
            throw new IllegalArgumentException("El numero de consultorio debe ser mayor a 0 ");
        }
        this.numero = numero;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Consultorio #" + numero + " - " + descripcion;
    }
}
