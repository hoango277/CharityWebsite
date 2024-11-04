$(document).ready(function () {
    bindEventCheckAllCheckBox('checkAll');
    enableOrDisableDeleteAll();
    autoCheckCheckboxAll('checkAll');
});

function bindEventCheckAllCheckBox(id) {
    $('#' + id).on('change', function () {
        if ((this).checked) {
            $(this).closest('table').find('input[type=checkbox]').prop('checked', true);
        } else {
            $(this).closest('table').find('input[type=checkbox]').prop('checked', false);
            $('#btnDelete').prop('disabled', true);
        }
    });
}

function enableOrDisableDeleteAll() {
    $('input[type=checkbox]').click(function () {
        if ($('input[type=checkbox]:checked').length > 0) {
            $('#btnDelete').prop('disabled', false);
        } else {
            $('#btnDelete').prop('disabled', true);
        }
    });
}

function autoCheckCheckboxAll(id) {
    var totalCheckbox = $('#' + id).closest('table').find('tbody input[type=checkbox]').length;
    $('#' + id).closest('table').find('tbody input[type=checkbox]').each(function () {
        var tableObj = $('#' + id).closest('table');
        $(this).on('change', function () {
            var totalCheckboxChecked = $(tableObj).find('tbody input[type=checkbox]:checked').length;
            if (totalCheckboxChecked == totalCheckbox) {
                $('#' + id).prop('checked', true);
            } else {
                $('#' + id).prop('checked', false);
            }
        });
    });
}