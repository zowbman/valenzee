<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/backend/public/head.jsp" %>
<div class="container" style="padding-bottom: 60px;">
  <h1 class="page-header">主播白名单</h1>
  <form id="form-white-list" class="form-inline form-sigin">
    <span class="hint hint-bigoID">*Please Enter Bigo ID</span>
    <span class="hint hint-fraction">*Please Enter Fraction</span>
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
      <tbody id="white-list-list">
        <tr>
            <td>00:00-00:30</td>
            <td>123456</td>
            <td>80</td>
        </tr>
        <tr>
            <td>00:00-00:30</td>
            <td>123456</td>
            <td>60</td>
        </tr>
        <tr>
            <td>00:00-00:30</td>
            <td>123456</td>
            <td>50</td>
        </tr>
        <tr>
            <td>00:00-00:30</td>
            <td>123456</td>
            <td>10</td>
        </tr>
      </tbody>
    </table>
  </div>
</div>
<%@ include file="/jsp/backend/public/footer.jsp" %>