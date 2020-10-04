package model.consulta;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author asael
 */
public class Consulta {
    
    private int codigo;
    private String codigoMedico;
    private String nombreMedico;
    private String codigoPaciente;
    private int idEspecialidad;
    private String nombreEspecialidad;
    private LocalDate fecha;
    private LocalTime hora;
    private int estado;
    private float total;

    public Consulta() {
    }

    public Consulta(String codigo, String codigoMedico, String codigoPaciente, int idEspecialidad, 
            String fecha, String hora, int estado, float total) {
        this.codigo = Integer.parseInt(codigo);
        setDatos(codigoMedico, codigoPaciente, idEspecialidad, fecha, hora, estado, total);
    }
    
    public Consulta(String codigoMedico, String codigoPaciente, int idEspecialidad, 
            String fecha, String hora, int estado, float total) {
        setDatos(codigoMedico, codigoPaciente, idEspecialidad, fecha, hora, estado, total);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCodigoMedico() {
        return codigoMedico;
    }

    public void setCodigoMedico(String codigoMedico) {
        this.codigoMedico = codigoMedico;
    }

    public String getCodigoPaciente() {
        return codigoPaciente;
    }

    public void setCodigoPaciente(String codigoPaciente) {
        this.codigoPaciente = codigoPaciente;
    }

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    private void setDatos(String codigoMedico, String codigoPaciente, int idEspecialidad, 
            String fecha, String hora, int estado, float total) {
        this.codigoMedico = codigoMedico;
        this.codigoPaciente = codigoPaciente;
        this.idEspecialidad = idEspecialidad;
        this.estado = estado;
        this.total = total;
        
        try {
            this.hora = LocalTime.parse(hora, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (Exception e) {
            try {
                this.hora = LocalTime.parse(hora, DateTimeFormatter.ofPattern("H:mm"));
            } catch (Exception ex) {
                ex.printStackTrace(System.out);
            }
        }

        try {
            this.fecha = LocalDate.parse(fecha);
        } catch (Exception e) {
            try {
                this.fecha = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy-M-d"));
            } catch (Exception ex) {
                try {
                    this.fecha = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                } catch (Exception exx) {
                    exx.printStackTrace(System.out);
                }
            }
        }
    }
}
