function checkBlock() {
    var dormitoryNumber =  $('#numberD').val();
    var blockNumber = $('#numberB').val();

    if(dormitoryNumber.length != 0 && blockNumber.length != 0 ) {
        $('#newBlock').removeAttr('disabled');
    } else {
        $('#newBlock').attr('disabled', 'disabled');
    }
}
