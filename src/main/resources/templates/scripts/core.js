

function getElement(name) {
    return document.getElementById(name);
}

function getValue(element) {
    return element.value;
}

function showElement(element) {
    element.style.display='block';
}

function hideElement(element) {
    element.style.display='none';
}

function deleteAllOptions(element) {
    var options = element.options;
    var len = options.length;
    console.log("longitud:",options.length);
    for (var i=1;i<len;i++){
        console.log("longitud:",options.length);
        console.log("indice: ",i," elemento:",element.item(i));
        removeLastOption(element);
    }
}

function removeLastOption(element) {
    if (element.length > 0) {
        element.remove(element.length-1);
    }
}

function showAlertError(message){
    var myDiv = document.getElementById('alertMessage');
    myDiv.innerHTML = "<strong>Upss!!</strong> " + message;
    showElement(getElement('alertMessage'));
}