$(function(){
    scheduleInfo();//加载排班列表
    whiteList();//加载主播白名单列表

    //排班查询添加校验
    $('#form-search-schedule').validate({
        submitHandler: function(form){
            scheduleInfo($(form).find('#date').val());
        },
        errorPlacement: function(error, element) {
            $(element).closest('form').find('.error').append(error);
        },
        errorElement:'span',
        errorClass:'hint',
        rules:{
            date:{
                required:true,
                dateISO:true,
            }
       },
       messages:{
            date:{
                required:'*Please enter date',
                dateISO:'*Please enter a valid date'
            }
       }
    });
    //排班查询
    $('#form-search-schedule .date').click(function(){
        $(this).closest('form').find('input').val($(this).text());
        scheduleInfo($(this).text());
    });
    //排班信息方法
    function scheduleInfo(date){
        if($('#schedule-info').length == 0)
            return;
        $.ajax({
            type: 'GET',
            url: '/backend/json/v1/getScheduleInfo',
            data:{
                date:date
            },
            success: function(data){
                var _html='';
                $.each(data.data,function(i,item){
                    if(item.scheduled){
                        _html+='<tr class="row_'+item.scheduleId+' success">';
                    }else{
                        _html+='<tr class="row_'+item.scheduleId+'">';
                    }
                    _html+='<td>'+item.startTime+'</td>';
                    _html+='<td>'+item.duration+'</td>';
                    _html+='<td>'+item.bigoID+'</td>';
                    _html+='<td>'+item.whatsAppNumber+'</td>';
                    _html+='<td>';
                    _html+='<input type="hidden" name="startTimeId" value="'+item.id+'">'
                    if(!item.scheduled){
                        _html+='<a class="btn btn-default btn-xs schedule-add-btn" href="javascript:;"  role="button">添加</a> ';
                    }else{
                        _html+='<input type="hidden" name="scheduleId" value="'+item.scheduleId+'">'
                        _html+='<a class="btn btn-default btn-xs schedule-edit-btn" href="javascript:;"  role="button">修改</a> ';
                        _html+='<a class="btn btn-default btn-xs schedule-remove-btn" href="javascript:;" role="button">移除</a> ';
                    }
                    _html+='<a class="btn btn-default btn-xs manual-input-btn" href="javascript:;"  role="button">手动输入</a> ';
                    _html+='</td>';
                    _html+='</tr>';
                });
                $('#schedule-info').html(_html);
                tableRowSpan('schedule-info');
            },
            error: function() {
                alert('请求数据失败');
            }
        });
    }
    //排班申请列表
    $(document).delegate('.schedule-add-btn','click',function(){
        var startTimeId = $(this).parent().find('input').val();
        showScheduleApply(startTimeId)
    });
    $(document).delegate('.schedule-edit-btn','click',function(){
        var startTimeId = $(this).parent().find('input:eq(0)').val();
        var scheduleId = $(this).parent().find('input:eq(1)').val()
        showScheduleApply(startTimeId, scheduleId)
    });
    function showScheduleApply(startTimeId,scheduleId){
        var type ='ForEdit';
        if(scheduleId == void 0){
            scheduleId=-1;
            type='ForAdd';
        }
        $.ajax({
            type: 'GET',
            url: '/backend/json/v1/getApplyList'+type,
            data:{
                applyDate:$('#date').val(),
                startTimeId:startTimeId,
                scheduleId:scheduleId
            },
            success: function(data){
                if(data.code == 0){
                    var _html='';
                    $.each(data.data,function(i,item){
                        _html+='<tr';
                        if(item.scheduled){
                            _html+=' class="danger"';
                        }
                        _html+='>';
                        _html+='<td>'+item.addTime+'</td>';
                        _html+='<td>'+item.bigoID+'</td>';
                        _html+='<td>'+item.applyDate+'</td>';
                        _html+='<td>'+item.duration+'</td>';
                        _html+='<td>'+item.remarks+'</td>';
                        _html+='<td>';
                        if(!item.scheduled){
                            _html+='<input type="hidden" name="applyId" value="'+item.id+'">'
                            _html+='<input type="hidden" name="oldScheduleId" value="'+item.oldScheduleId+'">'
                            _html+='<a class="btn btn-default btn-xs apply-pass-submit-btn" href="javascript:;"  role="button">通过</a>';
                        }
                        _html+='</td>';
                        _html+='</tr>'
                    });
                    $('#schedule-apply-body').html(_html);
                    $('#schedule-apply').modal('show');
                }else{
                    alert(data.msg);
                }
            },
            error: function() {
                alert('请求数据失败');
            }
        });
    }
    //排班申请通过
    $(document).delegate('.apply-pass-submit-btn','click',function(){
        var applyId = $(this).parent().find('input:eq(0)').val();
        var oldScheduleId = $(this).parent().find('input:eq(1)').val();
        passApply(oldScheduleId,applyId);
    });
    function passApply(oldScheduleId,applyId){
        var type ='ForEdit';
        if(oldScheduleId == 0){
            type='ForAdd';
        }
        $.ajax({
            type: 'POST',
            url: '/backend/json/v1/passApply'+type,
            data:{
                applyId:applyId,
                oldScheduleId:oldScheduleId
            },
            success: function(data){
                if(data.code != 0){
                    alert(data.msg);
                }else{
                    $('#schedule-apply').modal('hide');
                    scheduleInfo();
                }
            },
            error: function() {
                alert('请求数据失败');
            }
        });
    }
    //移除排班
    $(document).delegate('.schedule-remove-btn','click',function(){
        var id = $(this).parent().find('input:eq(1)').val();
        if(!confirm("是否移除记录")){
            return;
        }
        $.ajax({
            type: 'POST',
            url: '/backend/json/v1/removeSchedule',
            data:{
                id:id
            },
            success: function(data){
                if(data.code != 0){
                    alert(data.msg);
                }else{
                    scheduleInfo();
                }
            },
            error: function() {
                alert('请求数据失败');
            }
        });
    });
    //手动输入排班信息
    $(document).delegate('.manual-input-btn','click',function(){
        var startTimeId = $(this).parent().find('input:eq(0)').val();
        var startTimeText = $(this).parent().parent().find('td:eq(0)').text();
        var _html = '<option value="'+startTimeId+'">'+startTimeText.split('~')[0]+'</option>';
        $('#startTime').html(_html);
        var oldScheduleId = $(this).parent().find('input:eq(1)').val();
        if(typeof(oldScheduleId) == "undefined"){
            oldScheduleId = -1;
        }
        $('#oldScheduleId').val(oldScheduleId);
        $('#manual-input-schedule-form')[0].reset();
        $('#manual-input-schedule').modal('show');
    });
    //手动输入排班校验
    $('#manual-input-schedule-form').validate({
        submitHandler: function(form){
            $.ajax({
                type: 'POST',
                url: "/backend/json/v1/manualInputSchedule",
                data: {
                    date:$('#date').val(),
                    bigoID:$('#bigoID').val(),
                    whatsAppNumber:$('#whatsAppNumber').val(),
                    timeSlot:$('#startTime').val(),
                    oldScheduleId:$('#oldScheduleId').val()
                },
                success: function(data){
                    if(data.code == 0){
                        scheduleInfo($('#date').val());
                        $('#manual-input-schedule').modal('hide');
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
    //主播白名单列表方法
    function whiteList(){
        if($('#white-list').length == 0)
            return;
        $.ajax({
            type: 'GET',
            url: '/backend/json/v1/getWhiteList',
            success: function(data){
                if(data.code == 0){
                    var _html='';
                    $.each(data.data,function(i,item){
                        _html+='<tr>'
                        _html+='<td>'+item.addTime+'</td>';
                        _html+='<td>'+item.bigoID+'</td>';
                        _html+='<td>'+item.fraction+'</td>';
                        _html+='</tr>'
                    });
                    $('#white-list').html(_html);
                }else{
                    alert(data.msg)
                }
            },
            error: function() {
                alert('请求数据失败');
            }
        });
    }
    //主播白名单添加校验
    $('#form-white-list').validate({
        submitHandler: function(form){
           //主播白名单添加
           $.ajax({
               type: 'POST',
               url: '/backend/json/v1/addWhiteList',
               data: $(form).serialize(),
               success: function(data){
                   if(data.code == 0){
                       alert("添加成功");
                       $('#form-white-list')[0].reset();
                       whiteList();
                   }else{
                       alert(data.msg);
                   }
               },
               error: function() {
                   alert('提交失败');
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
            },
            fraction:{
                required: true,
            }
        },
        messages:{
            bigoID:{
                required:'*Please Enter Bigo ID'
            },
            fraction:{
                required:'*Please Enter Fraction'
            }
        }
    });
});