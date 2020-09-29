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

    private String codigo;
    private String codigoPaciente;
    private String codigoLaboratorista;
    private String codigoTipoExamen;
    private String codigoMedico;
    private InputStream orden;
    private LocalDate fecha;
    private LocalTime hora;
    private int estado;
    private float total;

    public Examen() {
    }

    public Examen(String codigo, String codigoPaciente, String codigoLaboratorista,
            String codigoTipoExamen, String codigoMedico, String orden, String fecha,
            String hora, int estado, float total) {
        this.codigo = codigo;
        this.codigoPaciente = codigoPaciente;
        this.codigoLaboratorista = codigoLaboratorista;
        this.codigoTipoExamen = codigoTipoExamen;
        this.codigoMedico = codigoMedico.trim().isEmpty() ? null: codigoMedico;
        this.estado = estado;
        this.total = total;

        if (!orden.trim().isEmpty()) {
            try {
                this.orden = new FileInputStream("/home/asael/uploads/archivoEntrada/" + orden);
            } catch (FileNotFoundException e) {
                e.printStackTrace(System.out);
            }

        }
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoPaciente() {
        return codigoPaciente;
    }

    public void setCodigoPaciente(String codigoPaciente) {
        this.codigoPaciente = codigoPaciente;
    }

    public String getCodigoLaboratorista() {
        return codigoLaboratorista;
    }

    public void setCodigoLaboratorista(String codigoLaboratorista) {
        this.codigoLaboratorista = codigoLaboratorista;
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
}
