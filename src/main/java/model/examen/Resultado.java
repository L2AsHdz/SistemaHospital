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
public class Resultado {

    private String codigoExamen;
    private InputStream resultado;
    private LocalDate fecha;
    private LocalTime hora;

    public Resultado() {
    }

    public Resultado(String codigoExamen, String resultado, String fecha, String hora) {
        this.codigoExamen = codigoExamen;
        
        try {
            this.resultado = new FileInputStream("/home/asael/uploads/datosEntrada/" + resultado);
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
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
                    e.printStackTrace(System.out);
                }
            }
        }
    }

    public String getCodigoExamen() {
        return codigoExamen;
    }

    public void setCodigoExamen(String codigoExamen) {
        this.codigoExamen = codigoExamen;
    }

    public InputStream getResultado() {
        return resultado;
    }

    public void setResultado(InputStream resultado) {
        this.resultado = resultado;
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
}
