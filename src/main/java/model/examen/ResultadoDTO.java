package model.examen;

import java.io.Serializable;

/**
 * @date 3/10/2020
 * @time 14:01:08
 * @author asael
 */
public class ResultadoDTO extends Resultado implements Serializable {
    
    private String paciente;
    private String tipoExamen;
    private String medico;
    private String laboratorista;
    private String tipoInforme;
    private float costo;
    private int cantRealizados;

    public ResultadoDTO() {
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getTipoExamen() {
        return tipoExamen;
    }

    public void setTipoExamen(String tipoExamen) {
        this.tipoExamen = tipoExamen;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getLaboratorista() {
        return laboratorista;
    }

    public void setLaboratorista(String laboratorista) {
        this.laboratorista = laboratorista;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public int getCantRealizados() {
        return cantRealizados;
    }

    public void setCantRealizados(int cantRealizados) {
        this.cantRealizados = cantRealizados;
    }

    public String getTipoInforme() {
        return tipoInforme;
    }

    public void setTipoInforme(String tipoInforme) {
        this.tipoInforme = tipoInforme;
    }
}
