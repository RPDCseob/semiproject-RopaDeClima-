<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style></style>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<body>
	<form action="color" method="POST">
		<table>
			<tr>
				<th>체크</th>
				<th>색깔</th>
			</tr>
			<c:forEach items="${list}" var="bbs">
				<tr>
					<td><input type="radio" name="color" value=${bbs.color_name}></td>
					<td>${bbs.color_name}</td>
				</tr>
			</c:forEach>
		</table>
		<tr>
			<td colspan="3" class="btn_area">
				<button onclick="topcheck()">상의</button>
				<button onclick="bottomcheck()">하의</button>
				<button onclick="outercheck()">아우터</button>
				<button onclick="dresscheck()">원피스</button>
				<button onclick="shoescheck()">신발</button>
			</td>
		</tr>
	</form>
</body>
<script>
	function topcheck() {
		if (!$("input[name='color']:checked").val()) {
			alert('최소한 하나를 선택하십시오.');
			return false;
		} else {
			var ipc = $("input[name='color']:checked").val();
			$("#topcolor", opener.document).val(ipc); //클래스로 수정해서 배열에 값 넣기 변수에 인덱스 추가 
			window.close();
		}

	}
	function bottomcheck() {
		if (!$("input[name='color']:checked").val()) {
			alert('최소한 하나를 선택하십시오.');
			return false;
		} else {
			var ipc = $("input[name='color']:checked").val();
			$("#bottomcolor", opener.document).val(ipc); //클래스로 수정해서 배열에 값 넣기 변수에 인덱스 추가 
			window.close();
		}

	}
	function outercheck() {
		if (!$("input[name='color']:checked").val()) {
			alert('최소한 하나를 선택하십시오.');
			return false;
		} else {
			var ipc = $("input[name='color']:checked").val();
			$("#outercolor", opener.document).val(ipc); //클래스로 수정해서 배열에 값 넣기 변수에 인덱스 추가 
			window.close();
		}

	}
	function dresscheck() {
		if (!$("input[name='color']:checked").val()) {
			alert('최소한 하나를 선택하십시오.');
			return false;
		} else {
			var ipc = $("input[name='color']:checked").val();
			$("#dresscolor", opener.document).val(ipc); //클래스로 수정해서 배열에 값 넣기 변수에 인덱스 추가 
			window.close();
		}

	}
	function shoescheck() {
		if (!$("input[name='color']:checked").val()) {
			alert('최소한 하나를 선택하십시오.');
			return false;
		} else {
			var ipc = $("input[name='color']:checked").val();
			$("#shoescolor", opener.document).val(ipc); //클래스로 수정해서 배열에 값 넣기 변수에 인덱스 추가 
			window.close();
		}

	}
	
</script>
</html>