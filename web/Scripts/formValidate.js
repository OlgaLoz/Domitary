function checkParams() {
    var maxLen = 7;
    var number =  $('#numberD').val();
    var address = $('#address').val();
    var blocks = $('#maxBlock').val();

    if(number.length > maxLen)
        $('#numberD').val(number.substr(0, maxLen));

    if(blocks.length > maxLen)
        $('#maxBlock').val(blocks.substr(0, maxLen));

    var checkNumber = +number;
    var checkBlock = +blocks;

    $('#numberDError').text((isNaN(checkNumber) && number.length !=0 || checkNumber < 0)?"Должно быть положительным числом!":"");
    $('#blockError').text ((isNaN(checkBlock) && blocks.length !=0 || checkBlock < 0)?"Должно быть положительным числом!":"");


    if(number.length != 0 && address.length != 0 && blocks.length != 0 && !isNaN(checkNumber) && !isNaN(checkBlock) && checkNumber > 0 && checkBlock> 0 ) {
        $('#newDormitory').removeAttr('disabled');
    } else {
        $('#newDormitory').attr('disabled', 'disabled');
    }
}
