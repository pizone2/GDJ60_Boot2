/**
 *  board Form 유효성 검사
 */

// const submitButton = document.getElementById("submitButton");

// submitButton.addEventListener("click", function(){
// 	console.log("sumbmit button click")
// });
	
/*$("#submitButton").click(function(){
    console.log("submit button clicked")
    if($("#title").val() == "" || $("#id").val() == ""){
        alert("Please enter both title and ID");
    } else {
        alert("Submitted successfully");
        $("#contactForm").submit(); // 폼을 서버로 제출
    }
});*/
$("#submitButton").click(function(){

	if($("#title").val() == "" || $("#id").val() == ""){
		alert("제목과 아이디를 입력해주세요");
	} else{
		alert("Submitted successfully");
        $("#contactForm").submit(); // 폼을 서버로 제출
	}
});




