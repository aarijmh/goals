<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link type="text/css" href="css/base.css" rel="stylesheet" />
<link type="text/css" href="css/ForceDirected.css" rel="stylesheet" />
<script type="text/javascript" src="script/jit.js"></script>
<script type="text/javascript">
var labelType, useGradients, nativeTextSupport, animate;

(function() {
  var ua = navigator.userAgent,
      iStuff = ua.match(/iPhone/i) || ua.match(/iPad/i),
      typeOfCanvas = typeof HTMLCanvasElement,
      nativeCanvasSupport = (typeOfCanvas == 'object' || typeOfCanvas == 'function'),
      textSupport = nativeCanvasSupport 
        && (typeof document.createElement('canvas').getContext('2d').fillText == 'function');
  //I'm setting this based on the fact that ExCanvas provides text support for IE
  //and that as of today iPhone/iPad current text support is lame
  labelType = (!nativeCanvasSupport || (textSupport && !iStuff))? 'Native' : 'HTML';
  nativeTextSupport = labelType == 'Native';
  useGradients = nativeCanvasSupport;
  animate = !(iStuff || !nativeCanvasSupport);
})();

var Log = {
  elem: false,
  write: function(text){
    if (!this.elem) 
      this.elem = document.getElementById('log');
    this.elem.innerHTML = text;
    this.elem.style.left = (500 - this.elem.offsetWidth / 2) + 'px';
  }
};
var fd;
var st ;
function init(){
  // init data
  var json = [
      {
        "adjacencies": [
            "graphnode21", 
            {
              "nodeTo": "graphnode1",
              "nodeFrom": "graphnode0",
              "data": {
                "$color": "#557EAA"
              }
            }, {
              "nodeTo": "graphnode13",
              "nodeFrom": "graphnode0",
              "data": {
                "$color": "#909291"
              }
            }, {
              "nodeTo": "graphnode14",
              "nodeFrom": "graphnode0",
              "data": {
                "$color": "#557EAA"
              }
            }, {
              "nodeTo": "graphnode15",
              "nodeFrom": "graphnode0",
              "data": {
                "$color": "#557EAA"
              }
            }, {
              "nodeTo": "graphnode16",
              "nodeFrom": "graphnode0",
              "data": {
                "$color": "#557EAA"
              }
            }, {
              "nodeTo": "graphnode17",
              "nodeFrom": "graphnode0",
              "data": {
                "$color": "#557EAA"
              }
            }
        ],
        "data": {
          "$color": "#83548B",
          "$type": "circle",
          "$dim": 10
        },
        "id": "graphnode0",
        "name": "graphnode0"
      }, {
        "adjacencies": [
            {
              "nodeTo": "graphnode2",
              "nodeFrom": "graphnode1",
              "data": {
                "$color": "#557EAA"
              }
            }, {
              "nodeTo": "graphnode4",
              "nodeFrom": "graphnode1",
              "data": {
                "$color": "#909291"
              }
            }, {
              "nodeTo": "graphnode5",
              "nodeFrom": "graphnode1",
              "data": {
                "$color": "#909291"
              }
            }, {
              "nodeTo": "graphnode6",
              "nodeFrom": "graphnode1",
              "data": {
                "$color": "#909291"
              }
            }, {
              "nodeTo": "graphnode7",
              "nodeFrom": "graphnode1",
              "data": {
                "$color": "#909291"
              }
            }, {
              "nodeTo": "graphnode8",
              "nodeFrom": "graphnode1",
              "data": {
                "$color": "#909291"
              }
            }, {
              "nodeTo": "graphnode10",
              "nodeFrom": "graphnode1",
              "data": {
                "$color": "#557EAA"
              }
            }, {
              "nodeTo": "graphnode11",
              "nodeFrom": "graphnode1",
              "data": {
                "$color": "#909291"
              }
            }, {
              "nodeTo": "graphnode12",
              "nodeFrom": "graphnode1",
              "data": {
                "$color": "#909291"
              }
            }, {
              "nodeTo": "graphnode13",
              "nodeFrom": "graphnode1",
              "data": {
                "$color": "#557EAA"
              }
            }, {
              "nodeTo": "graphnode14",
              "nodeFrom": "graphnode1",
              "data": {
                "$color": "#557EAA"
              }
            }, {
              "nodeTo": "graphnode15",
              "nodeFrom": "graphnode1",
              "data": {
                "$color": "#557EAA"
              }
            }, {
              "nodeTo": "graphnode16",
              "nodeFrom": "graphnode1",
              "data": {
                "$color": "#909291"
              }
            }, {
              "nodeTo": "graphnode17",
              "nodeFrom": "graphnode1",
              "data": {
                "$color": "#557EAA"
              }
            }
        ],
        "data": {
          "$color": "#EBB056",
          "$type": "circle",
          "$dim": 11
        },
        "id": "graphnode1",
        "name": "graphnode1"
      }, {
        "adjacencies": [
            {
              "nodeTo": "graphnode5",
              "nodeFrom": "graphnode2",
              "data": {
                "$color": "#909291"
              }
            }, {
              "nodeTo": "graphnode9",
              "nodeFrom": "graphnode2",
              "data": {
                "$color": "#557EAA"
              }
            }, {
              "nodeTo": "graphnode18",
              "nodeFrom": "graphnode2",
              "data": {
                "$color": "#557EAA"
              }
            }
        ],
        "data": {
          "$color": "#416D9C",
          "$type": "circle",
          "$dim": 7
        },
        "id": "graphnode2",
        "name": "graphnode2"
      }, {
        "adjacencies": [
            {
              "nodeTo": "graphnode5",
              "nodeFrom": "graphnode3",
              "data": {
                "$color": "#909291"
              }
            }, {
              "nodeTo": "graphnode9",
              "nodeFrom": "graphnode3",
              "data": {
                "$color": "#557EAA"
              }
            }, {
              "nodeTo": "graphnode10",
              "nodeFrom": "graphnode3",
              "data": {
                "$color": "#557EAA"
              }
            }, {
              "nodeTo": "graphnode12",
              "nodeFrom": "graphnode3",
              "data": {
                "$color": "#557EAA"
              }
            }
        ],
        "data": {
          "$color": "#416D9C",
          "$type": "square",
          "$dim": 10
        },
        "id": "graphnode3",
        "name": "graphnode3"
      }, {
        "adjacencies": [],
        "data": {
          "$color": "#83548B",
          "$type": "square",
          "$dim": 11
        },
        "id": "graphnode4",
        "name": "graphnode4"
      }, {
        "adjacencies": [
          {
            "nodeTo": "graphnode9",
            "nodeFrom": "graphnode5",
            "data": {
              "$color": "#909291"
            }
          }
        ],
        "data": {
          "$color": "#C74243",
          "$type": "triangle",
          "$dim": 8
        },
        "id": "graphnode5",
        "name": "graphnode5"
      }, {
        "adjacencies": [
            {
              "nodeTo": "graphnode10",
              "nodeFrom": "graphnode6",
              "data": {
                "$color": "#557EAA"
              }
            }, {
              "nodeTo": "graphnode11",
              "nodeFrom": "graphnode6",
              "data": {
                "$color": "#557EAA"
              }
            }
        ],
        "data": {
          "$color": "#83548B",
          "$type": "circle",
          "$dim": 11
        },
        "id": "graphnode6",
        "name": "graphnode6"
      }, {
        "adjacencies": [],
        "data": {
          "$color": "#EBB056",
          "$type": "triangle",
          "$dim": 12
        },
        "id": "graphnode7",
        "name": "graphnode7"
      }, {
        "adjacencies": [],
        "data": {
          "$color": "#C74243",
          "$type": "star",
          "$dim": 10
        },
        "id": "graphnode8",
        "name": "graphnode8"
      }, {
        "adjacencies": [],
        "data": {
          "$color": "#83548B",
          "$type": "circle",
          "$dim": 12
        },
        "id": "graphnode9",
        "name": "graphnode9"
      }, {
        "adjacencies": [
          {
            "nodeTo": "graphnode11",
            "nodeFrom": "graphnode10",
            "data": {
              "$color": "#909291"
            }
          }
        ],
        "data": {
          "$color": "#70A35E",
          "$type": "triangle",
          "$dim": 11
        },
        "id": "graphnode10",
        "name": "graphnode10"
      }, {
        "adjacencies": [],
        "data": {
          "$color": "#70A35E",
          "$type": "circle",
          "$dim": 11
        },
        "id": "graphnode11",
        "name": "graphnode11"
      }, {
        "adjacencies": [],
        "data": {
          "$color": "#83548B",
          "$type": "triangle",
          "$dim": 10
        },
        "id": "graphnode12",
        "name": "graphnode12"
      }, {
        "adjacencies": [
          {
            "nodeTo": "graphnode14",
            "nodeFrom": "graphnode13",
            "data": {
              "$color": "#557EAA"
            }
          }
        ],
        "data": {
          "$color": "#EBB056",
          "$type": "star",
          "$dim": 7
        },
        "id": "graphnode13",
        "name": "graphnode13"
      }, {
        "adjacencies": [],
        "data": {
          "$color": "#EBB056",
          "$type": "triangle",
          "$dim": 12
        },
        "id": "graphnode14",
        "name": "graphnode14"
      }, {
        "adjacencies": [
            {
              "nodeTo": "graphnode16",
              "nodeFrom": "graphnode15",
              "data": {
                "$color": "#557EAA"
              }
            }, {
              "nodeTo": "graphnode17",
              "nodeFrom": "graphnode15",
              "data": {
                "$color": "#557EAA"
              }
            }
        ],
        "data": {
          "$color": "#83548B",
          "$type": "triangle",
          "$dim": 11
        },
        "id": "graphnode15",
        "name": "graphnode15"
      }, {
        "adjacencies": [
          {
            "nodeTo": "graphnode17",
            "nodeFrom": "graphnode16",
            "data": {
              "$color": "#557EAA"
            }
          }
        ],
        "data": {
          "$color": "#C74243",
          "$type": "star",
          "$dim": 7
        },
        "id": "graphnode16",
        "name": "graphnode16"
      }, {
        "adjacencies": [],
        "data": {
          "$color": "#416D9C",
          "$type": "circle",
          "$dim": 7
        },
        "id": "graphnode17",
        "name": "graphnode17"
      }, {
        "adjacencies": [
            {
              "nodeTo": "graphnode19",
              "nodeFrom": "graphnode18",
              "data": {
                "$color": "#557EAA"
              }
            }, {
              "nodeTo": "graphnode20",
              "nodeFrom": "graphnode18",
              "data": {
                "$color": "#557EAA"
              }
            }
        ],
        "data": {
          "$color": "#EBB056",
          "$type": "triangle",
          "$dim": 9
        },
        "id": "graphnode18",
        "name": "graphnode18"
      }, {
        "adjacencies": [],
        "data": {
          "$color": "#70A35E",
          "$type": "circle",
          "$dim": 8
        },
        "id": "graphnode19",
        "name": "graphnode19"
      }, {
        "adjacencies": [],
        "data": {
          "$color": "#C74243",
          "$type": "star",
          "$dim": 8
        },
        "id": "graphnode20",
        "name": "graphnode20"
      }
  ];
  // end
  // init ForceDirected
 fd = new $jit.ForceDirected({
    //id of the visualization container
    injectInto: 'infovis',
    //Enable zooming and panning
    //by scrolling and DnD
    Navigation: {
      enable: true,
      //Enable panning events only if we're dragging the empty
      //canvas (and not a node).
      panning: 'avoid nodes',
      zooming: 10 //zoom speed. higher is more sensible
    },
    // Change node and edge styles such as
    // color and width.
    // These properties are also set per node
    // with dollar prefixed data-properties in the
    // JSON structure.
    Node: {
      overridable: true
    },
    Edge: {
      overridable: true,
      color: '#23A4FF',
      lineWidth: 0.4
    },
    //Native canvas text styling
    Label: {
      type: labelType, //Native or HTML
      size: 10,
      style: 'bold'
    },
    //Add Tips
    Tips: {
      enable: true,
      onShow: function(tip, node) {
        //count connections
        var count = 0;
        node.eachAdjacency(function() { count++; });
        //display node info in tooltip
        tip.innerHTML = "<div class=\"tip-title\">" + node.name + "</div>"
          + "<div class=\"tip-text\"><b>connections:</b> " + count + "</div>";
      }
    },
    // Add node events
    Events: {
      enable: true,
      type: 'Native',
      //Change cursor style when hovering a node
      onMouseEnter: function() {
        fd.canvas.getElement().style.cursor = 'move';
      },
      onMouseLeave: function() {
        fd.canvas.getElement().style.cursor = '';
      },
      //Update node positions when dragged
      onDragMove: function(node, eventInfo, e) {
          var pos = eventInfo.getPos();
          node.pos.setc(pos.x, pos.y);
          fd.plot();
      },
      //Implement the same handler for touchscreens
      onTouchMove: function(node, eventInfo, e) {
        $jit.util.event.stop(e); //stop default touchmove event
        this.onDragMove(node, eventInfo, e);
      },
      //Add also a click handler to nodes
      onClick: function(node) {
        if(!node) return;
        // Build the right column relations list.
        // This is done by traversing the clicked node connections.
        var html = "<h4>" + node.name + "</h4><b> connections:</b><ul><li>",
            list = [];
        node.eachAdjacency(function(adj){
          list.push(adj.nodeTo.name);
        });
        //append connections information
        $jit.id('inner-details').innerHTML = html + list.join("</li><li>") + "</li></ul>";
      }
    },
    //Number of iterations for the FD algorithm
    iterations: 200,
    //Edge length
    levelDistance: 130,
    // Add text to the labels. This method is only triggered
    // on label creation and only for DOM labels (not native canvas ones).
    onCreateLabel: function(domElement, node){
      domElement.innerHTML = node.name;
      var style = domElement.style;
      style.fontSize = "0.8em";
      style.color = "#ddd";
    },
    // Change node styles when DOM labels are placed
    // or moved.
    onPlaceLabel: function(domElement, node){
      var style = domElement.style;
      var left = parseInt(style.left);
      var top = parseInt(style.top);
      var w = domElement.offsetWidth;
      style.left = (left - w / 2) + 'px';
      style.top = (top + 10) + 'px';
      style.display = '';
    }
  });
  // load JSON data.
  fd.loadJSON(json);
  // compute positions incrementally and animate.
  fd.computeIncremental({
    iter: 40,
    property: 'end',
    onStep: function(perc){
      Log.write(perc + '% loaded...');
    },
    onComplete: function(){
      Log.write('done');
      fd.animate({
        modes: ['linear'],
        transition: $jit.Trans.Elastic.easeOut,
        duration: 2500
      });
    }
  });
  // end
}

