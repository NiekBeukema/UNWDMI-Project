/*SweetAlert Init*/

$(function() {
	"use strict";
	
	var SweetAlert = function() {};

    //examples 
    SweetAlert.prototype.init = function() {
        $('.swal-btn-success').click(function(e){
            e.preventDefault();
            swal({
                title: "Good job!",
                text: "You clicked the button!",
                type: "success",
                confirmButtonClass: "btn-success",
                confirmButtonText: "Success"
            });
        });


    },
    //init
    $.SweetAlert = new SweetAlert, $.SweetAlert.Constructor = SweetAlert;
	
	$.SweetAlert.init();
});