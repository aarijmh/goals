<%@taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="script/jquery-2.1.0.js"></script>
<script src="script/vis.js"></script>
<script type="text/javascript">
	$(function() {
		/* 		var nodes = [ {
		 id : 1,
		 label : 'Node 1'
		 }, {
		 id : 2,
		 label : 'Node 2'
		 }, {
		 id : 3,
		 label : 'Node 3'
		 }, {
		 id : 4,
		 label : 'Node 4'
		 }, {
		 id : 5,
		 label : 'Node 5'
		 } ]; */

		var nodes = [];
		var edges = [];
		var str = <s:property value="jsonObject" escapeHtml="false"/>;
		$.each(str, function() {

			var id = "";
			var source = "";
			var target = "";
			var shape = "";
			var group = "";
			switch (this.type) {
			case 1:
				id = "con_" + this.id;
				shape = "square";
				group = "concepts";
				break;
			case 2:
				id = "pr_" + this.id;
				shape = "ellipse";
				group = "pedresources";

				break;
			case 3:
				id = "gr_" + this.id;
				shape = "box";
				group = "gameresources";
				break;
			case 4:
				id = "ccn_" + this.id;
				source = "con_" + this.parent;
				target = "con_" + this.child;
				break;
			case 5:
				id = "cpr_" + this.id;
				source = "con_" + this.parent;
				target = "pr_" + this.child;
				break;
			case 6:
				id = "pgr_" + this.id;
				source = "pr_" + this.parent;
				target = "gr_" + this.child;
				break;
			}
			if (this.type == 1 || this.type == 2 || this.type == 3) {

				nodes.push({
					id : id,
					label : this.name,
					description : this.description,
					shape : shape,
					group : group
				});
			} else if (this.type == 4 || this.type == 5 || this.type == 6) {
				edges.push({
					id : id,
					from : source,
					to : target
				});
			}

		});
		// create an array with edges

		// create a graph
		var container = document.getElementById('mygraph');
		var data = {
			nodes : nodes,
			edges : edges,
		};
		var options = {
			width : '1000px',
			height : '1000px',
			stabilize : false,
			clustering : true,
			dataManipulation : true,

			hierarchicalLayout : {
				direction : "UD"
			},
			clustering : {
				initialMaxNodes : 100,
				clusterThreshold : 500,
				reduceToNodes : 300,
				chainThreshold : 0.4,
				clusterEdgeThreshold : 20,
				sectorThreshold : 100,
				screenSizeThreshold : 0.2,
				fontSizeMultiplier : 4.0,
				maxFontSize : 1000,
				forceAmplification : 0.1,
				distanceAmplification : 0.1,
				edgeGrowth : 20,
				nodeScaling : {
					width : 1,
					height : 1,
					radius : 1
				},
				maxNodeSizeIncrements : 600,
				activeAreaBoxSize : 100,
				clusterLevelDifference : 2
			}

		};
		var graph = new vis.Graph(container, data, options);
/* 		graph.on('select', function(properties) {
			alert('selected nodes: ' + properties.nodes);
		});
 */
	});
</script>
</head>
<body>
	<div id="mygraph"></div>

</body>
</html>