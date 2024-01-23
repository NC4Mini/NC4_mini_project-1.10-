window.addEventListener('DOMContentLoaded',function(){ 





    $(document).ready(function() {

       

        var start_price = $('.goods_price .dc_price').text()  // 개당가격 표시할 변수!
        
        var number = $('.inp').val();                  //구매 수량을 조절할 변수 ! (inp클래스 초기 value값은 1로 헸음! 시작 수량이 1이니깐)
        var cost = $('.goods_price input').val(); ;    // 상품의 가격을 설정해줘야됨.  goods_price안에있는 input의 value값에 탬플릿 언어로 반찬 가격을 불러와야됨 
        $('.emph').text((number*cost)/20 + '원 적립');  // 첫 화면의 emph(적립금) 은  반찬가격(cost)의 0.5%로 출력
        $('.price .num').text(start_price);             // 첫 화면의 총 상품금액 (.price 안에 .num) 에다가 초기 가격을 출력!
        $('.emphh').text((number*cost)/20 + '원 적립'); //첫 화면의 윗쪽 1개당 적립금(변하지않는 값) 출력!
    
        

        //수량 조절 함수 시작
        $('.down').click(function(){               // 수량 내리기 버튼을 클릭할 때 발동!  
            if(number >= 0){                        // 만약에 수량이 0보다 많으면 ?
                number--;                          // 목적대로 수량을 1 깍음
                $(".inp").val(number);             // 깍았으니 수량클래스(.inp)의 값을 바꿔줌
                // if(number == 0){                   // 수량이 0 이면 0원 출력!
                //     $(".num").text(0);
                // }

                // else if(number*cost < 1000){       // 만약에 가격x수량 했을때 1000원 이하면?   ( 가격 표시할때 콤마로 구별하기 위해서 넣었음 ex) 1,000   1,000,000 이런식으로)
                //     $(".num").text(number*cost);   // 그냥 가격x수량으로 출력   
                // }


                // else if((number*cost % 1000) == 0){    //만약에 가격x수량이 1000으로 나눴을때 0 이면 (천단위로 끊기위해서임 , 위에서 말했듯이 1,000,000    1,000,000,000 이런식으로)
                    
                //     $(".num").text(Math.floor(number*cost/1000) + ',' + '000');  //가격x수량을 1000으로 나눴을때의 몫이랑  콤마랑 000을 붙여줌 ex)3000원이면 3000/1000의 몫은: 3이니깐 3에다 콤마 붙이고 000을 붙임 --> 3,000
                // }
                // else{    // 그외 나머지는 
                
                // $(".num").text(Math.floor(number*cost/1000) + ',' + number*cost%1000);  // 몫 + 콤마 + 나머지 이렇게해서 표현
                // }
                $(".num").text(comma(number*cost)); // comma = comma찍는 함수 아래에 생성했음
                
            }
    
            if(number < 0){             //0 이하면 false 반환
                return false;
            }
    
    
            $('.emph').text(comma((number*cost)/20) + '원 적립');// 적립금은 항상 가격x수량 x 0.05 해서 표현 하도록 설정
            
        
            
            
        });
    
    
    
        $('.up').click(function(){            // 수량올리기 버튼 클릭하면?
            number++;// 수량을 1 올려줌
            $(".inp").val(number);  // 올렸으니 수량클래스(.inp)의 값을 바꿔줌
            // if((number*cost % 1000) == 0){  //만약에 수량x가격이 1000으로 나눴을떄 0 이면(ex 3000 4000 5000) 몫 + 콤마 + 000 을 붙임
            //     $(".num").text(Math.floor(number*cost/1000) + ',' + '000');
            // }

            // else if(number*cost < 1000){  // 만약에 수량x가격이 1000이하면 그냥 수량x가격으로 추력
            //     $(".num").text(number*cost);
            // }
    
            // else{  // 그외 나머지는 수량x가격의 몫+ 콤마 + 나머지 로 출력
            //     $(".num").text(Math.floor(number*cost/1000) + ',' + number*cost%1000);
                
    
            // }
            $(".num").text(comma(number*cost));
    
    
    
            
            $('.emph').text(comma((number*cost)/20) + '원 적립');  // 마찬가지로 적립금 은 가격x수량x0.05로 출력
            
    
            
        });

        // 이미지 무한 슬라이드 시작
        var imgs;     // 사용할 이미지들
        var img_count;  // 카운트 변수
        
        imgs = $('.__slide-wrapper ul');  // 이미지는 .__slide-wrapper 안에있는 ul태그
        img_count = imgs.children().length;  //slide ul의 자식, 즉 li의 갯수 = 이미지의 갯수




        $('.__slide-go-left').click(function(){  //왼쪽으로 가는 버튼 클릭시 함수실행
            slide_left();
        });

        $('.__slide-go-right').click(function(){ //오른쪽으로 가는 버튼 클릭히 함수 실행
            slide_right();
        });

        function slide_left() {   //왼쪽 슬라이드 함수
            if(img_count > 0){    // 카운트가 0보다 크면 (사실상 계속 큼 일부로 계속 되게 했음 무한슬라이드니깐 ^^)
                $('.__slide-wrapper ul li').last().prependTo('.__slide-wrapper ul');  //마지막 이미지를 처음부분에다가 추가함 
                $('.__slide-wrapper ul').css("left",(-180)+"px");   // 여기서 180은 이미지의 width값 ! 왜냐하면 이미지 값만큼 움직여야 다음 이미지가 다보이니깐?  2칸씩 움직일꺼면 이미지width x 2 값 입력하면됨 
                $('.__slide-wrapper ul').stop().animate({  //속도 500으로 움직이는 animate함수 실행!
                    "left":"0px"
                }, 500, function(){
    
                });
            }
            return false;
        }

        function slide_right() { // 오른쪽 슬라이드 함수  
            if(img_count > 0){
                $('.__slide-wrapper ul').stop().animate({
                    "left": (-180) + "px"
                }, 500, function(){ 
                    $('.__slide-wrapper ul li').first().appendTo('.__slide-wrapper ul'); // 다른부분은 위에랑 같고 이부분은 appendTo(마지막에 추가하는 함수)로 첫번째이미지를 마지막에 추가함
                    $('.__slide-wrapper ul').css("left","0px");
                })
            }
            return false;
        }



       





        function comma(num){                       //콤마찍는 함수
            var len, point, str; 
               
            num = num + ""; 
            point = num.length % 3 ;
            len = num.length; 
           
            str = num.substring(0, point); 
            while (point < len) { 
                if (str != "") str += ","; 
                str += num.substring(point, point + 3); 
                point += 3; 
            } 
             
            return str;
         
        }



        







    });

});