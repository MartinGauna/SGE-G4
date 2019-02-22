$(function() {
    $("#cantidad").hide();
    $(document).on('change','#accion',function() {
        console.log($(this));
        // const id = "#cantidad-" + $(this)[0].id.substring($(this)[0].id.indexOf("-") + 1);
        if(this.selectedOptions[0].value === "subir" || this.selectedOptions[0].value === 'bajar') {
            $("#cantidad").show();
        }
    });
});