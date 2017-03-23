function check_empty() {
    if (document.getElementById('name').value == "" || document.getElementById('email').value == "" || document.getElementById('msg').value == "") {
        alert("Fill All Fields !");
    } else {
        document.getElementById('form').submit();
        alert("Form Submitted Successfully...");
    }
}

function div_show() {
    document.getElementById('abc').style.display = "block";
}

function div_hide(){
    document.getElementById('abc').style.display = "none";
}

$('form').submit(function(){ //listen for submit event
    $.each(params, function(id, value){
        $('<input />').attr('type', 'hidden')
            .attr('name', id)
            .attr('value', value)
            .appendTo('#form');
    });

    return true;
});