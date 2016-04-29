$(document).ready(function($) {
    if ($('#studentStatus').val() != 'Default') {
        $('#docNext').hide();
    }
});

$('#docNext').on('click', function () {
    $('#docStatement').hide();
    $('#docContract').show();
    $('#docHeader').text('Данные для договора');
});

$('#docBack').on('click', function() {
    if ($('#docContract').is(':visible') == true) {
        $('#docContract').hide();
        $('#docStatement').show();
        $('#docHeader').text('Заявление на заселение');
    }
    else {
        document.location.href = 'indexStudent.jsp';
    }
});