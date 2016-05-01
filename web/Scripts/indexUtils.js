+function ($) {
    
    $(document).ready(function () {
        
        var errorWindow = getUrlVars()["error"];
        if (errorWindow == null)
            return;
        
        var errorState;
        
        if (errorWindow == 'authorization') {
            errorState = getUrlVars()["state"];
            
            if (errorState != null){
                $('#authorizeError').text('Произошла ошибка. Повторите попытку позже.');
            }
            else {
                $('#authorizeError').text('Некорректный логин или пароль!');
            }
            
            $('#autorizModal').modal({
                show: 'true'
            });
        }
        else if (errorWindow = 'registration') {
            errorState = getUrlVars()["state"];

            if (errorState != null) {
                switch (errorState){
                    case '1':
                        $('#registerError').text('Такой пользователь уже существует!');
                        break;
                    case '2':
                        $('#registerError').text('Пароли не совпадают!');
                        break;
                    case '3':
                        $('#registerError').text('Произошла ошибка. Повторите попытку позже.');
                        break;
                    case '4':
                        $('#registerError').text('Проверьте, заполнены ли все поля.');
                        break;
                    case '5':
                        $('#registerError').text('Неправильно введена дата рождения!');
                        break;
                }
            }
            
            $('#registerModal').modal({
                show: 'true'
            });
        }
        
    });

    $('#authorizeClose').on('click', function () {
        $('#authorizeError').text('');
        history.pushState(null, null, '/');
    });

    $('#registerClose').on('click', function () {
        $('#registerError').text('');
        history.pushState(null, null, '/');
    });

    function getUrlVars() {
        var vars = {};
        var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi,
            function (m, key, value) {
                vars[key] = value;
            });
        return vars;
    }

}(jQuery);
