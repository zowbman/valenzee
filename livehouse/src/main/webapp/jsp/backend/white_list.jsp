<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/backend/public/head.jsp" %>
<div class="container" style="padding-bottom: 60px;">
  <h1 class="page-header">主播白名单</h1>
  <form id="form-white-list" class="form-inline form-sigin">
    <div class="error"></div>
    <div class="form-group">
      <label for="bigoID">Bigo ID</label>
      <input type="text" class="form-control" id="bigoID" name="bigoID" placeholder="">
    </div>
    <div class="form-group">
      <label for="fraction">评价分数</label>
      <input type="text" class="form-control" id="fraction" name="fraction" placeholder="">
    </div>
    <button type="submit" class="btn btn-default form-sigin-btn white-list-btn">SUBMIT</button>
  </form>
  <div class="table-responsive">
    <table class="table table-striped">
      <thead>
        <tr>
          <th>添加时间</th>
          <th>Bigo ID</th>
          <th>分数</th>
        </tr>
      </thead>
      <tbody id="white-list"></tbody>
    </table>
  </div>
</div>
<%@ include file="/jsp/backend/public/footer.jsp" %>