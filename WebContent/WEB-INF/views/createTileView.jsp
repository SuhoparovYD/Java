<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
   <head>
      <jsp:include page="_bootstrapStyle.jsp"></jsp:include> 
      <meta charset="UTF-8">
      <title>Create Tile</title>
   </head>
   <body>
   
      <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
      
      <h3>Create Tile</h3>
      
      <p style="color: red;">${errorString}</p>
      
      <form method="POST" action="${pageContext.request.contextPath}/createTile">
         <table border="0">
            <tr>
               <td>№ п/п</td>
               <td><input type="text" name="id" value="${tiles.id}" /></td>
            </tr>
            <tr>
               <td>Размер по X</td>
               <td><input type="text" name="x" value="${tiles.x}" /></td>
            </tr>
            <tr>
               <td>Размер по Y</td>
               <td><input type="text" name="y" value="${tiles.y}" /></td>
            </tr>
            <tr>
               <td>Комментарий</td>
               <td><input type="text" name="comments" value="${tiles.comments}" /></td>
            </tr>
            <tr>
               <td colspan="2">                   
                   <input type="submit" value="Submit" />
                   <a href="tileList">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
      
      <jsp:include page="_footer.jsp"></jsp:include>
      
   </body>
</html>