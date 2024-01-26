$(() => {
    $("#pwValidation").hide();
    $("#pwCheckResult").hide();

    //비밀번호 유효성 검사 여부
    let validationPw = false;
    //비밀번호 일치 여부
    let confirmPw = false;

    // 비밀번호 유효성 검사 정규식
    const validatePassword = (userPw) => {
        return /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*+=-]).{9,}$/.test(userPw);
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

        if($("#userPw").val() === $("#curUserPw").val()) {
            alert("현재 비밀번호와 동일한 비밀번호로 설정할 수 없습니다.");
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


        $.ajax({
            url: '/user/change',
            type: 'post',
            data: $("#modifyForm").serialize(),
            success: (obj) => {
                alert(obj.item.msg);
                // 화면 새로고침
                location.reload();
            },
            error: (err) => {
                if(err.responseJSON.errorCode === 900) {
                    alert("현재 비밀번호를 잘못 입력하셨습니다.");
                    $("#curUserPw").focus();
                    return;
                } else {
                    alert("알 수 없는 에러입니다. 관리자에게 문의하세요");
                    return;
                }
            }
        });
    });

    $("#resignBtn").on("click", (e) => {
        $.ajax({
            url: '/user/resign',
            type: 'post'
        }).done(function(){
            alert("회원탈퇴 성공");
        }).fail(function(){
            alert("알 수 없는 에러입니다.");
        });
    });
});