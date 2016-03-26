<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type"
			content="text/html; charset=ISO-8859-1">
		<title>Flash Testing</title>

		<!-- JSON support for IE (needed to use JS API) -->
		<script type="text/javascript" src="script/json2.min.js">
</script>

		<!-- Flash embedding utility (needed to embed Cytoscape Web) -->
		<script type="text/javascript" src="script/AC_OETags.min.js">
</script>

		<!-- Cytoscape Web JS API (needed to reference org.cytoscapeweb.Visualization) -->
		<script type="text/javascript" src="script/cytoscapeweb.min.js">
</script>

		<script type="text/javascript" src="script/xmlData.js">
</script>

		<script type="text/javascript">

var vis;

var options = {
	orientation : "topToBottom",
	depthSpace : 30,
	breadthSpace : 100,
	subtreeSpace : 30
};

if (!Array.prototype.indexOf) {
	Array.prototype.indexOf = function(obj, fromIndex) {
		if (fromIndex == null) {
			fromIndex = 0;
		} else if (fromIndex < 0) {
			fromIndex = Math.max(0, this.length + fromIndex);
		}
		for ( var i = fromIndex, j = this.length; i < j; i++) {
			if (this[i] === obj)
				return i;
		}
		return -1;
	};
}

Array.prototype.remove = function(from, to) {
  var rest = this.slice((to || from) + 1 || this.length);
  this.length = from < 0 ? this.length + from : from;
  return this.push.apply(this, rest);
};

var displayMap = {};

window.onload = function() {
	// id of Cytoscape Web container div

	var div_id = "cytoscapeweb";

	// NOTE: - the attributes on nodes and edges
	//       - it also has directed edges, which will automatically display edge arrows

	function rand_color() {
		function rand_channel() {
			return Math.round(Math.random() * 255);
		}

		function hex_string(num) {
			var ret = num.toString(16);

			if (ret.length < 2) {
				return "0" + ret;
			} else {
				return ret;
			}
		}

		var r = rand_channel();
		var g = rand_channel();
		var b = rand_channel();

		return "#" + hex_string(r) + hex_string(g) + hex_string(b);
	}

	// visual style we will use
	var visual_style = {
		global : {
			backgroundColor : "#ABCFD6"
		},
		nodes : {
			shape : "OCTAGON",
			borderWidth : 3,
			borderColor : "#ffffff",
			size : {
				defaultValue : 75,
				continuousMapper : {
					attrName : "weight",
					minValue : 25,
					maxValue : 75
				}
			},
			color : {
				discreteMapper : {
					attrName : "type",
					entries : [ {
						attrValue : "concept",
						value : "#0B94B1"
					}, {
						attrValue : "pr",
						value : "#9A0B0B"
					}, {
						attrValue : "gr",
						value : "#dddd00"
					} ]
				}
			},
			labelHorizontalAnchor : "center"
		},
		edges : {
			width : 3,
			color : "#0B94B1"
		}
	};

	// initialization options
	var options = {
		swfPath : "swf/CytoscapeWeb",
		flashInstallerPath : "swf/playerProductInstall"
	};

	vis = new org.cytoscapeweb.Visualization(div_id, options);

	vis.ready(function() {
		// set the style programmatically

			vis.addListener("click", "nodes", function(event) {
				handle_click(event);
			}).addListener("click", "edges", function(event) {
				handle_click(event);
			});

			function handle_click(event) {

				var target = event.target;
				// alert(target.data.label);
				
				var displayList = showArray.slice(0);
				var neighbours = vis.firstNeighbors( [ target ]);
				var neighNodes = neighbours.neighbors;
				
				if(displayMap[target.data.id])
				{
					var list = new Array();
					//list.push(target.data.id);
					
					var j = 0;
					for(j = 0; j < neighNodes.length; j++)
					{
						if(neighNodes[j].data.parentNode === target.data.id && showArray.indexOf(neighNodes[j].data.id) === -1)
						{
							list.push(fillDisplayList(neighNodes[j]));
						}
					}
					
					displayMap[target.data.id] = undefined;
					var count = 0,innerCount;
					for(count = 0; count < list.length; count++)
					{
						for(innerCount = 0 ; innerCount < list[count].length; innerCount++)
						{
							if(displayMap[list[count][innerCount]])
								displayMap[list[count][innerCount]] = undefined;
						}
					}
				}
				else
				{

					displayMap[target.data.id] = new Array();
					var j = 0;
					
					for(j = 0; j < neighNodes.length; j++)
					{
						if(neighNodes[j].data.parentNode === target.data.id && showArray.indexOf(neighNodes[j].data.id) === -1)
						{
							displayMap[target.data.id].push(neighNodes[j].data.id);
						}
					}
				}
				
				
				for(var prop in displayMap) {
				    if(displayMap.hasOwnProperty(prop))
				    {
					    if(displayMap[prop])
					    {
					 	    var innerCount = 0;
							for(innerCount = 0; innerCount < displayMap[prop].length; innerCount++)
							{
								displayList.push(displayMap[prop][innerCount]);
							}
						}
				    }
				}
				
				vis.filter("nodes", displayList);
				adjustLinks(displayList);

				vis.layout( {
					name : "Tree",
					options : options
				});
			}


			hide();
			adjustLinks(showArray);
			
			vis.layout( {
				name : "Tree",
				options : options
			});
		});

	var draw_options = {
		// your data goes here
		network : xml,

		// show edge labels too
		edgeLabelsVisible : false,

		// let's try another layout
		layout : "Tree",

		// set the style at initialisation
		visualStyle : visual_style,

		// hide pan zoom
		panZoomControlVisible : true,
		panZoomControlPosition: 'bottomRight'
	};
	
	//hide();
/*
vis.layout( {
		name : "Tree",
		options : options
	});*/
	
	vis.draw(draw_options);
	
	//hide();

};

