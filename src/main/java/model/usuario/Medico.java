package model.usuario;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author asael
 */
public class Medico extends Usuario implements Serializable {

    private String noColegiado;
    private String telefono;
    private String correo;
    private LocalTime horaInicio;
    private LocalTime horaFinal;
    private LocalDate fechaInicioLabores;
    private List<String> especialidades;

    public Medico() {
    }

    public Medico(String noColegiado, String telefono, String correo, String horaInicio,
            String horaFinal, String fechaInicioLabores, String codigo, String nombre,
            String cui, String password) {
        super(codigo, nombre, cui, password);
        this.noColegiado = noColegiado;
        this.telefono = telefono;
        this.correo = correo;
        
        try {
            this.horaInicio = LocalTime.parse(horaInicio, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (Exception e) {
            try {
                this.horaInicio = LocalTime.parse(horaInicio, DateTimeFormatter.ofPattern("H:mm"));
            } catch (Exception ex) {
                ex.printStackTrace(System.out);
            }
        }
        
        try {
            this.horaFinal = LocalTime.parse(horaFinal, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (Exception e) {
            try {
                this.horaFinal = LocalTime.parse(horaFinal, DateTimeFormatter.ofPattern("H:mm"));
            } catch (Exception ex) {
                ex.printStackTrace(System.out);
            }
        }

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

    public String getNoColegiado() {
        return noColegiado;
    }

    public void setNoColegiado(String noColegiado) {
        this.noColegiado = noColegiado;
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

    public void setHoraFinal(LocalTime horaFinal) {
        this.horaFinal = horaFinal;
    }

    public LocalDate getFechaInicioLabores() {
        return fechaInicioLabores;
    }

    public void setFechaInicioLabores(LocalDate fechaInicioLabores) {
        this.fechaInicioLabores = fechaInicioLabores;
    }

    public List<String> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<String> especialidades) {
        this.especialidades = especialidades;
    }
}
