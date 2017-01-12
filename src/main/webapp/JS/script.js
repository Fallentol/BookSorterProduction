(function($) {

    $.extend({
        request: function( options ) {


            return this;

        },
        // Объект, в котором хранится ответ от сервера, полученный через AJAX-запрос
        response: {
            result: {}
        }
    });



})(jQuery); // Используем немедленно вызываемую анонимную функцию
