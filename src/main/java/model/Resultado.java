package model;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;

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
