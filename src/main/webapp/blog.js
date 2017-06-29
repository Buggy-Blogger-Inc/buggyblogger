var bloglist = [];
var userloggedon = "";

$(document).ready(function() {

	$("#SignUpLink").click(function(){
		$("#landingPageCentralArea").hide();
		$("#LoginAndSignUp").show();
		$('#loginbox').hide(); 
		$('#signupbox').show();
	});

	$("#LoginLink").click(function(){
		$("#landingPageCentralArea").hide();
		$("#LoginAndSignUp").show();
		$('#signupbox').hide();
		$('#loginbox').show(); 		
	});

	$("#btn-log-out").click(function(){
		console.log("Loggin out, showing home page");
		$('#landingPage').show();
		$('#HomePage').hide();
		$('#SignUpSuccess').hide();
		$('#SignUpFailure').hide();
		$("#landingPageCentralArea").show();
		$("#LoginAndSignUp").hide();
		$('#signupbox').hide();
		$('#loginbox').hide(); 		
	});

	//Login Button(after submitting username and paswd) Handler
	$("#btn-signup").click(function() {
		//Ideally do validation here and then land on home page
		var uname = $("#signup-uname").val();
		var userEmail = $("#signup-email").val();
		var userBio = $("#signup-description").val();  
		var userAddress = $("#signup-address").val();
		var userPassword = $("#signup-password").val();
		var userContact = $("#signup-contact").val();


		if(uname==""){
			display_alert("UserName is a Mandatory field.");
			//display_alert("User name");
			//$("#addUserForm").show();
			return;
		}

		var atpos = userEmail.indexOf("@");
		var dotpos = userEmail.lastIndexOf(".");
		if (atpos<1 || dotpos<atpos+2 || dotpos+2>=userEmail.length) {
			display_alert("Invalid email address");
			$("#addUserForm").show();
			return;
		}

		/*if(Name==""){
			display_alert("name is a Mandatory field. Please fill the Name field");
			//display_alert("User name");
			//$("#addUserForm").show();
			return;
		}*/

		if(userAddress==""){
			display_alert("Please enter your address or city!");
			$("#addUserForm").show();
			return;
		}

		if(userPassword==""){
			display_alert("Please enter a valid password!");
			$("#addUserForm").show();
			return;
		}
		if(userContact==""){
			display_alert("Please enter your mobile number!");
			$("#addUserForm").show();
			return;
		}

		var user = {
				//"name" : Name, //vijgc
				"username" : uname,
				"password" : userPassword,
				"emailID" : userEmail,
				"address" : userAddress,				
				"phoneNo" : userContact,
				"userBio" : userBio
		};
		console.log("Make a AJAX call");
		$.ajax({
			url : 'rest/buggyblogger/user',
			type : 'POST',
			contentType: "application/json; charset=utf-8",
			success : function(data,status,jqXHR) {
				console.log("Successful AJAX call for sign up");
				$("#addUserResult").show();
				$("#SignUpSuccess").show();

			},
			data : JSON.stringify(user),
			error: function(jqXHR,status){

			}
		});
	});

	//Login Button(after submitting username and paswd) Handler
	$("#btn-login").click(function() {
		//Ideally do validation here and then land on home page
		var uname = $("#login-username").val();
		var passwd = $("#login-password").val();
		var user = {
			"username" : uname,
			"password" : passwd
		};
		$.ajax({
			url : 'rest/buggyblogger/login',
			type : 'post',
			dataType : 'text',
			contentType: "application/json; charset=utf-8",
			data : JSON.stringify(user),
			success : function(data, status, jqXHR){
				displayHomepage(uname);
				userloggedon = uname;
				console.log("Calling getBlogsByUser()");
				getBlogsByUser(uname);
			},	    
			error: function(jqXHR,status, err){
				$("#loginAlert").show();
				console.log("Login failed");
			}
		});
	});

	
	
	//Submit blog button 
	$("#btn-submit-blog").click(function() {
		//Ideally do validation here and then land on home page
		console.log("Submit Blog invoked");
		var title = $("#blogtitle").val();
		var content = $("#blogContent").val();
		var category = $("#blogcategory").val();
		console.log("category" + category);
		var username = userloggedon;
		var blog = {
			"title" : title,
			"category": category,
			"blogContent" : content
		};
		$.ajax({
			url : 'rest/buggyblogger/user/' + username + '/blog',
			type : 'post',
			dataType : 'text',
			contentType: "application/json; charset=utf-8",
			data : JSON.stringify(blog),
			success : function(data, status, jqXHR){
				alert("Blog added successfully");
				displayHomepage(userloggedon);
				console.log("Adding a blog: success");
				getBlogsByUser(userloggedon);
			},	    
			error: function(jqXHR,status, err){
				alert("Blog addition failed");
				displayHomepage(userloggedon);
				console.log("Blog addition failed");
			}
		});
	});
	
	
	//Button to Open Blog form
	$("#btn-open-blog-form").click(function() {
		//Hide Blogs and show blog form 
		console.log("hide blog area and show form");
		$("#BlogArea").hide();
		$("#AddBlogForm").show();
		
	});	
	
	$("#btn-cancel-blog").click(function() {
		//Hide Blogs and show blog form 
		console.log("hide blog area and show form");
		$("#AddBlogForm").hide();
		$("#BlogArea").show();		
	});	
	
	//Search blogs. Get blogs by username
	$("#btn-search-blog-uname").click(function() {
		//Ideally do validation here and then land on home page
		var keyword = $("#blog-keyword1").val();
		console.log("Searching for keyword" + keyword);
		console.log("Calling getBlogsByUser()");
		//getBlogsByTitle(keyword);
		getBlogsByUser(keyword);
	});
	
	//Search blogs. Get blogs by Category
	$("#btn-search-blog-cat").click(function() {
		//Ideally do validation here and then land on home page
		var keyword = $("#blog-keyword2").val();
		console.log("Searching for keyword" + keyword);
		console.log("Calling getBlogsByCat()");
		getBlogsByCat(keyword);		
	});
	
	//Search blogs. Get blogs by title
	$("#btn-search-blog-title").click(function() {
		//Ideally do validation here and then land on home page
		var keyword = $("#blog-keyword3").val();
		console.log("Searching for keyword" + keyword);
		console.log("Calling getBlogsByTitle()");
		getBlogsByTitle(keyword);		
	});


});

