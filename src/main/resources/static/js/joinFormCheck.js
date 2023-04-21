/**
 *  Join Form 검증
 */

$('#userName').blur(idDuplicateCheck);

function idDuplicateCheck(){
   console.log("id 중복 체크 실행")   
   
   $.ajax({
      type:"GET",
      url:"./idDuplicateCheck",
      data:{
         userName:$('#userName').val()
      },
      success:function(result){
         console.log(result)
        
        if(result == 1){
			console.log("사용 가능한 아이디입니다.")
		}else{
			console.log("중복 아이디입니다.")
		}
      },
      error:function(){
         console.log('error')
      }
   })
}



$('#passwordCheck').blur(function(){
	console.log("asfd")
	if($('#password').val() == $('#passwordCheck').val()) {
		console.log("비밀번호가 일치합니다.")		
	}else{
		console.log("비밀번호가 일치 하지 않습니다.")
		
	}
	
})