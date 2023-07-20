<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<style>
#insert {
	width: 250px;
	height: 100px;
	border: 5px solid green;
}

tr {
	border-top: 1px;
}

#list {
	border: 5px solid red;
}

#findone {
	border: 5px solid skyblue;
}

li {
	list-style: none;
}

#updateform {
	border: 5px solid skyblue;
}
</style>
</head>
<body>
	<h1 onclick="location.href='/'">전화번호부 홈</h1>
	<div id="insert">
		<form action="/insert" method="post">
			<input type="text" id="name" name="name" placeholder="이름입력">
			<input type="text" id="hp" name="hp" placeholder="전화번호입력"> <input
				type="text" id="memo" name="memo" placeholder="메모입력"> <input
				type="submit" value="전화번호 입력">
		</form>
	</div>
	<br>

	<div id="search">
		<form action="/search" method="get">
			<table>
				<tr>
					<th colspan="2">전화번호부 검색</th>
				</tr>
				<tr>
					<th colspan="2">검색<input type="text" name="keyword"> <input
						type="submit" value="전송"></th>
				</tr>
				<c:forEach var="pblist" items="${keyword}">
					<tr onclick="location.href='/findone?idx=${pblist.idx}'"
						id="keyword">
						<td id="idx" name="idx" hidden>${pblist.idx}</td>
						<td>이름 : ${pblist.name}</td>
						<td>전화번호 : ${pblist.hp}</td>
						<td>메모 : ${pblist.memo}</td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</div>

	<br>
	<div id="list">
		<table>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>전화번호</th>
				<th>메모</th>
			</tr>
			<c:forEach var="pb" items="${list}">
				<tr onclick="location.href='/findone?idx=${pb.idx}'">
					<td>${pb.idx}</td>
					<td>${pb.name}</td>
					<td>${pb.hp}</td>
					<td>${pb.memo}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<br>
	<br>
	<div id="findone">
		<h1 id="findone_title">상세정보</h1>
		<table>
			<tr>
				<td hidden>${phonebook.idx}</td>
			</tr>
			<tr>
				<th>이름 &nbsp &nbsp &nbsp:</th>
				<td>${phonebook.name}</td>
			</tr>
			<tr>
				<th>전화번호:</th>
				<td>${phonebook.hp}</td>
			</tr>
			<tr>
				<th>메모 &nbsp &nbsp &nbsp:</th>
				<td>${phonebook.memo}</td>
			</tr>
			<tr style="float: left;">
				<td><input type="button" value="수정"></td>
				<td><input type="button" value="삭제"
					onclick="location.href='/delete?idx=${phonebook.idx}'"></td>
			</tr>
		</table>
	</div>

	<form action="/update" id="updateform" hidden>
		<input type="text" id="idx" name="idx" value="${phonebook.idx}"
			hidden>
		<ul>
			<li><h1>책 정보 수정</h1></li>
			<li>이름 &nbsp &nbsp &nbsp: <input type="text" id="name"
				name="name" value="${phonebook.name}"></li>
			<li>전화번호: <input type="text" id="hp" name="hp"
				value="${phonebook.hp}"></li>
			<li>메모 &nbsp &nbsp &nbsp: <input type="text" id="memo"
				name="memo" value="${phonebook.memo}"></li>
			<li><input type="submit" value="저장"> <input
				type="button" value="취소" id="cancel"></li>
		</ul>
	</form>
</body>
<script>
	document.getElementById("findone").addEventListener("click", function() {
		document.getElementById("findone").hidden = true;
		document.getElementById("updateform").hidden = false;
	});
	document.getElementById("cancel").addEventListener("click", function() {
		location.reload();
	});
</script>

</html>