<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/frontend/public/head.jsp" %>
<div class="container">
    <h1 class="page-header">SLOT QUERY</h1>
    <form id="form-slot-query" class="form-inline form-sigin">
        <div class="error"></div>
        <div class="form-group">
          <label for="bigoID">Bigo ID</label>
          <input type="text" class="form-control" id="bigoID" name="bigoID" placeholder="">
        </div>
        <button type="submit" class="btn btn-default form-sigin-btn slot-query-btn">SUBMIT</button>
    </form>
    <div class="table-responsive">
        <table class="table table-striped table-bordered">
          <thead>
            <tr>
              <th>申请时间</th>
              <th>申请位置</th>
              <th>结果</th>
            </tr>
          </thead>
          <tbody id="solt-query-list"></tbody>
        </table>
    </div>
</div>
<%@ include file="/jsp/frontend/public/footer.jsp" %>