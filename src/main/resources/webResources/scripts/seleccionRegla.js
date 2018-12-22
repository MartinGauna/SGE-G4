$(function() {
    console.log( "ready!" );
    $(".fa-floppy-o").click(enableEdit);
    $(".fa-times").click(deleteRegla);
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
        url : "/seleccionReglas/",
        contentType: "application/json",
        data: entry,
        success: function() {
             rows[1].firstChild.value = entry.nombre;
             rows[2].firstChild.value = entry.consumoHora;
             rows[3].firstChild.value = entry.estado;
        },
        error: function (respondError) {
            console.log(respondError);
            alert('Error al Modificar');
        }
    });
}

function deleteRegla(event) {
    var _self = this;
    console.log(event);
    let entry = {};
    let rows = event.target.parentElement.parentElement.children;
    entry.id = rows[0].firstChild.value;

    $.ajax({
        url : "/seleccionReglas/" + entry.id,
        type: "DELETE",
        success: function(){
            $(_self).parent().parent().remove();
        },
        error: function (respondError) {
            console.log(respondError);
            alert('Error al borrar');
        }
    });
}


function getElement(name) {
    return document.getElementById(name);
}
