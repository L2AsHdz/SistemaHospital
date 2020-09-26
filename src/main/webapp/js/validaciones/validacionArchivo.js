$('document').ready(function () {
    $("#register-form").validate({

        rules: {
            archivoEntrada: {
                required: true
            }
        },
        messages: {
            archivoEntrada: {
                required: "No se ha seleccionado ningun archivo"
            }
        }
    });
});