function displaySignUpAndLoginForm(){
	$("#LoginAndSignUp").show();
}

function display_alert(str){

	console.log("Display alter message"+str);
	document.getElementById("SignUpFailureMsg").innerHTML = str;
	$("#SignUpFailure").show();
}

function displayHomepage(username){
	var str = "Displaying home page now for user : "+username;
	console.log(str);
	$("#AddBlogForm").hide();
	$("#landingPageCentralArea").hide();
	$("#LoginAndSignUp").hide();
	$('#loginbox').hide(); 
	$('#signupbox').hide();
	$("#landingPage").hide();
	$("#HomePage").show();
	document.getElementById('UserArea').innerHTML = '<ul class="nav navbar-nav navbar-center"> Welcome ' + username + '</ul>'
}

function getBlogsByTitle(keyword){
	
	console.log("Make a AJAX call to get all blogs by title: "+keyword);
	$.ajax({
		url : 'rest/buggyblogger/blog/' + keyword + '/blgs',
		type : 'GET',
		contentType: "application/json; charset=utf-8",
		success : function(data,status,jqXHR) {
			console.log("Successful AJAX call to get all blogs by title");
			displayBlogs(data);
		},
		error: function(jqXHR,status){
			console.log("Oops!! there was a problem!");	
		}
	});
}

function getBlogsByCat(keyword){
	
	console.log("Make a AJAX call to get all blogs by Category: "+keyword);
	$.ajax({
		url : 'rest/buggyblogger/blog/' + keyword + '/blogs',
		type : 'GET',
		contentType: "application/json; charset=utf-8",
		success : function(data,status,jqXHR) {
			console.log("Successful AJAX call to get all blogs by title");
			displayBlogs(data);
		},
		error: function(jqXHR,status){
			console.log("Oops!! there was a problem!");	
		}
	});
}
/*
function getBlogsByUserName(keyword){
	
	console.log("Make a AJAX call to get all blogs by User: "+keyword);
	$.ajax({
		url : 'rest/buggyblogger/user/' + keyword +'/blogs',
		type : 'GET',
		contentType: "application/json; charset=utf-8",
		success : function(data,status,jqXHR) {
			console.log("Successful AJAX call to get all blogs by title");
			displayBlogs(data);
		},
		error: function(jqXHR,status){
			console.log("Oops!! there was a problem!");	
		}
	});
}
*/
function getBlogsByUser(uname){
	//make a AJAX call to get all blogs and update 
	var username = uname;
	console.log("Make a AJAX call to get all blogs by user: "+username);

	$.ajax({
		url : 'rest/buggyblogger/user/' + username +'/blogs',
		type : 'GET',
		contentType: "application/json; charset=utf-8",
		success : function(data,status,jqXHR) {
			console.log("Successful AJAX call to get all blogs by User");
			displayBlogs(data);
		},
		error: function(jqXHR,status){
			console.log("Oops!! there was a problem!");	
		}
	});
}

function displayComments(data,blogId){
	console.log(JSON.stringify(data));
	console.log("Printing each entry : ");
	var json = data;
	var len = json.length;
	var blogComment = "";
	for (i=0; i < len; i++) {    
		console.log(json[i].username);
		console.log(json[i]);
		//var postedDate = new Date(json[i].postedDate);
		postedDate = formatAMPM(json[i].commentDate);
		console.log(postedDate);
		blogComment+= '<h6><span class="glyphicon glyphicon-time"></span> Post by ' + json[i].username + "," + postedDate + "</h5>"; 
		blogComment+= "<br><p>" + json[i].commentContent + "</p><br><br>";
		/*blogComment+= '<a href="#" value="' + json[i].blogId +'" onclick="getCommentsForBlogId(' + json[i].blogId +')"><span class="glyphicon glyphicon-user"></span>view comments</a>'
		blogComment+= '<a href="#" value="' + json[i].blogId +'" onclick="addCommentsForBlogId(' + json[i].blogId +')"><span class="glyphicon glyphicon-user"></span>add comments</a>'*/
		console.log(blogComment);
	}
	document.getElementById(blogId).innerHTML = blogComment;
}

