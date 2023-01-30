$(".commentGood>div>div>img").click(function(){
    $(this).css("display", "none");
    $(this).siblings().css("display", "block");
});