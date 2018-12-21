function deleteRegla(){
    $.ajax({
        url : "/regla/delete",
        type: "post",
        success    : function(){
            location.reload();
        },
        error: function (respondError) {
            if(respondError.status == 412){
                showAlertError("No se pudo eliminar la regla seleccionada");
            }else{
                showAlertError("Ocurrio un error. Intenta nuevamente");
            }
        }
    });
}

function modificarRegla(){
    $.ajax({
        url : "/regla/modificar",
        type: "post",
        success    : function(){
            location.reload();
        },
        error: function (respondError) {
            if(respondError.status == 412){
                showAlertError("No se pudo modificar la regla seleccionada");
            }else{
                showAlertError("Ocurrio un error. Intenta nuevamente");
            }
        }
    });
}