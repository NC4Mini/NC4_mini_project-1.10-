


$(document).ready(function(){
    var emoney = Number($('.possess .emph').text());

    

    $('.possess .emph').text(comma(emoney));
    
    //적립금 사용
    $(".emoney_chkbox").click(function(){
        if (emoney < 1000){
            alert("적립금은 1천원 이상부터 사용 가능합니다")
        }
        else{
            $(".emoney_reg input").val(comma(emoney));
            $("#paper_reserves").text(" - " + comma(emoney)+ " 원");
            $(".emoney_point").val(emoney);
            product_price();
        }
        
    })

    $("#emoney").blur(function(){  
        input_emoney = Number($("#emoney").val());
        if (input_emoney < 1000){
            alert("적립금은 1천원 이상부터 사용 가능합니다");
            $('#emoney').val("");
        }
        
        else if (input_emoney > emoney){
            alert("보유하신 적립금의 값을 초과하였습니다");
            $("#emoney").val(0);
        }      
        else{
            $("#emoney").val(comma(input_emoney));
            $("#paper_reserves").text(" - " + comma(input_emoney)+ " 원");
            $(".emoney_point").val(input_emoney);
            product_price();
        }
        
    });

    function product_price(){
        var product_cost = Number($(".info_price .cost").val());  //주문가격
        $("#productsTotalPrice").text(comma(product_cost));
        var delivery_cost = 0;
        if (product_cost >= 50000){
            $("#paper_delivery").text("0");
            delivery_cost = 0;
        }
        else{
            $("#paper_delivery").text("3,000");
            delivery_cost = 3000;
        }

        var emoney_point = Number($(".emoney_point").val());

        $("#paper_settlement").text(comma(product_cost + delivery_cost - emoney_point));

        $("#product_price_value").val(product_cost + delivery_cost - emoney_point);
    }


    

    product_price();
    
});


function comma(num){          //콤마찍는 함수
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