function addNode()
{
//alert(st.graph);
fd.graph.addNode({id:"ddd",name:"AArij",data:{"$type": "triangle","$dim": 7}});

//st.graph.addAdjacence(fd.graph.getNode("ddd"),fd.graph.getNode("graphnode18"),{"$color": "#FF0000"});


//
}

function collapse()
{
//var viz = new $jit.Viz({});

var graph = $jit.Graph();

graph.addNode({id:"ddd",name:"AArij",data:{"$type": "triangle","$dim": 7}});

alert("sdfd");
}
</script>
</head>
<body onload="init();">
<div id="container">

<div id="left-container">



        <div class="text">
        <h4>
Force Directed Static Graph    
        </h4> 

            A static JSON Graph structure is used as input for this visualization.<br /><br />
            You can <b>zoom</b> and <b>pan</b> the visualization by <b>scrolling</b> and <b>dragging</b>.<br /><br />
            You can <b>change node positions</b> by <b>dragging the nodes around</b>.<br /><br />
            The clicked node's connections are displayed in a relations list in the right column.<br /><br />
            The JSON static data is customized to provide different node types, colors and widths.
            
        </div>

        <div id="id-list"></div>


<div style="text-align:center;"><a href="example1.js">See the Example Code</a></div>            
</div>

<div id="center-container">
    <div id="infovis"></div>    
</div>

<div id="right-container">

<div id="inner-details"></div>

</div>

<div id="log"></div>
</div>

<input type="button" onclick="addNode();"> 
<input type="button" onclick="collapse();" value="collapse"> 

</body>
</html>