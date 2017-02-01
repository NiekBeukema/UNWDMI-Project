/*Export Table Init*/

"use strict";

$(document).ready(function() {
    $('#cloud').DataTable( {
        dom: 'lBfrtip',
        buttons: [
            'csv', 'excel', 'pdf', 'print'
        ],
        processing: true,
        serverSide: true,
        lengthMenu:[[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],
        "ajax":{
            url :"config/datatable.php", // json datasource
            type: "post",  // method  , by default get
        }
    } );
    $('#argentina-rt').DataTable( {
        dom: 'lBfrtip',
        buttons: [
            'csv', 'excel', 'pdf', 'print'
        ],
        processing: true,
        serverSide: true,
        lengthMenu:[[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]],
        "ajax":{
            url :"config/datatable-arg.php", // json datasource
            type: "post",  // method  , by default get
        }
    } );
} );

function RefreshGraph() {
    $.ajax({
        url : 'config/datatable.php',
        type : 'POST',
        dataType : 'json',
        success : function (result) {
            var getTime = new Date();
            datasetgpu1.update({x: getTime.getTime(), y: result['gpu1']});
            datasetcpu1.update({x: getTime.getTime(), y: result['cpu1']});
            $("#gputempbadge").text(result['gpu1']);
            $("#cputempbadge").text(result['cpu']);
        },
        error : function (obj, ovj, error) {
            reportError(error);
        }
    })
}