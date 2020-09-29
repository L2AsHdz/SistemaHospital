package model.examen;

/**
 *
 * @author asael
 */
public class TipoExamen {

    private String codigo;
    private String nombre;
    private int requiereOrden;
    private String descripcion;
    private float costo;
    private String tipoInforme;

    public TipoExamen() {
    }

    public TipoExamen(String codigo, String nombre, String requiereOrden, String descripcion, String costo, String tipoInforme) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.requiereOrden = (requiereOrden.equalsIgnoreCase("TRUE")) ? 1 : 0;
        this.descripcion = descripcion;
        this.costo = Float.parseFloat(costo);
        this.tipoInforme = tipoInforme;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRequiereOrden() {
        return requiereOrden;
    }

    public void setRequiereOrden(int requiereOrden) {
        this.requiereOrden = requiereOrden;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public String getTipoInforme() {
        return tipoInforme;
    }

    public void setTipoInforme(String tipoInforme) {
        this.tipoInforme = tipoInforme;
    }
}
