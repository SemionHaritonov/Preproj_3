$('#editModal').on('show.bs.modal', function (event) {
    let btnId = event.relatedTarget.id;
    let id = btnId.substring(btnId.indexOf('-') + 1, btnId.length);
    console.log(id);


    $.get('/admin/users/' + id, function (userEdit) {
        console.log(userEdit);
        let url = 'http://localhost:8080/admin/users/' + userEdit.id;
        $(document).find('#inputId').empty();
        $(document).find('#inputId').append(
            '<input type="number" readonly class="form-control" id="idEdit" value="' + userEdit.id + '"' +
            'aria-label="id" aria-describedby="addon-wrapping-email">'
        );
        $(document).find('#inputEmail').empty();
        $(document).find('#inputEmail').append(
            '<input type="email" class="form-control" id="emailEdit" value="' + userEdit.email + '"' +
            'aria-label="Username" aria-describedby="addon-wrapping-email">'
        );

        $(document).find('#inputPassword').empty();
        $(document).find('#inputPassword').append(
            '<input type="password" class="form-control" id="passwordEdit" value="' + userEdit.password + '"' +
            'aria-label="Username" aria-describedby="addon-wrapping-password" placeholder="Password">'
        );

        $(document).find('#inputName').empty();
        $(document).find('#inputName').append(
            '<input type="text" class="form-control" id="nameEdit" value="' + userEdit.name + '"' +
            'aria-label="Username" aria-describedby="addon-wrapping-name">'
        );

        $(document).find('#inputAge').empty();
        $(document).find('#inputAge').append(
            '<input type="text" class="form-control" id="ageEdit" value="' + userEdit.age + '"' +
            'aria-label="Username" aria-describedby="addon-wrapping-age">'
        );

        $(document).find('#inputRoles').empty();
        $(document).find('#inputRoles').append(
            '<input type="text" class="form-control" id="rolesEdit" value="' + userEdit.roles + '"' +
            'aria-label="Username" aria-describedby="addon-wrapping-roles">'
        );
    });
})

$('#deleteModal').on('show.bs.modal', function (event) {
    let btnId = event.relatedTarget.id;
    let id = btnId.substring(btnId.indexOf('-') + 1, btnId.length);
    console.log(id);


    $.get('/admin/users/' + id, function (userDelete) {
        console.log(userDelete);
        let url = 'http://localhost:8080/admin/users/' + userDelete.id;
        $(document).find('#deleteId').empty();
        $(document).find('#deleteId').append(
            '<input type="number" readonly class="form-control" id="idDelete" value="' + userDelete.id + '"' +
            'aria-label="id" aria-describedby="addon-wrapping-email">'
        );
        $(document).find('#deleteEmail').empty();
        $(document).find('#deleteEmail').append(
            '<input type="email" class="form-control" id="emailDelete" value="' + userDelete.email + '"' +
            'aria-label="Username" aria-describedby="addon-wrapping-email">'
        );

        $(document).find('#deleteName').empty();
        $(document).find('#deleteName').append(
            '<input type="text" class="form-control" id="nameDelete" value="' + userDelete.name + '"' +
            'aria-label="Username" aria-describedby="addon-wrapping-name">'
        );

        $(document).find('#deleteAge').empty();
        $(document).find('#deleteAge').append(
            '<input type="text" class="form-control" id="ageDelete" value="' + userDelete.age + '"' +
            'aria-label="Username" aria-describedby="addon-wrapping-age">'
        );

        $(document).find('#deleteRoles').empty();
        $(document).find('#deleteRoles').append(
            '<input type="text" class="form-control" id="rolesDelete" value="' + userDelete.roles + '"' +
            'aria-label="Username" aria-describedby="addon-wrapping-roles">'
        );
    });
})

$('#addBtn').click(function () {
    let email = $('#emailAdd').val();
    let name = $('#nameAdd').val();
    let password = $('#passwordAdd').val();
    let age = $('#ageAdd').val();
    let roles = $('#rolesAdd').val();
    const userAdd = {
        email: email ,
        name: name,
        password: password,
        age: age,
        roles: roles,
    }
    let url = 'http://localhost:8080/admin/users/'
    $.ajax({
        url: url,
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(userAdd),
        dataType: "json"
    });

});

$('#patchBtn').click(function () {
    let id = $('#idEdit').val()
    let email = $('#emailEdit').val();
    let name = $('#nameEdit').val();
    let password = $('#passwordEdit').val();
    let age = $('#ageEdit').val();
    let roles = $('#rolesEdit').val();

    const userEdited = {
        id: id,
        email: email ,//== null ? userEdit.email : email,
        name: name,// == null ? userEdit.name : name,
        password: password,
        age: age,// == null ? userEdit.age : age,
        roles: roles,// == null ? userEdit.roles : roles
    }

    console.log(userEdited);
    let url = 'http://localhost:8080/admin/users/' + id;

    $.ajax({
        url: url,
        type: 'PATCH',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(userEdited),
        dataType: "json"
    });
});

$('#deleteBtn').click(function () {
    let id = $('#idDelete').val()
    let url = 'http://localhost:8080/admin/users/' + id;
    $.ajax({
        url: url,
        type: 'DELETE'
    });
});

$('#logout').click(function () {
    $.post('/logout');
    window.location.replace('/auth/login');
});

$(document).on('hide.bs.modal', function () {
    updateMainPage();
})

$(document).ready(updateUserDetails());

$(document).ready(updateMainPage());

$(document).on('show.bs.tab', function () {
    updateMainPage();
})

function updateUserDetails() {
    $.get('/user/info', function (userAuthenticated) {
        $('#userDetailsEmail').text(userAuthenticated.email);
        $('#userDetailsRoles').text(userAuthenticated.roles);
        $('#tbodyAuthUserDetails').append(
            '<tr>'
            + '<td scope="col" class="text-center">' + userAuthenticated.id + '</td>'
            + '<td scope="col" class="text-center">' + userAuthenticated.name + '</td>'
            + '<td scope="col" class="text-center">' + userAuthenticated.email + '</td>'
            + '<td scope="col" class="text-center">' + userAuthenticated.age + '</td>'
            + '<td scope="col" class="text-center">' + userAuthenticated.roles + '</td>'
            + '</tr>>'
        )
    })
}

function updateMainPage() {
    $.get('/admin/users', function (allUsers) {
        if (allUsers.length) {
            $('#tbodyAllUsersDetails').empty();
            allUsers.forEach(user => $('#tbodyAllUsersDetails').append(
                '<tr>'
                + '<td scope="col" class="text-center">' + user.id + '</td>'
                + '<td scope="col" class="text-center">' + user.name + '</td>'
                + '<td scope="col" class="text-center">' + user.email + '</td>'
                + '<td scope="col" class="text-center">' + user.roles + '</td>'
                + '<td scope="col" class="text-center">'
                + '<button type="button" class="btn btn-primary" '
                + 'id="editBtn-' + user.id + '" '
                + 'data-toggle="modal" data-target="#editModal">Edit</button>'
                + '</td>'
                + '<td scope="col" class="text-center">'
                + '<button type="button" class="btn btn-danger" '
                + 'id="deleteBtn-' + user.id + '" '
                + 'data-toggle="modal" data-target="#deleteModal">Delete</button>'
                + '</td>'
                + '</tr>>'
            ));
        }
    })
}



