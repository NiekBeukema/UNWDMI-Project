/*Flot Init*/
$(document).ready(function() {
    "use strict";
    var totalPoints = 300;
    var options = {
        series:{
            shadowSize: 0,
            lines: {
                fill: false
            },
        },
        grid: {
            color: "#eee",
            hoverable: true,
            borderWidth: 0,
            backgroundColor: '#FFF'
        },
        colors: ["#566FC9"],
        tooltip: true,
        tooltipOpts: {
            content: "Y: %y",
            defaultTheme: false
        },
        yaxis: {
            min: 0,
            max: 100,
            font : {
                color : '#2f2c2c'
            }
        },
        xaxis: {
            show: false,
            font : {
                color : '#2f2c2c'
            }
        }
    };

    var data = [["2","87.7"]];

    $.plot("#cloudchart", data, options);

    var plot = $.plot("#cloudchart", data, options);
    // Fetch one series, adding to what we already have jquery.flot.time.js jquery.flot.time.js jquery.flot.time.js jquery.flot.time.js

    /***Line Chart***/

    function fetchData() {

        // Normally we call the same URL - a script connected to a
        // database - but in this case we only have static example
        // files, so we need to modify the URL.
        $.ajaxSetup({ cache: false });
        $.ajax({
            url: "config/flotchart.php",
            type: "post",
            dataType: "json",
            success: function onDataReceived(_data) {
                // Load all the data in one pass; if we only got partial
                // data we could merge it with what we already have.
                data = [_data];
                //plot.setData([_data]);
                //plot.draw();
                $.plot("#cloudchart", data, options);
                setTimeout(fetchData, 111);
            }
        });
    }
    setTimeout(fetchData, 111);
});

