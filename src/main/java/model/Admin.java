package model;

import java.io.Serializable;

/**
 *
 * @author asael
 */
public class Admin implements Serializable {

    private String codigo;
    private String nombre;
    private String nit;
    private String password;

    public Admin() {
    }

    public Admin(String codigo) {
        this.codigo = codigo;
    }

    public Admin(String nombre, String nit, String password) {
        this.nombre = nombre;
        this.nit = nit;
        this.password = password;
    }

    public Admin(String codigo, String nombre, String nit, String password) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.nit = nit;
        this.password = password;
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

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
