<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Graph Test</title>

<script type="text/javascript" src="script/vivagraph.js"></script>
<script type="text/javascript" src="script/jCanvaScript.js"></script>
<script type="text/javascript">

function init()
{
var globalPosX = 0;
var globalPosY = 0;
var g = Viva.Graph.graph();

jc.start('canvas_1',true);

  g.addNode(1,{url:"www.yahoo.com"});     // g has one node.
  g.addLink(2, 3);
  
  g.forEachNode(function(node) {
                
                var r = Math.floor(Math.random() * (254));
                var g = Math.floor(Math.random() * (254));
                var b = Math.floor(Math.random() * (254));
         
                
                var color = 'rgb('+r+','+g+','+b+')';
                jc.circle({x:globalPosX,y:globalPosY,radius:50,color:color,fill:1,'data':node}).draggable().dblclick(function(point){
			                alert(this['x'])
            		});
                ;
              //  jc.circle(globalPosX,globalPosY,50,color,1).draggable();
                globalPosX+=50;
                globalPosY+=20;
            });

//var g = new Graph(null,null,null,null);


            //and we need to redraw canvas in the end
            jc.start('canvas_1');

}

</script>
</head>

<body onload="init();">


<canvas height="1000" width="1000" id="canvas_1"></canvas>


</body>
</html>