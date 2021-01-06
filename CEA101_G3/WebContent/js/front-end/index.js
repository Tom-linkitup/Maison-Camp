$(document).ready(() => {
      /*height in pixels when the navbar becomes non opaque*/ 
    $(window).scroll(function() {
        if($(this).scrollTop() > 10)
        {
            $('.header').addClass('opaque');
        } else {
            $('.header').removeClass('opaque');
        }
    });

    $(".presentation-nav").hide()
    
    // news marquee
    let requestAnimationFrame = window.requestAnimationFrame || 
    window.mozRequestAnimationFrame ||
    window.webkitRequestAnimationFrame || 
    window.msRequestAnimationFrame;

    let cancelAnimationFrame = window.cancelAnimationFrame || 
    window.mozCancelAnimationFrame;

    let timer = null;
    let isPause = false;
    let t = null;

    function update() {
        if(isPause === false) {
            timer = undefined;
            clearTimeout(t);
            $(".marquee ul").animate({
                top: -50
            }, 500, function() {
                let $this = $(this);
                let dom = $this.find("li").eq(0).clone();
                $this.append(dom);
                $this.find("li").eq(0).remove();
                $this.css("top", "0");
            });
            t = setTimeout(() => {
                start()
            }, 4000);
        } 
    }

    function start() {
        if (!timer) {
            isPause = false;
            timer = requestAnimationFrame(update);
        }
    }

    function stop() {
        isPause = true;
        cancelAnimationFrame(timer);
        clearTimeout(t);
        timer = undefined;
    }

    start();

    $(".marquee").on("mouseenter", function() {
        stop()
    }).on("mouseleave", function() {
        start()
    });
    
    $("#checkboxtoggle").click((e) => {
        if($("#checkboxtoggle").prop("checked")){
            $('html,body').css("overflow", "hidden");
            $('body').css('height', '100%');
        }else if($("#checkboxtoggle").prop("checked") === false){
            $('html,body').removeAttr('style');
        }
    })

    $(window).scroll(function() {
        if($(this).scrollTop() > 1200)
        {
            $('.presentation-nav').show();
        } else {
            $('.presentation-nav').fadeOut("slow");
        }

    });

    $(".signup").click(function(){
        $(".content-1").css("display","none");
        $(".content-3").css("display","none");
        $(".content-2").css("display","block");
    })
    $(".login").click(function(){
        $(".content-2").css("display","none");
        $(".content-3").css("display","none");
        $(".content-1").css("display","block");
    })
    $(".forget").click(function(){
        $(".content-1").css("display","none");
        $(".content-2").css("display","none");
        $(".content-3").css("display","block");
    })

    $(".signin").click(function(){
        $("#lightBox").css("display","block");
    })

    $("#cancelLogin").click(() => {
        $("#lightBox").css("display","none");
    })
    
})