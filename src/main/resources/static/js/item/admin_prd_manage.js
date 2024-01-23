$(document).ready(function() {
  if($('#tblParent tbody tr').length == 0){    //만약에 tbody 의 tr(상품개수)이 0개가 되면
      $('.no_data').css('display', 'block');   //상품이 없습니다의 디스플레이를 block 으로 변경!
  }
});