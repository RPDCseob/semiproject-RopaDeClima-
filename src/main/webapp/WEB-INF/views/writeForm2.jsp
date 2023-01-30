<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="resources/common.css" type="text/css">
<style>
.affix {
	top: 20px;
	z-index: 9999 !important;
}

body {
	width: 100%;
	height: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
}

form {
	width: 1200px;
	height: 100vh;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}

form>section {
	width: 100%;
	height: 700px;
	display: flex;
	justify-content: center;
	align-items: center;
}

table {
	width: 100%;
	height: 100%;
	border-collapse: collapse;
}

#cloth_width {
	width: 200px;
}

td {
	border-collapse: collapse;
}

.container{
	height:50px;
	display:flex;
	align-items: center;
}

form>section>article {
	width: 45%;
	height: 100%;
	display: flex;
	flex-direction: column;
	justify-content: flex-start;
}

.sortcode {
	display: none;
}

.right-box {
	width: 100%;
	height: 100%;
	float: right;
	background-color: #F2F2F2;
	border: 1px solid #333333;
}

.right-box>input {
	width: 100%;
	height: 5%;
}

.right-box>img {
	width: 100%;
	height: 95%;
}

.left-box {
	width: 80%;
	height: 100%;
}

img {
	width: 500px;
	height: 100%;
	object-fit: cover;
}
</style>
</head>
<body>

	<!-- <div class="container">
  <div class="row">
    <nav class="col-sm-3">
      <ul class="nav nav-pills nav-stacked" data-spy="affix" data-offset-top="205">
        <li class="active"><a href="#section1">Section 1</a></li>
        <li><a href="#section2">Section 2</a></li>
        <li><a href="#section3">Section 3</a></li>
      </ul>
    </nav>
    <div class="#section2">   
      <h1>Some text to enable scrolling</h1>
      <h1>Some text to enable scrolling</h1>
    </div>
  </div>
</div> -->
	<form action="write" method="POST" enctype="multipart/form-data">
		<input type="text" name="sortcode" value=1 class="sortcode">
		<div class="container">
			<button type="button" class="btn btn-info" data-toggle="collapse"
				data-target="#demo" onclick="top_Call()">상의</button>
			<button type="button" class="btn btn-info" data-toggle="collapse"
				data-target="#demo" onclick="bottom_Call()" value='하의'>하의</button>
			<button type="button" class="btn btn-info" data-toggle="collapse"
				data-target="#demo" onclick="outer_Call()" value='아우터'>아우터</button>
			<button type="button" class="btn btn-info" data-toggle="collapse"
				data-target="#demo" onclick="dress_Call()" value='원피스'>원피스</button>
			<button type="button" class="btn btn-info" data-toggle="collapse"
				data-target="#demo" onclick="shoes_Call()" value='신발'>신발</button>
			<div id="demo" class="collapse">
				<!-- 			<table>
				<tbody id="list">

				</tbody>
			</table> -->
			</div>
		</div>
		<section>
			<div class="left-box">
				<table>
					<tr>
						<th colspan="2" style="width: 300px">장소</th>
						<th>날짜</th>

					</tr>
					<tr>
						<td colspan="2"><input type="text" id="board_place"
							name="board_place"></td>
						<td><input type="date" id="board_date" name="board_date"></td>

					</tr>
					<tr>
						<th>날씨</th>
						<th id="cloth_width">의상</th>
						<th>색상</th>
						<th>의상번호</th>
						<th>의상버튼</th>
					</tr>
					<!-- <tr>
					
						<th><input type="button" name="top" value="상의"
							onclick="top_Call()" /></th>
						<th><input type="button" name="bottom" value="하의"
							onclick="bottom_Call()" /></th>
						<th><input type="button" name="outer" value="아우터"
							onclick="outer_Call()" /></th>
						<th><input type="button" name="dress" value="원피스"
							onclick="dress_Call()" /></th>
						<th><input type="button" name="shoes" value="신발"
							onclick="shoes_Call()" /></th>
					</tr> -->
					<tr>
						<td rowspan="5" id="weatherList"></td>
						<td rowspan="5" id="list" name="cloth" class="writeAreaCloth"></td>
						<td><input type="text" id="topcolor" name="topcolor"
							readonly="readonly"></td>
						<td><input type="text" id="top" name="cloth"
							readonly="readonly"></td>
						<td><input type="button" onclick="topcheck()" value="상의"></td>
					</tr>
					<tr>
						<td><input type="text" id="bottomcolor" name="bottomcolor"
							readonly="readonly"></td>
						<td><input type="text" id="bottom" name="cloth"
							readonly="readonly"></td>
						<td><input type="button" onclick="bottomcheck()" value="하의"></td>
					</tr>
					<tr>
						<td><input type="text" id="outercolor" name="outercolor"
							readonly="readonly"></td>
						<td><input type="text" id="outer" name="cloth"
							readonly="readonly"></td>
						<td><input type="button" onclick="outercheck()" value="아우터"></td>
					</tr>
					<tr>
						<td><input type="text" id="dresscolor" name="dresscolor"
							readonly="readonly"></td>
						<td><input type="text" id="dress" name="cloth"
							readonly="readonly"></td>
						<td><input type="button" onclick="dresscheck()" value="원피스"></td>
					</tr>
					<tr>
						<td><input type="text" id="shoescolor" name="shoescolor"
							readonly="readonly"></td>
						<td><input type="text" id="shoes" name="cloth"
							readonly="readonly"></td>
						<td><input type="button" onclick="shoescheck()" value="신발"></td>
					</tr>

					</tr>
					<tr>
						<td colspan="5" class="btn_area">
							<button id="write">작성완료</button>
						</td>
					</tr>
				</table>
			</div>
			<article>
				<div class="right-box">
					<input type="file" id="photo" name="photo"
						onchange="readURL(this);"> <img id="preview" />
				</div>
			</article>
		</section>
	</form>
