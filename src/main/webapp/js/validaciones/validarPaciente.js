$('document').ready(function () {

    $.validator.addMethod("notEmpty", function (value, element) {
        return value.trim().length !== 0;
    }, "El campo no puede contener solo espacios en blanco");

    $("#form-paciente").validate({
        rules: {
            codigo: {
                required: true,
                maxlength: 20,
                notEmpty: true
            },
            nombre: {
                required: true,
                maxlength: 45,
                notEmpty: true
            },
            sexo: {
                required: true,
                minlength: 1
            },
            cui: {
                required: true,
                maxlength: 15,
                digits: true
            },
            telefono: {
                required: true,
                maxlength: 10,
                digits: true
            },
            correo: {
                required: true,
                maxlength: 45,
                email: true
            },
            birth: {
                required: true
            },
            peso: {
                required: true,
                number: true
            },
            sangre: {
                required: true,
                minlength: 1
            },
            password: {
                required: true,
                minlength: 8
            }
        },
        messages: {
            codigo: {
                required: "Este campo es obligatorio",
                maxlength: "No debe ser mayor a 20 caracteres"
            },
            nombre: {
                required: "Este campo es obligatorio",
                maxlength: "No debe ser mayor a 45 caracteres"
            },
            sexo: {
                required: "Este campo es obligatorio",
                minlength: "No ha seleccionado ninguna opcion"
            },
            cui: {
                required: "Este campo es obligatorio",
                maxlength: "No puede ser mayor a 15 caracteres",
                digits: "Este campo solo debe contener numeros"
            },
            telefono: {
                required: "Este campo es obligatorio",
                maxlength: "No puede ser mayor a 10 caracteres",
                digits: "Este campo solo debe contener numeros"
            },
            correo: {
                required: "Este campo es obligatorio",
                maxlength: "No puede ser mayor a 45 caracteres",
                email: "Ingrese un correo valido"
            },
            birth: {
                required: "Este campo es obligatorio"
            },
            peso: {
                required: "Este campo es obligatorio",
                number: "El dato debe ser numerico"
            },
            sangre: {
                required: "No ha seleccionado ningna opcion",
                minlength: "Seleccione una opcion"
            },
            password: {
                required: "Este campo es obligatorio",
                minlength: "Debe tener al menos 8 caracteres"
            }
        }
    });
});