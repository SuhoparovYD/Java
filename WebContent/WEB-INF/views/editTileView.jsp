<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
   <head>
      <jsp:include page="_bootstrapStyle.jsp"></jsp:include> 
      <meta charset="UTF-8">
      <title>Edit Tile</title>
   </head>
   <body>

      <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>

      <h3>Edit number Tile</h3>

      <p style="color: red;">${errorString}</p>

      <c:if test="${not empty tile}">
         <form method="POST" action="${pageContext.request.contextPath}/editTile">

            <table border="0">
               <tr>
                  <td>Id</td>
                  <td><input type="text" name="id" value="${tile.id}" /></td>
               </tr>
               
               <tr>
                  <td>x</td>
                  <td><input type="text" name="x" value="${tile.x}" /></td>
               </tr>
               
               <tr>                 
                  <td>y</td>
                  <td><input type="text" name="y" value="${tile.y}" /></td>
               </tr>             
               
               <tr>      
                  <td>comments</td>
                  <td><input type="text" name="comments" value="${tile.comments}" /></td>
               </tr>
               <tr>
                  <td colspan = "2">
                      <input type="submit" value="Submit" />
                      <a href="${pageContext.request.contextPath}/tileList">Cancel</a>
                  </td>
               </tr>
            </table>
         </form>
      </c:if>

      <jsp:include page="_footer.jsp"></jsp:include>

   </body>
</html>