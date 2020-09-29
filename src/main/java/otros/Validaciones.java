package otros;

import datos.CRUD;
import datos.admin.AdminDAOImpl;
import datos.consulta.ConsultaDAOImpl;
import datos.consulta.InformeDAOImpl;
import datos.especialidad.AsignacionEspecialidadDAO;
import datos.especialidad.AsignacionEspecialidadDAOImpl;
import datos.especialidad.EspecialidadDAO;
import datos.especialidad.EspecialidadDAOImpl;
import datos.examen.ResultadoDAOImpl;
import datos.examen.TipoExamenDAO;
import datos.examen.TipoExamenDAOImpl;
import datos.laboratorista.LaboratoristaDAOImpl;
import datos.medico.MedicoDAOImpl;
import datos.paciente.PacienteDAOImpl;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.consulta.Consulta;
import model.consulta.Informe;
import model.examen.Resultado;
import model.usuario.Admin;
import model.usuario.Laboratorista;
import model.usuario.Medico;
import model.usuario.Paciente;

/**
 *
 * @author asael
 */
public class Validaciones {

    private static final CRUD<Admin> adminDAO = AdminDAOImpl.getAdminDAO();
    private static final CRUD<Paciente> pacienteDAO = PacienteDAOImpl.getPacienteDAO();
    private static final EspecialidadDAO especialidadDAO = EspecialidadDAOImpl.getEspecialidadDAO();
    private static final CRUD<Medico> medicoDAO = MedicoDAOImpl.getMedicoDAO();
    private static final TipoExamenDAO tipoExamenDAO = TipoExamenDAOImpl.getTipoExamenDAO();
    private static final CRUD<Laboratorista> laboratoristaDAO = LaboratoristaDAOImpl.getLaboratoristaDAO();
    private static final CRUD<Resultado> resultadoDAO = ResultadoDAOImpl.getResultadoDAO();
    private static final CRUD<Consulta> consultaDAO = ConsultaDAOImpl.getconsultaDAO();
    private static final AsignacionEspecialidadDAO asignacionDAO = AsignacionEspecialidadDAOImpl.getAsignacionEsDAO();
    private static final CRUD<Informe> informeDAO = InformeDAOImpl.getInformeDAO();

    public static void validarAdmin(String codigo, String nombre, String cui, String password, int i) throws FileInputException {
        String etiqueta = "Etiqueta admin no " + (i + 1);

        if (codigo.trim().isEmpty() || nombre.trim().isEmpty() || cui.trim().isEmpty() || password.trim().isEmpty()) {
            throw new FileInputException(etiqueta + ": Hay uno o mas valores vacios");
        } else if (adminDAO.exists(codigo)) {
            throw new FileInputException(etiqueta + ": El admin ya esta registrado");
        }
    }

    public static void validarPaciente(String codigo, String nombre, String sexo, String birth, String cui, String telefono,
            String peso, String tipoSangre, String correo, String password, int i) throws FileInputException {
        
        String etiqueta = "Etiqueta paciente no " + (i + 1);
        if (codigo.trim().isEmpty() || nombre.trim().isEmpty() || sexo.trim().isEmpty() || birth.trim().isEmpty()
                || cui.trim().isEmpty() || telefono.trim().isEmpty() || peso.trim().isEmpty() || tipoSangre.trim().isEmpty()
                || correo.trim().isEmpty() || password.trim().isEmpty()) {
            throw new FileInputException(etiqueta + ": Hay uno o mas valores vacios");
        } else if (!isFecha(birth)) {
            throw new FileInputException(etiqueta + ": El formato de la fecha no es valido");
        } else if (!isFloat(peso)) {
            throw new FileInputException(etiqueta + ": El peso debe ser un dato numerico");
        } else if (pacienteDAO.exists(codigo)) {
            throw new FileInputException(etiqueta + ": El paciente ya esta registrado");
        }
    }

    public static void validarEspecialidad(String nombre, String costo, int i) throws FileInputException {
        
        String etiqueta = "Etiqueta consulta no " + (i + 1);
        if (nombre.trim().isEmpty() || costo.trim().isEmpty()) {
            throw new FileInputException(etiqueta + ": Hay uno o mas valores vacios");
        } else if (!isFloat(costo)) {
            throw new FileInputException(etiqueta + ": El costo debe ser un valor numerico");
        } else if (especialidadDAO.exists(nombre)) {
            throw new FileInputException(etiqueta + ": Tipo de consulta ya existe");
        }
    }

