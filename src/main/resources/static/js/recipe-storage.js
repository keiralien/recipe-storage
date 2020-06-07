//    JQUERY
$(document).ready(function() {

    function reloadDirections (html) {
        $('#directionsSection').replaceWith($(html));
    }

    $('#addDirection').click(function (event) {
        event.preventDefault();
        var data = $('directionsList').serialize();
        data += 'addDirection';
        $.post('/recipe/add', data, reloadDirections);
    });

});
