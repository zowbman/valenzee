<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/backend/public/head.jsp" %>
<div class="container" style="padding-bottom: 60px;">
  <h1 class="page-header">Music Live House</h1>
  <h4>2017年2月23日 时间表</h4>
  <div class="table-responsive">
    <table class="table table-striped">
      <thead>
        <tr>
          <th>Time Slot</th>
          <th>Bigo ID</th>
          <th>WhatsApp Number</th>
          <th>Opt</th>
        </tr>
      </thead>
      <tbody id="schedule-list">
        <tr>
            <td>00:00-00:30</td>
            <td>123456</td>
            <td>1234567</td>
            <td>
                <a class="btn btn-default btn-xs" href="javascript:;" onclick="showScheduleApply();" role="button">修改</a>
                <a class="btn btn-default btn-xs schedule-remove-btn" href="javascript:;" role="button">移除</a>
            </td>
        </tr>
        <tr>
            <td>00:00-00:30</td>
            <td>123456</td>
            <td>1234567</td>
            <td>
                <a class="btn btn-default btn-xs" href="javascript:;" onclick="showScheduleApply();" role="button">修改</a>
                <a class="btn btn-default btn-xs schedule-remove-btn" href="javascript:;" role="button">移除</a>
            </td>
        </tr>
        <tr>
            <td>00:00-00:30</td>
            <td>123456</td>
            <td>1234567</td>
            <td>
                <a class="btn btn-default btn-xs" href="javascript:;" onclick="showScheduleApply();" role="button">修改</a>
                <a class="btn btn-default btn-xs schedule-remove-btn" href="javascript:;" role="button">移除</a>
            </td>
        </tr>
        <tr>
            <td>00:00-00:30</td>
            <td>123456</td>
            <td>1234567</td>
            <td>
                <a class="btn btn-default btn-xs" href="javascript:;" onclick="showScheduleApply();" role="button">修改</a>
                <a class="btn btn-default btn-xs schedule-remove-btn" href="javascript:;" role="button">移除</a>
            </td>
        </tr>
        <tr>
            <td>00:00-00:30</td>
            <td>未选择</td>
            <td>未选择</td>
            <td><a class="btn btn-default btn-xs" href="javascript:;" onclick="showScheduleApply();" role="button">添加</a></td>
        </tr>
        <tr>
            <td>00:00-00:30</td>
            <td>123456</td>
            <td>1234567</td>
            <td>
                <a class="btn btn-default btn-xs" href="javascript:;" onclick="showScheduleApply();" role="button">修改</a>
                <a class="btn btn-default btn-xs schedule-remove-btn" href="javascript:;" onclick="" role="button">移除</a>
            </td>
        </tr>
      </tbody>
    </table>
  </div>
  <nav>
    <ul class="pagination">
      <li><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo; 前一天</span></a></li>
      <li class="active"><a href="#">2017-02-23<span class="sr-only">(current)</span></a></li>
      <li class="disabled"><a href="#" aria-label="Next"><span aria-hidden="true">后一天 &raquo;</span></a></li>
    </ul>
  </nav>
</div>
<!-- 模态框 -->
<div class="modal fade" id="schedule-apply">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <div id="schedule-apply-titile"></div>
      </div>
      <div class="modal-body">
        <form id="schedule-apply-form">
          <table class="table">
            <thead>
              <tr>
                <th>申请时间</th>
                <th>Bigo ID</th>
                <th>Opt</th>
              </tr>
            </thead>
            <tbody id="schedule-apply-body">
                <tr>
                    <td>2017-02-23</td>
                    <td>12345</td>
                    <td><a class="btn btn-default btn-xs schedule-add-apply-btn" href="javascript:;" onclick="" role="button">确认</a></td>
                </tr>
                <tr>
                    <td>2017-02-23</td>
                    <td>12345</td>
                    <td><a class="btn btn-default btn-xs schedule-add-apply-btn" href="javascript:;" onclick="" role="button">确认</a></td>
                </tr>
                <tr>
                    <td>2017-02-23</td>
                    <td>12345</td>
                    <td><a class="btn btn-default btn-xs schedule-add-apply-btn" href="javascript:;" onclick="" role="button">确认</a></td>
                </tr>
                <tr>
                    <td>2017-02-23</td>
                    <td>12345</td>
                    <td><a class="btn btn-default btn-xs schedule-add-apply-btn" href="javascript:;" onclick="" role="button">确认</a></td>
                </tr>
            </tbody>
          </table>
        </form>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script type="text/javascript">
    function showScheduleApply(){
        $("#schedule-apply").modal('show');
    }
</script>
<%@ include file="/jsp/backend/public/footer.jsp" %>