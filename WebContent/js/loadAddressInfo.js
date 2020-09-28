
//页面加载事件
$(function(){
	//获取到省份列表的位置
	var $pro = $("[name='province']");
	
	//调用后台功能，查询出所有省份信息
	var url = "/springMvc_4/pro/findAllProInfo";
	var params = {};
	$.post(
	url,
	params,
	function(pro){
		var $data = $(pro);
		$data.each(function(index,dom){
			$pro.append("<option value='"+dom.provinceid+"'>"+dom.name+"</option>");	
		});
		
	},
	"json"
	
	);
	
	
});

	function loadCity(obj){
		//省份Id
		var provinceId = obj.value;
		//通过Js获取到省份名
		var proName = obj[provinceId].innerHTML;
		
		//获取隐藏框位置
		$("#provinceName").val(proName);
		//获取city信息的保存位置
		var $city = $("[name='city']");
		var url = "/springMvc_4/city/findCityInfoByProvinceId";
		var params = {"provinceId":provinceId};
		$city.empty();
		$.post(
			url,
			params,
			function(citys){
				//转换为Jquery对象
				var $data = $(citys);
				
				$data.each(function(index,dom){
				
					$city.append("<option value='"+dom.name+"'>"+dom.name+"</option>");
					
					
				});
			}
		
		);
		
		
	}

















