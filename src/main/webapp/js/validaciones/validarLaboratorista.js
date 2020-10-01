$('document').ready(function () {
    
    $.validator.addMethod("notEmpty", function(value, element) {
        return value.trim().length !== 0;
    }, "El campo no puede contener solo espacios en blanco");
    
    $("#form-laboratorista").validate({
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
            registroSalud: {
                required: true,
                maxlength: 45,
                notEmpty: true
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
            tipoExamen: {
                required: true,
                minlength: 1
            },
            fechaLabores: {
                required: true
            },
            password: {
                required: true,
                minlength: 8
            },
            turnos: {
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
            registroSalud: {
                required: "Este campo es obligatorio",
                maxlength: "No puede ser mayor a 45 caracteres"
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
            tipoExamen: {
                required: "Este campo es obligatorio",
                minlength: "Seleccione una opcion"
            },
            fechaLabores: {
                required: "Este campo es obligatorio"
            },
            password: {
                required: "Este campo es obligatorio",
                minlength: "Debe tener al menos 8 caracteres"
            },
            turnos: {
                required: "No ha seleccionado ningun turno"
            }
        },
        errorPlacement: function(error, element) {
            if (element.is(":checkbox")) {
                error.appendTo(element.parents(".turno"));
            } else {
                error.insertAfter(element);
            }
        }
    });
});