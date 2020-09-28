

//毫秒转日期
function turnTime(ms){
	var oDate = new Date(ms),
	oYear = oDate.getFullYear(),
	oMonth = oDate.getMonth()+1,
	oDay = oDate.getDate(),
	oHour = oDate.getHours(),
	oMin = oDate.getMinutes(),
	oSen = oDate.getSeconds(),
	oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) +' '+ getzf(oHour) +':'+ getzf(oMin) +':'+getzf(oSen);//最后拼接时间
	return oTime;
}
//给时间补上零
function getzf(num){
	if(parseInt(num) < 10){
		num = '0'+num;
	}
	return num;
}


//页面加载事件
$(function(){
	loadCustByPage(1);
	
	
});

function del(id){
	alert(id);
	
	if(confirm("你确定要删除吗？")){
		location.href="/springMvc_4/cust/api/v_1.0/delCustById?custId="+id;
	}
}


function loadCustByPage(indexpage){
	
	//获取用户输入搜索框的值
	var custName = $("[name='custName']").val();
	
	var custLevel = $("[name='custLevel']").val();
	
	
	
	var url = "/springMvc_4/cust/api/v_1.0/findCustByPage";
	var params = {"indexpage":indexpage,"custName":custName,"custLevel":custLevel};
	//获取数据保存位置
	var myBody = $("#myTbody");
	//没加载一次，清除上次容器的数据
	myBody.empty();
	
	$.post(
		url,
		params,
		function(apiBean){
			var data = apiBean.dataList;
			//把dom对象转换为Jquery对象
			
			var $obj = $(data);

			$obj.each(function(index,dom){
				
				myBody.append("<tr><td style='text-align:center; vertical-align: middle;'>"+(index+1)+"</td>" +
						"<td style='text-align:center; vertical-align: middle;'>"+dom.custName+"</td>" +
						"<td style='text-align:center; vertical-align: middle;'>"+dom.custSource+"</td>" +
						"<td style='text-align:center; vertical-align: middle;'>"+dom.custLevel+"</td>" +
						"<td style='text-align:center; vertical-align: middle;'>"+dom.custLinkman+"</td>" +
						"<td style='text-align:center; vertical-align: middle;'>"+dom.custMobile+"</td>" +
						"<td style='text-align:center; vertical-align: middle;'>"+turnTime(dom.custBirthday)+"</td>" +
						"<td style='text-align:center; vertical-align: middle;'>"+dom.picFilepath+"</td>" +
						"<td style='text-align:center; vertical-align: middle;'><a href=''class='btn btn-info btn-sm active'>编辑</a>&nbsp;<a href='#'class='btn btn-warning btn-sm active' onclick='del("+dom.custId+")'>删除</a></td></tr>");
				
			});
			
			
			//使用js封装分页数据	
			
			var pagehtml ="";
		
			pagehtml += "<a onclick='loadCustByPage(1)' class='btn btn-default btn-sm active' >首页</a>&nbsp;";
			if(apiBean.indexpage > 1){
				
				pagehtml += "<a onclick='loadCustByPage("+(apiBean.indexpage - 1)+")' class='btn btn-default btn-sm active'> 上一页 </a>&nbsp;";
			}else{
				pagehtml +="<span class='btn btn-default btn-sm disabled'>上一页</span>&nbsp;";
				
			}
			
			
			if(apiBean.totalPage < 4){
				for (var i = 1; i <= apiBean.totalPage; i++) {
					pagehtml +="<a  onclick='loadCustByPage("+i+")' class='btn btn-default btn-sm active'>"+i+"</a>&nbsp;";
				}
				
			}else if(apiBean.indexpage + 4 <= apiBean.totalPage){
				
				for ( var i = apiBean.indexpage; i < apiBean.indexpage + 4; i++) {
					pagehtml += "<a  onclick='loadCustByPage("+i+")' class='btn btn-default btn-sm active'> "+i+" </a>&nbsp;";
				}
			}else{
				for ( var i = apiBean.totalPage - 3; i <= apiBean.totalPage; i++) {
					pagehtml += "<a onclick='loadCustByPage("+i+")' class='btn btn-default btn-sm active'> "+i+" </a>&nbsp;";
				}
			}
			
			
			if(apiBean.indexpage < apiBean.totalPage){
				
				pagehtml += "<a onclick='loadCustByPage("+(apiBean.indexpage + 1)+")' class='btn btn-default btn-sm active'> 下一页 </a>&nbsp;";
			}else{
				pagehtml +="<span class='btn btn-default btn-sm disabled'>下一页</span>&nbsp;";
			}
			
			
			pagehtml += "<a onclick='loadCustByPage("+(apiBean.totalPage)+")' class='btn btn-default btn-sm active'>尾页</a>&nbsp;";
			pagehtml += "<font class='page-header' size='4.5px'>当前第&nbsp;"+apiBean.indexpage+"&nbsp;页/共&nbsp;"+apiBean.totalPage+"&nbsp;页</font>";
			pagehtml +="</ul>";
			$("#myPage").html(pagehtml);
			
			
		},
		"json"
	
	
	);
	
}