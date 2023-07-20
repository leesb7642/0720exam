<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
  <script>
 function send(){
	 $("div").load("/form.html");
 }
 $(function(){
	 $("#btn").click(function(){
		 $("div").load("/test.txt");
	 })
 });
 
  </script>
</head>
<body>
<input type="button" onclick="send()" value="basic ajax실행"> <br>
<input type="button" id="btn" value="jquery ajax실행"><br>
<br>
<form action="/insert" method="post">
<input type="text" id="name" name="name" placeholder="이름입력">
<input type="text" id="hp" name="hp" placeholder="전화번호입력">
<input type="text" id="memo" name="memo" placeholder="메모입력">
<input type="submit" value="전화번호 입력">
</form>
<!-- 전송을 위한 두가지 방법 form태그(페이지 새로고침) ,ajax(현재페이지 유지) -->

<div id="list">

</div>
</body>
<script>
$('#list').load('/findAll')
/* $.ajax({
	url:'/findAll',
	success:function(result){
		$('#list').val(result);
	}
}); */
</script>
</html>