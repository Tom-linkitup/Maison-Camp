        $(document).ready(() => {
            /*height in pixels when the navbar becomes non opaque*/
//            $(window).scroll(function() {
//                if ($(this).scrollTop() > 10) {
//                    $('.header').addClass('opaque');
//                } else {
//                    $('.header').removeClass('opaque');
//                }
//            });
        	let id,info,price,name,quantity=$('#sendQuantity').val();
        	
//	新增資訊到燈箱裡
            $(".card").click(function(){
            	id = $(this).find(".id").val();            	
            	info = $(this).find(".info").val();            	
            	price = $(this).find(".price").val();
            	name = $(this).find(".name").text();
                $("#white").css('display','block');
                $("#black").css('display','block');
                $('.boxImg').attr("src",$(this).children(".itemImg").attr("src"));
                $('.lightboxName').html(name);
                $('.lightboxInfo').html(info);
                $('.lightboxPrice').text("$NT"+price );
//------------------------------------------
                $("#sendPrice").val(price); //傳值到FORM表單裡
                $("#sendName").val(name);
                $("#sendItemId").val(id);
            })
//關燈箱
            $("#black").click(function(){
                $("#white").css('display','none');
                $("#black").css('display','none');
                $("#sendQuantity").val(1);
            });
            
//ajax加入購物車   
            $('.addtoCart').click(function(){
            	
            	$.ajax({
            		url:"<%=request.getContextPath()%>/ShoppingServlet",
            		type:"POST",
            		data:{
            			"action":"ADD",
            			"itemId":id,
            			"price":price,
            			"name":name,
            			"quantity":quantity
            		},
            		success: function(){
            			console.log('新增成功');
            		},
            		error:function(e){
            			console.log(e);
            		}
            	});
            });
            
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
        
        
 