    public static void validarMedico(String codigo, String nombre, String colegiado, String cui, String telefono,
            String correo, String horaInicio, String horaFinal, String fechaInicioLabores, String password,
            List<String> titulos, int i) throws FileInputException {

        String etiqueta = "Etiqueta doctor no " + (i + 1);
        if (codigo.trim().isEmpty() || nombre.trim().isEmpty() || colegiado.trim().isEmpty() || cui.trim().isEmpty()
                || telefono.trim().isEmpty() || correo.trim().isEmpty() || horaInicio.trim().isEmpty()
                || horaFinal.trim().isEmpty() || fechaInicioLabores.trim().isEmpty() || password.trim().isEmpty()
                || titulos.isEmpty()) {
            throw new FileInputException(etiqueta + ": Hay uno o mas valores vacios");
        } else if (!isHora(horaFinal) || !isHora(horaInicio)) {
            throw new FileInputException(etiqueta + ": La hora no tiene un formato correcto");
        } else if (!isFecha(fechaInicioLabores)) {
            throw new FileInputException(etiqueta + ": La fecha no tiene un formato correcto");
        } else if (medicoDAO.exists(codigo)) {
            throw new FileInputException(etiqueta + ": El medico ya esta registrado");
        }

        for (String t : titulos) {
            if (!especialidadDAO.exists(t)) {
                throw new FileInputException(etiqueta + ": No existe la especialidad en el sistema");
            }
        }

    }

    public static void validarTipoExamen(String codigo, String nombre, String requiereOrden, String descripcion,
            String costo, String tipoInforme, int i) throws FileInputException {
        
        String etiqueta = "Etiqueta examen no " + (i + 1);
        if (codigo.trim().isEmpty() || nombre.trim().isEmpty() || requiereOrden.trim().isEmpty() || descripcion.trim().isEmpty()
                || costo.trim().isEmpty() || tipoInforme.trim().isEmpty()) {
            throw new FileInputException(etiqueta + ": Hay uno o mas valores vacios");
        } else if (!requiereOrden.equalsIgnoreCase("TRUE") && !requiereOrden.equalsIgnoreCase("FALSE")) {
            throw new FileInputException(etiqueta + ": El valor orden esta erroneo");
        } else if (!tipoInforme.equalsIgnoreCase("IMG") && !tipoInforme.equalsIgnoreCase("PDF")) {
            throw new FileInputException(etiqueta + ": El valor informe esta erroneo");
        } else if (!isFloat(costo)) {
            throw new FileInputException(etiqueta + ": El costo debe ser un dato numrico");
        } else if (tipoExamenDAO.exists(codigo)) {
            throw new FileInputException(etiqueta + ": El tipo examen ya esta registrado");
        }
    }

    public static void validarLaboratorista(String codigo, String nombre, String registro, String cui,
            String telefono, String correo, String fechaInicioLabores, String tipoExamen, String password,
            List<String> dias, int i) throws FileInputException {

        String etiqueta = "Etiqueta laboratorista no " + (i + 1);
        if (codigo.trim().isEmpty() || nombre.trim().isEmpty() || registro.trim().isEmpty() || cui.trim().isEmpty()
                || telefono.trim().isEmpty() || correo.trim().isEmpty() || fechaInicioLabores.trim().isEmpty()
                || tipoExamen.trim().isEmpty() || password.trim().isEmpty() || dias.isEmpty()) {
            throw new FileInputException(etiqueta + ": Hay uno o mas valores vacios");
        } else if (!isFecha(fechaInicioLabores)) {
            throw new FileInputException(etiqueta + ": La fecha no tiene un formato valido");
        } else if (!areDias(dias)) {
            throw new FileInputException(etiqueta + ": Los dias contienen uno o mas errores");
        } else if (laboratoristaDAO.exists(codigo)) {
            throw new FileInputException(etiqueta + ": El laborista ya esta registrado");
        } else if (!tipoExamenDAO.exists(tipoExamen)) {
            throw new FileInputException(etiqueta + ": No existe el tipo de examen al que hace referencia");
        }
    }

    public static void validarResultado(String codigo, String paciente, String examen, String medico,
            String laboratorista, String orden, String resultado, String fecha, String hora, int i) throws FileInputException {

        String etiqueta = "Etiqueta resultado no " + (i + 1);
        if (codigo.trim().isEmpty() || paciente.trim().isEmpty() || examen.trim().isEmpty()
                || fecha.trim().isEmpty() || laboratorista.trim().isEmpty() || hora.trim().isEmpty()
                || resultado.trim().isEmpty()) {
            throw new FileInputException(etiqueta + ": Hay uno o mas valores vacios");
        } else if (!isFecha(fecha)) {
            throw new FileInputException(etiqueta + ": La fecha no tiene un formato valido");
        } else if (!isHora(hora)) {
            throw new FileInputException(etiqueta + ": La hora no tiene un formato valido");
        } else if (!orden.trim().isEmpty() && !exists(orden)) {
            throw new FileInputException(etiqueta + ": No se encontro el archivo de la orden: " + orden);
        } else if (!exists(resultado)) {
            throw new FileInputException(etiqueta + ": No se encontro el archivo del informe: " + resultado);
        } else if (resultadoDAO.exists(codigo)) {
            throw new FileInputException(etiqueta + ": El resultado ya esta registrado en el sistema");
        } else if (!pacienteDAO.exists(paciente)) {
            throw new FileInputException(etiqueta + ": El paciente no existe en el sistema");
        } else if (!tipoExamenDAO.exists(examen)) {
            throw new FileInputException(etiqueta + ": El tipo de examen no existe en el sistema");
        } else if (!medico.trim().isEmpty() && !medicoDAO.exists(medico)) {
            throw new FileInputException(etiqueta + ": El medico no existe en el sistema");
        } else if (!laboratoristaDAO.exists(laboratorista)) {
            throw new FileInputException(etiqueta + ": El laboratorista no existe en el sistema");
        }//Falta validar que el laboratorista trabaje ese dia
    }

