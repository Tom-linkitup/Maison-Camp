$(document).ready(function() {
  let lis = $(".click-me");
  lis.click(function(){
    lis.removeClass("active");
    $(this).addClass("active");
  })
    
  $("#info-edit").click(function(){
    $("#password-show").css("display","none");
    $("#credit-show").css("display","none");
    $("#info-show").css("display","");
  })

  $("#password-edit").click(function(){
    $("#info-show").css("display","none");
    $("#credit-show").css("display","none");
    $("#password-show").css("display","");
  })

  $("#payment").click(function(){
    $("#info-show").css("display","none");
    $("#password-show").css("display","none");
    $("#credit-show").css("display","");
  })

  //credit card
  new Card({
    form: document.querySelector('#credit'),
    container: '.card',
    formSelectors: {
      numberInput: 'input[name=payment]',
      expiryInput: 'input[name=expiry]',
      cvcInput: 'input[name=cvv]',
      nameInput: 'input[name=name]'
    },
  
    width: 390, // optional — default 350px
    formatting: true,
  
    placeholders: {
      number: '•••• •••• •••• ••••',
      name: 'Full Name',
      expiry: '••/••',
      cvc: '•••'
    }
  })

  $(".plus-card").click(function(){
    $(".credit-lightBox").css("display","");
  })

  $("#cancel-plus").click(function(){
    $(".credit-lightBox").css("display","none");
  })
});