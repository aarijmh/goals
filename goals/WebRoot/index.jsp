<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta name="description"
	content="[An example of getting started with Cytoscape.js]" />
<link rel="stylesheet" href="css/cytoscape.js-panzoom.css">
<link rel="stylesheet" href="css/cytoscape.js-navigator.css">
<script src="script/jquery-2.1.0.js"></script>
<script src="script/dagre.js"></script>
<meta charset=utf-8 />
<title>Cytoscape.js initialisation</title>
<script src="script/cytoscape.js"></script>
<script src="script/cytoscape.js-panzoom.js"></script>
<script src="script/cytoscape.js-navigator.js"></script>
<script src="script/cytoscape.js-cxtmenu.js"></script>
<script src="script/my/ScenarioGenerator.js"></script>
<style type="text/css">
body {
	font: 14px helvetica neue, helvetica, arial, sans-serif;
}

#cy {
	height: 100%;
	width: 100%;
	position: absolute;
}
</style>


<script type="text/javascript">
var projectId = 21;
	$(function() { // on dom ready

		$('#id').click(function() {
			alert("asdsa");
		});
	
	intializeCyto();
	addId();

	});
</script>
</head>
<body>


	<a href="javascript:addId();">Click Me</a>
	<div id="cy"></div>

	<div class="cytoscape-navigator">
		<canvas></canvas>
		<div class="cytoscape-navigatorView"></div>
		<div class="cytoscape-navigatorOverlay"></div>
	</div>
</body>

<script type="text/javascript">

<<<<<<< .mine
		var prefix = '';
		var faveColor = '';
		var faveShape = '';
		var parent = '';

		switch (type) {
		case 1:
			prefix = 'con_';
			faveColor = '#EDA1ED';
			faveShape = 'ellipse';
			parent = 'a';
			break;
		case 2:
			prefix = 'pr_';
			faveColor = '#86B342';
			faveShape = 'octagon';
			parent = 'b';
			break;
		case 3:
			prefix = 'gr_';
			faveColor = '#EDA1ED';
			faveShape = 'ellipse';
			parent = 'c';
			break;
		}

		var node = {
			group : "nodes",
			data : {
				weight : 75,
				name : name,
				id : prefix + id,
				faveColor : faveColor,
				faveShape : faveShape,
				parent : parent
			},
			position : {
				x : x,
				y : y
			}
		};
		return node;
	}
	function addId() {
		var json;
		var conceptMap = {};
		var prMap = {};
		try {
			json = <s:property value="jsonObject" escapeHtml="false"/>;

			var g = new dagre.Digraph();
			$.each(json.concept, function() {
				conceptMap['con_' + this.id] = this;
				g.addNode('con_' + this.id, {
					label : this.name,
					width : 144,
					height : 100
				});
			});

			$.each(json.pr, function() {
				prMap['pr_' + this.id] = this;
				g.addNode('pr_' + this.id, {
					label : this.name,
					width : 144,
					height : 100
				});
			});

			$.each(json.cr, function() {
				g.addEdge('cr' + this.id, 'con_' + this.parent, 'con_' + this.child);
			});

			$.each(json.cpr, function() {
				g.addEdge('cpr' + this.id, 'con_' + this.parent, 'pr_' + this.child);
			});

			var layout = dagre.layout().nodeSep(2).run(g);

			layout.eachNode(function(u, value) {
				var node = conceptMap[u] || prMap[u] || grMap[u];
				cy.add(createNode(node.type, node.id, node.name, value.x, value.y));
			});

			$.each(json.cr, function() {
				cy.add({
					group : "edges",
					data : {
						weight : 75,
						name : this.name,
						source : 'con_' + this.parent.toString(),
						target : 'con_' + this.child.toString(),
						type : this.type
					}
				});
			});

			$.each(json.cpr, function() {
				cy.add({
					group : "edges",
					data : {
						weight : 75,
						name : this.name,
						source : 'con_' + this.parent.toString(),
						target : 'pr_' + this.child.toString(),
						type : this.type
					}
				});
			});
			//alert(eles.nodes("[[indegree  0]]").length);
			var eles = cy.elements().nodes("[[indegree != 0]]");
			for ( var i = 0; i < eles.length; i++) {
				//var ele = eles[i];
				//ele.hide();
			}

			var options = {
				name : 'preset',

				fit : true, // whether to fit to viewport
				ready : undefined, // callback on layoutready
				stop : undefined, // callback on layoutstop
				positions : undefined, // map of (node id) => (position obj)
				zoom : undefined, // the zoom level to set (prob want fit = false if set)
				pan : undefined, // the pan level to set (prob want fit = false if set)
				padding : 30
			// padding on fit
			};
			//cy.layout(options);		
			cy.layout(options);
			cy.forceRender();

			var defaults = ({
				zoomFactor : 0.05, // zoom factor per zoom tick
				zoomDelay : 45, // how many ms between zoom ticks
				minZoom : 0.1, // min zoom level
				maxZoom : 10, // max zoom level
				fitPadding : 50, // padding when fitting
				panSpeed : 10, // how many ms in between pan ticks
				panDistance : 10, // max pan distance per tick
				panDragAreaSize : 75, // the length of the pan drag box in which the vector for panning is calculated (bigger = finer control of pan speed and direction)
				panMinPercentSpeed : 0.25, // the slowest speed we can pan by (as a percent of panSpeed)
				panInactiveArea : 8, // radius of inactive area in pan drag box
				panIndicatorMinOpacity : 0.5, // min opacity of pan indicator (the draggable nib); scales from this to 1.0
				autodisableForMobile : true, // disable the panzoom completely for mobile (since we don't really need it with gestures like pinch to zoom)

				// icon class names
				sliderHandleIcon : 'fa fa-minus',
				zoomInIcon : 'fa fa-plus',
				zoomOutIcon : 'fa fa-minus',
				resetIcon : 'fa fa-expand'
			});

			cy.panzoom(defaults);

			$('#cy').cyNavigator({
				container : false,
				viewLiveFramerate : 0,
				thumbnailEventFramerate : 30,
				thumbnailLiveFramerate : false,
				dblClickDelay : 200
			});

		} catch (e) {
			alert(e);
		}
	}
=======
>>>>>>> .r16
</script>
</html>
