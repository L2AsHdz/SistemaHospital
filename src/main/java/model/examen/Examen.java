package model.examen;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author asael
 */
public class Examen {

    private int codigo;
    private String codigoPaciente;
    private String codigoTipoExamen;
    private String nombreTipoExamen;
    private String codigoMedico;
    private String nombreMedico;
    private InputStream orden;
    private LocalDate fecha;
    private LocalTime hora;
    private int estado;
    private float total;

    public Examen() {
    }

    public Examen(String codigo, String codigoPaciente, String codigoTipoExamen,
            String codigoMedico, String orden, String fecha, String hora, int estado,
            float total) {
        this.codigo = Integer.parseInt(codigo);
        setDatos(codigoPaciente, codigoTipoExamen, codigoMedico, fecha, hora, estado, total);
        
        if (!orden.trim().isEmpty()) {
            try {
                this.orden = new FileInputStream("/home/asael/uploads/datosEntrada/" + orden);
            } catch (FileNotFoundException e) {
                e.printStackTrace(System.out);
            }

        }
    }

    public Examen(String codigoPaciente, String codigoTipoExamen,
            String codigoMedico, InputStream orden, String fecha, String hora, int estado,
            float total) {
        this.orden = orden;
        setDatos(codigoPaciente, codigoTipoExamen, codigoMedico, fecha, hora, estado, total);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCodigoPaciente() {
        return codigoPaciente;
    }

    public void setCodigoPaciente(String codigoPaciente) {
        this.codigoPaciente = codigoPaciente;
    }

    public String getCodigoTipoExamen() {
        return codigoTipoExamen;
    }

    public void setCodigoTipoExamen(String codigoTipoExamen) {
        this.codigoTipoExamen = codigoTipoExamen;
    }

    public String getCodigoMedico() {
        return codigoMedico;
    }

    public void setCodigoMedico(String codigoMedico) {
        this.codigoMedico = codigoMedico;
    }

    public String getNombreTipoExamen() {
        return nombreTipoExamen;
    }

    public void setNombreTipoExamen(String nombreTipoExamen) {
        this.nombreTipoExamen = nombreTipoExamen;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public InputStream getOrden() {
        return orden;
    }

    public void setOrden(InputStream orden) {
        this.orden = orden;
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

    private void setDatos(String codigoPaciente, String codigoTipoExamen,
            String codigoMedico, String fecha, String hora, int estado, float total) {
        this.codigoPaciente = codigoPaciente;
        this.codigoTipoExamen = codigoTipoExamen;
        this.codigoMedico = codigoMedico.trim().isEmpty() ? null : codigoMedico;
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
