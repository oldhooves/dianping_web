$(function() {
	common.showMessage($("#message").val());
	//$("#mainForm").validate({
	//	rules : {
	//		"title" : "required",
	//		"subtitle" : "required",
	//		"link" : "required",
	//		"price" : "required"
    //
	//	},
	//	messages : {
	//		"title" : "请输入标题！"
	//	}
	//});
});


function modify() {
	$("#mainForm").submit();
}

function goback() {
	location.href = $('#basePath').val() + '/businesses';
}