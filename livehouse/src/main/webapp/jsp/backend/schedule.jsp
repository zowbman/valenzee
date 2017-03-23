<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/backend/public/head.jsp" %>
<div class="container" style="padding-bottom: 60px;">
  <h1 class="page-header">Music Live House</h1>
  <form id="form-search-schedule" class="form-inline form-sigin">
    <div class="error"></div>
    <div class="input-group">
        <div class="input-group-btn">
            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">DATE <span class="caret"></span></button>
            <ul class="dropdown-menu">
                <c:forEach var="item" items="${date}">
                    <li><a href="javascript:;" class="date">${item}</a></li>
                </c:forEach>
            </ul>
        </div><!-- /btn-group -->
        <input size="16" id="date" name="date" type="text" value="${currentDate}" class="form-control form_datetime">
    </div><!-- /input-group -->
    <button type="submit" class="btn btn-default form-searche-schedule-btn form-sigin-btn">SEARCH</button>
  </form>
  <div class="table-responsive">
    <table class="table table-bordered table-hover">
      <thead>
        <tr>
          <th>Time Slot</th>
          <th>Duration(hour)</th>
          <th>Bigo ID</th>
          <th>WhatsApp Number</th>
          <th>Opt</th>
        </tr>
      </thead>
      <tbody id="schedule-info"></tbody>
    </table>
  </div>
</div>
<!-- 模态框 -->
<div class="modal fade" id="schedule-apply">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <div id="schedule-apply-titile" style="font-size:20px;text-align:center;">
            <strong>排班申请列表</strong>
        </div>
      </div>
      <div class="modal-body">
        <form id="schedule-apply-form">
          <table class="table">
            <thead>
              <tr>
                <th>提交申请时间</th>
                <th>Bigo ID</th>
                <th>申请时间段</th>
                <th>时长(小时)</th>
                <th>备注</th>
                <th>Opt</th>
              </tr>
            </thead>
            <tbody id="schedule-apply-body"></tbody>
          </table>
        </form>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<div class="modal fade" id="manual-input-schedule">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <div id="schedule-apply-titile" style="font-size:20px;margin-bottom:5px;">
            <strong>手动输入排班信息</strong>
        </div>
        <span style="color:red;">*注意:如时间段为1个小时，手动输入则拆分成2个0.5小时排班行</span>
      </div>
      <div class="modal-body">
        <form id="manual-input-schedule-form" class="form-sigin form-horizontal">
            <input type="hidden" name="oldScheduleId" id="oldScheduleId" value="-1">
            <div class="form-group">
                <label for="bigoID" class="col-sm-3 control-label">Bigo ID</label>
                <div class="col-sm-6">
                  <input type="text" id="bigoID" name="bigoID" class="form-control" placeholder="">
                </div>
            </div>
            <div class="form-group">
                <label for="whatsAppNumber" class="col-sm-3 control-label">WhatsApp Number</label>
                <div class="col-sm-6">
                  <input type="text" id="whatsAppNumber" name="whatsAppNumber" class="form-control" placeholder="">
                </div>
            </div>
            <div class="form-group">
                <label for="timeSlot" class="col-sm-3 control-label">Time Slot</label>
                <div class="col-sm-6">
                    <select id="startTime" name="startTime" class="form-control" disabled></select>
                </div>
            </div>
            <div class="form-group">
                <label for="duration" class="col-sm-3 control-label">Duration</label>
                <div class="col-sm-6">
                     <label class="radio-inline">
                       <input type="radio" name="duration" id="duration" value="1" checked="true"> 0.5 hour
                     </label>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-6">
                    <button class="form-sigin-btn manual-input-schedule-btn" type="submit">SUBMIT</button>
                </div>
            </div>
        </form>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script type="text/javascript">
    $('.form_datetime').datetimepicker({
        minView: "month",
        todayBtn:  1,
    	autoclose: 1,
    	format: 'yyyy-mm-dd'
    });
    function tableRowSpan(tbodyId){
        var firsttr='';
        var currenttr='';
        var spanNum=0;
        var tr = $('#'+tbodyId+' tr');
        var len = tr.length;
        $(tr).each(function(i){
            if(i==0){
                firsttr=$(this);
            }else{
                currenttr=$(this);
                if(firsttr.attr('class') != 'row_-1' && firsttr.attr('class') == currenttr.attr('class')){
                    spanNum++;
                    var newText ='';
                    newText += firsttr.find('td:eq(0)').text().split('~')[0];
                    newText += '~'
                    newText += currenttr.find('td:eq(0)').text().split('~')[1];
                    firsttr.find('td:eq(0)').text(newText)
                    currenttr.hide();//remove();
                }else{
                    firsttr = $(this);
                    spanNum = 1;
                }
            }
        });
        //检测第一个和最后一个是否同一申请，如果是则合并
        firsttr = $(tr).first();
        currenttr = $(tr).last();
        if(firsttr.attr('class') != 'row_-1' && firsttr.attr('class') == currenttr.attr('class')){
            var newText ='';
            newText += currenttr.find('td:eq(0)').text().split('~')[0];
            newText += '~'
            newText += firsttr.find('td:eq(0)').text().split('~')[1];
            firsttr.find('td:eq(0)').text(newText)
            currenttr.hide();//remove();
        }
    }
</script>
<%@ include file="/jsp/backend/public/footer.jsp" %>