function fillDisplayList(targetNode)
{
	var returnList = new Array();
	if(displayMap[targetNode.data.id])
	{
		
		var arr = displayMap[targetNode.data.id];
		var z = 0;
		for(z = 0; z < arr.length; z++)
		{
			var neighbours = vis.firstNeighbors( [ arr[z] ]);
			var neighNodes = neighbours.neighbors;
			
			var t;
			
			for(t = 0; t < neighNodes.length; t++)
			{
				
				if(neighNodes[t].data.parentNode === targetNode.data.id && showArray.indexOf(targetNode.data.id) === -1 && neighNodes[t].data.parentNode !== targetNode.data.id)
				{
					if(displayMap[neighNodes[t].data.id])
					{
						returnList.push(fillDisplayList(neighNodes[t]));
					}
					else
						returnList.push(neighNodes[j].data.id);
				}
			}
			
			returnList.push(arr[z]);
				
		}
	}
	
	returnList.push(targetNode.data.id);

	return returnList;
}


function addNode() {
	
}
var showArray = new Array();
var tempEdges = new Array();
var secondArray = new Array();

function hide() {

	//vis.filter("nodes", ['1', '2']);
	showArray = new Array();
	var nodes = vis.nodes(false);
	var k = 0;

	for (k = 0; k < nodes.length; k++) {
		var n = nodes[k];
		
		if(n.data.type === "pr" || n.data.type === "gr")
		{	
			continue;
		}
		var neighbours = vis.firstNeighbors( [ n ]);
		var neighEdges = neighbours.edges;
		//	alert("sdfd");

		var i = 0;
		var include = true;
		for (i = 0; i < neighEdges.length; i++) {

			if (neighEdges[i].data.reltype.toLowerCase() == "hasparts") {
				if (neighEdges[i].data.source == n.data.id)
					continue;
				else {
					include = false;
					break;
				}
			}
		}

		if (include)
			showArray.push(n.data.id);
	}

	vis.filter("nodes", showArray);
	//alert("sdfd");
/*	vis.layout( {
		name : "Tree",
		options : options
	});*/

	//	alert(showArray);

}

function callAdjustLinks() {
	adjustLinks(showArray);
}

function adjustLinks(visibleNodes) {
	var i;
	/*
		Delete all temporary edges
	*/
		for (i = 0; i < tempEdges.length; i++) {
			vis.removeEdge(tempEdges[i]);
		}
		
		tempEdges = new Array();
	/*
		End deleting temporary edges
	*/
	
	for (i = 0; i < visibleNodes.length; i++) {
		var node = vis.node(visibleNodes[i]);
		if (node) {
			var neighbours = vis.firstNeighbors( [ node ]);
			var nextNodes = neighbours.neighbors;

			if (nextNodes) {
				var j;
				for (j = 0; j < nextNodes.length; j++) {
					var neighbour = nextNodes[j];
					if (neighbour.data.parentNode === node.data.id)
						continue;
					if (visibleNodes.indexOf(neighbour.data.id) !== -1) {
						continue;
					}

					var visibleParent = getVisibleParent(visibleNodes,
							neighbour);

					if (visibleParent) {
						var edgeId = "concept_" + node.data.realId
								+ "toconcept_" + visibleParent.data.realId;
						var visibleEdge = vis.edge(edgeId);
						if (visibleEdge) {

						} else {
							var edgeData = {
								id : edgeId,
								source : node.data.id,
								target : visibleParent.data.id,
								directed : true,
								label : "Temporary",
								reltype : "Temporary"
							};

							var edge = vis.addEdge(edgeData, true);
							tempEdges.push(edgeId);
						}
					}

				}
			}
		}
	}
}

function getVisibleParent(vNodes, targetNode) {
	if (targetNode) {
		if (vNodes.indexOf(targetNode.data.id) !== -1)
			return targetNode;
		else {
			if (targetNode.data.parentNode)
				return getVisibleParent(vNodes, vis
						.node(targetNode.data.parentNode));
		}

	}

	return null;
}
</script>

		<style>
/* The Cytoscape Web container must have its dimensions set. */
html,body {
	height: 100%;
	width: 100%;
	padding: 0;
	margin: 0;
}

#cytoscapeweb {
	width: 70%;
	height: 300;
}
</style>

	</head>
	<body>
		<div id="cytoscapeweb">
			Cytoscape Web will replace the contents of this div with your graph.
		</div>

		<div id="note">
			<p>
				Click nodes or edges.
			</p>
			<input type="button" value="Click To add node" onclick="addNode();">
			<input type="button" value="hide" onclick="hide();">

			<input type="button" value="adjustLinks" onclick="callAdjustLinks();">
		</div>
	</body>
</html>