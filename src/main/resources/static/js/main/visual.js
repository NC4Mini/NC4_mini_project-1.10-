'use strict';

// const ul = document.querySelector('.promotions'); //html에서 ul을 가져옴
// const firstIndexClone = ul.firstElementChild.cloneNode(true); //index1의 복사본
// ul.appendChild(firstIndexClone); //ul의 가장 뒤에 복사본을 붙임

// // visual.css의 ul의 총 너비를 4200에서 5250으로 변경

// function move() {
	
// 	let curIndex = 0; //current Index

// 	setInterval(function(){ //setInterval  = 일정 간격으로 코드를 반복 실행하는 함수, {코드}, 시간간격(ms)
		
// 		var size = 1900;
// 		if ($(window).width() <= 1900){
// 			size = $(window).width()
// 		}
// 		ul.style.transition = '0.6s'; //0.2초에 걸쳐 객체 변화
// 		ul.style.transform = "translate3d(-"+size*(curIndex+1)+"px, 0px, 0px)"; //translate3d = 현재위치에서 x, y, z축의 거리만큼 이동

// 		curIndex++; //index	1씩 증가

// 		//언제까지?
// 		if(curIndex === 5) { // 현재 index가 4인 경우,
// 			setTimeout(function(){ //시간지연함수 실행(일정 시간 후 특정 함수/코드를 지연한 뒤 실행)
// 				ul.style.transition = '0s'; //transition 삭제
// 				ul.style.transform = "translate3d(0px, 0px, 0px)"; //현재 위치에서 이동 없음
// 			}, 601) //복사본이 자리잡은 0.601초 뒤 transition, transform 끔
// 			curIndex = 0; //index 1로 되돌림
// 		} // 

// 	}, 3000); // 3초 간격 {코드} 실행
// }

 
$(document).ready(function(){                                  // 브라우저가 로딩이 됐을때 아래 코드 실행!

	if($(window).width() <= 1900 && $(window).width() > 1050){     // 만약의 현재 브라우저 창의 크기가 1900보다 작고 1050보다 크면
		$('#visual').css('width', $(window).width());              // visual의 width 값을 현재 브라우저랑 맞춤

		$('.swipe .slide_list').css('width', $(window).width());   // .slide_list의 width값도 현재 브라우저랑 맞춤
	}
	else if($(window).width() <= 1050){                            // 현재 브라우저 창의 크기가 1050보다 작으면?
		$('#visual').css('width', 1050+"px");                      // visual, slide_list의 크기를 1050으로 고정

		$('.swipe .slide_list').css('width', 1050+"px");
	}
	else if($(window).width() > 1900){                             // 현재 브라우저 창의 크기가 1900보다 크다면!?
		$('#visual').css('width', 1900+"px");                      // visual, slide_list의 크기를 1900으로 고정

		$('.swipe .slide_list').css('width', 1900+"px");
	}

	$(window).resize(function() {                                     // 현재 브라우저 창의 크기를 조절할때마다 아래 코드 실행!
		 
		if($(window).width() <= 1900 && $(window).width() > 1050){      // 위에와 똑같은 코드 반복!
			$('#visual').css('width', $(window).width()); 
	
			$('.swipe .slide_list').css('width', $(window).width());
		}
		else if($(window).width() <= 1050){
			$('#visual').css('width', 1050+"px");
	
			$('.swipe .slide_list').css('width', 1050+"px");
		}
		else if($(window).width() > 1900){
			$('#visual').css('width', 1900+"px");
	
			$('.swipe .slide_list').css('width', 1900+"px");
		}
	
	

	});
	

		var $banner = $(".swipe").find("ul");

		var $bannerWidth = $banner.children().outerWidth();//배너 이미지의 폭
		// var $bannerLength = $banner.children().length;//배너 이미지의 갯수
		var rollingId;

		//정해진 초마다 함수 실행
		rollingId = setInterval(function() { rollingStart(); }, 3000);//다음 이미지로 롤링 애니메이션 할 시간차

		//마우스 오버시 롤링을 멈춘다.
		$banner.mouseover(function(){
			//중지
			clearInterval(rollingId);
			$(this).css("cursor", "pointer");
		});
		//마우스 아웃되면 다시 시작
		$banner.mouseout(function(){
			rollingId = setInterval(function() { rollingStart(); }, 3000);
			$(this).css("cursor", "default");
		});
		
		function rollingStart() {
			// $banner.css("width", $bannerWidth * $bannerLength + "px");
			
			//배너의 좌측 위치를 옮겨 준다.
			$banner.animate({left: - $bannerWidth + "px"}, 1500, function() { //숫자는 롤링 진행되는 시간이다.
				// //첫번째 이미지를 마지막 끝에  추가한다.
				
				
				$('.swipe ul li').first().appendTo('.swipe ul');
				//다음 움직임을 위해서 배너 좌측의 위치값을 초기화 한다.
				$(this).css("left", 0);
				//이 과정을 반복하면서 계속 롤링하는 배너를 만들 수 있다.
			});
		}



	
	
});