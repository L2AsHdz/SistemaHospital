$('document').ready(function () {
    $("#form-consulta").validate({
        rules: {
            hora: {
                required: true
            },
            fecha: {
                required: true
            },
            orden: {
                required: true
            },
            medicoO: {
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
            orden: {
                required: "No ha subido el archivo de la orden"
            },
            medicoO: {
                required: "El medico es obligatorio si hay una orden"
            }
        }
    });
});