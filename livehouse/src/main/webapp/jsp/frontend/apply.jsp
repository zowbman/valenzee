<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Live House|排班</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="Live House,排班">
	<meta http-equiv="description" content="Live House">
	<link rel="stylesheet" type="text/css" href="/css/common.css">
	<link rel="stylesheet" type="text/css" href="/css/frontend.css">
	<link rel="stylesheet" type="text/css" href="/bootstrap3/css/bootstrap.min.css">
	<script src="/js/jquery.min.js"></script>
	<script src="/js/common.js"></script>
	<script src="/js/frontend.js"></script>
	<!-- 引入 Particleground.js -->
    <script src="/particlegroundjs/production/particleground.js"></script>
    <!-- 引入 粒子特效js -->
    <script src="/particlegroundjs/production/wave.js"></script>
  </head>
  <body id="apply-body">
  	<section class="bg" style="position:absolute;top:0;left:0;bottom:0;right:0;"></section>
  	<div class="container">
  	    <div id="form-title"><h2>Book Music Live House Slot</h2></div>
  		<form id="form-apply" method="post" class="form-sigin form-horizontal" action="">
  		    <div class="form-group">
                <label class="col-sm-3 control-label"></label>
                <div class="col-sm-6">
                    <span class="hint hint-bigoID">*Please Enter Bigo ID</span>
                    <span class="hint hint-whatsAppNumber">*Please Enter WhatsApp Number</span>
                    <span class="hint hint-timeSlot">*Please Enter Time Slot</span>
                </div>
            </div>
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
                  <input type="text" id="timeSlot" name="timeSlot" class="form-control" placeholder="">
                </div>
            </div>
            <div class="form-group">
                <label for="time" class="col-sm-3 control-label">Time</label>
                <div class="col-sm-6">
                     <label class="radio-inline">
                       <input type="radio" name="time" id="time" value="option1"> 0.5 hour
                     </label>
                     <label class="radio-inline">
                       <input type="radio" name="time" id="time" value="option2"> 1 hour
                     </label>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-6">
                    <button class="form-sigin-btn apply-btn" type="submit">SUBMIT</button>
                </div>
	  		</div>
  		</form>
  	<div>
  	<script>
	     $(function(){
	        var settings = {
	           eventElem: document,
	           num: 3,
               lineColor: ['rgba(0, 190, 112, .5)', 'rgba(0, 190, 112, .7)', 'rgba(0, 190, 112, .9)'],//线条颜色
               lineWidth: [.5, .7, .9],//线条宽度
               offsetLeft: [2, 1, 0],//线条向左偏移值
               offsetTop: .75,// 三条线都向上偏移容器高度的0.75倍
               crestHeight: [10, 14, 18],// 三条线依次的波峰高度
               rippleNum: 2,// 三条线都只有两个波峰（波纹）
               speed: .1
	        };
	        if( window.isMobile ){
	            settings.opacity = .6;
	            settings.distance = 60;
	            settings.range = 200;
	            settings.maxR = 4;
	            settings.num = .14;
	            settings.maxSpeed = .6;
	        }
	        new Particleground.wave( '.bg', settings);
	    });
    </script>
  </body>
</html>
