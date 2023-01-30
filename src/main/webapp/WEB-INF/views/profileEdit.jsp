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
   <form action="edit" method="post" enctype="multipart/form-data">
      <header class="row">
         <div class="small-12 medium-12 large-12 columns">

            <div class="logo">
               <a href="editLogoimg"><img src="resources/logos/logo.png" alt="Typewolf"></a>
            </div>
            <div class="tagline">
               <a href="editLogoimg">ROPA DE CLIMA</a>
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
                     <img src="/photo/${file.newPhoto_name}" />
                  </c:forEach>
               </td>
            </tr>
         </c:if>
      </table> 
      </div>
      
      
      <div class="profil">
         <div class="photo">
            <input type="file" name="photo" />
            <c:forEach items="${fileList}" var="file">
               <img id="profile_Img" src="/photo/${file.chan_name}" alt="myimg">
            </c:forEach>
            <h3>${nick.mem_nickname}</h3>

         </div>
         <div>
            키:<input type="text" name="height" value="${info.height}">cm
            몸무게:<input type="text" name="weight" value="${info.weight}">kg
         </div>
         <div>
            <br>
            <button>편집완료</button>
         </div>
      </div>
   </form>
</body>
<script>
   
</script>
</html>