<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<link rel="stylesheet" href="resources/css/detail.css" type="text/css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<style>
</style>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<body>
	<div class="writeWrap">
		<header class="writeHeader">
			<div class="small-12 medium-12 large-12 columns">

				<div class="logo">
					<a href="logoimg"><img src="resources/logos/logo.png" alt="Typewolf"></a>
				</div>
			</div>
		</header>
		<main class="writeMain">
			<div class="writeAreaImg">
				<div class="writeImg">
					<div class="writeImgHeader">
						<div class="writeImgHeaderLeft">
							<c:if test="${post_pho.size()>0}">
								<tr>
									<td><c:forEach items="${pro_photo}" var="pro_photo">
											<img src="/photo/${pro_photo.chan_name}">
											<!--왼쪽 이미지 위에 아이콘 웃는애-->
											<br />
										</c:forEach></td>
								</tr>
							</c:if>


							<p>${detail_member.mem_nickname}</p>
						</div>
						<div class="writeImgHeaderRight">
							<p>${detail_post.board_time}</p>
						</div>
					</div>
					<div class="writeImgMain"></div>
				</div>
				<c:if test="${memNum eq detail_post.mem_num}">
				<div class="delete_btn">
					<img src="./resources/img/close_outline.png" data-toggle="modal"
						data-target="#myModal">
					<%-- <img onclick="location.href='detail/delete?board_num=${detail_post.board_num}'" 
					src="./resources/img/close_outline.png"  data-toggle="modal" data-target="#myModal"> --%>
					<!-- <input type="button" value="삭제하기"> -->
				</div>
				</c:if>
			</div>
			<div class="writeAreaComment">
				<div class="commentArea">
					<div class="commentWeather">
						<!-- <img> -->
						<p>${detail_weather.weather_name}</p>
						<!--날씨 아이콘-->
						<p>최저 ~ 최고</p>
					</div>
					<div class="commentTag">
						<div>
							<p class="tagHeight">#${detail_profile.height}cm</p>
							<p class="tagWeight">#${detail_profile.weight}kg</p>
						</div>
						<p class="tagPlace">#${detail_post.board_place}</p>
					</div>
					<div class="commentCheck">
						<img src="./resources/img/triangle_outline.png"
							class="commentReport">
						<!--신고 아이콘-->
						<div>
							<div class="commentBookArea"
								onclick="location.href='detail/Book_chk?board_num=${detail_post.board_num}'">
								<img src="./resources/img/bookmark_outline.png"
									class="commentBook">
								<!--북마크 빈 아이콘-->
								<img src="./resources/img/bookmark.png"
									class="commentBookActive">
								<!--북마크 색깔 아이콘-->
							</div>
							<div class="commentLikeArea"
								onclick="location.href='detail/Like_chk?board_num=${detail_post.board_num}'">
								<img src="./resources/img/heart_outline.png" class="commentLike">
								<!--좋아요 빈 아이콘-->
								<img src="./resources/img/heart_.png" class="commentLikeActive">
								<!--좋아요 색깔 아이콘-->
							</div>
						</div>
					</div>
					<div class="likeCount">
						<p id="likeCount">${detail_post.board_good}</p>
					</div>
					<div class="commentInput">
						<input type="text" name="comment" id="commentWrite"
							class="commentWrite" placeholder="댓글 작성"> <input
							type="button" id="commentButton" class="commentButton" value="게시">
					</div>
					<input type="hidden" value="${comtNum}" id=comtNum>
					<input type="hidden" value="${boardNum}" id="boardNum">
					<input type="hidden" value="${memNum}" id="memNum">
					<input type="hidden" value="${commentDetail}" id="commentDetail">
					<div class="commentView">
						<table>
							<c:forEach items="${commentDetail}" var="comm">
								<tr>
									<td id="acontent${comm.comt_num}">${comm.comt_content}</td>
									<td>&nbsp&nbsp</td>
									<td>${comm.comt_time}</td>
									<td>&nbsp&nbsp</td>
									<c:if test="${memNum eq comm.mem_num}">
										<td>
											<button id="abt${comm.comt_num}"
												onclick="updateComment('${comm.comt_num}','${comm.comt_content}')">수정</button>
										</td>
										<td>&nbsp&nbsp</td>
										<td>
											<button onclick="del(${comm.comt_num})">삭제</button>
										</td>
									</c:if>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</main>
	</div>



	<!-- Modal -->
	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">게시물 삭제</h4>
				</div>
				<div class="modal-body">
					<p>게시물을 삭제하시겠습니까?</p>
				</div>
				<div class="modal-footer">
					<button
						onclick="location.href='detail/delete?board_num=${detail_post.board_num}'"
						type="button" class="btn btn-default" data-dismiss="modal">삭제</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
				</div>
			</div>

		</div>
	</div>
