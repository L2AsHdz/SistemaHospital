$('document').ready(function () {
    $("#form-resultado").validate({
        rules: {
            hora: {
                required: true
            },
            fecha: {
                required: true
            },
            resultado: {
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
            resultado: {
                required: "No ha subido el resultado del examen"
            }
        }
    });
});