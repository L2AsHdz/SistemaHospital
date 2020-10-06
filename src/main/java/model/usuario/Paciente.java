package model.usuario;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author asael
 */
public class Paciente extends Usuario implements Serializable{

    private String sexo;
    private LocalDate birth;
    private String telefono;
    private float peso;
    private String tipoSangre;
    private String correo;
    private int informes;

    public Paciente() {
    }

    public Paciente(String sexo, String birth, String telefono, String peso, String tipoSangre, 
            String correo, String codigo, String nombre, String cui, String password) {
        super(codigo, nombre, cui, password);
        this.sexo = sexo;
        this.telefono = telefono;
        this.peso = Float.parseFloat(peso);
        this.tipoSangre = tipoSangre;
        this.correo = correo;
        
        try {
            this.birth = LocalDate.parse(birth);
        } catch (Exception e) {
            try {
                this.birth = LocalDate.parse(birth, DateTimeFormatter.ofPattern("yyyy-M-d"));
            } catch (Exception ex) {
                try {
                    this.birth = LocalDate.parse(birth, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                } catch (Exception exx) {
                    exx.printStackTrace(System.out);
                }
            }
        }
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getInformes() {
        return informes;
    }

    public void setInformes(int informes) {
        this.informes = informes;
    }
}
