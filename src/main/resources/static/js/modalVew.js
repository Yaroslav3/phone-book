$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});


<!-- open modal view button add-->
$(document).on("click", "#btn-filter", function () {
    $("#modal-filter").modal("show");
});

<!-- delete -->
$('.table .delBtn').on('click', function (event) {
    event.preventDefault();
    var href = $(this).attr('href');
    $('#deleteModal').find('#delRef').attr('href', href);
    $('#deleteModal').modal("show");
});


<!-- getOne Phone -->
$(document).ready(function () {


    let url = '/updatePhone/';
    let id = "";


    $('.table .editBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');

        console.log(href);

        $.ajax({
            url: href,
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                console.log(data);
                $.each(data, function (i, phone) {
                    $('.myForm #name-name').val(data.name);
                    $('.myForm #surname-surname').val(data.surname);
                    $('.myForm #patronymic-patronymic').val(data.patronymic);
                    $('.myForm #phone-Mobile').val(data.phoneMobile);
                    $('.myForm #phoneHome').val(data.phoneHome);
                    $('.myForm #email').val(data.email);
                    $('.myForm #address').val(data.address);
                    id = data.id;
                });
                $('.myForm #modalEdit').modal("show");
            }
        });


        <!-- update form Phone -->
        $('#modalEdit').find('#btnSaveEdit').on('click', function (event) {
            event.preventDefault();

            var formData = new FormData();

            formData.append('name', $('#name-name').val());
            formData.append('surname', $('#surname-surname').val());
            formData.append('patronymic', $('#patronymic-patronymic').val());
            formData.append('phoneMobile', $('#phone-Mobile').val());
            formData.append('phoneHome', $('#phoneHome').val());
            formData.append('email', $('#email').val());
            formData.append('address', $('#address').val());

            console.log(url + id + " test");


            $.ajax({
                url: url + id,
                headers: 'Content-Type:application/json',
                type: 'POST',
                data: formData,
                dataType: 'json',
                contentType: false,
                processData: false,
                beforeSend: function () {
                    console.log("Request has been sent. Wait for a response")
                },
                complete: function () {
                    console.debug('The request is complete!');
                    alert('status "OK"')
                },
                error: function (req, text, error) {
                    console.error('Ups! Error: ' + text + ' | ' + error);
                }
            });
            $('.myForm-feeder-bulls #modalEdit-feeder-bulls').modal("hide");
            $(document).ajaxStop(function () {
                window.location.reload();
            });
        });
    });
});
