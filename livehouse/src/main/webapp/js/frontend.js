$(function(){
	//排班申请
	$('#form-apply').submit(function(e){
	    e.preventDefault();
        if(formSign()){
            alert('提交成功，请等待结果!')
        }
	});
	//申请查询
	$("#form-slot-query").submit(function(e){
	    e.preventDefault();
	    if(formSign()){
            //alert('提交成功，请等待结果!')
        }
	});
})
