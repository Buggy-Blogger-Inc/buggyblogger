$(document).ready(function() {
	$("#addLink").click(function(e) {
		$("#loginForm").hide();
		$("#addForm").show();
	});
	$("#loginLink").click(function(e) {
		$("#addForm").hide();
		$("#loginForm").show();
	});
	$("#findLink").click(function(e) {
		$("#addForm").hide();
		$("#findForm").show();
	});
	$("#addBtn").click(function() {
		$("#addForm").hide();
		var username = $("#username").val();
		var password = $("#password").val();
		var emailID = $("#emailID").val();
		var address = $("#address").val();
		var phoneNo = $("#phoneNO").val();
		var userBio = $("#userBio").val();
		
		var user = {
			"username" : username,
			"password" : password,
			"emailID" : emailID,
			"address" : address,
			"phoneNo" : phoneNo,
			"userBio" : userBio
		};
		$.ajax({
			url : 'rest/buggyblogger/user',
			type : 'post',
			dataType : 'json',
			contentType: "application/json; charset=utf-8",
			success : function(data) {
				$("#addResult").show();
			},
			data : JSON.stringify(user)
		});
	});

});
