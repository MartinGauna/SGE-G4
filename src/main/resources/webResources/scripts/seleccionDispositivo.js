$(function() {
    console.log( "ready!" );
    $(".fa-floppy-o").click(enableEdit);
    $(".fa-times").click(deleteDispositivo);

    $("select").each(function (index) {
        // console.log( $(this)[0] );
        const id = "#i-" + $(this)[0].id.substring($(this)[0].id.indexOf("-") + 1);
        // console.log(id);
        const value = $(id)[0].value;
        // console.log(value);
        if(value === "Activo"){
            $(this)[0][0].selected="selected";
        } else if(value === "Apagado") {
            $(this)[0][1].selected="selected";
        } else if(value === "ahorro") {
            $(this)[0][2].selected="selected";
        }
    });
});

function enableEdit(event) {
    console.log(event);
    let entry = {};
    let rows = event.target.parentElement.parentElement.children;
    let comboId = "#" + rows[2].lastElementChild.id;

    entry.id = rows[0].firstChild.value;
    entry.nombre = rows[1].firstChild.value;
    entry.estado = $(comboId).val();
    entry.consumoHora = rows[3].firstChild.value;

    $.ajax({
        type: "put",
        url : "/seleccionDispositivo/",
        contentType: "application/json",
        data: entry,
        success: function() {
            alert('Exito al Modificar - Nombre: ' + entry.nombre + " - ID: " + entry.id);
        },
        error: function (respondError) {
            alert('Error al Modificar - Nombre: ' + entry.nombre + " - ID: " + entry.id);
            conosole.log(respondError);
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
