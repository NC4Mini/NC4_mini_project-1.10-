$(() => {
    $("#pwValidation").hide();
    $("#pwCheckResult").hide();

    //비밀번호 유효성 검사 여부
    let validationPw = false;
    //비밀번호 일치 여부
    let confirmPw = false;
    //비밀번호 인증 여부
    let checkPw = false;

    $("#password_check").on("click", (e) => {
        $.ajax({
            url: '/user/password-check',
            type: 'post',
            data: {curUserPw:$("#curUserPw").val(),
                checkPw:checkPw}
        }).done(function(data){
            console.log(data);
                alert("본인인증이 완료되었습니다.");
                checkPw = true;
        }).fail(function(){
        alert("본인인증을 실패하였습니다.");
        });
    });


    // 비밀번호 유효성 검사 정규식
    const validatePassword = (UserPw) => {
        return /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*+=-]).{9,}$/.test(UserPw);
    }

    // 비밀번호 입력 시 비밀번호 유효성 검사
    $("#userPw").on("change", () => {
        if(validatePassword($("#userPw").val())) {
            validationPw = true;
            $("#pwValidation").hide();
        }else {
            validationPw = false;
            $("#pwValidation").show();
        }

        if($("#userPw").val() === $("#userPwCheck").val()) {
            confirmPw = true;
            $("#pwCheckResult").text("비밀번호가 일치합니다.");
            $("#pwCheckResult").css("color", "green");
        } else {
            confirmPw = false;
            $("#pwCheckResult").text("비밀번호가 일치하지 않습니다.");
            $("#pwCheckResult").css("color", "red");
        }
    });


    // 비밀번호 확인과 비밀번호가 일치하는 지 검사
    $("#userPwCheck").on("change", () => {
        $("#pwCheckResult").show();

        if($("#userPw").val() === $("#userPwCheck").val()) {
            confirmPw = true;
            $("#pwCheckResult").text("비밀번호가 일치합니다.");
            $("#pwCheckResult").css("color", "green");
            return;
        }

        confirmPw = false;
        $("#pwCheckResult").text("비밀번호가 일치하지 않습니다.");
        $("#pwCheckResult").css("color", "red");
    });

    $("#modifyBtn").on("click", (e) => {

        if($("#userPw").val() === "" || $("#userPw").val() === null) {
            validationPw = true;
            confirmPw = true;
            $("#pwValidation").hide();
            $("#pwCheckResult").hide();
        }

        if(checkPw == false) {
            alert("비밀번호 인증을 완료하세요.");
            return;
        }

        // pw 유효성 검사가 틀렸을 때
        if(!validationPw) {
            alert("비밀번호는 영문자, 숫자, 특수문자 조합의 9자리 이상으로 설정하세요.");
            $("#userPw").focus();
            return;
        }

        // pw가 일치하지 않을 때
        if(!confirmPw) {
            alert("비밀번호가 일치하지 않습니다.");
            $("#userPwCheck").focus();
            return;
        }

        const formData = new FormData($("#modifyForm")[0]);

        const addrArr = [];

        addrArr.push({
            addrZone: $("#addrZone").val(),
            addrBasic: $("#addrBasic").val(),
            addrDetail: $("#addrDetail").val()
        });

        formData.append("addrList", JSON.stringify(addrArr));

        $.ajax({
            url: '/user/change',
            type: 'post',
            data: formData,
            cache: false,
            contentType: false,
            processData: false
        }).done(function(){
            alert("회원정보를 변경하였습니다.");
        }).fail(function(){
            alert("비밀번호가 다릅니다.");
        });
    });

    $("#resignBtn").on("click", (e) => {
        if(checkPw == false) {
            alert("비밀번호 인증을 완료하세요.");
            return;
        }

        $.ajax({
            url: '/user/resign',
            type: 'post'
        }).done(function(){
            alert("회원탈퇴 성공");
            location.href = "/";
        }).fail(function(){
            alert("알 수 없는 에러입니다.");
        });
    });

    // 주소 API 적용
    $("#addressSearch").on("click", () => {
        new daum.Postcode({
            oncomplete: function (data) {
                var addr = '';
                if (data.userSelectedType === 'R') {
                    addr = data.roadAddress;
                } else {
                    addr = data.jibunAddress;
                }
                $("#addrZone").val(data.zonecode);
                $("#addrBasic").val(addr);
                $("#addrDetail").val("").focus();

            }
        }).open();
    });

});