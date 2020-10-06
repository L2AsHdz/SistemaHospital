package model.usuario;

import java.io.Serializable;

/**
 *
 * @author asael
 */
public class Admin extends Usuario implements Serializable {

    public Admin() {
    }

    public Admin(String codigo, String nombre, String cui, String password) {
        super(codigo, nombre, cui, password);
    }

}
