/*Flot Init*/
var dataset;
$(document).ready(function() {
    var items;
    $.ajax({

        url : 'config/vischart.php',
        type : 'POST',
        dataType : 'json',
        success : function (result) {
            items = [{x: result[0][0], y: result[0][1]}];
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

