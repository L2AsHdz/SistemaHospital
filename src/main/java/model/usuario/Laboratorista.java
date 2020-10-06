package model.usuario;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author asael
 */
public class Laboratorista extends Usuario implements Serializable{

    private String registroSalud;
    private String telefono;
    private String codigoTipoExamen;
    private String correo;
    private LocalDate fechaInicioLabores;
    private List<String> turnos;

    public Laboratorista() {
    }

    public Laboratorista(String registroSalud, String telefono, String codigoTipoExamen,
            String correo, String fechaInicioLabores, String codigo, String nombre,
            String cui, String password) {
        super(codigo, nombre, cui, password);
        this.registroSalud = registroSalud;
        this.telefono = telefono;
        this.codigoTipoExamen = codigoTipoExamen;
        this.correo = correo;

        try {
            this.fechaInicioLabores = LocalDate.parse(fechaInicioLabores);
        } catch (Exception e) {
            try {
                this.fechaInicioLabores = LocalDate.parse(fechaInicioLabores, DateTimeFormatter.ofPattern("yyyy-M-d"));
            } catch (Exception ex) {
                try {
                    this.fechaInicioLabores = LocalDate.parse(fechaInicioLabores, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                } catch (Exception exx) {
                    exx.printStackTrace(System.out);
                }
            }
        }
    }

    public String getRegistroSalud() {
        return registroSalud;
    }

    public void setRegistroSalud(String registroSalud) {
        this.registroSalud = registroSalud;
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

    public void setCodigoTipoExamen(String codigoTipoExamen) {
        this.codigoTipoExamen = codigoTipoExamen;
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

    public List<String> getTurnos() {
        return turnos;
    }

    public void setTurnos(List<String> turnos) {
        this.turnos = turnos;
    }
}
