function formSign(){
    $('.form-sigin').find('.hint').attr('style','display:none');
    var ySubmit = true;
    $.each($('.form-sigin').find('input'),function(i,item){
        if(!ySubmit){
            return;
        }
        if(null == $(item).val() || '' == $(item).val()){
            $('.form-sigin').find('.hint-'+ $(item).attr('name') +'').attr('style','display:block;');
            $(item).focus();
            ySubmit = false;
        }
    });
    return ySubmit;
}