<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>

<link rel="stylesheet" href="resources/common.css" type="text/css">
<style></style>
</head>
<body>
	<form action="write" method="POST" enctype="multipart/form-data">
	<table>
	<thead>
		<tr>
			<th>장소</th>
			<td><input type="text" name="board_place"></td>
			<th>날짜</th>
			<td><input type="date" name="board_date"></td>
			<th>사진</th>
			<td><input type="file" name="photo"></td>
		</tr>
		<tr>
		<th><input type="button" name = "top" value= "상의" onclick="top_Call()"/></th>
		<th><input type="button" name = "bottom" value= "하의" onclick="bottom_Call()"/></th>
		<th><input type="button" name = "outer" value= "아우터" onclick="outer_Call()"/></th>
		<th><input type="button" name = "dress" value= "원피스" onclick="dress_Call()"/></th>
		<th><input type="button" name = "shoes" value= "신발" onclick="shoes_Call()"/></th>
		</tr>
		</thead>
		<tbody id = "list">
	
		</tbody>
		<tr>
				<td colspan="3" class="btn_area">
					<button onclick="check()">작성완료</button>
				</td>
		</tr>
	</table>
	

	</form>
</body>
<script>

//listCall();

 function listCall() {
	$.ajax({
		type:'get'
		,url:'listCall'
		,data:{}
		,dataType:'json'
		,success:function(data){			
			console.log(data);
			drawList(data.list);
		}
		,error:function(e){
			console.log(e);
		}
	});
} 
 function bottom_Call() {
	$.ajax({
		type:'get'
		,url:'bottomCall'
		,data:{}
		,dataType:'json'
		,success:function(data){			
			console.log(data);
			drawList(data.list);
		}
		,error:function(e){
			console.log(e);
		}
	});
} 
 function top_Call() {
	$.ajax({
		type:'get'
		,url:'clothCall'
		,data:{}
		,dataType:'json'
		,success:function(data){			
			console.log(data);
			drawList(data.list);
		}
		,error:function(e){
			console.log(e);
		}
	});
} 
 function outer_Call() {
	$.ajax({
		type:'get'
		,url:'outerCall'
		,data:{}
		,dataType:'json'
		,success:function(data){			
			console.log(data);
			drawList(data.list);
		}
		,error:function(e){
			console.log(e);
		}
	});
} 
 function dress_Call() {
	$.ajax({
		type:'get'
		,url:'dressCall'
		,data:{}
		,dataType:'json'
		,success:function(data){			
			console.log(data);
			drawList(data.list);
		}
		,error:function(e){
			console.log(e);
		}
	});
} 
 function shoes_Call() {
	$.ajax({
		type:'get'
		,url:'shoesCall'
		,data:{}
		,dataType:'json'
		,success:function(data){			
			console.log(data);
			drawList(data.list);
		}
		,error:function(e){
			console.log(e);
		}
	});
} 

/* function top_Call(page) {
	$.ajax({
		type:'get'
		,url:'listCall'
		,data:{page:page}
		,dataType:'json'
		,success:function(data){
			console.log(data);
			drawList(data.list);
		}
		,error:function(e){
			console.log(e);
		}
	});
} */

 function drawList(list){
	var content = '';
	
	for(i=0; i<list.length; i++){
		//console.log(list[i]);
		content += '<tr>';
		/* content += '<td>'+list[i].idx+'</td>'; */
 		content += '<td><input type ="radio" name = "cloth" value="'+list[i].cloth_num+'"/></td>'; 
 		content += '<td><a href="javascript:void(0);">'+list[i].cloth_name+'</a></td>'; 
/* 		content += '<td>'+list[i].cloth_name+'</td>'; */ 
		console.log(list[i].cloth_name);
 	//	var date = new Date(list[i].reg_date); 
 	//	content += '<td>'+date.toLocaleDateString('ko-KR')+'</td>'; 
 	//	console.log(date);
	//	console.log(date.toLocaleDateString('ko-KR')); 
		/* content += '<td>'+list[i].bHit+'</td>'; */
 		content += '</tr>';
	}
	$('#list').empty();
	$('#list').append(content);
} 

function check() {
	var chkArr = '';

	$('input[type="checkbox"]:checked').each(function(cloth_num,cloth_name){
		 console.log(list[i].cloth_num);
		if($(this).val() != 'on'){
			chkArr.push($(this).val());
		}
	});
	console.log(chkArr);
	
	$.ajax({
		type:'GET'
		,url:'write'
		,data:{}
		,dataType:'json'
		,success:function(data){
			console.log("test")
			console.log(data);
		}
		,error:function(e){
			console.log(e);
		}
	});
}

</script>
</html>