    public static void validarConsulta(String codigo, String paciente, String medico,
            String especialidad, String fecha, String hora, int i) throws FileInputException {

        String etiqueta = "Etiqueta cita no " + (i + 1);
        if (codigo.trim().isEmpty() || paciente.trim().isEmpty() || medico.trim().isEmpty()
                || especialidad.trim().isEmpty() || fecha.trim().isEmpty() || hora.trim().isEmpty()) {
            throw new FileInputException(etiqueta + ": Hay uno o mas valores vacios");
        } else if (!isFecha(fecha)) {
            throw new FileInputException(etiqueta + ": La fecha no tiene un formato correcto");
        } else if (!isHora(hora)) {
            throw new FileInputException(etiqueta + ": La hora no tiene un formato correcto");
        } else if (consultaDAO.exists(codigo)) {
            throw new FileInputException(etiqueta + ": Ya existe una consulta registrada con ese codigo");
        } else if (!pacienteDAO.exists(paciente)) {
            throw new FileInputException(etiqueta + ": El paciente no existe en el sistema");
        } else if (!medicoDAO.exists(medico)) {
            throw new FileInputException(etiqueta + ": El medico no existe en el sistema");
        } else if (!asignacionDAO.verificarAsignacion(medico, especialidadDAO.getIdEspecialidad(especialidad))) {
            throw new FileInputException(etiqueta + ": El medico no cuenta con la especialidad especificada");
        }
    }
    
    public static void validarInforme(String codigo, String paciente, String medico, 
            String informe, String fecha, String hora, int i) throws FileInputException {

        String etiqueta = "Etiqueta reporte no " + (i + 1);
        if (codigo.trim().isEmpty() || paciente.trim().isEmpty() || medico.trim().isEmpty()
                || fecha.trim().isEmpty() || informe.trim().isEmpty() || hora.trim().isEmpty()) {
            throw new FileInputException(etiqueta + ": Hay uno o mas valores vacios");
        } else if (!isFecha(fecha)) {
            throw new FileInputException(etiqueta + ": La fecha no tiene un formato valido");
        } else if (!isHora(hora)) {
            throw new FileInputException(etiqueta + ": La hora no tiene un formato valido");
        } else if (informeDAO.exists(codigo)) {
            throw new FileInputException(etiqueta + ": El reporte ya esta registrado en el sistema");
        } else if (!pacienteDAO.exists(paciente)) {
            throw new FileInputException(etiqueta + ": El paciente no existe en el sistema");
        } else if (!medicoDAO.exists(medico)) {
            throw new FileInputException(etiqueta + ": El medico no existe en el sistema");
        }
    }

    //Verifica si la cadena es un float
    public static boolean isFloat(String f) {
        try {
            Float.parseFloat(f);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Verifica si la cadena es un entero
    public static boolean isInt(String i) {
        try {
            Long.parseLong(i);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Verifica si el formato de la fecha es correcto
    public static boolean isFecha(String f) {
        try {
            LocalDate.parse(f);
            return true;
        } catch (Exception e) {
            try {
                LocalDate.parse(f, DateTimeFormatter.ofPattern("yyyy-M-d"));
                return true;
            } catch (Exception ex) {
                try {
                    LocalDate.parse(f, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                    return true;
                } catch (Exception exx) {
                    return false;
                }
            }
        }
    }

    //Verifica si el formato de la hora es correcto
    public static boolean isHora(String h) {
        try {
            LocalTime.parse(h, DateTimeFormatter.ofPattern("HH:mm"));
            return true;
        } catch (Exception e) {
            try {
                LocalTime.parse(h, DateTimeFormatter.ofPattern("H:mm"));
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
    }

    //verifica si los dias son correctos
    public static boolean areDias(List<String> dias) {
        boolean flag = false;
        for (String d : dias) {
            if (d.equalsIgnoreCase("lunes") || d.equalsIgnoreCase("martes") || d.equalsIgnoreCase("miercoles")
                    || d.equalsIgnoreCase("jueves") || d.equalsIgnoreCase("viernes") || d.equalsIgnoreCase("sabado")
                    || d.equalsIgnoreCase("domingo")) {
                flag = true;
            }
        }
        return flag;
    }

    public static boolean exists(String nameFile) {
        File file = new File("/home/asael/uploads/archivoEntrada/" + nameFile);
        return file.exists();
    }

    //Verifica si el numero es mayor a cero
    public static boolean isMayorACero(String s) {
        return (Float.parseFloat(s) > 0);
    }
}
