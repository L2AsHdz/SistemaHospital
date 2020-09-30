$('document').ready(function () {
    $("#form-tipoExamen").validate({

        rules: {
            codigo: {
                required: true,
                maxlength: 10
            },
            nombre: {
                required: true,
                maxlength: 45
            },
            requiereOrden: {
                required: true,
                number: true
            },
            descripcion: {
                required: true,
                maxlength: 1200
            },
            costo: {
                required: true,
                number: true
            },
            tipoInforme: {
                required: true
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
                number: "seleccione una opcion"
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
                required: "Este campo es obligatorio"
            }
        }
    });
});