        $(document).ready(() => {
            /*height in pixels when the navbar becomes non opaque*/
//            $(window).scroll(function() {
//                if ($(this).scrollTop() > 10) {
//                    $('.header').addClass('opaque');
//                } else {
//                    $('.header').removeClass('opaque');
//                }
//            });

            $(".card").click(function(){
            	var id = $(this).find(".id").val();            	
            	var info = $(this).find(".info").val();            	
            	var price = $(this).find(".price").val();
            	var name = $(this).find(".name").text();
                $("#white").css('display','block');
                $("#black").css('display','block');
                $('.boxImg').attr("src",$(this).children(".itemImg").attr("src"));
                $('.lightboxInfo').html(name + "<br>" + info);
                $('.lightboxPrice').text("$NT"+price );
                //------------------------------------------
                $("#sendPrice").val(price); //傳值到FORM表單裡
                $("#sendName").val(name);
                $("#sendItemId").val(id);
            })

            $("#black").click(function(){
                $("#white").css('display','none');
                $("#black").css('display','none');
                $("#sendQuantity").val(1);
            })
        });
        	
        $('.changeQuantity').click(function(){
        	let btn = $(this);
        	let oldValue = btn.parent().find("#sendQuantity").val();
        	
        	if(btn.text() == "+"){
        		var newVal = parseInt(oldValue) + 1;
        	}else{
        		if(oldValue > 0){
        			var newVal = parseInt(oldValue) - 1;
        		}else{
        			newVal = 0;
        		}
        	}    
        	$("#sendQuantity").val(newVal);
        });
 