$("#BlogArea").on('click', '.input-group-btn', function(event) {
	console.log("blogpostshowcomment: Caught dynamic href element !!!");
	//$('#createblogcomment').hide();
	var blogId = $(this).attr('href');
	//alert(blogId);
	console.log("onclick of view comments"+blogId);
	//event.preventDefault();
	/*$.ajax({
		url : 'rest/buggyblogger/blog/' + blogId +'/comments',
		type : 'GET',
		contentType: "application/json; charset=utf-8",
		success : function(data,status,jqXHR) {
			console.log("Successful AJAX call to get all blogs by User");
			displayComments(data,blogId);
		},
		error: function(jqXHR,status){
			console.log("Oops!! there was a problem!");	
		}
	});*/
});
function displayBlogs(data){
	//$('#bloglisttable tbody').empty();
	//$('#bloglist').hide();
	$("#AddBlogForm").hide();
	$("#BlogArea").show();
	console.log(JSON.stringify(data));
	console.log("Printing each entry : ");
	var json = data;
	var len = json.length;
	var userblog = "";
	document.getElementById('BlogArea').innerHTML = userblog; // lkanth check this
	console.log("length "+ len);
	//var blogTable = $('#bloglisttable tbody');
    
   /* for (i = 0; i < data.links.length; i++) {
    	blogTable.append('<tr><td>' + data.links[i].reference + '</td><td> <a href="' + data.links[i].uri + '" class="blogpostdetails"> Show Details </a></td></tr>');
    } */

	userblog+= '<h4><small>Latest Blogs</small></h4>';
	
	for (i=0; i < len; i++) {    
		console.log(json[i].username);
		console.log(json[i]);
		//var postedDate = new Date(json[i].postedDate);
		postedDate = formatAMPM(json[i].date);
		console.log(postedDate);
		var blogId = json[i].blogId;
		userblog+= "<hr><h2>" + json[i].title + "</h2>";
		userblog+= '<h5><span class="glyphicon glyphicon-time"></span> Post by ' + json[i].username + "," + postedDate + "</h5>"; 
		userblog+= '<h5><span class="label label-danger">' + json[i].category + '</span>';// <span class="label label-primary">Ipsum</span></h5>';
		userblog+= "<br><p>" + json[i].blogContent + "</p><br><br>";
		//userblog+= '<a href="#" onclick="displaySignUpAndLoginForm()" id="SignUpLink" value="' + json[i].blogId +'><span class="glyphicon glyphicon-user"></span>view comments</a>'
		//userblog+= '<a href="#" onclick="getCommentsForBlogId(' + blogId +')"><span class="glyphicon glyphicon-user"></span>view comments</a>';
		//userblog+= '<a href="' + json[i].blogId + '" class="input-group-btn"><span class="glyphicon glyphicon-user"></span>view comments</a>';
		userblog+= '<a href="#" id="'+ json[i].blogId +'" onclick="viewCommentsForBlogId()"><span class="glyphicon glyphicon-user"></span>view comments</a>';
		userblog+= '<div id="'+ json[i].blogId +'/>'
		userblog+= '<a href="#" onclick="addCommentsForBlogId()"><span class="glyphicon glyphicon-user"></span>add comments</a>';
		console.log(userblog);
	}
	document.getElementById('BlogArea').innerHTML = userblog;
}

function viewCommentsForBlogId(){
	//make a AJAX call to get all blogs and update 
	var blogId = 123;
	console.log("Make a AJAX call to add comment for blogId: "+blogId);

	$.ajax({
		url : 'rest/buggyblogger/blog/' + blogId +'/comment',
		type : 'GET',
		contentType: "application/json; charset=utf-8",
		success : function(data,status,jqXHR) {
			console.log("Successful AJAX call to get all blogs by User");
			displayComments(data,blogId);
		},
		error: function(jqXHR,status){
			console.log("Oops!! there was a problem!");	
		}
	});
}

function formatAMPM(a) {
	var d = new Date(a),
    minutes = d.getMinutes().toString().length == 1 ? '0'+d.getMinutes() : d.getMinutes(),
    hours = d.getHours().toString().length == 1 ? '0'+d.getHours() : d.getHours(),
    ampm = d.getHours() >= 12 ? 'pm' : 'am',
    months = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'],
    days = ['Sun','Mon','Tue','Wed','Thu','Fri','Sat'];
return days[d.getDay()]+' '+months[d.getMonth()]+' '+d.getDate()+' '+d.getFullYear()+' '+hours+':'+minutes+ampm;
}
