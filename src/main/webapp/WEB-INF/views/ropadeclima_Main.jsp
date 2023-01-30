<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<style>
#logo {
	border: solid gray;
	width: 120px;
	height: 72px;
}

.logodiv {
	text-align: center;
}

table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
	padding: 5px 10px;
}

img {
	width: 200px;
	height: 170px;
	padding: 5px 10px;
	padding-bottom: 0px;
}

#list {
	float: right;
	background-color: gray;
	margin-right: 25%;
	padding: 0px;
	width: 820px;
}

.navbar-inverse {
	height: 72px;
	background-color: gray;
}

.show {
	height: 30px;
	width: 100%;
	float: left;
	margin-left: 300px;
}

.filter {
	float: right;
	margin-right: 400px;
	width: 220px;
	height: 30px;
}

.weatherTable {
	float: left;
	background-color: lime;
}

#weather {
	float: left;
	width: 350px;
}
</style>

<body>
	<nav class="navbar-inverse">
		<img id="logo" src="resources/logos/logo.png"> <a
			href="profile?mem_num='${detail_member.mem_num}'">프로필</a>
	
	
	<c:if test="${sessionScope.loginId == ''}">
		<button class="login">로그인</button>
	</c:if>
	<c:if test="${sessionScope.loginId != ''}">
		<button class="logOut">로그아웃</button>
	</c:if>
	
	</nav>
	<h1>날씨</h1>
	<h2 class="api_weather">${SKY.fcstValue}</h2>
	<button class="allShow">전체조회</button>
	<button class="newOrder">최신순</button>
	<button class="like">인기순</button>


	<div id="weather">
		<aside>
			<table>
				<tr>
					<th id="fcstDate">오늘 날짜</th>
					<td>${TMX.fcstDate}</td>
				</tr>
				<tr>
					<th id="TMX">최고 온도</th>
					<td>${TMX.fcstValue}</td>
				</tr>
				<tr>
					<th id="TMN">최저 온도</th>
					<td>${TMN.fcstValue}</td>
				</tr>
			</table>
		</aside>
	</div>

	<div id="list"></div>
	<!-- ================================= -->
	<form action="/submit" method="post" enctype="multipart/form-data"
		id="requestForm">
		<div class="option_sex">
			<h6>성별</h6>
			<input type="checkbox" name="sex" value="남" id="man"
				style="display: none"> <input type="button" value="남자"
				id="manBtn"> <input type="checkbox" name="sex" value="여"
				id="woman" style="display: none"> <input type="button"
				value="여자" id="womanBtn">
		</div>
		
		<div class = "weather">
		</div>
		
		<div class="option_season">
			<h6>계절</h6>
			<input type="checkbox" name="season" value="1" id="spring"
				style="display: none"> <input type="button" value="봄"
				id="springBtn"> <input type="checkbox" name="season"
				value="2" id="summer" style="display: none"> <input
				type="button" value="여름" id="summerBtn"> <input
				type="checkbox" name="season" value="3" id="fall"
				style="display: none"> <input type="button" value="가을"
				id="fallBtn"> <input type="checkbox" name="season" value="4"
				id="winter" style="display: none"> <input type="button"
				value="겨울" id="winterBtn">
		</div>
		<div class="option_age">
			<h6>연령</h6>
			<input type="checkbox" name="age" value="1" id="10"
				style="display: none"> <input type="button" value="10대"
				id="10Btn"> <input type="checkbox" name="age" value="2"
				id="20" style="display: none"> <input type="button"
				value="20대" id="20Btn"> <input type="checkbox" name="age"
				value="3" id="30" style="display: none"> <input
				type="button" value="30대" id="30Btn"> <input type="checkbox"
				name="age" value="4" id="40" style="display: none"> <input
				type="button" value="40대" id="40Btn"> <input type="checkbox"
				name="age" value="5" id="50" style="display: none"> <input
				type="button" value="50대+" id="50Btn">
		</div>
		<div class="option_term">
			<h6>기간</h6>
			<select id="term" name="term">
				<option disabled selected>기간선택</option>
				<option value="1">한달</option>
				<option value="3">3개월</option>
				<option value="6">6개월</option>
				<option value="12">1년 이상</option>
			</select>
		</div>
		<div>
			<h6>옷</h6>
			<table>
				<tbody>
				<thead>
					<tr>
						<th>옷</th>
					</tr>
				</thead>
				<tr>
					<td rowspan="5" id="Cloth_top" name="top" class="Cloth_top"></td>
				</tr>
				</tbody>
			</table>
		</div>


		<a href="javascript:submit();" id=serch>검색</a>
