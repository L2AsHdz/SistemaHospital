package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author asael
 */
public class Medico implements Serializable{
    
    private String codigo;
    private String nombre;
    private String noColegiado;
    private String cui;
    private String telefono;
    private String correo;
    private LocalTime horaInicio;
    private LocalTime horaFinal;
    private LocalDate fechaInicioLabores;
    private String password;

    public Medico() {
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

    public String getNoColegiado() {
        return noColegiado;
    }

    public void setNoColegiado(String noColegiado) {
        this.noColegiado = noColegiado;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(LocalTime horaFin) {
        this.horaFinal = horaFin;
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
