$(function(){
    //排班添加申请
    $('.schedule-add-apply-btn').click(function(){
        alert("添加成功");
    });
    //移除排班
    $('.schedule-remove-btn').click(function(){
        alert("移除排班成功");
        $(this).parent().parent().find("td:eq(1)").text("未选择");
        $(this).parent().parent().find("td:eq(2)").text("未选择");
        $(this).prev().text("添加");
        $(this).remove();
    });
    //加载主播白名单列表
    $("#white-list").ready(function(){
        whiteList();
    });
    //主播白名单列表方法
    function whiteList(){
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