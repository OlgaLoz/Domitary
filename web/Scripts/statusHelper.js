$(document).ready(function(){
    var hidenStatus = $('#hideStatus').val();
    switch (hidenStatus) {
        case 'Default':
            $('#status').text('Документы на заселение не поданы');
            break;
        case 'Candidate':
            $('#status').text('Кандидат');
            break;
        case 'DeaneryPassed':
            $('#status').text('Деканат пройден');
            break;
        case 'DeaneryNotPassed':
            $('#status').text('Деканат не пройден');
            break;
        case 'BodyCheckPassed':
            $('#status').text('Врач пройден');
            break;
        case 'BodyCheckNotPassed':
            $('#status').text('Врач не пройден');
            break;
        case 'Settled':
            $('#status').text('Заселен');
            break;
        case 'NotSettled':
            $('#status').text('Не заселен');
            break;
    }

});


