$(function(){
    //开始时间下拉获取数据
    $.ajax({
        type: 'GET',
        url: "/dict/json/v1/getStartTime",
        success: function(data){
            if(data.code == 0){
                $.each(data.data,function(i,item){
                    $("#startTime").append("<option value="+item.id+">"+item.startTime+"</option>");
                });
                $('#startTime').selectpicker('refresh');
            }else{
                alert(data.msg);
            }
        },
        error: function() {
            alert("请求数据失败");
        }
    });
    //排班校验
    $('#form-apply').validate({
        submitHandler: function(form){
            //排班申请
            $.ajax({
                type: 'POST',
                url: "/frontend/json/v1/applySubmit",
                data: $(form).serialize(),
                success: function(data){
                    if(data.code == 0){
                        alert("提交成功，请等待结果");
                        $('#form-apply')[0].reset();
                    }else{
                        alert(data.msg);
                    }
                },
                error: function() {
                    alert("提交失败");
                }
            });
        },
        rules:{
            bigoID:{
                required: true,
            },
            whatsAppNumber:{
                required: true,
            }
        },
        messages:{
            bigoID:{
                required:'*Please Enter Bigo ID'
            },
            whatsAppNumber:{
                required:'*Please Enter WhatsApp Number'
            }
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
