package model.consulta;

/**
 * @date 3/10/2020
 * @time 13:30:46
 * @author asael
 */
public class InformeDTO extends Informe{
    private String medico;
    private String especialidad;
    private float costo;

    public InformeDTO() {
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }
}
