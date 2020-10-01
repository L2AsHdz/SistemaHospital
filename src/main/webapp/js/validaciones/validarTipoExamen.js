$('document').ready(function () {
    
    $.validator.addMethod("notEmpty", function(value, element) {
        return value.trim().length !== 0;
    }, "El campo no puede contener solo espacios en blanco");
    
    $("#form-tipoExamen").validate({

        rules: {
            codigo: {
                required: true,
                maxlength: 10,
                notEmpty: true
            },
            nombre: {
                required: true,
                maxlength: 45,
                notEmpty: true
            },
            requiereOrden: {
                required: true,
                minlength: 1
            },
            descripcion: {
                required: true,
                maxlength: 1200,
                notEmpty: true
            },
            costo: {
                required: true,
                number: true
            },
            tipoInforme: {
                required: true,
                minlength: 1
            }
        },
        messages: {
            codigo: {
                required: "Este campo es obligatorio",
                maxlength: "Los caracteres maximos son 10"
            },
            nombre: {
                required: "Este campo es obligatorio",
                maxlength: "Los caracteres maximos son 45"
            },
            requiereOrden: {
                required: "Este campo es obligatorio",
                minlength: "Seleccione una opcion"
            },
            descripcion: {
                required: "Este campo es obligatorio",
                maxlength: "Los caracteres maximos son 1200"
            },
            costo: {
                required: "Este campo es obligatorio",
                number: "Debe ser un dato numerico"
            },
            tipoInforme: {
                required: "Este campo es obligatorio",
                minlength: "Seleccione una opcion"
            }
        }
    });
});