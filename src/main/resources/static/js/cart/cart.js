// 배송지추가에 쓰일 api : problem 
window.addEventListener('DOMContentLoaded',function(){

    const juso = document.querySelector('#juso');
    const user_address = document.querySelector('#addrMain');
    const user_detail_address = document.querySelector('#addrSub'); 
    const juso_search = document.querySelector('.feild');

    juso.addEventListener('click', addr_search);  
    // 검색 클릭 하면 함수 실행

    
    function addr_search(){   //주소 검색 카카오 api 
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById("addrMain").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("addrSub").focus();


                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
                // 예제를 참고하여 다양한 활용법을 확인해 보세요.
                // http://postcode.map.daum.net/guide  api주소 
            }
        }).open();
    }
});

$(function(){ 
    $('.plus').click(function(){ //count up
        var n = $('.plus').index(this);
        var num = $(".num:eq("+n+")").val();
        num = $(".num:eq("+n+")").val(num*1+1); 
        
    });
    $('.minus').click(function(){  // count down..
        var n = $('.minus').index(this);
        var num = $(".num:eq("+n+")").val();
        if (num!=1){
            num = $(".num:eq("+n+")").val(num*1-1); 
        }
    });
});
function check_sel_all(checkbox)  { /*개별 선택에 따른 전체선택상태변경 */
    const selectall = document.querySelectorAll('input[name="checkAll"]');
    const checkboxes = document.querySelectorAll('input[name="checkOne"]');    
    var temp = false;
    var temp2 = true;
    checkboxes.forEach((checkbox) => {
        if (checkbox.checked){
            temp=true;
        }
        if (!checkbox.checked){
            temp2=false;
        }
        if (selectall[0].checked && !checkbox.checked){ //전체 선택이 아닐 경우1
            selectall[0].checked = false;            
            selectall[1].checked = false;
        }
    });
    
    if (temp === false){  //전체 선택이 아닐 경우2
        selectall[0].checked = false;            
        selectall[1].checked = false;
    }  
    
    else if (temp2 === true){ //전체선택일 경우
        selectall[0].checked = true;            
        selectall[1].checked = true;
    }  
    
}

function sel_all(selectAll){ /* 전체선택버튼 활성화 */
    const checkboxes = document.querySelectorAll('input[type="checkbox"]');
    
    checkboxes.forEach((checkbox) => {
        checkbox.checked = selectAll.checked
    });
}

function del_row(ths){
    var ths = $(ths);

    ths.parents("li").remove();
}



function dropup(){ /* 접기 / 펼치기 */
    

    if(document.getElementById('dropup_list').style.display === 'block') {
        document.getElementById('dropup_list').style.display = 'none';
        $(".btn_dropup").addClass('off');
        return;
      } 
    
    
    else{
        document.getElementById('dropup_list').style.display = 'block';
        $(".btn_dropup").removeClass('off');


    }  
}


$(document).ready(function(){ /* 체크박스 선택후 삭제하기 */
    $('.btn_delete').click(function(){
 
      // 현재 체크된 체크박스의 li 정보 얻기
      $("input:checkbox[name=checkOne]").each(function() {
          if (this.checked)
          {
                var ths = $(this);
                ths.parents("li").remove();
          }
      }); 
    });
});
