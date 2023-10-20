$(document).ready(function(){
    $("#formRegister").validate({
        rules:{
            registrationNumber: {
                required: true,
                minlength: 8
            },
              city: {
                required: true
            },
            make: {
                required: true,
            },
            model: {
                required: true
            },
            color: {
                required: true
            },
            ownerId: {
                required: true,
                minlength: 14
            }
        }
    })
})