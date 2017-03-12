<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/frontend/public/head.jsp" %>
<div class="container">
    <h1 class="page-header">TODAY SCHEDULE</h1>
    <h4>${today}</h4>
    <div class="table-responsive">
        <table class="table table-hover table-bordered">
          <thead>
            <tr>
              <th>申请时间段</th>
              <th>时长(小时)</th>
              <th>Bigo ID</th>
            </tr>
          </thead>
          <tbody id="today-schedule"></tbody>
        </table>
    </div>
</div>
<script type="text/javascript">
    function tableRowSpan(){
        var firsttr='';
        var currenttr='';
        var spanNum=0;
        var tr = $('#today-schedule tr');
        $(tr).each(function(i){
            if(i==0){
                firsttr=$(this);
            }else{
                currenttr=$(this);
                var first_td_0=firsttr.find('td:eq(0)').text();
                var first_td_1=firsttr.find('td:eq(1)').text();
                var first_td_2=firsttr.find('td:eq(2)').text();
                var current_td_0=currenttr.find('td:eq(0)').text();
                var current_td_1=currenttr.find('td:eq(1)').text();
                var current_td_2=currenttr.find('td:eq(2)').text();
                if(first_td_2 !='未选择' && first_td_2 == current_td_2){
                    spanNum++;
                    var newApplyDate='';
                    var newDuration;
                    newApplyDate+= first_td_0.split('~')[0];
                    newApplyDate+='~';
                    newApplyDate+=current_td_0.split('~')[1];
                    newDuration = parseFloat(first_td_1)+parseFloat(current_td_1);
                    firsttr.find('td:eq(0)').text(newApplyDate);
                    firsttr.find('td:eq(1)').text(newDuration);
                    currenttr.hide();//remove();
                }else{
                    firsttr = $(this);
                    spanNum = 1;
                }
            }
        });
    }
</script>
<%@ include file="/jsp/frontend/public/footer.jsp" %>