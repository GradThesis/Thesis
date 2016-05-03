<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
		<title>MOOCLink | Home</title>
		<!-- Mobile Specific Metas
			================================================== -->
		<meta name="viewport"
			content="width=device-width, initial-scale=1, maximum-scale=1">
		<!-- CSS
			================================================== -->
		<!-- Bootstrap  -->
		<link type="text/css" rel="stylesheet"
			href="bootstrap/css/bootstrap.min.css">
		<!-- web font  -->
		<link href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'
			rel='stylesheet' type='text/css'>
		<!-- plugin css  -->
		<link rel="stylesheet" type="text/css"
			href="js-plugin/animation-framework/animate.css">
		<!-- Pop up-->
		<link rel="stylesheet" type="text/css"
			href="js-plugin/magnific-popup/magnific-popup.css">
		<!-- Flex slider-->
		<link rel="stylesheet" type="text/css"
			href="js-plugin/flexslider/flexslider.css">
		<!-- Owl carousel-->
		<link rel="stylesheet"
			href="js-plugin/owl.carousel/owl-carousel/owl.carousel.css">
		<link rel="stylesheet"
			href="js-plugin/owl.carousel/owl-carousel/owl.transitions.css">
		<link rel="stylesheet"
			href="js-plugin/owl.carousel/owl-carousel/owl.theme.css">
		<!-- icon fonts -->
		<link type="text/css" rel="stylesheet"
			href="font-icons/custom-icons/css/custom-icons.css">
		<link type="text/css" rel="stylesheet"
			href="font-icons/custom-icons/css/custom-icons-ie7.css">
		<!-- nekoAnim-->
		<link rel="stylesheet" type="text/css"
			href="js-plugin/appear/nekoAnim.css">
		<!-- Custom css -->
		<link type="text/css" rel="stylesheet" href="css/layout.css">
		<link type="text/css" id="colors" rel="stylesheet" href="css/light.css">
		<link type="text/css" rel="stylesheet" href="css/custom.css">
		<!--[if lt IE 9]><script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
		<script src="js/modernizr-2.6.1.min.js"></script>
		<!-- Favicons
			================================================== -->
		<link rel="shortcut icon" href="images/favicon.ico">
		<link rel="apple-touch-icon" href="images/apple-touch-icon.png">
		<link rel="apple-touch-icon" sizes="72x72"
			href="images/apple-touch-icon-72x72.png">
		<link rel="apple-touch-icon" sizes="114x114"
			href="images/apple-touch-icon-114x114.png">
		<link rel="apple-touch-icon" sizes="144x144"
			href="images/apple-touch-icon-144x144.png">
	
	
	</head>

	<body onload="returnJSONData();" class="activateAppearAnimation" >
		<!-- Primary Page Layout 
		================================================== -->
		<!-- globalWrapper -->
		<div id="globalWrapper">
			<header class="navbar-fixed-top">
			<div id="mainHeader" role="banner">
				<div class="container">
					<nav class="navbar navbar-default scrollMenu" role="navigation">
					<div class="navbar-header">
						<!-- responsive navigation -->
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target=".navbar-collapse">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<!-- Logo -->
						<a class="navbar-brand" href="index.html"><img
							src="images/main-logo.png" alt="SEATTLE website template" /></a>
					</div>
					<div class="collapse navbar-collapse" id="mainMenu">
						<!-- Main navigation -->
						<ul class="nav navbar-nav pull-right">
	
							<li class="primary"><a href="index.jsp"
								class="firstLevel active">Back to Home</a></li>
							<!-- <li class="sep"></li>
									<li class="primary"> 
										<a href="features-neko-shortcodes.html" class="firstLevel hasSubMenu" >Providers</a>
										<ul class="subMenu">
											<li><a href="features-animations.html">Coursera</a></li>
											<li><a href="features-neko-shortcodes.html">edX</a></li>
											<li><a href="features-footers.html">Udacity</a></li>
										</ul>
									</li>
									<li class="sep"></li>
									<li class="primary">
										<a href="template-menu-left.html" class="firstLevel hasSubMenu">Subjects</a>
										<ul class="subMenu">
											<li><a href="template-menu-left.html">Menu left</a></li>
											<li><a href="template-sidebar-right.html">Sidebar right</a></li>
											<li><a href="template-2-sidebars.html">2 sidebars</a></li>
											<li><a href="template-contact.html">Contact</a></li>
											<li><a href="template-full-width.html">Full width</a></li>
											<li><a href="template-plans.html">Our plans</a></li>
											<li><a href="template-about.html">About us</a></li>
											<li><a href="template-team.html">Our team</a></li>
											<li><a href="template-services.html">Services</a></li>
											<li><a href="template-faq.html">FAQ</a></li>
											<li><a href="template-404.html">404 error</a></li>
											<li class="last"><a href="template-site-map.html">Site map</a></li>
										</ul>
									</li>
									<li class="sep"></li>
									<li class="primary">
										<a href="#" class="firstLevel">Upcoming</a>
									</li>
									<li class="sep"></li> -->
							<!-- <li id="lastMenu" class="last">
										<form class="navbar-form navbar-right" id="nav-search" method="get" action="results.jsp"> 
										      <div class="form-group">
										        <input type="text" name="keyword" id="keyword" class="form-control" placeholder="Search" id="nav-form">
										      </div>
										      <button type="submit" class="btn btn-lg" id="searchButton"><span class="glyphicon glyphicon-search"></span></button>
										</form>
									</li> -->
	
						</ul>
						<!-- End main navigation -->
					</div>
					</nav>
				</div>
			</div>
			</header>
			<!-- header -->
			<!-- ======================================= content ======================================= -->
			<section id="content"> <br />
			<!-- works -->
			<div class="container">
				<div class="row">
					<div class="col-md-12 text-center mb15">
						<h1>Results</h1>
					</div>
				</div>
			</div>
			<section class="nekoDataOwl owl-carousel pb15 imgHover"
				data-neko_items="6" data-neko_navigation="true"
				data-neko_pagination="false" data-neko_mousedrag="true"
				data-nekoanim="fadeInUp" data-nekodelay="100"> <!-- portfolio item -->
			<article class="item" id="allresults"> 
				<!-- <div class="imgWrapper">
					<img alt="" src="http://img.youtube.com/vi/uYzAgwFhxPo/0.jpg" class="img-responsive">
				</div>
				<div class="mediaHover">
					<div class="mask"></div>
					<div class="iconLinks"> 
						<a href="coursera_course_200.html" title="link" class="portfolioSheet sizer">
							<i class="icon-link iconRounded"></i>
						</a> 
						<a href="http://www.youtube.com/watch?v=uYzAgwFhxPo" class="image-iframe" title="Video Popup" >
							<i class="icon-videocam iconRounded"></i>
						</a> 
					</div>
				</div> 
				<div class="boxContent">
							<h3>Introduction to Digital Sound Design</h3>
							<p> <strong>Provider:</strong> Coursera<br />
							<strong>Start Date:</strong> 07/21/14<br />
							Sound has always been a significant part of human experience. It shapes and transforms our everyday world.  Sounds and music are embedded in almost every aspect of daily...<br />
								<a href="portfolio-project-fullwidth-image.html" class="moreLink">&rarr; read more</a>
							</p>
				</div>  -->
						
						
						
						
						
						
						</article> <!-- portfolio item --> </section> <!-- works --> <!-- parallax -->
			<section id="paralaxSlice3" data-stellar-background-ratio="0.5">
			<div class="maskParent">
				<div class="paralaxMask"></div>
				<div class="paralaxText">
					<blockquote>
						<p>
							I would like to make it so that education<br>is a right and
							not a privilege.
						</p>
						<small><cite title="Daphne Koller">Daphne Koller,
								Coursera</cite></small>
					</blockquote>
					<!-- <p>-Daphne Koller, Coursera</p> -->
	
				</div>
			</div>
			</section> <!-- parallax --> <!-- contact --> <section id="contact"
				class="color1 pt40 pb40">
			<div class="container">
				<div class="row">
					<div class="col-md-12 mb15 text-center">
						<h1>Starting Soon</h1>
						<h2 class="subTitle">Courses starting in the near future</h2>
					</div>
					<div class="table-responsive">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>#</th>
									<th>Course Name</th>
									<th>Provider</th>
									<th>Start Date</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td><a href="">AnatomyX: Musculoskeletal Cases</a></td>
									<td><a href="">edX</a></td>
									<td>08/01/2014</td>
								</tr>
								<tr>
									<td>2</td>
									<td><a href="">Learning How to Learn: Powerful mental
											tools to help you master tough subjects</a></td>
									<td><a href="">Coursera</a></td>
									<td>08/01/2014</td>
								</tr>
								<tr>
									<td>3</td>
									<td><a href="">A System View of Communications: From
											Signals to Packets (Part 1)</a></td>
									<td><a href="">edX</a></td>
									<td>08/01/2014</td>
								</tr>
								<tr>
									<td>4</td>
									<td><a href="">Introduction to Linux</a></td>
									<td><a href="">edX</a></td>
									<td>08/01/2014</td>
								</tr>
								<tr>
									<td>5</td>
									<td><a href="">Applying to U.S. Universities</a></td>
									<td><a href="">Coursera</a></td>
									<td>08/03/2014</td>
								</tr>
								<tr>
									<td>6</td>
									<td><a href="">Mathematical Biostatistics Boot Camp 2</a></td>
									<td><a href="">Coursera</a></td>
									<td>08/04/2014</td>
								</tr>
							</tbody>
						</table>
						<center>
							<button type="button" class="btn btn-default btn-lg">see
								more</button>
						</center>
					</div>
				</div>
			</div>
			<!-- contact --> </section> <!-- content --> </section>
			<!-- call to action -->
			<section class="color2">
	
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="ctaBox ctaBoxFullwidth ctaBox2Cols">
							<div class="ctaBoxText" data-nekoanim="fadeInUp"
								data-nekodelay="0">
								<h1>
									<strong>MOOCLINK</strong> collects open courses<br />as linked
									open data.
								</h1>
							</div>
							<div class="ctaBoxBtn" data-nekoanim="fadeInDown"
								data-nekodelay="0">
								<a class="btn btn-lg btn-border" title=""
									href="http://sebk.me:3030" target="blank"> <i
									class="icon-down-open-big"></i> sparql endpoint
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
	
			</section>
			<!-- call to action -->
			<!-- footer -->
			<footer id="footerWrapper" class="footer2"> <section
				id="mainFooter">
			<div class="container">
				<div class="row">
					<div class="col-md-3 col-sm-6">
						<div class="footerWidget">
							<img src="images/main-logo.png" alt="MOOC Link Logo"
								id="footerLogo">
							<p>
								<a href="#"
									title="MOOCLink, Linked Data Online Course Aggregator">MOOCLink</a>
								is an effort to collect open courseware as linked data.
							</p>
						</div>
					</div>
					<div class="col-md-3 col-sm-6">
						<div class="footerWidget">
	
							<h3>Related work</h3>
							<ul class="list-unstyled worksList">
								<li><a
									href="http://tomheath.com/papers/bizer-heath-berners-lee-ijswis-linked-data.pdf"
									class="tips" title=""
									data-original-title="Linked Data - The Story So Far"><img
										src="images/theme-pics/works3.jpg" alt="works"></a></li>
								<li><a href="http://www.lrmi.net/the-specification"
									class="tips" title=""
									data-original-title="Learning Resource Metadata Initiative"><img
										src="images/theme-pics/works3.jpg" alt="works"></a></li>
								<li><a href="#" class="tips" title=""
									data-original-title="Little Neko work"><img
										src="images/theme-pics/works3.jpg" alt="works"></a></li>
								<li><a href="#" class="tips" title=""
									data-original-title="Little Neko work"><img
										src="images/theme-pics/works4.jpg" alt="works"></a></li>
								<li><a href="#" class="tips" title=""
									data-original-title="Little Neko work"><img
										src="images/theme-pics/works5.jpg" alt="works"></a></li>
								<!-- <li><a href="#" class="tips" title="" data-original-title="Little Neko work"><img src="images/theme-pics/works6.jpg" alt="works"></a></li> -->
	
							</ul>
						</div>
					</div>
					<div class="col-md-3 col-sm-6">
						<div class="footerWidget">
	
							<h3>Closer look</h3>
							<ul class="list-unstyled iconList">
								<li><a href="#">GitHub</a></li>
								<li><a href="#">Ontology</a></li>
								<li><a href="#">SPARQL endpoint</a></li>
							</ul>
						</div>
					</div>
					<div class="col-md-3 col-sm-6">
						<div class="footerWidget">
	
							<h3>Contact us</h3>
							<address>
								<p>
									<i class="icon-location"></i>&nbsp;7001 E Williams Field Rd<br>Mesa,
									AZ 85212<br> <i class="icon-phone"></i>&nbsp;812.219.5912 <br>
									<i class="icon-mail-alt"></i>&nbsp;<a
										href="mailto:sakagema@indiana.edu">sakagema@indiana.edu</a>
								</p>
							</address>
						</div>
					</div>
				</div>
			</div>
			</section> <section id="footerRights">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<ul class="socialNetwork">
							<li><a href="#" class="tips" title="follow me on Facebook"><i
									class="icon-facebook-1 iconRounded"></i></a></li>
							<li><a href="#" class="tips" title="follow me on Twitter"><i
									class="icon-twitter-bird iconRounded"></i></a></li>
							<li><a href="#" class="tips" title="follow me on Google+"><i
									class="icon-gplus-1 iconRounded"></i></a></li>
							<li><a href="#" class="tips" title="follow me on Linkedin"><i
									class="icon-linkedin-1 iconRounded"></i></a></li>
							<li><a href="#" class="tips" title="follow me on Dribble"><i
									class="icon-dribbble iconRounded"></i></a></li>
						</ul>
					</div>
					<div class="col-md-12">
						<p>
							Copyright ������ 2014 <a href="http://www.little-neko.com"
								target="blank">Little NEKO</a> / All rights reserved.
						</p>
					</div>
				</div>
			</div>
			</section> </footer>
			<!-- End footer -->
		</div>
		<!-- global wrapper -->
		<!-- End Document 
		================================================== -->
		<script type="text/javascript" src="js-plugin/respond/respond.min.js"></script>
		<script type="text/javascript"
			src="js-plugin/jquery/jquery-1.10.2.min.js"></script>
		<script type="text/javascript"
			src="js-plugin/jquery-ui/jquery-ui-1.8.23.custom.min.js"></script>
		<!-- third party plugins  -->
		<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript"
			src="js-plugin/easing/jquery.easing.1.3.js"></script>
		<!-- carousel -->
		<script type="text/javascript"
			src="js-plugin/owl.carousel/owl-carousel/owl.carousel.min.js"></script>
		<!-- pop up -->
		<script type="text/javascript"
			src="js-plugin/magnific-popup/jquery.magnific-popup.min.js"></script>
		<!-- isotope -->
		<script type="text/javascript"
			src="js-plugin/isotope/jquery.isotope.min.js"></script>
		<!-- form -->
		<script type="text/javascript"
			src="js-plugin/neko-contact-ajax-plugin/js/jquery.form.js"></script>
		<script type="text/javascript"
			src="js-plugin/neko-contact-ajax-plugin/js/jquery.validate.min.js"></script>
		<!-- parallax -->
		<script type="text/javascript"
			src="js-plugin/parallax/js/jquery.stellar.min.js"></script>
		<script type="text/javascript"
			src="js-plugin/parallax/js/jquery.localscroll-1.2.7-min.js"></script>
		<!-- appear -->
		<script type="text/javascript" src="js-plugin/appear/jquery.appear.js"></script>
		<!-- Custom  -->
		<script type="text/javascript" src="js/custom.js"></script>
		<!-- autohide nav bar -->
		<script type="text/javascript"
			src="js-plugin/autohide/jquery.bootstrap-autohidingnavbar.min.js"></script>
		<script>
	      $("header.navbar-fixed-top").autoHidingNavbar();
	    </script>
	    
	    <script type="text/javascript">
	
		/* function getParameterByName(name, url) {
		    if (!url) url = window.location.href;
		    name = name.replace(/[\[\]]/g, "\\$&");
		    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)", "i"),
		        results = regex.exec(url);
		    if (!results) return null;
		    if (!results[2]) return '';
		    return decodeURIComponent(results[2].replace(/\+/g, " "));
		} */
		
			function returnJSONData() {
				alert('alert1');	
				var processData = 'JSON';
				$.ajax({
					type : "POST",
					url : "./search",
					data : {
						processData: processData,
						keyword: getParameterByName('keyword')
					},
					success : function(result) {
						alert(result.length);
						var rowCounter = 0;
		                var course="";
						for(var i = 0; i <= result.length; i++) {
		
		                        if (result[i] !== undefined) {
		
		                            if ( rowCounter == 0) {
		                                course += "<div class=\"row\">";
		                            }
		
		                            course += "<div class=\"boxContent\">";
		                           /*  course += "<a href=\"#\">";
		                           
		                            if(result[i]["image"] === "-"){
		
		                                if(result[i]["courseProvider"] === "khanacademy.com") {
		                                    course += "<img class=\"img-responsive\" src=\"img/ka.png\" alt=\"\">";    
		                                } else {
		                                    course += "<img class=\"img-responsive\" src=\"img/ocw.png\" alt=\"\">";    
		                                }
		                           		
		                            }
		                           	else{
		                           			course += "<img class=\"img-responsive\" src=\""+data[i]["image"]+"\" alt=\"\">";
		                           	} */
		                           // course += "<\/a>";
		                            course += "<h3> "+result[i]["Course_name"]+"<\/h3>";
		                            //course += "<a title=\""+ result[i]["name"] +"\" href=\""+ result[i]["courseLink"] +"\">"+ result[i]["name"] +"<\/a>";
		                            //course += "<\/h3>";
		                            course += "<p> <strong>Provider:<\/strong> Coursera<br \/>"; 
		                            //course += "<div class=\"description\">";
		                            course += result[i]["desc"].replace(/(<([^>]+)>)/ig,"").truncate(100);
		                            course += "<\/div>";
		                            course += "<ul class=\"course-info\">";
		                            course += "<li><strong>Provider:<\/strong> " + result[i]["courseProvider"] + "<\/li>";
		                            course += "<li><strong>Duration:<\/strong> " + result[i]["duration"] + "<\/li>";
		                            // course += "<li><strong>Published:<\/strong> 19 Nov, 2015<\/li>";
		                            // course += "<li><strong>Type:<\/strong> Video<\/li>";
		                            // course += "<li><strong>Name:<\/strong> <a href=\"http:\/\/tutsplus.com\/authors\/adi-purdila\">" + data[i]["categoryName"] + "<\/a><\/li>";
		                           // course += "<li><strong>Categories:<\/strong> " + result[i]["categoryName"] + " <\/li>";
		                           // course += "<li><strong>Price:<\/strong> " + result[i]["price"] + " <\/li>";
		                            //course += "<li><strong>Teacher:<\/strong> " + result[i]["tname"] + " <\/li>";
		                           // course += "<li><strong>Course Type:<\/strong> " + result[i]["type"] + " <\/li>";
		                            course += "<\/ul>";
		                            course += "<\/div>";
		
		                            if (rowCounter == 2) {
		                                course += "</div>";
		                                rowCounter = -1;
		                            }
		
		                            rowCounter++;
		
		
		                        }
		                      
		
		                    }
						  $('#allresults').append(course);
						//console.log(result);
						//makeTable(result);
						/* alert("Data type process: "+result.name
						        +" First Name: "+result.duration) */
		
					},
					error : function(xhr, ajaxOptions, thrownError) {
						alert("Error status code: " + xhr.status);
						alert("Error details: " + thrownError);
					}
				});
		
			}
		
		</script>
	    
	</body>
</html>
