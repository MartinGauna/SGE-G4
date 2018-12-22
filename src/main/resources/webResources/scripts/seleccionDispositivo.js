$(function() {
    console.log( "ready!" );
    $(".fa-floppy-o").click(enableEdit);
    $(".fa-times").click(deleteDispositivo);
});

function enableEdit(event) {
    console.log(event);
    let entry = {};
    let rows = event.target.parentElement.parentElement.children;

    entry.id = rows[0].firstChild.value;
    entry.nombre = rows[1].firstChild.value;
    entry.estado = rows[2].firstChild.value;
    entry.consumoHora = rows[3].firstChild.value;

    $.ajax({
        type: "put",
        url : "/seleccionDispositivo/",
        contentType: "application/json",
        data: entry,
        success: function() {
             rows[1].firstChild.value = entry.nombre;
             rows[2].firstChild.value = entry.consumoHora;
             rows[3].firstChild.value = entry.estado;
        },
        error: function (respondError) {
            alert('Error al Modificar');
            if(respondError.status == 410 || respondError.status == 400) {
                showAlertError("No se pudo eliminar el dispositivo seleccionado");
            }else{
                showAlertError("Ocurrio un error. Intenta nuevamente");
            }
        }
    });
}

function deleteDispositivo(event) {
    var _self = this;
    console.log(event);
    let entry = {};
    let rows = event.target.parentElement.parentElement.children;
    entry.id = rows[0].firstChild.value;

    $.ajax({
        url : "/seleccionDispositivo/" + entry.id,
        type: "DELETE",
        success    : function(){
            $(_self).parent().parent().remove();
        },
        error: function (respondError) {
            alert('Error al borrar');
            if(respondError.status == 410 || respondError.status == 400) {
                showAlertError("No se pudo eliminar el dispositivo seleccionado");
            }else{
                showAlertError("Ocurrio un error. Intenta nuevamente");
            }
        }
    });
}


function getElement(name) {
    return document.getElementById(name);
}
