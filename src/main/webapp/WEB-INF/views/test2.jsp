<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style></style>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<body>
<button onclick="location.href='writeForm'">글쓰기</button>

<a href="profile?mem_num='${mainList.mem_num}'">프로필</a>
<table>
	<thead>
		<tr>
			<th>작성자</th>
			<th>사진</th>
		</tr>
	</thead>
	
	<tbody id="list">
	
	</tbody>
</table>
</body>
<script>
listCall();

function listCall() {
	$.ajax({
		type : 'get',
		url : 'mainlist',
		data : {},
		dataType : 'json',
		success : function(data) {
			console.log(data);
			drawList(data.mainList);
		},
		error : function(e) {
			console.log(e);
		}
	});
}

function drawList(mainList){
	var content = '';
	
	for(i=0; i<mainList.length; i++){
		//console.log(list[i]);
		content += '<tr>';
		content += '<td>'+mainList[i].board_num+'</td>';
		content += '<td><a href="detail?board_num='+mainList[i].board_num+'">'+mainList[i].board_place+'</a></td>';
		content += '</tr>';
	}
	$('#list').empty();
	$('#list').append(content);
}
</script>
</html>