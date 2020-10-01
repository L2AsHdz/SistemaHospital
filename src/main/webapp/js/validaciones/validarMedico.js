$('document').ready(function () {
    
    $.validator.addMethod("notEmpty", function(value, element) {
        return value.trim().length !== 0;
    }, "El campo no puede contener solo espacios en blanco");
    
    $("#form-medico").validate({
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
            noColegiado: {
                required: true,
                maxlength: 20,
                digits: true
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
            horaInicio: {
                required: true
            },
            horaFinal: {
                required: true
            },
            fechaLabores: {
                required: true
            },
            password: {
                required: true,
                minlength: 8,
                notEmpty: true
            },
            checkEsp: {
                required: true
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
            noColegiado: {
                required: "Este campo es obligatorio",
                maxlength: "No puede ser mayor a 20 caracteres",
                digits: "El campo solo debe contener numeros"
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
            horaInicio: {
                required: "Este campo es obligatorio"
            },
            horaFinal: {
                required: "Este campo es obligatorio"
            },
            fechaLabores: {
                required: "Este campo es obligatorio"
            },
            password: {
                required: "Este campo es obligatorio",
                minlength: "Debe tener al menos 8 caracteres"
            },
            checkEsp: {
                required: "No ha seleccionado ninguna especialidad"
            }
        },
        errorPlacement: function(error, element) {
            if (element.is(":checkbox")) {
                error.appendTo(element.parents(".esp"));
            } else {
                error.insertAfter(element);
            }
        }
    });
});