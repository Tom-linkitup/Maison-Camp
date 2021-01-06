$(document).ready(() => {

    $("#float-sidebar").hide();
      /*height in pixels when the navbar becomes non opaque*/ 
    $(window).scroll(function() {
        if($(this).scrollTop() > 40)
        {
            $('#float-sidebar').show();
        } else {
            $('#float-sidebar').fadeOut("slow");
        }
    });
    
    $("#float-top i").click(() => {
        $("html, body").animate({scrollTop: 0}, 1000);
    });
    
    $(window).load(function() {
    	  $('.flexslider').flexslider({
    	    animation: "slide"
    	  });
    });
    
})