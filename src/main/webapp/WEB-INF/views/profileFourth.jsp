<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
   href="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
   rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script
   src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script src="resources/js/jquery.twbsPagination.js"></script>
<link rel="stylesheet" href="resources/css/common2.css" type="text/css">
</head>
<body>
   <form action="/" method="post" enctype="multipart/form-data">
      <header class="row">
         <div class="small-12 medium-12 large-12 columns">

            <div class="logo">
               <a href="logoimg"><img src="resources/logos/logo.png"
                  alt="Typewolf"></a>
            </div>
            <div class="tagline">
               <a href="logoimg">ROPA DE CLIMA</a>
            </div>
         </div>
      </header>
      <nav class="header">
         <button type="button"
            onclick="location.href='profile?mem_num=${info.mem_num}'">작성한
            글</button>
         <button type="button"
            onclick="location.href='profileSecond?mem_num=${info.mem_num}'">누른
            좋아요</button>
         <button type="button"
            onclick="location.href='profileThird?mem_num=${info.mem_num}'">누른
            북마크</button>
         <button type="button"
            onclick="location.href='profileFourth?mem_num=${info.mem_num}'">작성한
            댓글</button>
      </nav>

      <div class="my">
         <table>
            <thead>
               <tr>
                  <th>내용</th>
                  <th>작성시간</th>
               </tr>
            </thead>
            <tbody id="list">

            </tbody>
            <tr>
               <td colspan="5" id="paging">
                  <div class="container">
                     <nav aria-label="Page navigaion">
                        <ul class="pagination" id="pagination"></ul>

                     </nav>
                  </div>
               </td>
            </tr>
         </table>
      </div>


      <div class="profil">
         <div class="photo">
            <c:forEach items="${fileList}" var="file">
               <img id="profil_Img" src="/photo/${file.chan_name}" alt="myimg">
            </c:forEach>
            <h3>${nick.mem_nickname}</h3>
         </div>

         <div>키:${info.height}cm 몸무게:${info.weight}kg</div>
         <div>
            <br>
            <button type="button"
               onclick="location.href='profileEdit?mem_num=${info.mem_num}'">프로필
               편집</button>
            <button type="button"
               onclick="location.href='tal?mem_num=${info.mem_num}'" value="탈퇴">탈퇴하기</button>

         </div>
      </div>
   </form>
</body>

<script>
var showPage = 1;
dat_listCall(showPage);
/* 	function tal() {
 var result = confirm('탈퇴하시겠습니까?');
 if (result) {
 //yes
 document.location.href = './tal';
 } else {
 //no
 }
 } */

function dat_listCall(page) {
	$.ajax({
		type : 'get',
		url : 'listComment',
		data : {
			page : page
			,mem_num: ${info.mem_num}
		},
		dataType : 'JSON',
		success : function(data) {
			console.log(data);
			drawList(data.co_list);
			$('#pagination').twbsPagination({
				startPage : 1, // 시작 페이지
				totalPages : data.total, // 총 페이지 수
				visiblePages : 4, // 기본으로 보여줄 페이지 수
				onPageClick : function(e, page) { // 클릭했을때 실행 내용
					console.log(e);
					dat_listCall(page);

				}
			});
		},
		error : function(e) {
			console.log('실패');
			console.log(e);
		}
	});
}

   function drawList(co_list) {
      var content = '';
      console.log("리스트");
      for (i = 0; i < co_list.length; i++) {
         console.log(co_list[i]);
         content += '<tr>';
         content += '<td>' + co_list[i].comt_content + '</td>';
         
         var date = new Date(co_list[i].comt_time);
         content += '<td>' + date.toLocaleDateString('ko-KR') + '</td>';
         content += '</tr>';
      }
      $('#list').empty(content);
      $('#list').append(content);
   }
</script>
</html>