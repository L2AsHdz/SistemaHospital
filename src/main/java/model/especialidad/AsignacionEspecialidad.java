package model.especialidad;

/**
 *
 * @author asael
 */
public class AsignacionEspecialidad {

    private String codigoMedico;
    private int idEspecialidad;

    public AsignacionEspecialidad() {
    }

    public AsignacionEspecialidad(String codigoMedico, int idEspecialidad) {
        this.codigoMedico = codigoMedico;
        this.idEspecialidad = idEspecialidad;
    }

    public String getCodigoMedico() {
        return codigoMedico;
    }

    public void setCodigoMedico(String codigoMedico) {
        this.codigoMedico = codigoMedico;
    }

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }
}
