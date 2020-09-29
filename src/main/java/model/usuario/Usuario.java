package model.usuario;

import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author asael
 */
public class Usuario {

    private String codigo;
    private String nombre;
    private String cui;
    private String password;

    public Usuario() {
    }

    public Usuario(String codigo, String nombre, String cui, String password) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cui = cui;
        this.password = password;
    }
    
    

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCUI() {
        return cui;
    }

    public void setCUI(String nit) {
        this.cui = nit;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEncryptPassword() {
        return DigestUtils.sha1Hex(password);
    }
}
