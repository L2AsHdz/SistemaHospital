$('document').ready(function () {    
    
    $.validator.addMethod("horario", function(value, element) {
        var horaE = $("#horaEntrada").val();
        var horaF = $("#horaSalida").val();
        
        return value >= horaE && value < horaF;
    }, "La hora no esta dentro del horario del medico");
    
    $("#form-consulta").validate({
        rules: {
            hora: {
                required: true,
                horario: true
            },
            fecha: {
                required: true
            }
        },
        messages: {
            hora: {
                required: "La hora es obligatoria"
            },
            fecha: {
                required: "La fecha es obligatoria"
            }
        }
    });
});