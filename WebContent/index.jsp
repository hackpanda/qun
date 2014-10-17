<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

 <!DOCTYPE html>
	<head>
	<title>QQ群关系可视化查询 每一个被GFW的网站都是一个伟大的网站</title>
	<!-- 
	Fuck you Tencent and Fuck you GFW.	
	-->
    <meta charset="utf-8">
	<link rel="stylesheet" href="<%=basePath %>js/jquery-ui.css">
	<script src="<%=basePath %>js/jquery.js"></script>
  <script src="<%=basePath %>js/jquery-ui.js"></script>
    <script src="<%=basePath %>js/d3.js"></script>
    <style>
    .node circle {
      cursor: pointer;
      stroke: #3182bd;
      stroke-width: 1.5px;
  }
  .node text {
      font: 10px sans-serif;
      pointer-events: none;
      text-anchor: start;
  }

  .node div {
      font: 10px sans-serif;
      pointer-events: none;
      text-anchor: start;
  }

  line.link {
      fill: none;
      stroke: #9ecae1;
      stroke-width: 1.5px;
  }
  line.link:hover {
    stroke: red;
    stroke-width: 3px;
}

.overlay {
  fill: none;
  pointer-events: all;

}

div.tooltip {   
  position: absolute;           
  text-align: left; 
  padding: 2px;             
  font: 12px sans-serif;        
  background: lightsteelblue;   
  border: 0px;      
  border-radius: 8px;           
  pointer-events: none;         
}

#topbar {
position: fixed;
left: 0;
top: 0;
z-index: 2048;
width: 100%;
height: 20px;
}


#draggable { 
margin-top:30px;
width: auto;
 height: auto; 
 padding: 5px;
 z-index:1024;
 position: absolute;
}

#rightcornerad {
 z-index:1024;
 position: absolute;

top:200px;
right:0px;
}


#hidetopbar{
text-decoration:underline;
color:blue;
}

a {
text-decoration:underline;
}

</style>
</head>
  <div id="draggable" class="ui-widget-content">  
此窗口可拖动<br>
<a href="search.html" target="_blank" style="color:blue;">搜索群名和昵称(已修复)</a><br>

    <input name="num" type="text" id="num" size="20" maxlength="20" />
    
    <select name="type" id="type">
       <option value="1">QQ号</option>
       <option value="2">群号</option>
     </select>
    <br/>
<br/>

<br>
    <img src="<%=basePath %>servlet/ImgServlet" id="captcha" onclick="document.getElementById('captcha').src='<%=basePath %>servlet/ImgServlet?'+Math.random();
    document.getElementById('captchra-form').focus();"/>
    <br>
验证码<input type="text" name="captcha" id="captcha-form" autocomplete="off" /><br/>
     <input type="submit" name="submit" id="submit" value="查询" />

<br/>
<button type="button" id="removesingle" onclick="removesingle()">去除独立节点</button>
<button type="button" id="shownick" onclick="shownick()">显示QQ昵称</button>
<br>
<button type="button" id="removenick" onclick="removenick()">隐藏QQ昵称</button>
<button type="button" id="reload" onclick="restart()">重新加载</button>

</div>
<body>

<script>

  if(navigator.userAgent.indexOf('MSIE')!=-1){
      alert('IE浏览器和本功能不兼容，推荐使用最新版本的Chrome浏览器!');      
      }
	 $( "#draggable" ).draggable();
	 var qstr='';
	  $(document).ready(function() {
$("#submit").click(function() {
$('#readme').remove();
$('#submit').attr('disabled', true);
qstr=$("#num").val();
nodes = [];
clinks = [];
linkedByIndex = {};

//被封的话随便找个反向代理挂上，呵(Fuck)呵(GFW)

var ajaxsrc='<%=basePath %>servlet/JsonServlet?'+'type='+$("#type").val()+'&qstr='+qstr+'&captcha='+$("#captcha-form").val();
d3.json(ajaxsrc).on("beforesend", function(request) { request.withCredentials = true; }).get(function (error, json) {
	document.getElementById('captcha').src='<%=basePath %>servlet/ImgServlet?'+Math.random();
	$('#submit').attr('disabled', false);
		if(error!=null){
			if(error.toString().slice(-1)=='C'){
			alert('验证码错误');
		}else if(error.toString().slice(-1)=='N'){
			alert('QQ号或者群号未找到');
		}else if(error.toString().slice(-1)=='T'){
			alert('该QQ加入的群过多，无法查询');
		}else{
			alert('未知错误，请重试');
		}
		
		return;
		}
        nodes = json.nodes;
        json.links.forEach(function (l) {

            var sourceNode = json.nodes.filter(function (n) {
                return n.type == 'qun' && n.num == l.source;
            })[0],
            targetNode = json.nodes.filter(function (n) {
                return n.type == 'qq' && n.num == l.target;
            })[0];
            if (sourceNode != null && targetNode != null) {
             linkedByIndex[sourceNode.num + "," + targetNode.num] = 1;
             clinks.push({
                source: sourceNode,
                target: targetNode,
                auth: l.auth,
                nick: l.nick
            });
         }
     }); 
	restart();
	}); 

});
});
    </script>
	<script src="<%=basePath %>js/qqgroup.js"></script>	


</body>
</html>

