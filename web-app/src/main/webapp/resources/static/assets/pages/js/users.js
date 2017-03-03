function editUser(id) {
    $.ajax(
        {
            url: '/user?id=' + id,
            dataType: 'html',
            success: function (response) {
                // console.log(response);
                document.open();
                document.write(response);
                document.close();
            },
            error: function (response) {
                console.error(response);
            }
        }
    );
}

function createUser() {
    $.ajax(
        {
            url: '/createuser',
            dataType: 'html',
            success: function (response) {
                // console.log(response);
                document.open();
                document.write(response);
                document.close();
            },
            error: function (response) {
                console.error(response);
            }
        }
    );
}

function updateUser(id) {
    var login = $('#inputLogin').val();
    var password = $('#inputPassword').val();
    var description = $('#inputDescription').val();

    $.ajax({
        type: 'PUT',
        url: 'user?id=' + id + '&login=' + login + '&password=' + password + '&description=' + description
    });
}

function deleteUser(id) {
    $.ajax({
        type: 'DELETE',
        url: 'user?id=' + id
    });
}