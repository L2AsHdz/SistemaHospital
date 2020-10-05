$('document').ready(function () {
    $("#form-informe").validate({
        rules: {
            hora: {
                required: true
            },
            fecha: {
                required: true
            },
            informe: {
                required: true
            },
            nuevaHora: {
                required: true
            },
            nuevaFecha: {
                required: true
            }
        },
        messages: {
            hora: {
                required: "La hora es obligatoria"
            },
            fecha: {
                required: "La fecha es obligatoria"
            },
            informe: {
                required: "No ha escrito el informe de la consulta"
            },
            nuevaHora: {
                required: "No ha ingresado una hora para la nueva consulta"
            },
            nuevaFecha: {
                required: "No ha ingresado una fecha para la nueva consulta"
            }
        }
    });
});