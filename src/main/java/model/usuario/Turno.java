package model.usuario;

/**
 *
 * @author asael
 */
public class Turno {

    private String codigoLaboratorista;
    private Dia dia;

    public Turno() {
    }

    public Turno(String codigoLaboratorista, String dia) {
        this.codigoLaboratorista = codigoLaboratorista;
        if (dia.equalsIgnoreCase("lunes")) {
            this.dia = Dia.MONDAY;
        } else if (dia.equalsIgnoreCase("martes")) {
            this.dia = Dia.TUESDAY;
        } else if (dia.equalsIgnoreCase("miercoles")) {
            this.dia = Dia.WEDNESDAY;
        } else if (dia.equalsIgnoreCase("jueves")) {
            this.dia = Dia.THURSDAY;
        } else if (dia.equalsIgnoreCase("viernes")) {
            this.dia = Dia.FRIDAY;
        } else if (dia.equalsIgnoreCase("sabado")) {
            this.dia = Dia.SATURDAY;
        } else if (dia.equalsIgnoreCase("domingo")) {
            this.dia = Dia.SUNDAY;
        }
    }

    public String getCodigoLaboratorista() {
        return codigoLaboratorista;
    }

    public void setCodigoLaboratorista(String codigoLaboratorista) {
        this.codigoLaboratorista = codigoLaboratorista;
    }

    public Dia getDia() {
        return dia;
    }

    public void setDia(Dia dia) {
        this.dia = dia;
    }
}
