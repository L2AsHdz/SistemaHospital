package model.especialidad;

import java.io.Serializable;

/**
 *
 * @author asael
 */
public class Especialidad implements Serializable {

    private int id;
    private String nombre;
    private float costo;

    public Especialidad() {
    }

    public Especialidad(String nombre, String costo) {
        this.nombre = nombre;
        this.costo = Float.parseFloat(costo);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }
}
