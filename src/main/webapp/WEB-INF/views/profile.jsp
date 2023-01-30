<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<link rel="stylesheet" href="resources/css/common.css" type="text/css">
</head>
<body>
   <form action="/" method="post" enctype="multipart/form-data">
      <header class="row">
         <div class="small-12 medium-12 large-12 columns">

            <div class="logo">
               <a href="logoimg"><img src="resources/logos/logo.png" alt="Typewolf"></a>
            </div>
            <div class="tagline">
               <a href="logoimg">ROPA DE CLIMA</a>
            </div>
         </div>
      </header>
         <nav>
            <button type="button" onclick="location.href='profile?mem_num=${info.mem_num}'">작성한 글</button>
            <button type="button" onclick="location.href='profileSecond?mem_num=${info.mem_num}'">누른 좋아요</button>
            <button type="button" onclick="location.href='profileThird?mem_num=${info.mem_num}'">누른 북마크</button>
            <button type="button" onclick="location.href='profileFourth?mem_num=${info.mem_num}'">작성한 댓글</button>
         </nav>
         
         
      <div class="my">
         <table>
         <c:if test="${post_phoList.size()>0}">
            <tr>
               <td>
                  <c:forEach items="${post_phoList}" var="file">
                  <a href="detail?board_num=${file.board_num}"><img src="/photo/${file.newPhoto_name}" /></a>
                     
                  </c:forEach>
               </td>
            </tr>
         </c:if>
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
               onclick="location.href='profileEdit?mem_num=${member.mem_num}'">프로필편집</button>
            <button type="button" onclick="location.href='tal?mem_num=${info.mem_num}'" value="탈퇴">탈퇴하기</button>

         </div>
      </div>
   </form>
</body>
<script>
   function tal() {
      var result = confirm('탈퇴하시겠습니까?');
      if (result) {
         //yes
         document.location.href = './tal';
      } else {
         //no
      }
   }
</script>
</html>