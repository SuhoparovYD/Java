<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  
<!DOCTYPE html>
<html>
 <head>
 
   <jsp:include page="_bootstrapStyle.jsp"></jsp:include> 
 
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

   <title>Раскрой</title>

   <script src="canvasjs.min.js"></script>  
   <script type="text/javascript" src="slabshow.js"> </script>
    
 </head>
 <body>

    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>

    <h5>  Раскрой плиты № 
    <% out.println(request.getAttribute("currentNo")); %>
    плитка 
    <% out.println(request.getAttribute("tcstr")); %>
    </h5>    

   <form name="tileSize">
     <input type="hidden" name="tx" value="${tx}" size="4">
     <input type="hidden" name="ty" value="${ty}" size="4">
   </form>

   <script  type="text/javascript">       
     var ar1 = new Array(80); 
     var ar2 = new Array(80); 
     var yMax=-1;
     var xTile =  document.tileSize.tx.value;  
     var yTile =  document.tileSize.ty.value;  
     var noMaxRect = 0;
   </script>	   

  		<c:forEach items="${slabList}" var="slab" >
          <script  type="text/javascript">         
             ar1[++yMax] = ${slab.xl};
			 ar2[yMax] = ${slab.xr};
		  </script>		 
  		</c:forEach> 
  		
   
	<canvas id="mypicture" width="600" height="500">
    	<p>Ваш браузер не поддерживает рисование.</p>
    		
   <script>   
       drawSlab();
   </script>    
    
    </canvas>
    
<div class="col-md-offset-0">
<form action="${pageContext.request.contextPath}/slabToTiles">
№ плиты   <select name="ncurrent" > 
   <option disabled>Выберите номер плиты</option>   
   <c:forEach items="${noslabList}" var="noslab"> 
      <option value=${noslab.id}>${noslab.slabno}</option>
   </c:forEach>  
</select>
  <input type="submit" Value="O`k"/>
</form> 
<br>
<form action="${pageContext.request.contextPath}/slabToTiles">
Размер плитки  <select name="tcurrent"> 
   <option disabled>Выберите размер плитки</option>   
   <c:forEach items="${tileList}" var="tile"> 
      <option value=${tile.id}>${tile.x}*${tile.y}</option>
   </c:forEach>  
</select>
  <input type="submit" Value="O`k"/>
</form> 
</div>

<div class="col-md-offset-1">
    
    <form name="NoMaxRect">
       № прямоугольника <input type="text" name="noMR" value = "0" size="1">
    <input type="button" class="btn btn-default name="buttonMRect" value="Следующий" onClick="newMRect();"><br>
</form>  

<form name="forma1">
	Номер строки <input type="text" name="yn" value = "3" size="1">  
    Координаты (л,п) <input type="text" name="xln"  value = "33" size="4">
                     <input type="text" name="xrn" value = "243" size="4">
    <input type="button" class="btn btn-default name="buttonCoord" value="Заменить" onClick="newSlab();"><br>
</form>
</div>

    <jsp:include page="_footer.jsp"></jsp:include>

 </body>
</html>