function intializeCyto() {

	$('#cy')
			.cytoscape(
					{
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
							'border-width' : 4,
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
							window.cy = this;
							cy.elements().unselectify();
							cy.on('tap', 'node', function(e) {
								var node = e.cyTarget;
								var neighborhood = node.neighborhood()
										.add(node);

								cy.elements().addClass('faded');

								neighborhood.removeClass('faded');
								cy.filter('node[id = "a"]')
										.removeClass('faded');
								cy.filter('node[id = "b"]')
										.removeClass('faded');
								cy.filter('node[id = "c"]')
										.removeClass('faded');
							});
							cy.on('tap', function(e) {
								if (e.cyTarget === cy) {
									cy.elements().removeClass('faded');
								}
							});

							var defaults = {
								menuRadius : 100, // the radius of the
													// circular menu
								// in pixels
								selector : 'node,edge', // elements matching
														// this
								// Cytoscape.js selector will
								// trigger cxtmenus
								commands : [ // an array of commands to list
												// in the
										// menu
										{
											content : '<span class="fa fa-flash fa-2x">Edit</span>',
											select : function() {
												switch (this.data('type')) {
												case 1:
													$("#conceptLink")
															.attr(
																	'href',
																	'concept.action?action=showAddEdit&id='
																			+ parseInt(this
																					.data(
																							'id')
																					.split(
																							'_')[1])
																			+ '&projectId='
																			+ projectId);
													$("#conceptLink").click();
													break;
												case 2:
													$("#prLink")
															.attr(
																	'href',
																	'pedagogicalresource.action?action=showAddEdit&id='
																			+ parseInt(this
																					.data(
																							'id')
																					.split(
																							'_')[1])
																			+ '&projectId='
																			+ projectId);
													$("#prLink").click();
													break;
												case 3:
													$("#grLink")
															.attr(
																	'href',
																	'gameresource.action?action=showAddEdit&id='
																			+ parseInt(this
																					.data(
																							'id')
																					.split(
																							'_')[1])
																			+ '&projectId='
																			+ projectId);
													$("#grLink").click();
													break;
												case 4:
													$("#crLink")
															.attr(
																	'href',
																	'concept.action?action=showAddEditRelation&relationId='
																			+ parseInt(this
																					.data(
																							'id')
																					.split(
																							'_')[1])
																			+ '&projectId='
																			+ projectId);
													$("#crLink").click();
													break;
												case 5:
													$("#pcrLink")
															.attr(
																	'href',
																	'pedagogicalresource.action?action=showAddEditRelation&relationId='
																			+ parseInt(this
																					.data(
																							'id')
																					.split(
																							'_')[1])
																			+ '&projectId='
																			+ projectId);
													$("#pcrLink").click();
													break;
												case 6:
													$("#gprLink")
															.attr(
																	'href',
																	'gameresource.action?action=showAddEditRelation&relationId='
																			+ parseInt(this
																					.data(
																							'id')
																					.split(
																							'_')[1])
																			+ '&projectId='
																			+ projectId);
													$("#gprLink").click();
													break;
												}
											}
										},
										{
											content : '<span class="fa fa-star fa-2x">Delete</span>',
											select : function() {

												var node = this;
												bootbox
														.confirm(
																"This is an irreversible action. Are you sure? ",
																function(result) {
																	if (result) {

																		var url = "";

																		switch (node
																				.data('type')) {
																		case 1:
																			url = 'concept.action?action=delete&id='
																					+ node
																							.data(
																									'id')
																							.split(
																									'_')[1];
																			break;
																		case 2:
																			url = 'pedagogicalresource.action?action=delete&id='
																					+ node
																							.data(
																									'id')
																							.split(
																									'_')[1];
																			break;
																		case 3:
																			url = 'gameresource.action?action=delete&id='
																					+ node
																							.data(
																									'id')
																							.split(
																									'_')[1];
																			break;
																		case 4:
																			url = 'concept.action?action=deleteRelation&relationId='
																					+ node
																							.data(
																									'id')
																							.split(
																									'_')[1];
																			break;
																		case 5:
																			url = 'pedagogicalresource.action?action=deleteRelation&relationId='
																					+ node
																							.data(
																									'id')
																							.split(
																									'_')[1];
																			break;
																		case 6:
																			url = 'gameresource.action?action=deleteRelation&relationId='
																					+ node
																							.data(
																									'id')
																							.split(
																									'_')[1];
																			break;
																		}

																		$
																				.ajax(
																						{
																							url : url,
																							context : document.body
																						})
																				.done(
																						function(
																								data) {

																							bootbox
																									.alert({
																										message : 'Entity deleted successfully',
																										title : 'Entity delete message',
																										closeButton : true
																									});

																							var filter = "";
																							switch (node
																									.data('type')) {
																							case 1:
																							case 2:
																							case 3:
																								filter = 'node[id = "'
																										+ node
																												.data('id')
																										+ '"]';
																								break;
																							case 4:
																							case 5:
																							case 6:
																								filter = 'edge[id = "'
																										+ node
																												.data('id')
																										+ '"]';
																								break;
																							}
																							var n = cy
																									.filter(filter);
																							if (n.length != 0) {
																								n
																										.remove();
																							}

																						})
																				.error(
																						function(
																								a,
																								b,
																								c) {
																							alert(a
																									+ "\n"
																									+ b
																									+ "\n"
																									+ c);
																						});
																	}
																});

											}
										} ],
								fillColor : 'rgba(0, 0, 0, 0.75)', // the
																	// background
								// colour of the
								// menu
								activeFillColor : 'rgba(92, 194, 237, 0.75)', // the
								// colour
								// used
								// to
								// indicate
								// the
								// selected
								// command
								activePadding : 20, // additional size in pixels
													// for the
								// active command
								indicatorSize : 24, // the size in pixels of the
													// pointer
								// to the active command
								separatorWidth : 3, // the empty spacing in
													// pixels
								// between successive commands
								spotlightPadding : 4, // extra spacing in
														// pixels
								// between the element and the
								// spotlight
								minSpotlightRadius : 24, // the minimum
															// radius in
								// pixels of the spotlight
								maxSpotlightRadius : 38, // the maximum
															// radius in
								// pixels of the spotlight
								itemColor : 'white', // the colour of text in
														// the
								// command's content
								itemTextShadowColor : 'black', // the text
																// shadow
								// colour of the
								// command's content
								zIndex : 9999
							// the z-index of the ui div
							};

							cy.cxtmenu(defaults);

						}
					});
}

function createNode(type, id, name, x, y) {

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
		faveColor = '#CAA1Ec';
		faveShape = 'octagon';
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

function addId() {

	var formData = {
		action : "showAddEditConceptJson",
		projectId : projectId
	};

	$.ajax({
		url : "concept.action",
		type : 'POST',
		data : formData,
		context : document.body
	}).done(
			function(data) {

				var json;
				var conceptMap = {};
				var prMap = {};
				var grMap = {};
				try {
					json = JSON.parse(data);

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
						cy.add(createNode(node.type, node.id, node.name,
								value.x, value.y));
					});

					$.each(json.cr, function() {
						cy.add({
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
						cy.add({
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
						cy.add({
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
					// cy.layout(options);
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

					cy.panzoom(defaults);

					/*
					 * $('#cy').cyNavigator({ container : true,
					 * viewLiveFramerate : 0, thumbnailEventFramerate : 30,
					 * thumbnailLiveFramerate : false, dblClickDelay : 200 });
					 */

				} catch (e) {
					alert(e);
				}

			}).fail(function(a, b, c) {
		alert(a + "\n" + b + "\n" + c);
	});

}