</body>
<script>
detail_pho_List();

	$(".commentCheck>div>div>img").click(function() {
		$(this).css("display", "none");
		$(this).siblings().css("display", "block");
	});
	
/*  function Like_chk() {
	console.log("좋아요")		

		$.ajax({
			type : 'POST',
			url : '/Like_chk',
			data : {"board_num="+board_num+"&mem_num="+mem_num},
			dataType : 'json',
			success : function(data) {
				console.log("Like_chk")
				console.log(data);
			},
			error : function(e) {
				console.log(e);
				}
			});
		
	}  */
function detail_pho_List() {
	var board_num = ${detail_post.board_num}
	
	
	$.ajax({
		type: "GET",
		dataType: "json",
		url: "pho_List", // 요청보낼 url
		data: {board_num},
		success: function(data){
			console.log(data);
			console.log(data.fileList);

			// 이미지 출력
			readURL(data);
		} // success
	});
}
	
	function readURL(data){
		var content = '';
		//console.log(data.fileList.newFileName);
		
		//data.fileList.newFileName
		content += '<div>';
		content += '<img id="readPhoto" src="/photo/'+data.fileList.newPhoto_name+'">';
		content += '</div>';
		$(".writeImgMain").html(content);
		
	} // readURL()	
	
	function updateComment(comt_num,comt_content){
	   $('#acontent'+comt_num).html(
	      "<textarea id='edit_acontent"+comt_num+"'>"+comt_content+"</textarea>"
	      +"<style>#edit_acontent"+comt_num+"{width:200px; height:40px; resize:none;} </style>"
	   );
	   
	   $('#abt'+comt_num).html(
	      "<a onclick='answerEditSave("+comt_num+")' id='btnEdit'>완료</a> "
	   );
}
function answerEditSave(comt_num){
   var acontent = $("#edit_acontent"+comt_num).val();
   
   $.ajax({
		type:'get',
		url:'updateComment',
		data:{comt_num,acontent},
		dataType:'JSON',
		success:function(data){
	         if(data.login == false){
	            alert('로그인이 필요한 서비스 입니다.');
	            location.href='./';
	         }else{
	         	alert("수정");
				location.reload();
	         }
		},
		error:function(e){
			console.log(e);
		}
	});
}
	
	



$("#commentButton").click(function(){
	   
	 var boardNum = $('#boardNum').val();
	 var memNum = $('#memNum').val(); 
     var commentID = $('#commentWrite').val();
     var commentAdd = $('#commentDetail').val();
	   
	   console.log("commentID : "+ commentID+" / "+"memNum : "+ memNum+"/"+"boardNum : "+boardNum);
	   console.log("commentAdd : "+commentAdd);
	   
	   $.ajax({
	      type:'GET',
	      url:'commentAdd',
	      data:{boardNum,memNum,commentID,commentAdd},
	      dataType:'JSON',
	      success:function(data){
	         console.log(data);
	         if(data.login == false){
	            alert('로그인이 필요한 서비스 입니다.');
	            location.href='./';
	         }else{
	        	location.reload();
// 	        	commentWrite(data.commentAdd);
	         }
	         
	         },
	         error:function(e){
	            console.log(e);
	         }
	   });
	});


function commentWrite(commentAdd){
	
	var content = '';
	for(var i =0; i<commentAdd.length; i++){
		console.log("commentAdd : "+commentAdd[i]);
		content += '<tr>';
		content += '<td>'+commentAdd[i].comt_num+'</td>';
		content += '<td>'+commentAdd[i].comt_content+'</td>';
		content += '</tr>';
	}
	$('#commentAdd').empty();
	$('#commentAdd').append(content);
}

function del(comt_num){
	
	console.log("comt_num : "+comt_num);
	
	if (!confirm("삭제하시겠습니까?")) {
        return false;
	}
	
	$.ajax({
		type:'get',
		url:'del',
		data:{comt_num},
		dataType:'JSON',
		success:function(data){
// 			 console.log(data);
	         if(data.login == false){
	            alert('로그인이 필요한 서비스 입니다.');
	            location.href='./';
	         }else{
	         	
	         
	         	location.reload();
	         }
		},
		error:function(e){
			console.log(e);
		}
	});
	
} 
	



</script>
</html>