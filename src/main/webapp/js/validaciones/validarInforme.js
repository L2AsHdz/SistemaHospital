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
            }
        }
    });
});