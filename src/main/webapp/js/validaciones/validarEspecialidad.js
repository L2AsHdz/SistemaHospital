$('document').ready(function () {
    
    $.validator.addMethod("notEmpty", function(value, element) {
        return value.trim().length !== 0;
    }, "El campo no puede contener solo espacios en blanco");
    
    $("#form-especialidad").validate({

        rules: {
            nombre: {
                required: true,
                maxlength: 45,
                notEmpty: true
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