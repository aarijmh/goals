
 var cyG;
function intializeCytoScenario() {

	 cyG = 
			cytoscape(
					{
						container: $('#cyGraph')[0],
						style : cytoscape.stylesheet().selector('node').css({
							'shape' : 'data(faveShape)',
							'width' : 'mapData(weight, 40, 80, 20, 60)',
							'content' : 'data(name)',
							'text-valign' : 'center',
							'text-outline-width' : 2,
							'text-outline-color' : 'data(faveColor)',
							'background-color' : 'data(faveColor)',
							'color' : '#fff'
						}).selector(':selected').css({
							'border-width' : 3,
							'border-color' : '#333'
						})

						.selector('$node > node').css({
							'padding-top' : '20px',
							'padding-left' : '10px',
							'padding-bottom' : '10px',
							'padding-right' : '10px',
							'text-valign' : 'top',
							'text-halign' : 'center'
						})

						.selector('edge').css({
							'opacity' : 0.666,
							'width' : 'mapData(strength, 70, 100, 2, 6)',
							'target-arrow-shape' : 'triangle',
							'source-arrow-shape' : 'circle',
							'line-color' : 'data(faveColor)',
							'source-arrow-color' : 'data(faveColor)',
							'target-arrow-color' : 'data(faveColor)'
						}).selector('edge.questionable').css({
							'line-style' : 'dotted',
							'target-arrow-shape' : 'diamond'
						}).selector('.faded').css({
							'opacity' : 0.75,
							'text-opacity' : 0.75
						}),

						layout : {
							name : 'breadthfirst',
							directed : true,
							padding : 10
						},
						elements : {
							nodes : [ {
								data : {
									id : 'a',
									type : 0,
									label : 'Concepts',
									name : 'Concepts'
								}
							}, {
								data : {
									id : 'b',
									type : 0,
									label : 'Pedagogical Resources',
									name : 'Pedagogical Resources'
								}
							}, {
								data : {
									id : 'c',
									type : 0,
									label : 'Game Resources',
									name : 'Game Resources'
								}
							} ]

						},

						ready : function() {
							cyG = this;
							cyG.elements().unselectify();
							cyG.on('tap', 'node', function(e) {
								var node = e.cyTarget;
								var neighborhood = node.neighborhood()
										.add(node);

								cyG.elements().addClass('faded');

								neighborhood.removeClass('faded');
								cyG.filter('node[id = "a"]')
										.removeClass('faded');
								cyG.filter('node[id = "b"]')
										.removeClass('faded');
								cyG.filter('node[id = "c"]')
										.removeClass('faded');
							});
							cyG.on('tap', function(e) {
								if (e.cyTarget === cyG) {
									cyG.elements().removeClass('faded');
								}
							});

						}
					});
}




function addData(json) {

				//var json;
				var conceptMap = {};
				var prMap = {};
				var grMap = {};
				try {
					json = JSON.parse(json);

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

					$.each(json.gr, function() {
						grMap['gr_' + this.id] = this;
						g.addNode('gr_' + this.id, {
							label : this.name,
							width : 144,
							height : 100
						});
					});

					$.each(json.cr, function() {
						g.addEdge('cr_' + this.id, 'con_' + this.parent, 'con_'
								+ this.child);
					});

					$.each(json.cpr, function() {
						g.addEdge('cpr_' + this.id, 'con_' + this.parent, 'pr_'
								+ this.child);
					});

					$.each(json.gpr, function() {
						g.addEdge('gpr_' + this.id, 'pr_' + this.parent, 'gr_'
								+ this.child);
					});

					var layout = dagre.layout().nodeSep(2).run(g);

					layout.eachNode(function(u, value) {
						var node = conceptMap[u] || prMap[u] || grMap[u];
						try
						{
							var createdNode = createNodeScenario(node.type, node.id, node.name,
									value.x, value.y,node.selected);
						cyG.add(createdNode);
						
						}
						catch(e)
						{
							alert(e);
						}
					});

					$.each(json.cr, function() {
						cyG.add({
							group : "edges",
							data : {
								weight : 75,
								id : 'ccr_' + this.id,
								name : this.name,
								source : 'con_' + this.parent.toString(),
								target : 'con_' + this.child.toString(),
								type : this.type
							}
						});
					});

					$.each(json.cpr, function() {
						cyG.add({
							group : "edges",
							data : {
								weight : 75,
								id : 'pcr_' + this.id,
								name : this.name,
								source : 'con_' + this.parent.toString(),
								target : 'pr_' + this.child.toString(),
								type : this.type
							}
						});
					});

					$.each(json.gpr, function() {
						cyG.add({
							group : "edges",
							data : {
								weight : 75,
								id : 'gpr_' + this.id,
								name : this.name,
								source : 'pr_' + this.parent.toString(),
								target : 'gr_' + this.child.toString(),
								type : this.type
							}
						});
					});

					var options = {
						name : 'preset',

						fit : true, // whether to fit to viewport
						ready : undefined, // callback on layoutready
						stop : undefined, // callback on layoutstop
						positions : undefined, // map of (node id) => (position
												// obj)
						zoom : undefined, // the zoom level to set (prob want
											// fit =
						// false if set)
						pan : undefined, // the pan level to set (prob want
											// fit =
						// false if set)
						padding : 30
					// padding on fit
					};
					// cyG.layout(options);
					cyG.layout(options);
					cyG.forceRender();

					var defaults = ({
						zoomFactor : 0.05, // zoom factor per zoom tick
						zoomDelay : 45, // how many ms between zoom ticks
						minZoom : 0.1, // min zoom level
						maxZoom : 10, // max zoom level
						fitPadding : 50, // padding when fitting
						panSpeed : 10, // how many ms in between pan ticks
						panDistance : 10, // max pan distance per tick
						panDragAreaSize : 75, // the length of the pan drag
												// box in
						// which the vector for panning is
						// calculated (bigger = finer control of
						// pan speed and direction)
						panMinPercentSpeed : 0.25, // the slowest speed we can
													// pan by
						// (as a percent of panSpeed)
						panInactiveArea : 8, // radius of inactive area in
												// pan drag
						// box
						panIndicatorMinOpacity : 0.5, // min opacity of pan
														// indicator
						// (the draggable nib); scales
						// from this to 1.0
						autodisableForMobile : true, // disable the panzoom
						// completely for mobile (since
						// we don't really need it with
						// gestures like pinch to zoom)

						// icon class names
						sliderHandleIcon : 'fa fa-minus',
						zoomInIcon : 'fa fa-plus',
						zoomOutIcon : 'fa fa-minus',
						resetIcon : 'fa fa-expand'
					});

					cyG.panzoom(defaults);

					/*
					 * $('#cy').cyNavigator({ container : true,
					 * viewLiveFramerate : 0, thumbnailEventFramerate : 30,
					 * thumbnailLiveFramerate : false, dblClickDelay : 200 });
					 */

				} catch (e) {
					alert(e);
				}

			}


function createNodeScenario(type, id, name, x, y,selected) {

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
		if(selected)
			faveColor = '#335CD6';
		break;
	case 2:
		prefix = 'pr_';
		faveColor = '#86B342';
		faveShape = 'octagon';
		parent = 'b';
		if(selected)
			faveColor = '#335CD6';
		break;
	case 3:
		prefix = 'gr_';
		faveColor = '#CAA1Ec';
		faveShape = 'octagon';
		parent = 'c';
		if(selected)
			faveColor = '#335CD6';
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
			parent : parent,
			type : type
		},
		position : {
			x : x,
			y : y
		}
	};
	return node;
}

