function errorMessage(errorMessage, $scope) {

    var errorWindow = getVars(errorMessage)["error"];
    var errorMsgText = '';

    if (errorWindow == null)
        return;

    if (errorWindow == 'authorization') {
        var errorState = getVars(errorMessage)["state"];

        if (errorState != null) {
            errorMsgText = 'Произошла ошибка. Повторите попытку позже.';
        }
        else {
            errorMsgText = 'Некорректный логин или пароль!';
        }
    }
    else if (errorWindow = 'registration') {
        var errorState = getVars(errorMessage)["state"];

        if (errorState != null) {
            switch (errorState) {
                case '1':
                    errorMsgText = 'Такой пользователь уже существует!';
                    break;
                case '2':
                    errorMsgText = 'Пароли не совпадают!';
                    break;
                case '3':
                    errorMsgText = 'Произошла ошибка. Повторите попытку позже.';
                    break;
                case '4':
                    errorMsgText = 'Проверьте, заполнены ли все поля.';
                    break;
                case '5':
                    errorMsgText = 'Неправильно введена дата рождения!';
                    break;
                default:
                    errorMsgText = 'Произошла ошибка. Повторите попытку позже.';
                    break;
            }
        }
    }

    $scope.errorMessage = errorMsgText;
}

function getVars(errorMessage) {
    var vars = {};
    var parts = errorMessage.replace(/[?&]+([^=&]+)=([^&]*)/gi,
        function (m, key, value) {
            vars[key] = value;
        });
    return vars;
}