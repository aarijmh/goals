<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script src="script/jquery-2.1.0.js"></script>
<script src="script/cytoscape.js"></script>

<style type="text/css">
body {
	font: 14px helvetica neue, helvetica, arial, sans-serif;
}

#cy {
	height: 50%;
	width: 50%;
	position: absolute;
	left: 0;
	top: 0;
}
</style>

<script type="text/javascript">
	$(function() { // on dom ready

$('#id').click(function(){alert("asdsa");});

$('#cy').cytoscape({
			style : cytoscape.stylesheet().selector('node').css({
				'content' : 'data(name)',
				'text-valign' : 'center',
				'color' : 'white',
				'text-outline-width' : 2,
				'text-outline-color' : '#888'
			}).selector('edge').css({
				'target-arrow-shape' : 'triangle'
			}).selector(':selected').css({
				'background-color' : 'black',
				'line-color' : 'black',
				'target-arrow-color' : 'black',
				'source-arrow-color' : 'black'
			}).selector('.faded').css({
				'opacity' : 0.25,
				'text-opacity' : 0
			}),

			elements : {
				nodes : [ {
					data : {
						id : 'j',
						name : 'Jerry'
					}
				}, {
					data : {
						id : 'e',
						name : 'Elaine'
					}
				}, {
					data : {
						id : 'k',
						name : 'Kramer'
					}
				}, {
					data : {
						id : 'g',
						name : 'George'
					}
				} ],
				edges : [ {
					data : {
						source : 'j',
						target : 'e'
					}
				}, {
					data : {
						source : 'j',
						target : 'k'
					}
				}, {
					data : {
						source : 'j',
						target : 'g'
					}
				}, {
					data : {
						source : 'e',
						target : 'j'
					}
				}, {
					data : {
						source : 'e',
						target : 'k'
					}
				}, {
					data : {
						source : 'k',
						target : 'j'
					}
				}, {
					data : {
						source : 'k',
						target : 'e'
					}
				}, {
					data : {
						source : 'k',
						target : 'g'
					}
				}, {
					data : {
						source : 'g',
						target : 'j'
					}
				} ]
			},

			ready : function() {
				window.cy = this;

				// giddy up...

				cy.elements().unselectify();

				cy.on('tap', 'node', function(e) {
					var node = e.cyTarget;
					var neighborhood = node.neighborhood().add(node);

					cy.elements().addClass('faded');
					neighborhood.removeClass('faded');
				});

				cy.on('tap', function(e) {
					if (e.cyTarget === cy) {
						cy.elements().removeClass('faded');
					}
				});
			}
		});

	});


</script>
</head>
<body>
<div id="cy"></div>
<input type="button" value="asdsd" onclick="addMe();">
</body>
<script type="text/javascript">
function addMe()
{
alert("asff");
}


</script>
</html>