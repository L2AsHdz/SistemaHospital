$('document').ready(function () {
    $("#form-login").validate({

        rules: {
            codigo: {
                required: true
            },
            password: {
                required: true
            }
        },
        messages: {
            codigo: {
                required: "No ha ingresado un codigo"
            },
            password: {
                required: "Contraseña vacia"
            }
        }
    });
});