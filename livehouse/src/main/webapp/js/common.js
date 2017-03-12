function formFieldError(errors){
    var errorString = '';
    $('.form-sigin').find('.hint').attr('style','display:none');
    $.each(errors,function(i,item){
        errorString += item.message;
        $('.form-sigin').find('.hint').text('*'+errorString);
        $('.form-sigin').find('.hint').attr('style','display:block;');
        $(item.element).focus();
        return false;
    });
}
$(document).keypress(function(e){
	if(e.ctrlKey && e.which == 13 || e.which == 10) {
		$('#loginModal').modal('show');
	} else if (e.shiftKey && e.which==13 || e.which == 10) {
		$('#loginModal').modal('show');
	}
	$('#pwd').focus();
});
$(function(){
    $('#loginModal').find('form').submit(function(e){
        e.preventDefault();
        var referrerUrl = window.location.href;
        referrerUrl = referrerUrl.replace(/^http:\/\/[^/]+/, "");
        $.ajax({
            type: 'POST',
            url: '/frontend/json/v1/login',
            data: {
                pwd: $('#pwd').val(),
                referrerUrl:referrerUrl
            },
            success: function(data){
                alert(data.msg);
                if(data.code==0){
                    location.href=data.data
                }
            },
            error: function() {
                alert("请求数据失败");
            },
            complete: function(){
                $('#loginModal').modal('hide');
            }
        });
    });
});

