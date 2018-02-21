<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>秒杀详情页</title>
    <%@include file="common/head.jsp"%>
    <%--静态包含1个servlet,动态是2个先head加载再加载本页面--%>
    <style>
        .glyphicon{
            color:red;
        }
        .glyphicon.glyphicon-Phone{
            color:#000;
        }
    </style>
</head>
<body>
<a class="btn btn-info" href="/seckill/list">返回</a>
<h1>${seckill.seckillid}</h1>
<div class="container">
    <div class="panel panel-default text-center">
      <div class="panel-heading"><h1>${seckill.name}</h1></div>
        <div class="panel-body">
        <h2 class="panel-body">
            <span class="glyphicon glyphicon-time"></span>
            <span class="glyphicon" id="seckill-box"></span>
      </h2>
        </div>
    </div>
</div>
<%--弹出层--%>
<div id="killPhoneModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title text-center">
                    <span class="glyphicon glyphicon-phone"></span>
                   秒杀电话
                </h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-8 col-xs-offset-2">
                        <input type="text"   id="killPhoneKey" placeholder="请输入手机号^o^" class="form-control" />
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <%--验证信息--%>
                <span id="killPhoneMessage" class="glyphicon"></span>
                <button type="button" id="killPhoneBtn" class="btn btn-success">
                    <span class="glyphicon glyphicon-Phone">
                  提交
                </span></button>
 </div>
        </div>
    </div>
</div>
</body>
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script src="https://cdn.bootcss.com/jquery.countdown/2.2.0/jquery.countdown.js"></script>
<script src="/resource/script/seckill.js" type="text/javascript"></script>

<script type="text/javascript">
    $(function () {
        // 使用EL表达式传入参数
        seckill.detail.init({
            seckillid:${seckill.seckillid},
            startTime: ${seckill.startTime.time}, //转换毫秒
            endTime: ${seckill.endTime.time}
        });
    });
</script>
</html>
