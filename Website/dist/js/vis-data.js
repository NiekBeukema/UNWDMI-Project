/*Flot Init*/
var dataset;
$(document).ready(function() {
    var items;
    $.ajax({
        //Doe iets met date: en cloudcoverage:
        url : 'config/vischartini.php',
        type : 'POST',
        dataType : 'json',
        success : function (result) {
            items = [];
            for (i=0; i < Object.keys(result).length; i++){
                console.log("Adding Items: " + {x: result[i][0], y: result[i][1]});
                items[i] = {x: result[i][0], y: result[i][1]};
            }
            makeGraph(items);
        },
        error : function (obj, ovj, error) {
            reportError(error);
        }
    })
});

function makeGraph(items) {
    var getTime = new Date();
    var container = document.getElementById('cloudchart');
    console.log(items);
    dataset = new vis.DataSet(items);
    var options = {
        start: getTime.getTime()-1000000, //-86400000 for last day
        end: getTime.getTime()+6000000,
        dataAxis: {
            left: {
                range: {
                    min: 30,
                    max: 100
                },
                title: {
                    text: 'Coverage'
                }
            },

        },
        drawPoints: {
            enabled: true,
            size: 2,
            style: 'circle'
        }


    };
    var graph2d = new vis.Graph2d(container, dataset, options);
    RefreshGraph();
}


function RefreshGraph() {
    $.ajax({
        url : 'config/vischart.php',
        type : 'POST',
        dataType : 'json',
        success : function (result) {
            var getTime = new Date();
            dataset.update({x: result[0][0], y: result[0][1]});
        },
        error : function (obj, ovj, error) {
            console.log(error);
        }
    })
    setTimeout(RefreshGraph, 3000);
}

