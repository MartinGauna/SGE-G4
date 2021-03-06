function getNameMethodology() {
    return getElement('idDisp');
}

function deleteDispositivo() {
    $.ajax({
        url : "/seleccionDispositivo",
        type: "delete",
        success    : function(){
            location.reload();
        },
        error: function (respondError) {
            if(respondError.status == 412){
                showAlertError("No se pudo eliminar el dispositivo seleccionado");
            }else{
                showAlertError("Ocurrio un error. Intenta nuevamente");
            }
        }
    });
}

function modificarDispositivo() {
    $.ajax({
        url : "/dispositivo/modificar",
        type: "post",
        success    : function(){
            location.reload();
        },
        error: function (respondError) {
            if(respondError.status == 412){
                showAlertError("No se pudo modificar el dispositivo seleccionado");
            }else{
                showAlertError("Ocurrio un error. Intenta nuevamente");
            }
        }
    });
}