</body>
<script>
	weather_listCall();
	/* function test(){
		var test = document.getElementById('top').value;
		console.log(test);
	} */
	function update() {
		$.ajax({
			type : 'get',
			url : 'update',
			data : {},
			dataType : 'json',
			success : function(data) {
				console.log(data);
			},
			error : function(e) {
				console.log(e);
			}
		});
	}
	function weather_listCall() {
		$.ajax({
			type : 'get',
			url : 'weather_listCall',
			data : {},
			dataType : 'json',
			success : function(data) {
				console.log(data);
				weatherList(data.weather_list);
			},
			error : function(e) {
				console.log(e);
			}
		});
	}
	function bottom_Call() {
		$.ajax({
			type : 'get',
			url : 'bottomCall',
			data : {},
			dataType : 'json',
			success : function(data) {
				console.log(data);
				drawList(data.list);
			},
			error : function(e) {
				console.log(e);
			}
		});
	}
	function top_Call() {
		$.ajax({
			type : 'get',
			url : 'topCall',
			data : {},
			dataType : 'json',
			success : function(data) {
				console.log(data);
				drawList(data.list);
			},
			error : function(e) {
				console.log(e);
			}
		});
	}
	function outer_Call() {
		$.ajax({
			type : 'get',
			url : 'outerCall',
			data : {},
			dataType : 'json',
			success : function(data) {
				console.log(data);
				drawList(data.list);
			},
			error : function(e) {
				console.log(e);
			}
		});
	}
	function dress_Call() {
		$.ajax({
			type : 'get',
			url : 'dressCall',
			data : {},
			dataType : 'json',
			success : function(data) {
				console.log(data);
				drawList(data.list);
			},
			error : function(e) {
				console.log(e);
			}
		});
	}
	function shoes_Call() {
		$.ajax({
			type : 'get',
			url : 'shoesCall',
			data : {},
			dataType : 'json',
			success : function(data) {
				console.log(data);
				drawList(data.list);
			},
			error : function(e) {
				console.log(e);
			}
		});
	}

	function drawList(list) {
		var content = '';

		for (i = 0; i < list.length; i++) {
			//console.log(list[i]);
			content += '<tr>';
			/* content += '<td>'+list[i].idx+'</td>'; */
			content += '<td><input type ="radio" name = "cloth" onclick = "popup()"value="'
					+ list[i].cloth_num + '"/></td>';
			content += '<td>'
					+ list[i].cloth_name +'</td>';
			content += '</tr>';
		}
		$('#list').empty();
		$('#list').append(content);
	}

	function weatherList(weather_list) {
		var content = '';
		console.log("weather")

		for (i = 0; i < weather_list.length; i++) {
			//console.log(list[i]);
			content += '<tr>';
			/* content += '<td>'+list[i].idx+'</td>'; */
			content += '<td><input type ="radio" name = "weather" value="'+weather_list[i].weather_num+'"/></td>';
			content += '<td>'
					+ weather_list[i].weather_name + '</td>';
			/* 		content += '<td>'+list[i].cloth_name+'</td>'; */
			/* console.log(list[i].cloth_name); */
			//	var date = new Date(list[i].reg_date); 
			//	content += '<td>'+date.toLocaleDateString('ko-KR')+'</td>'; 
			//	console.log(date);
			//	console.log(date.toLocaleDateString('ko-KR')); 
			/* content += '<td>'+list[i].bHit+'</td>'; */
			content += '</tr>';
		}
		$('#weatherList').empty();
		$('#weatherList').append(content);
	}

	function check() {
		var chkArr = '';

		$('input[type="checkbox"]:checked').each(
				function(cloth_num, cloth_name) {
					console.log(list[i].cloth_num);
					if ($(this).val() != 'on') {
						chkArr.push($(this).val());
					}
				});
		console.log(chkArr);

		$.ajax({
			type : 'GET',
			url : 'write',
			data : {},
			dataType : 'json',
			success : function(data) {
				console.log("test")
				console.log(data);
			},
			error : function(e) {
				console.log(e);
			}
		});
	}
	function popup() {
		console.log("팝업열기");
		var url = "popup.html";
		var name = "popup test";
		var option = "width = 500, height = 550, top = 100, left = 200, location = no"
		window.open(url, name, option);
	}

	function topcheck() {
		if (!$("input[name='cloth']:checked").val()) {
			alert('최소한 하나를 선택하십시오.');
			return false;
		} else {
			var ipc = $("input[name='cloth']:checked").val();
			$("#top", document).val(ipc);

		}

	}
	function bottomcheck() {
		if (!$("input[name='cloth']:checked").val()) {
			alert('최소한 하나를 선택하십시오.');
			return false;
		} else {
			var ipc = $("input[name='cloth']:checked").val();
			$("#bottom", document).val(ipc);

		}

	}
	function outercheck() {
		if (!$("input[name='cloth']:checked").val()) {
			alert('최소한 하나를 선택하십시오.');
			return false;
		} else {
			var ipc = $("input[name='cloth']:checked").val();
			$("#outer", document).val(ipc);

		}

	}
	function dresscheck() {
		if (!$("input[name='cloth']:checked").val()) {
			alert('최소한 하나를 선택하십시오.');
			return false;
		} else {
			var ipc = $("input[name='cloth']:checked").val();
			$("#dress", document).val(ipc);

		}

	}
	function shoescheck() {
		if (!$("input[name='cloth']:checked").val()) {
			alert('최소한 하나를 선택하십시오.');
			return false;
		} else {
			var ipc = $("input[name='cloth']:checked").val();
			$("#shoes", document).val(ipc);

		}

	}

	$("#write").click(function() {

		$board_place = $("#board_place");
		$board_date = $("#board_date");
		$photo = $("#photo");
		$topcolor = $("#topcolor");
		$top = $("#top");
		$bottomcolor = $("#bottomcolor");
		$bottom = $("#bottom");
		$outercolor = $("#outercolor");
		$outer = $("#outer");
		$dresscolor = $("#dresscolor");
		$dress = $("#dress");
		$shoescolor = $("#shoescolor");
		$shoes = $("#shoes");
		$weather = $("input[name='weather']:checked");

		if ($board_place.val() == '') {
			alert('장소를 입력해주세요');
			$board_place.focus();
			return false;
		} else if ($board_date.val() == '') {
			alert('사진날짜를 입력해주세요');
			$board_date.focus();
			return false;
		} else if ($photo.val() == '') {
			alert('사진을 업로드해주세요');
			$photo.focus();
			return false;
		} else if ($weather.val() == null) {
			alert('날씨를 선택해주세요');
			$weather.focus();
			return false;
		} else if ($topcolor.val() == '') {
			alert('상의색상을 입력해주세요');
			$topcolor.focus();
			return false;
		} else if ($bottomcolor.val() == '') {
			alert('하의색상을 입력해주세요');
			$bottomcolor.focus();
			return false;
		} else if ($outercolor.val() == '') {
			alert('아우터색상을 입력해주세요');
			$outercolor.focus();
			return false;
		} else if ($dresscolor.val() == '') {
			alert('원피스색상을 입력해주세요');
			$dresscolor.focus();
			return false;
		} else if ($shoescolor.val() == '') {
			alert('신발색상을 입력해주세요');
			$shoescolor.focus();
			return false;
		} else if ($top.val() == '') {
			alert('상의종류를 선택해주세요');
			$top.focus();
			return false;
		} else if ($bottom.val() == '') {
			alert('하의종류를 선택해주세요');
			$bottom.focus();
			return false;
		} else if ($outer.val() == '') {
			alert('아우터종류를 선택해주세요');
			$outer.focus();
			return false;
		} else if ($dress.val() == '') {
			alert('원피스종류를 선택해주세요');
			$dress.focus();
			return false;
		} else if ($shoes.val() == '') {
			alert('신발종류를 선택해주세요');
			$shoes.focus();
			return false;
		} else {

			console.log('서버로 전송');

			var param = {};
			param.board_place = $board_place.val();
			param.pw = $pw.val();
			param.name = $name.val();
			param.age = $age.val();
			param.gender = $gender.val();
			param.email = $email.val();
			console.log(param);

		}

	});
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				document.getElementById('preview').src = e.target.result;
			};
			reader.readAsDataURL(input.files[0]);
		} else {
			document.getElementById('preview').src = "";
		}
	}
</script>
</html>