package model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author asael
 */
public class Paciente implements Serializable {

    private String codigo;
    private String nombre;
    private String sexo;
    private LocalDate birth;
    private String cui;
    private String telefono;
    private float peso;
    private String tipoSangre;
    private String correo;
    private String password;

    public Paciente() {
    }

    public Paciente(String codigo) {
        this.codigo = codigo;
    }

    public Paciente(String nombre, String sexo, LocalDate birth, String cui, String telefono, float peso, String tipoSangre, String correo, String password) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.birth = birth;
        this.cui = cui;
        this.telefono = telefono;
        this.peso = peso;
        this.tipoSangre = tipoSangre;
        this.correo = correo;
        this.password = password;
    }

    public Paciente(String codigo, String nombre, String sexo, LocalDate birth, String cui, String telefono, float peso, String tipoSangre, String correo, String password) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.sexo = sexo;
        this.birth = birth;
        this.cui = cui;
        this.telefono = telefono;
        this.peso = peso;
        this.tipoSangre = tipoSangre;
        this.correo = correo;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
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

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