</body>
<script>
	var sky = ${SKY.fcstValue};
	console.log(sky);
	var tmx = ${TMX.fcstValue};
	var tmn = ${TMN.fcstValue};
	var msg = "조회된 기록이 없습니다.";
	var base_date = ${TMN.fcstDate};
recommend_list();
api();
weather_Call();
top_Call();

	

	// 추천하기
	function recommend_list() {
		$.ajax({
			type : 'GET'
			,url : 'recommend'
			,data : {'tmn' : tmn, 'tmx' : tmx}
			,dataType : 'JSON'
			,success : function (data) {
				drawList(data);
			}
			,error : function (e) {
				console.log(e);
			}
		});
	}

	function drawList(data) {
		var content = '';
		for(var i=0; i<data.fileList.length; i++){
			content += '<div style = "float:left">';
			content += '<a href="detail?board_num = ' + data.fileList[i].board_num+ '"><img id="readPhoto" src="/photo/'+data.fileList[i].newPhoto_name+'"></a>';
			content += '<div>';	
			if(i%4 == 0){
				if(i == 0){
					continue;
				}
				content += '<br>';
			}
		}
		$('#list').empty();
		$('#list').append(content);
	}
	//전체조회
	function allShow_list(data) {

		var content = '';
		var area = '';
		console.log(data.fileList);
		for (var i = 0; i < data.fileList.length; i++) {
			content += '<div style = "float:left">';
			content += '<a href="detail?board_num='
					+ data.fileList[i].board_num
					+ '"><img id="readPhoto" src="/photo/'+data.fileList[i].newPhoto_name+'"></a>';
			content += '</div>';
			if (i % 4 == 0) {
				if (i == 0) {
					continue;
				}
				content += '<br>';
			}
		}

		console.log(content);
		$('#list').empty();
		$('#list').append(content);
	}

	//최신순 조회
	function newOrder(data) {

		var content = '';
		var area = '';
		console.log(data);
		for (var i = 0; i < data.new_fileList.length; i++) {
			content += '<div style = "float:left">';
			content += '<a href="detail?board_num='
					+ data.fileList[i].board_num
					+ '"><img id="readPhoto" src="/photo/'+data.fileList[i].newPhoto_name+'"></a>';
			content += '</div>'
			if (i % 4 == 0) {
				content += '<br>';
				if (i == 0) {
					continue;
				}
			}
		}
		console.log(content);
		$('#list').empty();
		$('#list').append(content);
	}

	//인기순 조회
	function likeList(data) {
		var content = '';
		var area = '';
		for (var i = 0; i < like.length; i++) {
			content += '<div style = "float:left">';
			content += '<a href="detail?board_num='
					+ data.fileList[i].board_num
					+ '"><img id="readPhoto" src="/photo/'+data.fileList[i].newPhoto_name+'"></a>';
			content += '</div>'
			if (i % 4 == 0) {
				content += '<br>';
			}
		}
		console.log(content);
		$('#list').empty();
		$('#list').append(content);
	}
	// 4의 배수마다 br 태그 삽입
	function api() {
		$.ajax({
				type : 'GET',
				url : 'http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?serviceKey=0C0KxQlchEfYKRkvh2LGG%2BA1EWgJYxjN9yNA1TOUc94OOtJV9yHRv8a0yJj5DnyLaR%2F3qbtacF808BhBY8tr3w%3D%3D&pageNo=1&numOfRows=1000&dataType=JSON&base_date='+base_date+'&base_time=0500&nx=37&ny=126',
				data : {},
				dateType : 'JSON',
				success : function(data) {
					console.log(data);
					},
					error : function(e) {
						console.log(e);
					}
				});
	}
	$(function() {
		$('.allShow').click(function() {
			$.ajax({
				type : 'GET',
				url : 'allShow',
				data : {},
				dataType : 'JSON',
				success : function(data) {
					console.log(data);
					allShow_list(data);
				},
				error : function(e) {
					console.log(e);
				}

			});
		});
	});

	$(function() {
		$('.newOrder').click(function() {
			$.ajax({
				type : 'GEt',
				url : 'newOrder',
				data : {},
				dtataType : 'JSON',
				success : function(data) {
					console.log(data);
					allShow_list(data);
				},
				error : function(e) {
					console.log(e);
				}
			});
		});
	});

	$(function() {
		$('.allShow').click(function() {
			if ($(this).text() == '전체조회') {
				$(this).text('추천하기');
			} else {
				$(this).text('전체조회');
				if ($(this).text() == '전체조회') {
					recommend_list();
				}
			}
		});
	});

	$(function() {
		$('.like').click(function() {
			$.ajax({
				type : 'GET',
				url : 'like',
				data : {},
				dataType : 'JSON',
				success : function(data) {
					console.log(data);
					allShow_list(data);
				},
				error : function(e) {
					console.log(e);
				}
			});
		});
	});

	// 날씨 알림
	$(function() {
		if ($('.api_weather').text() == 4) {
			$('.api_weather').text('흐림');
		}
		if ($('.api_weather').text() == 3) {
			$('.api_weather').text('구름많음');
		}
		if ($('.api_weather').text() == 1) {
			$('.api_weather').text('맑음');
		}
	});

	$(function() {
		$('.login').click(function() {
			$.ajax({
				type : 'GET',
				url : 'mainLogin',
				data : {},
				success : function() {
					console.log('move the login');
					moveLogin();
				},
				error : function(e) {
					console.log(e);
				}
			});
		});
	});

	function moveLogin() {
		$('.login').click(function() {
			location.href = 'mainLogin';
		});
	}
	/* ===================================================================== */
	

	function submit() {
		var ArrayList = $('#requestForm').serialize();

		$.ajax({
			url : '/project/submit',
			type : 'post',
			dataType : 'json',
			data : ArrayList,
			success : function(data) {
				console.log("submit data ==== ", data);
				var html = "";

				for ( var i in data.ArrayList) {
					html += '<div style = "float:left">';
					html += '<a href="detail?board_num='
							+ data.ArrayList[i].board_num
							+ '"><img id="readPhoto" src="/photo/'+data.ArrayList[i].newPhoto_name+'"></a>';
					html += '</div>'
					if (i % 4 == 0) {
						html += '<br>';
					}
				}
				$('#list').empty();
				$('#list').append(html);
					}
				});
	}

	$("#manBtn").click(
			function() {
				if ($("#man").prop("checked") == false) {
					$("#man").prop("checked", true);
					$("#manBtn").css("background-color", "#B2EBF4").css(
							"font-weight", "bold");
				} else {
					$("#man").prop("checked", false);
					$("#manBtn").css("background-color", "white").css(
							"font-weight", "normal");
				}
			});

	$("#womanBtn").click(
			function() {
				if ($("#woman").prop("checked") == false) {
					$("#woman").prop("checked", true);
					$("#womanBtn").css("background-color", "#B2EBF4").css(
							"font-weight", "bold");
				} else {
					$("#woman").prop("checked", false);
					$("#womanBtn").css("background-color", "white").css(
							"font-weight", "normal");
				}
			});

	$("#clearBtn").click(
			function() {
				if ($("#clear").prop("checked") == false) {
					$("#clear").prop("checked", true);
					$("#clearBtn").css("background-color", "#B2EBF4").css(
							"font-weight", "bold");
				} else {
					$("#clear").prop("checked", false);
					$("#clearBtn").css("background-color", "white").css(
							"font-weight", "normal");
				}
			});

	$("#cloudyBtn").click(
			function() {
				if ($("#cloudy").prop("checked") == false) {
					$("#cloudy").prop("checked", true);
					$("#cloudyBtn").css("background-color", "#B2EBF4").css(
							"font-weight", "bold");
				} else {
					$("#cloudy").prop("checked", false);
					$("#cloudyBtn").css("background-color", "white").css(
							"font-weight", "normal");
				}
			});

	$("#snowBtn").click(
			function() {
				if ($("#snow").prop("checked") == false) {
					$("#snow").prop("checked", true);
					$("#snowBtn").css("background-color", "#B2EBF4").css(
							"font-weight", "bold");
				} else {
					$("#snow").prop("checked", false);
					$("#snowBtn").css("background-color", "white").css(
							"font-weight", "normal");
				}
			});

	$("#rainBtn").click(
			function() {
				if ($("#rain").prop("checked") == false) {
					$("#rain").prop("checked", true);
					$("#rainBtn").css("background-color", "#B2EBF4").css(
							"font-weight", "bold");
				} else {
					$("#rain").prop("checked", false);
					$("#rainBtn").css("background-color", "white").css(
							"font-weight", "normal");
				}
			});

	$("#springBtn").click(
			function() {
				if ($("#spring").prop("checked") == false) {
					$("#spring").prop("checked", true);
					$("#springBtn").css("background-color", "#B2EBF4").css(
							"font-weight", "bold");
				} else {
					$("#spring").prop("checked", false);
					$("#springBtn").css("background-color", "white").css(
							"font-weight", "normal");
				}
			});

	$("#summerBtn").click(
			function() {
				if ($("#summer").prop("checked") == false) {
					$("#summer").prop("checked", true);
					$("#summerBtn").css("background-color", "#B2EBF4").css(
							"font-weight", "bold");
				} else {
					$("#summer").prop("checked", false);
					$("#summerBtn").css("background-color", "white").css(
							"font-weight", "normal");
				}
			});

	$("#fallBtn").click(
			function() {
				if ($("#fall").prop("checked") == false) {
					$("#fall").prop("checked", true);
					$("#fallBtn").css("background-color", "#B2EBF4").css(
							"font-weight", "bold");
				} else {
					$("#fall").prop("checked", false);
					$("#fallBtn").css("background-color", "white").css(
							"font-weight", "normal");
				}
			});

	$("#winterBtn").click(
			function() {
				if ($("#winter").prop("checked") == false) {
					$("#winter").prop("checked", true);
					$("#winterBtn").css("background-color", "#B2EBF4").css(
							"font-weight", "bold");
				} else {
					$("#winter").prop("checked", false);
					$("#winterBtn").css("background-color", "white").css(
							"font-weight", "normal");
				}
			});

	$("#10Btn").click(
			function() {
				if ($("#10").prop("checked") == false) {
					$("#10").prop("checked", true);
					$("#10Btn").css("background-color", "#B2EBF4").css(
							"font-weight", "bold");
				} else {
					$("#10").prop("checked", false);
					$("#10Btn").css("background-color", "white").css(
							"font-weight", "normal");
				}
			});

	$("#20Btn").click(
			function() {
				if ($("#20").prop("checked") == false) {
					$("#20").prop("checked", true);
					$("#20Btn").css("background-color", "#B2EBF4").css(
							"font-weight", "bold");
				} else {
					$("#20").prop("checked", false);
					$("#20Btn").css("background-color", "white").css(
							"font-weight", "normal");
				}
			});

	$("#30Btn").click(
			function() {
				if ($("#30").prop("checked") == false) {
					$("#30").prop("checked", true);
					$("#30Btn").css("background-color", "#B2EBF4").css(
							"font-weight", "bold");
				} else {
					$("#30").prop("checked", false);
					$("#30Btn").css("background-color", "white").css(
							"font-weight", "normal");
				}
			});

	$("#40Btn").click(
			function() {
				if ($("#40").prop("checked") == false) {
					$("#40").prop("checked", true);
					$("#40Btn").css("background-color", "#B2EBF4").css(
							"font-weight", "bold");
				} else {
					$("#40").prop("checked", false);
					$("#40Btn").css("background-color", "white").css(
							"font-weight", "normal");
				}
			});

	$("#50Btn").click(
			function() {
				if ($("#50").prop("checked") == false) {
					$("#50").prop("checked", true);
					$("#50Btn").css("background-color", "#B2EBF4").css(
							"font-weight", "bold");
				} else {
					$("#50").prop("checked", false);
					$("#50Btn").css("background-color", "white").css(
							"font-weight", "normal");
				}
			});

	function weather_Call() {
		$.ajax({
			type : 'get',
			url : 'weatherCall',
			data : {},
			dataType : 'json',
			success : function(data) {
				console.log(data);
				weatherList(data.weatherlist);
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
				topList(data.list);
			},
			error : function(e) {
				console.log(e);
			}
		});
	}

	function weatherList(weatherlist) {
		var content = '';
		for (i = 0; i < weatherlist.length; i++) {
			content += '<tr>';
			content += '<td><input type ="checkbox" name = "weather" onclick = "popup()"value="'+ weatherlist[i].weather_num + '"/></td>';
			content += '<td>' + weatherlist[i].weather_name + '</td>';
			content += '</tr>';
		}
		$('.weather').empty();
		$('.weather').append(content);
	}
	
	function topList(list) {
		var content = '';

		for (i = 0; i < list.length; i++) {
			content += '<tr>';
			content += '<td><input type ="checkbox" name = "top" onclick = "popup()"value="'
					+ list[i].cloth_num + '"/></td>';
			content += '<td>' + list[i].cloth_name + '</td>';
			content += '</tr>';
		}
		$('#Cloth_top').empty();
		$('#Cloth_top').append(content);
	}
</script>
</html>