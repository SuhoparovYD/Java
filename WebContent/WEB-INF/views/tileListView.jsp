<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
 
     <meta charset="UTF-8">
    <jsp:include page="_bootstrapStyle.jsp"></jsp:include> 
    
    <style> <%@include file='css/jquery-ui.css' %>  </style> 
    <style> <%@include file='css/ui.jqgrid.css' %>  </style> 
     
    <link href="css/jquery-ui.css" rel="stylesheet" />
    <link href="css/ui.jqgrid.css" rel="stylesheet" />

    <script src="jquery-1.10.2.min.js"></script>
    <script src="jquery-ui-1.10.0.js"></script>
    <script src="i18n/grid.locale-ru.js"></script>
    <script src="jquery.jqGrid.min.js"></script>
       
    <title>Tile List</title>
 </head>
 <body>

    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>

    <h3>Tile List</h3>
	   



<script >

var i = 0; 
var ldata = {id:0,x:300,y:400,comments:"note   ",editurl:"eurl   "};


$(document).ready(function() {
 	
 $("#Tilesgrid").jqGrid({
		datatype: "local",
		height: 250,
     colNames: ['idTile', 'Размер по X (Ширина)', 'Размер по Y (Высота)','Описание',
 'Edit'],
     colModel: [{
         name: 'id',
         index: 'id',
         width: 100
     }, {
         name: 'x',
         index: 'x',
         width: 150,
         editable: true
     }, {
         name: 'y',
         index: 'y',
         width: 150,
         editable: true
     }, {
         name: 'comments',
         index: 'comments',
         width: 150,
         editable: true
     }, {
         name: 'editurl',
         index: 'editurl',
         width: 32,
         editable: true
     }],
     pager: '#pager',
     rowNum: 10,
     rowList: [10, 20, 30],
     sortname: 'invid',
     sortorder: 'desc',
     viewrecords: true,
     gridview: true,
     caption: 'Data Report',
     jsonReader: {
         repeatitems: false,
     },
     editurl: "${pageContext.request.contextPath}/tileList"
 });
 jQuery("#Tilesgrid").jqGrid('navGrid', '#pager', {
     edit: true,
     add: true,
     del: true,
     search: true
 });
	<c:forEach items="${tileList}" var="tile" >
       ldata.id = ${tile.id};
       ldata.x = ${tile.x};
       ldata.y = ${tile.y};
       ldata.comments = String("${tile.comments}");
       ldata.editurl ='<a href="editTile?id=${tile.id}">Edit</a>';
       jQuery("#Tilesgrid").jqGrid('addRowData',++i,ldata);
	 
	</c:forEach>
});

</script >

    <div>
        <table id="Tilesgrid"></table>
        <div id="pager"></div>
    </div>
    <br>   
<a href="createTile" >Create Tile</a>
   <br>


    <jsp:include page="_footer.jsp"></jsp:include>

 </body>
</html>