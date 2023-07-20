<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>

</head>
<body>
<div id="insert">
<form action="/insert" method="post">
<input type="text" id="name" name="name" placeholder="이름입력">
<input type="text" id="hp" name="hp" placeholder="전화번호입력">
<input type="text" id="memo" name="memo" placeholder="메모입력">
<input type="submit" value="전화번호 입력">
</form>
</div>
<br>

<div id="list">
<table border="1">
<tr><th>번호</th><th>이름</th><th>전화번호</th><th>메모</th></tr>
<c:forEach var="pb" items="${list}">
<tr><td onclick="findonebyid(${pb.idx})">${pb.idx}</td><td>${pb.name}</td><td>${pb.hp}</td><td>${pb.memo}</td></tr>
</c:forEach>
</table>
</div>
<br>
<div id="list2"></div>
<br>
<div id="findone">
</div>
</body>
<script>
$('#list2').load('/findAll2')
/* $.ajax({
	url:'/findAll',
	success:function(result){
		$('#list').val(result);
	}
}); */
function findonebyid(idx){
	$.ajax({
		url:'/findone',
		contentType: 'application/x-www-form-urlencoded',
		data:{"idx":idx},
		dataType:'json',
		success:function(result){
			//String에서 객체로 넘어올경우 object로 결과를 수신한다.
			//console.log(result);//객체아님
			var content = `번호:\${result.idx}<br>이름:\${result.name}<br>전화번호:\${result.hp}<br>메모:\${result.memo}<br>`
			console.log(content);
			$('#findone').html(content);
			
		}
	});
}
</script>
</html>