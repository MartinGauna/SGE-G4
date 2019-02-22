$(function() {
    console.log( "ready!" );
    $(".fa-floppy-o").click(enableEdit);
    $(".fa-times").click(deleteRegla);
    $(".select-Accion").each(function (index) {
        // console.log( $(this)[0] );
        const id = "#i-" + $(this)[0].id.substring($(this)[0].id.indexOf("-") + 1);
        // console.log(id);
        const value = $(id)[0].value;
        // console.log(value);
        if(value === "prenderDispositivo"){
            $(this)[0][0].selected="selected";
        } else if(value === "apagarDispositivo"){
            $(this)[0][1].selected="selected";
        } else if(value === "cambiarModoAAhorro") {
            $(this)[0][2].selected="selected";
        } else if (value === 'subir'){
            $(this)[0][3].selected="selected";
        }else if (value === 'bajar'){
            $(this)[0][4].selected="selected";
        }
    });

    $("input[name='cantidad']").each(function(){
        console.log('cantidad log');
        console.log($(this)[0].id);
        const id = "#select-" + $(this)[0].id.substring($(this)[0].id.indexOf("-") + 1);
        const value = $(id).find(":selected")[0] ? $(id).find(":selected").value : '';

        if(!(value === 'subir' || value === 'bajar')){
            $(this).hide();
        } else {
            $(this).show();
        }
    });
    $(document).on('change','.accion',function() {
        console.log($(this));
        const id = "#cantidad-" + $(this)[0].id.substring($(this)[0].id.indexOf("-") + 1);
        if(this.selectedOptions[0].value === "subir" || this.selectedOptions[0].value === 'bajar'){
            $(id).show();
        }
    });
});

function enableEdit(event) {
    console.log(event);
    let entry = {};
    let rows = event.target.parentElement.parentElement.children;

    entry.id = rows[0].firstElementChild.value; // ID regla
    entry.accion = rows[2].firstElementChild.value; // accion
    entry.cantidad = rows[2].lastElementChild.value ? rows[2].lastElementChild.value : 0; // Cantidad si es bajar o subir
    entry.condicionCMagnitud = rows[3].children[0].selectedOptions[0].value; // magnitud
    entry.condicionCriterio = rows[3].children[1].selectedOptions[0].text(); // condicion
    entry.condicionValor = rows[3].children[2].value; //valor condicion
    $.ajax({
        type: "POST",
        url : "/seleccionReglas",
        contentType: "application/json",
        data: entry,
        success: function() {
             // rows[1].firstChild.value = entry.nombre;
             // rows[2].firstChild.value = entry.consumoHora;
             // rows[3].firstChild.value = entry.estado;
            alert('Se modificó la regla con éxito');
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
    entry.id = rows[0].childNodes[1].value;

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
