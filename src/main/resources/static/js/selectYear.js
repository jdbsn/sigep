$(function () {
    var currYear = new Date().getFullYear(), option, i;

    for (i = 1900; i < currYear + 2; i += 1) {
        option = $(document.createElement('option')).val(i).text(i);
        option.prop('selected', i === currYear + 1);
        $('#year').append(option);
    }
});