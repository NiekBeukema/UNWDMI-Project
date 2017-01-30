/*Export Table Init*/

"use strict";

$(document).ready(function() {
    $('#cloud').DataTable( {
        dom: 'Bfrtip',
        buttons: [
            'csv', 'excel', 'pdf', 'print'
        ],
        processing: true,
        serverSide: true,
        "ajax":{
            url :"config/datatable.php", // json datasource
            type: "post",  // method  , by default get
        }
    } );
    $('#visibility').DataTable( {
        dom: 'Bfrtip',
        buttons: [
            'csv', 'excel', 'pdf', 'print'
        ]
    } );
} );