//Javascript


function drawSlab() {
	
	  var drawingCanvas = document.getElementById('mypicture');
      
	  if(drawingCanvas && drawingCanvas.getContext) {
	    	
	   var context = drawingCanvas.getContext('2d');        
	   var yTop=20;
	   var yStep=5;
	   var xLeft=255;
	   var xRight=0;
	   var yLeft=0;
	   var yRight=0;
	  //var yMax=ar1.length-1; //определено в jsp
	   var i,j,l,I,J;
	   var svLen,Smxv;
	   var Sv=new Array(64);
	   var Indx=new Array(32);  
	   
	   function xySize(xsLeft,ysLeft,xsRigh,ysRigh,xsTop,ysTop,xsBot,ysBot,xBias,yBias){
		  var yv=7; 			// выход выносноных линий X
		  var xs=12; 			// Длина стрелки X;
		  var ys=2;		        // Ширина стрелки X;
		  var xv=7; 			// выход выносноных линий Y
		  var ds=2; 			// Ширина стрелки Y
		  var ls=12;			// Длина стрелки Y ;  
		  
		    context.beginPath();
		    context.strokeStyle = "#711";
		    context.fillStyle = "#000";
		    context.lineWidth = 1;

		// выносные линии X
			    context.moveTo(xsLeft, yTop+ysLeft);
			    context.lineTo(xsLeft, yTop+yBias + yv);

			    context.moveTo(xsRigh, yTop+ysRigh);
			    context.lineTo(xsRigh, yTop+yBias + yv);

		// размерная линия X
		  	    context.moveTo(xsRigh, yTop+yBias);
		  	    context.lineTo(xsLeft,  yTop+yBias);

		// Стрелки  
		  	    context.moveTo(xsLeft, yTop+yBias);
		  	    context.lineTo((xsLeft+xs), yTop+yBias+ys);
		  	    context.moveTo(xsLeft, yTop+yBias);
		  	    context.lineTo(xsLeft+xs, yTop+yBias-ys);

		  	    context.moveTo(xsRigh, yTop+yBias);
		  	    context.lineTo(xsRigh-xs, yTop+yBias+ys);
		  	    context.moveTo(xsRigh, yTop+yBias);
		  	    context.lineTo(xsRigh-xs, yTop+yBias-ys);

		// выносные линии Y
			    context.moveTo(xsTop, yTop+ysTop);
		  	    context.lineTo(xBias+xv, yTop+ysTop);
		  	    
		  	    context.moveTo(xsBot, yTop+ysBot);
		  	    context.lineTo(xBias+xv, yTop+ysBot); 
		  	    
		// размерная линия Y  
			    context.moveTo(xBias, yTop+ysTop);
		  	    context.lineTo(xBias, yTop+ysBot);

		// Стрелки
			    context.moveTo(xBias, yTop+ysTop);
		  	    context.lineTo(xBias+ds, yTop+ysTop+ls);
		  	    context.moveTo(xBias, yTop+ysTop);
		  	    context.lineTo(xBias-ds, yTop+ysTop+ls);    
		    
			    context.moveTo(xBias, yTop+ysBot);
		  	    context.lineTo(xBias+ds, yTop+ysBot-ls);
		  	    context.moveTo(xBias, yTop+ysBot);
		  	    context.lineTo(xBias-ds, yTop+ysBot-ls);    
		    
		    	context.font = "16px Arial";
		    	context.fillText((xsRigh-xsLeft)*10, (xsRigh)/2, yTop+yBias-5);
		    	context.fillText((ysBot-ysTop+yStep)*10,xBias-40, (yMax*yStep)/2);

		     context.closePath();
		     context.stroke();     
		} //  function xySize

	 // подложка 
	    context.strokeStyle = "#000";
	    context.fillStyle = "#eef";
	    context.beginPath();

	    context.fillRect(0,0,600,500);
	    context.closePath();
	    context.stroke();
	    context.fill();
	    
	 // плита          
	    context.beginPath();
	    context.strokeStyle = "#045";
	    context.lineWidth = 2;
	    context.fillStyle = "#045";

	    context.moveTo(ar2[0], yTop);
	    context.lineTo(ar1[0], yTop);

	   for (i=0; i < yMax; i++)  {
	        context.moveTo(ar1[i], yTop+i*yStep);
	        context.lineTo(ar1[i+1], yTop+(i+1)*yStep);
	        context.moveTo(ar2[i], yTop+i*yStep);
	        context.lineTo(ar2[i+1], yTop+(i+1)*yStep);  
	     }
	    context.moveTo(ar2[i], yTop+i*yStep);
	    context.lineTo(ar1[i], yTop+i*yStep);

	    context.closePath();
	    context.stroke();

	//  Размеры плиты
	    for (i=0; i <= yMax; i++)  {
	       if (ar1[i]  <= xLeft)  {
	           xLeft = +ar1[i];
	           yLeft = i;
	        }
	       if (ar2[i]  >= xRight)  {
	           xRight = +ar2[i];
	           yRight = i;
	        }
	    }
	    
	    xySize(xLeft,yLeft*yStep,xRight,yRight*yStep,ar2[0],0,ar2[yMax],yMax*yStep,xRight+120,(yMax+14)*yStep);

	     
	//  Площади вписанных прямоугольников  (64 max)
	     for (I=0; I < 8; I++)                  
	       for (J=0; J < 8; J++) {
	         for (xLeft=0, xRigh=250, i=I; i <= yMax-J; i++) {
	           if (ar1[i] > xLeft)  xLeft = ar1[i];
	           if (ar2[i] < xRigh)  xRigh = ar2[i];
	         }
	         Sv[I*8+J]=(xRigh - xLeft) * (yMax-J - I);
	       }
	      
	// Поиск 16 Max прямоугольников
	     svLen=Sv.length;
	     for (l=0; l < 16; l++) {  
	        for (Smxv=0, j=0; j < svLen; j++)
	          if (Sv[j] > Smxv) { 
	            Smxv=Sv[j];
	   	        J=j;
	         } // 
	        Indx[l]=J;
	        Sv[J]=0;                                   	// zero Sv rectangle
	      } 
	      
	 // Находим выбранный прямоугольник   - noMaxRect
	     for (l=0; l < 16; l++) {                      
	       I= parseInt(Indx[l] / 8); 
	       J=Indx[l]-I*8;
	       for (xLeft=0, xRigh=250, i=I; i < yMax-J; i++) {
	           if (ar1[i] > xLeft)  xLeft = ar1[i];
	           if (ar2[i] < xRigh)  xRigh = ar2[i];
	       }
	       if (l==noMaxRect)  {
	         J=yMax-J;         
	         break;
	       }  
	     }  
	     
	  // Отрисовка прямоугольника             
	         context.beginPath();
	         context.fillStyle = "#396";
	         context.fillRect(xLeft,yTop+I*yStep,xRigh-xLeft,(J-I)*yStep)
	                  
	  // Плитка
	         context.strokeStyle = "#999";

	          for (i=0; i < (xRigh-xLeft)/xTile; i++) { 
	       	   context.moveTo(xLeft+i*xTile, yTop+I*yStep);
	       	   context.lineTo(xLeft+i*xTile, yTop+J*yStep);
	          }

	          for (i=0; i < (J-I)*yStep/yTile; i++) { 
	       	   context.moveTo(xLeft, yTop+I*yStep +i*yTile);
	       	   context.lineTo(xRigh, yTop+I*yStep +i*yTile);
	          }	 
	        
	          context.fillStyle = "#111";
	          context.font = "16px Arial";
	          context.fillText("Площадь п-ка = " +((J-I)*yStep*(xRigh-xLeft)),xLeft+10, yMax*yStep +150);
	     
	          context.closePath();
	          context.stroke();
	          
	     xySize(xLeft,(J-I)*yStep,xRigh,(J-I)*yStep,xRigh,I*yStep,xRigh,J*yStep,xRight+70,(yMax+9)*yStep); 
	  // function xySize(xsLeft,ysLeft,xsRigh,ysRigh,xsTop,ysTop,xsBot,ysBot,xBias,yBias){    
	  }  //if(drawingCanvas && drawingCanvas.getContext) 
}

//добавить кнопку выбора максим прямоуг  и  плитки

function newSlab(){
  var xls= +document.forma1.xln.value;
  var xrs= +document.forma1.xrn.value;
  var ys= +document.forma1.yn.value;
  
  if (ys < yMax ) {
     ar1[ys]=xls;
     ar2[ys]=xrs;
     drawSlab();
  }
}

function newMRect(){
	if (+document.NoMaxRect.noMR.value == noMaxRect) 
	   document.NoMaxRect.noMR.value++;
	if (+document.NoMaxRect.noMR.value >=16) 
		document.NoMaxRect.noMR.value=0;
	noMaxRect= +document.NoMaxRect.noMR.value;
	drawSlab();
}






  
 