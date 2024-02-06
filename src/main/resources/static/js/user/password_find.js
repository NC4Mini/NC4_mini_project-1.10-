$(()=>{

$('.modal').on('hidden.bs.modal', function (e) {
    console.log('modal close');
    $(this).find('form')[0].reset()
});

$(".btn_type1").click(function () {
    let userEmail = $("#userEmail").val();
    let userId = $("#userId").val();
    let pwFindCheck = false;

    $.ajax({
        type: "POST",
        url: "/user/find-pw",
        data: {
            "userEmail": userEmail,
            "userId": userId,
            pwFindCheck : pwFindCheck }
        }). done(function (obj) {
            if (obj.pwFindCheck === true) {
               if(obj.mailSend) {
                   alert("이메일이 발송되었습니다.");
                   location.href = "/user/login";
               }
            } else {
                alert('아이디와 이메일을 확인해주세요..');
        }});
});

});