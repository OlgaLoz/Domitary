function getStatusText(hiddenStatus){
    switch (hiddenStatus) {
        case 'Default':
            return 'Документы на заселение не поданы';
        case 'Candidate':
            return 'Кандидат';
        case 'DeaneryPassed':
            return 'Деканат пройден';
        case 'DeaneryNotPassed':
            return 'Деканат не пройден';
        case 'BodyCheckPassed':
            return 'Врач пройден';
        case 'BodyCheckNotPassed':
            return 'Врач не пройден';
        case 'Settled':
            return 'Заселен';
        case 'NotSettled':
            return 'Не заселен';
    }
}


