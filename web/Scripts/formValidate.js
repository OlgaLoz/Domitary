function checkParams() {
    var number =  $('#numberD').val();
    var address = $('#address').val();
    var blocks = $('#maxBlock').val();


    $('#numberDError').text((!parseInt(number) && number.length !=0)?"Должно быть числом!":"");
    $('#blockError').text ((!parseInt(blocks) && blocks.length !=0)?"Должно быть числом!":"");


    if(number.length != 0 && address.length != 0 && blocks.length != 0 && parseInt(number) && parseInt(blocks)) {
        $('#newDormitory').removeAttr('disabled');
    } else {
        $('#newDormitory').attr('disabled', 'disabled');
    }
}
