(function($){

   // 변수의 중복 , 충돌을 막기 위해서 객체를 사용한다.   
   // 자바스크립트 표준 ECMA Script 5 (ES5) => ECMA Script 2015(ES6) Next 버전
   // 객체 Object(ECMA Script5)  => 객체 Object(ECMA Script6) => 클래스 Class
   // 1. 객체 생성 방법 
   // let Kurly = new Object();  // 생성자 방식 new 키워드가 시작되면
   // 2. let Kurly = {}          // 리터럴 방식

   let Kurly = {    // 객체이름(클래스이름) : 파스칼 케이스 기번 : 첫글자 대문자 중간에 연결단어도 대문자
      init: function(){  //속성 : function(){}  => 속성에 펑션이 붙으면 메서드(Method)라 말한다.

         this.header();
         this.section1();
         this.section2();
         this.section4();
         this.section6();
         this.쿠키가져오학습();   // 로딩시 탑 모달 쿠키 가져오기 getCookie
         this.topModalLocal();   // 탑 모달 : 쿠키 이용 [7일간 안보기]
         this.introModal();      // 인트로 모달 : 로컬스토레이지 이용 [다시 안보기]

         this.quickMenu();       // 퀵메뉴
         this.goTop();           // 고탑버튼

      },
      topModalLocal: function(){ // 탑모달 쿠키
         
         //1. 탑모달 닫기 그리고 쿠키 설정하기  7일간 안보기
         $('.top-modal-close').on({
            click: function(){
               $('#topModal').fadeOut(0);
               let newDate = new Date();// 날짜 객체 생성
               
               // 7일간 설정
               newDate.setDate(newDate.getDate()+7);

               // document.cookie = `쿠키이름=쿠키값;  경로=/;  만료일expires=쿠키설정날짜기한.국제표준시설정;`;
               document.cookie = `KURLYTOPMDAL=mjkurlytopmodal; path=/; expires=${newDate.toUTCString()};`;

            }
         });

         //2. 쿠키 가져오기
         //   쿠키이름, 쿠키값이  일치한 쿠키를 찾는다
         //   만료기한이 남아 있다면 모달 숨긴다.
         //   만료기한이 지나면 모달 보인다.
         function getCookie(){

               if(document.cookie==='') return;

               const cookie = document.cookie.split(';');  // 쿠키를 쎄미콜론 단위로 나누어서 배열처리 저장
               let   arr = [];

               // 배열에 쿠키이름, 쿠키값 속성별 저장하기
               cookie.map((item, idx)=>{
                  arr[idx] = {
                     쿠키이름 : item.split('=')[0].trim(),
                     쿠키값 :   item.split('=')[1].trim()
                  }
               })

            // 쿠키이름, 쿠키값이  일치한 쿠키를 찾는다 
            arr.map((item)=>{
                  // 쿠키이름, 쿠키값 이 있다면(찾았다면) 모달 숨기기
                  if( item.쿠키이름==='KURLYTOPMDAL' && item.쿠키값==='mjkurlytopmodal' ){
                     $('#topModal').fadeOut(0);
                     return;
                  }               
            })
         }
         getCookie();
        

      },
      쿠키가져오학습: function(){

         // 쿠키는 만료기한(Expires) 정한다.
         // 쿠키중 만료기한이 없는 쿠키는 세션쿠키
         // 쿠키이름(Name) / 쿠키값(Value) / 패스(paht=/) / 만료기한(Expires)

         // 쿠키확인(가져오기)하기
         // console.log( document.cookie );

         // 쿠키가져오기 => 배열에저장하기 : ';' 쎄미콜론 단위로 처리해서 배열에 저장하기
         let cookie = document.cookie.split(';');
         // console.log( cookie ); 


         // 가져왔는데 배열값 내용에 공백이 포한되어있다. 문제점 해결 => 공백제거 trim() 내장함수
         // let cookie2 = document.cookie.split(';').trim();  //오류발생 split()함수와 trime() 충돌문제 발생
         // console.log( cookie2 ); 
         // 배열 맵함수 이용 공백제거 그리고 쿠키이름과 = 쿠키값을 분류 저장한다.
         // 객체에 저장     
         // 1234=abc    
         let obj = [];
         cookie.map(function(항목, 색인번호){  //항목 item   / 색인번호  index => 약어 idx
            obj[색인번호] = {
               학교이름: 항목.split('=')[0].trim(),  // asdfasd = 12341234
               학생이름: 항목.split('=')[1]
            }
         });

         // console.log( obj );


         let arr = ['사과','바나나','딸기','자몽','귤','오렌지'];
         // console.log( arr );

         
         arr.map(function(item, idx){
            // console.log( idx, item );
         });


         for(let i=0; i<arr.length; i++){
            // console.log(i, arr[i] );
         }



      },
      introModal: function(){ 
         // 인트로 모달 닫기
         $('#introModal .close-btn').on({
            click:function(e){
               e.preventDefault();
               alert();
               $('#introModal').fadeOut(600);
            }
         });

         // 인트로 모달 닫기
         // 다시는 안보기 : 로컬스토레이지 메로리 사용
         $('#introModal .close-open-none-btn').on({
            click:function(e){
               e.preventDefault();
               alert();
               $('#introModal').fadeOut(600);
               localStorage.setItem('MJKURLYINTROMODAL', 'marketkurlyintomodal');               
            }
         });


         // 웹페이지 열리면 또는 새로고치면
         // 로컬스토레이지 키와 값을 가져오기
         // 그리고 가져온 키와 비교 있다면 모달 숨김
         // 만약에 키가 없다면 열림
         // 키가 없으면 값이 없다 그래서 null
         if( localStorage.getItem('MJKURLYINTROMODAL')!==null ){
            $('#introModal').fadeOut(0); // 인트로 모달창 숨기기
         }
         else{
            $('#introModal').fadeIn(0);  // 인트로 모달창  보이기
         }

        
        

      },
      header: function(){

         // 1. 제이쿼리 $ 충돌 막기 위해서 즉시표현함수식(IIFE) 사용
         // 2. 홈페이지에 각 요소 마다 사용되는 변수가 중복사용 막기위해서 객체(Object)사용
         // 3. 홈페이지에 각 요소 마다 사용되는 선택자 클래스 요소가 공용사용되는 경우를 막기위서 
         //    고유한 아이디를 활용 변수를 지정하여 사용 
         //    요소 선택자(Element Selector) 변수 접두어는 달러사인 사용하여 다른변수와 차별화한다.
         const  $serviceCenterBtn =  $('#header .service-center-btn');
         const  $serviceBox       =  $('#header .service-box');
         const  $mapTootipBtn     =  $('#header .map-tootip-btn');
         const  $mapTooltipBox    =  $('#header .map-tooltip-box');
         const  $topTooltip       =  $('#header .top-tooltip');
         const  $mapTooltipMemnu  =  $('#header .map-tooltip-memnu');

         
         // 고객센터 버튼에 마우스 올리면
         $serviceCenterBtn.on({
            mouseenter: function(){
               $topTooltip.show();
            }
         });

         // 해당칸을 마우스가 떠나면 툴팁메뉴 숨김
         $serviceBox.on({
            mouseleave: function(){
               $topTooltip.hide();
            }
         });

         // 배송지 마우스 오버 이벤트
         $mapTootipBtn.on({
            mouseenter: function(){
               $mapTooltipMemnu.show();
            }
         });

         // 배송지 박스 영역을 떠나면 툴팁메뉴 숨김
         $mapTooltipBox.on({
            mouseleave: function(){
               $mapTooltipMemnu.hide();
            }
         })




         // 윈도우 스크롤 이벤트
         // 현재 스크롤탑값(scrollTop()) 이 100픽셀 이상이면 헤더영역 row3을 고정한다.
         $(window).scroll(function(){
            if( $(window).scrollTop() >= 100 ){
               $('#header .row3').addClass('on');
            }
            else{
               $('#header .row3').removeClass('on');
            }
         });



      },
      section1: function(){
         //0. 가변 변수설정 / 불변변수
         let   cnt               = 0;
         let   setId             = 0;
         const $slideWrap        = $('#section1 .slide-wrap');
         const $currentPage      = $('#section1 .current-page');
         const $slideContainer   = $('#section1 .slide-container');
         const $arrow            = $('#section1 .arrow');
         const $arrowRightBtn    = $('#section1 .arrow-right-btn');
         const $arrowLeftBtn     = $('#section1 .arrow-left-btn');


         
         //1. 메인슬라이드함수
         function mainSlide(){ // 1 ~ 19
            $slideWrap.stop().animate({left: `${-100 * cnt}%` }, 600, 'easeInOutExpo', function(){
               if(cnt>19){cnt=0}   // 끝이면 처음으로 0
               if(cnt< 0){cnt=19}  // 처음이면 끝으로 19
               $slideWrap.stop().animate({left: `${-100 * cnt}%` }, 0); //순간이동 처음으로
            });
            $currentPage.text( cnt+1===21 ? 1 : (cnt+1===0 ? 20 : cnt+1) );

            // 슬라이드 왼쪽 좌표값 변경 상태 확인
            // console.log( $slideWrap.offset().left ); //좌우이동슬라이드
            // console.log( $slideWrap.offset().top );  상하이동슬라이드
         }

         //2. 다음카운트함수
         function nextCount(){
            cnt++; //1 
            mainSlide();
         }
         //2. 이전카운트함수
         function prevCount(){
            cnt--;
            mainSlide();
         }


         //3. 자동타이머함수
         function autoTimer(){
            clearInterval(setId); // 이전 셋팅된 메모리 내용 삭제
            setId=setInterval(nextCount, 4000); // 현재 셋팅된 메모리 실행
         }
         //4. 자동타이머함수실행
         autoTimer();


         //5. 슬라이드 컨테이너에 마우스 이벤트(Mouse Event)
         //   mouseenter: 화살버튼 부드럽게 나타난다. 페이드 인(fadeIn(1000)) / 슬라이드 정지
         //   mouseleave: 화살버튼 기본은 페이드 아웃(fadeOut(1000)) / 슬라이드 플레이
         $slideContainer.on({
            mouseenter: function(){
               $arrow.stop().fadeIn(600);
               clearInterval(setId); // 슬라이드 일시정지
            },
            mouseleave: function(){
               $arrow.stop().fadeOut(600);
               autoTimer(); // 슬라이드 플레이(타이머호출)
            }
         });

         //6-1. 다음화살버튼 클릭이벤트 : 일시 정지된 슬라이드  다음슬라이드
         $arrowRightBtn.on({
            click: function(e){
               e.preventDefault();
               nextCount();
            }
         });

         //6-2. 이전화살버튼 클릭이벤트 : 일시 정지된 슬라이드  이전슬라이드
         $arrowLeftBtn.on({
            click: function(e){
               e.preventDefault();
               prevCount();   
            }
         });


         //7. 터치 스와이프 : 선택자 슬라이드 콘테이터를 이용한다.         
         //   마우스의 터치  시작 위치와 터치가 끝나는 위치를 이용하여
         //   다음슬라이드와 이전슬라이드 판단하여 실행한다.
         let touchStart = null;
         let touchEnd = null;

         let mouseDown = false;  // 마우스 다운 상태를 기억 변수 0

         let dragStart = null;
         let dragEnd = null;
         let winW = 1903;
         
         $slideContainer.on({
            mousedown: function(e){
               // winW = $(window).width();
               winW = $(window).innerWidth();
               // winW = $(window).outerWidth();
               touchStart = e.clientX; // 터치 시작
               dragStart = e.clientX-$slideWrap.offset().left-winW;  // 드래그 시작 - 슬라이드박스.offset().left-winW(창너비)
               mouseDown = true; // 터치 스와이프를 시작한다. 뜻 1
            },
            mouseup: function(e){ // 영역을 떠나고 마우스업을 하면 마우스 업 이벤트가 없다. 그래서 아래에 마우스리브 추가
               touchEnd = e.clientX;
               if( touchStart-touchEnd > 0 ){
                  // 다음슬라이드
                  nextCount();
               }
               if( touchStart-touchEnd < 0 ){
                  // 이전슬라이드
                  prevCount();
               }
               mouseDown = false; // 마우스다운을 초기화
            },                       
            mouseleave: function(e){ // 마우스가 영역을 떠나면 마우스 업 이벤트로 간주한다.
               // 마우스 다운이 아닌상태이면
               // 절대 마우스리브 이벤트가 실행하면안된다.
               if(mouseDown===false) return; //리턴 종료
               
               touchEnd = e.clientX;
               if( touchStart-touchEnd > 0 ){
                  // 다음슬라이드
                  nextCount();
               }
               if( touchStart-touchEnd < 0 ){
                  // 이전슬라이드
                  prevCount();
               }
               mouseDown = false; // 마우스다운을 초기화

            },            
            mousemove: function(e){  // 마우스를 따라다니는 슬라이드 => 드래그 앤 드롭
               // console.log( e );
               if( mouseDown===false ) return;  // 반드시 마우스 다운 상태에서만 드래그 앤 드롭
               dragEnd = e.clientX;  // 드래그 끝

               // 메인슬라이드 박스를 잡고 이동 그리고 놓기 => 드래그 앤 드롭
               $slideWrap.css({left: dragEnd-dragStart });  // 드래그 이동 거리 만큼 이동
            }

         });

         






      },
      section2: function(){
         let cnt=0;
         const $arrowRight       = $('#section2 .arrow-right');
         const $arrowLeft        = $('#section2 .arrow-left');
         const $slideContainer   = $('#section2 .slide-container');
         const $slideWrap        = $('#section2 .slide-wrap');
               

         // 1. 메인슬라이드함수
         mainSlide(); //초기실행
         function mainSlide(){
            $slideWrap.stop().animate({left: `${-100*cnt}%` }, 600,'easeInOutExpo');

            // 좌우 화살 버튼 
            cnt < 1 ? $arrowLeft.stop().fadeOut(300)  : $arrowLeft.stop().fadeIn(300);
            cnt > 3 ? $arrowRight.stop().fadeOut(300) : $arrowRight.stop().fadeIn(300);
         }

         // 2. 다음카운트함수
         function nextCount(){
            cnt++;
            if(cnt>4) cnt=4;
            mainSlide();
         }
         function prevCount(){
            cnt--;
            if(cnt<0) cnt=0;
            mainSlide();
         }

         // 3. 다음버튼클릭이벤트
         $arrowRight.on({
            click: function(e){
               e.preventDefault();
               nextCount();
            }
         });
         $arrowLeft.on({
            click: function(e){
               e.preventDefault();
               prevCount();
            }
         });

         
         
         // 터치 스와이프 & 드래그앤 드롭
         let touchStart = null;
         let touchEnd = null;
         let mouseDown = false;
         let dragStart = null;
         let dragEnd = null;
         let winW = 1903;
         let conW = $slideContainer.innerWidth();
         
         $slideContainer.on({
            mousedown: function(e){
               winW = $(window).innerWidth();
               conW = $slideContainer.innerWidth();
               touchStart = e.clientX;
               // 섹션1 슬라이드 앞뒤 추가된거 그래서 -창너비
               // 섹션2 슬라이드 앞뒤 추가된게 없어요 그래서 창너비 빼면 안된다.
               // 슬라이드박스가 좌우 여백이 있는 경우 + (창너비-슬라이드박스너비)/2
               dragStart = e.clientX-$slideWrap.offset().left + (winW-conW)/2;
               mouseDown = true;
            },
            mouseup: function(e){
               touchEnd = e.clientX;
               if( touchStart-touchEnd > 0 ){
                  nextCount();
               }
               if( touchStart-touchEnd < 0 ){
                  prevCount();
               }
               mouseDown = false;
            },                       
            mouseleave: function(e){
               if(mouseDown===false) return;               
               touchEnd = e.clientX;
               if( touchStart-touchEnd > 0 ){
                  nextCount();
               }
               if( touchStart-touchEnd < 0 ){
                  prevCount();
               }
               mouseDown = false;
            },            
            mousemove: function(e){
               if( mouseDown===false ) return;
               dragEnd = e.clientX;
               $slideWrap.css({left: dragEnd-dragStart });
            }

         });

      },
      section4: function(){
         let cnt=0;
         const $arrowRight       = $('#section4 .arrow-right');
         const $arrowLeft        = $('#section4 .arrow-left');
         const $slideContainer   = $('#section4 .slide-container');
         const $slideWrap        = $('#section4 .slide-wrap');
               

         // 1. 메인슬라이드함수
         mainSlide(); //초기실행
         function mainSlide(){
            $slideWrap.stop().animate({left: `${-100*cnt}%` }, 600,'easeInOutExpo');

            // 좌우 화살 버튼 
            cnt < 1 ? $arrowLeft.stop().fadeOut(300)  : $arrowLeft.stop().fadeIn(300);
            cnt > 3 ? $arrowRight.stop().fadeOut(300) : $arrowRight.stop().fadeIn(300);
         }

         // 2. 다음카운트함수
         function nextCount(){
            cnt++;
            if(cnt>4) cnt=4;
            mainSlide();
         }
         function prevCount(){
            cnt--;
            if(cnt<0) cnt=0;
            mainSlide();
         }

         // 3. 다음버튼클릭이벤트
         $arrowRight.on({
            click: function(e){
               e.preventDefault();
               nextCount();
            }
         });
         $arrowLeft.on({
            click: function(e){
               e.preventDefault();
               prevCount();
            }
         });

         
         
         // 터치 스와이프 & 드래그앤 드롭
         let touchStart = null;
         let touchEnd = null;
         let mouseDown = false;
         let dragStart = null;
         let dragEnd = null;
         let winW = 1903;
         let conW = $slideContainer.innerWidth();
         
         $slideContainer.on({
            mousedown: function(e){
               winW = $(window).innerWidth();
               conW = $slideContainer.innerWidth();
               touchStart = e.clientX;
               // 섹션1 슬라이드 앞뒤 추가된거 그래서 -창너비
               // 섹션2 슬라이드 앞뒤 추가된게 없어요 그래서 창너비 빼면 안된다.
               // 슬라이드박스가 좌우 여백이 있는 경우 + (창너비-슬라이드박스너비)/2
               dragStart = e.clientX-$slideWrap.offset().left + (winW-conW)/2;
               mouseDown = true;
            },
            mouseup: function(e){
               touchEnd = e.clientX;
               if( touchStart-touchEnd > 0 ){
                  nextCount();
               }
               if( touchStart-touchEnd < 0 ){
                  prevCount();
               }
               mouseDown = false;
            },                       
            mouseleave: function(e){
               if(mouseDown===false) return;               
               touchEnd = e.clientX;
               if( touchStart-touchEnd > 0 ){
                  nextCount();
               }
               if( touchStart-touchEnd < 0 ){
                  prevCount();
               }
               mouseDown = false;
            },            
            mousemove: function(e){
               if( mouseDown===false ) return;
               dragEnd = e.clientX;
               $slideWrap.css({left: dragEnd-dragStart });
            }

         });

      },
      section6: function(){
         let cnt=0;
         const $arrowRight       = $('#section6 .arrow-right');
         const $arrowLeft        = $('#section6 .arrow-left');
         const $slideContainer   = $('#section6 .slide-container');
         const $slideWrap        = $('#section6 .slide-wrap');
               

         // 1. 메인슬라이드함수
         mainSlide(); //초기실행
         function mainSlide(){
            $slideWrap.stop().animate({left: `${-100*cnt}%` }, 600,'easeInOutExpo');

            // 좌우 화살 버튼 
            cnt < 1 ? $arrowLeft.stop().fadeOut(300)  : $arrowLeft.stop().fadeIn(300);
            cnt > 3 ? $arrowRight.stop().fadeOut(300) : $arrowRight.stop().fadeIn(300);
         }

         // 2. 다음카운트함수
         function nextCount(){
            cnt++;
            if(cnt>4) cnt=4;
            mainSlide();
         }
         function prevCount(){
            cnt--;
            if(cnt<0) cnt=0;
            mainSlide();
         }

         // 3. 다음버튼클릭이벤트
         $arrowRight.on({
            click: function(e){
               e.preventDefault();
               nextCount();
            }
         });
         $arrowLeft.on({
            click: function(e){
               e.preventDefault();
               prevCount();
            }
         });

         
         
         // 터치 스와이프 & 드래그앤 드롭
         let touchStart = null;
         let touchEnd = null;
         let mouseDown = false;
         let dragStart = null;
         let dragEnd = null;
         let winW = 1903;
         let conW = $slideContainer.innerWidth();
         
         $slideContainer.on({
            mousedown: function(e){
               winW = $(window).innerWidth();
               conW = $slideContainer.innerWidth();
               touchStart = e.clientX;
               // 섹션1 슬라이드 앞뒤 추가된거 그래서 -창너비
               // 섹션2 슬라이드 앞뒤 추가된게 없어요 그래서 창너비 빼면 안된다.
               // 슬라이드박스가 좌우 여백이 있는 경우 + (창너비-슬라이드박스너비)/2
               dragStart = e.clientX-$slideWrap.offset().left + (winW-conW)/2;
               mouseDown = true;
            },
            mouseup: function(e){
               touchEnd = e.clientX;
               if( touchStart-touchEnd > 0 ){
                  nextCount();
               }
               if( touchStart-touchEnd < 0 ){
                  prevCount();
               }
               mouseDown = false;
            },                       
            mouseleave: function(e){
               if(mouseDown===false) return;               
               touchEnd = e.clientX;
               if( touchStart-touchEnd > 0 ){
                  nextCount();
               }
               if( touchStart-touchEnd < 0 ){
                  prevCount();
               }
               mouseDown = false;
            },            
            mousemove: function(e){
               if( mouseDown===false ) return;
               dragEnd = e.clientX;
               $slideWrap.css({left: dragEnd-dragStart });
            }

         });

      },
      quickMenu: function(){
         // console.log( $('#section2 .slide-container').offset().top );

         // 스크롤 탑값이 300 이상이면 고정하는 이벤ㄴ트
         $(window).scroll(function(){
            if( $(window).scrollTop() >=300 ){
               $('#quickMenu').addClass('on');
            }
            else{
               $('#quickMenu').removeClass('on');
            }
         })


      },
      goTop: function(){
         // const sec5Top = $('#section5').offset().top;
         //
         // $(window).scroll(function(){
         //    if( $(window).scrollTop() >= sec5Top ){
         //       $('#goTop').addClass('on');
         //    }
         //    else{
         //       $('#goTop').removeClass('on');
         //    }
         // });

      }
   } 

   // Kurly.section1();  // 객체이름.메서드이름()  객체이름.멤버 
   // Kurly.section2();  // 객체이름.메서드이름()  객체이름.멤버 
   // Kurly.header();  // 객체이름.메서드이름()  객체이름.멤버 
   // Kurly.topModal();  // 객체이름.메서드이름()  객체이름.멤버 

   Kurly.init();  // 객체이름.대표메서드이름()




})(jQuery);