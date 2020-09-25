package model;

import java.time.LocalDate;

/**
 *
 * @author asael
 */
public class Laboratorista {

    private String codigo;
    private String nombre;
    private String registroSalud;
    private String cui;
    private String telefono;
    private String codigoTipoExamen;
    private String correo;
    private LocalDate fechaInicioLabores;
    private String password;

    public Laboratorista() {
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

    public String getRegistroSalud() {
        return registroSalud;
    }

    public void setRegistroSalud(String registroSalud) {
        this.registroSalud = registroSalud;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCodigoTipoExamen() {
        return codigoTipoExamen;
    }

    public void setCodigoTipoExamen(String codigoExamen) {
        this.codigoTipoExamen = codigoExamen;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public LocalDate getFechaInicioLabores() {
        return fechaInicioLabores;
    }

    public void setFechaInicioLabores(LocalDate fechaInicioLabores) {
        this.fechaInicioLabores = fechaInicioLabores;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
