$('document').ready(function () {
    $("#form-especialidad").validate({

        rules: {
            nombre: {
                required: true,
                maxlength: 45
            },
            costo: {
                required: true,
                number: true
            }
        },
        messages: {
            nombre: {
                required: "Este campo es obligatorio",
                maxlength: "Los caracteres maximos son 45"
            },
            costo: {
                required: "Este campo es obligatorio",
                number: "Debe ser un dato numerico"
            }
        }
    });
});