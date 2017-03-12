$(function(){
    //开始时间下拉获取数据
    if($('#form-apply #startTime').length ==1){
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
    }
    //排班申请校验
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
                        $('#applyDate').selectpicker('render');
                        $('#startTime').selectpicker('render');
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
                digits:true,
                maxlength:15
            },
            whatsAppNumber:{
                required: true,
                maxlength:15
            }
        },
        messages:{
            bigoID:{
                required:'*Please Enter Bigo ID',
                digits:'*Please enter a valid Bigo ID ',
                maxlength:'*Please enter a length less than 15 characters'
            },
            whatsAppNumber:{
                required:'*Please Enter WhatsApp Number',
                maxlength:'*Please enter a length less than 15 characters'

            }
        }
    });
	//申请查询校验
    $('#form-slot-query').validate({
        submitHandler: function(form){
            $.ajax({
                type: 'POST',
                url: "/frontend/json/v1/applyQuery",
                data: $(form).serialize(),
                success: function(data){
                    if(data.code==0){
                        var _html='';
                        $.each(data.data,function(i,item){
                            _html+='<tr>';
                            _html+='<td>'+item.addTime+'</td>';
                            _html+='<td>'+item.applyDate+'</td>';
                            _html+='<td>'+item.applyStatus+'</td>';
                            _html+='</tr>';
                        });
                        $('#solt-query-list').html(_html);
                    }else{
                        alert(data.msg);
                    }
                },
                error: function() {
                    alert("提交失败");
                }
            });
        },
        errorPlacement: function(error, element) {
            $(element).closest('form').find('.error').append(error);
        },
        errorElement:'span',
        errorClass:'hint',
        rules:{
            bigoID:{
                required: true,
                digits:true,
                maxlength:15
            }
       },
       messages:{
            bigoID:{
                required:'*Please Enter Bigo ID',
                digits:'*Please enter a valid Bigo ID ',
                maxlength:'*Please enter a length less than 15 characters'
            }
       }
    });
    //今日排班
    if($('#today-schedule').length ==1){
        $.ajax({
            type: 'GET',
            url: '/frontend/json/v1/todaySchedule',
            success: function(data){
                if(data.code==0){
                    var _html='';
                    $.each(data.data,function(i,item){
                        _html+='<tr';
                        if(!item.scheduled){
                            _html+=' class="active"';
                        }
                        _html+='>';
                        _html+='<td>'+item.startTime+'</td>';
                        _html+='<td>'+item.duration+'</td>';
                        _html+='<td>'+item.bigoID+'</td>';
                        _html+='</tr>';
                    });
                    $('#today-schedule').html(_html);
                    tableRowSpan();
                }else{
                    alert(data.msg);
                }
            },
            error: function() {
                alert('请求数据失败');
            }
        });
    }
})
