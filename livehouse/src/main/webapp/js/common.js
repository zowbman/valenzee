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