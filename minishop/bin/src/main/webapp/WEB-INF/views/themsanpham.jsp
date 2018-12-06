<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE HTML>
<html>
<head>
<title>Pooled Admin Panel Category Flat Bootstrap Responsive Web Template | Home :: w3layouts</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Pooled Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- Bootstrap Core CSS -->
<link rel="stylesheet" href='<c:url value="/resource/template/css/bootstrap.min.css" />'/>
<!-- Custom CSS -->
<link rel="stylesheet" href='<c:url value="/resource/template/css/style.css" />'/>
<link rel="stylesheet" href='<c:url value="/resource/template/css/morris.css" />'/>

<!-- Graph CSS -->
<link rel="stylesheet" href='<c:url value="/resource/template/css/font-awesome.css" />'/>

<!-- jQuery -->
<script src='<c:url value="/resource/jquery/jquery-3.2.1.min.js" />'></script>

<!-- //jQuery -->
<link href='//fonts.googleapis.com/css?family=Roboto:700,500,300,100italic,100,400' rel='stylesheet' type='text/css'/>
<link href='//fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
<!-- lined-icons -->
<link rel="stylesheet" href='<c:url value="/resource/template/css/icon-font.min.css" />'/>

<!-- //lined-icons -->
</head> 
<body>
   <div class="page-container">
   <!--/content-inner-->
<div class="left-content">
	<div class="row">
		<h3>Sản phẩm</h3>
		<div class="btn btn-info"><a href='<c:url value="/hoadon"/>'>Hóa đơn</a></div>
		<form id="form-sanpham" action="">
			<div class="col-md-5 col-sm-12 form-group">
				<label for="tensanpham">Tên sản phẩm</label><br/>
				<input type="text" id="tensanpham" name="tensanpham" class="form-control" placeholder="Nhap ten san pham"/><br/>
				
				<label for="giatien">Giá tiền</label><br/>
				<input type="text" id="giatien" name="giatien" class="form-control" placeholder="Nhap gia tien"/><br/>
				
				<span>Giành cho : </span><br/>
				<label class="radio-inline">
					<input type="radio" name="gianhcho" id="nam" value="nam"/>Nam
				</label>
				<label class="radio-inline">
					<input type="radio" name="gianhcho" id="nu" value="nu"/>Nữ
				</label><br/>
				
				<br/><label for="danhmucsanpham">Danh mục : </label>
					<select name="danhmucsanpham" class="form-control" id="danhmucsanpham">
						<c:forEach var="value" items="${danhmuc }">
							<option value="${value.getMadanhmuc() }">${value.getTendanhmuc() }</option>
						</c:forEach>
					</select><br/>
				
				<label for="mota">Mô tả</label><br/>
				<textarea rows="4" type="text" id="mota" name="mota" class="form-control" placeholder="Nhap mo ta"/></textarea><br/>
				
				<label for="hinhanh">Hình ảnh</label><br/>
				<input type="file" id="hinhanh" name="hinhanh" class="form-control" placeholder="tai anh len"/><br/>
				
				<div id="containerchitietsanpham">
					<div class="chitietsanpham">
						<span>Chi tiết</span><br/>
							<select name="mausanpham" class="form-control" id="mausanpham">
								<c:forEach var="valuemau" items="${listMauSanPham }">
									<option value="${valuemau.getMamau() }">${valuemau.getTenmau() }</option>
								</c:forEach>
							</select><br/>
							
							<select name="sizesanpham" class="form-control" id="sizesanpham">
								<c:forEach var="valuesize" items="${listSizeSanPham }">
									<option value="${valuesize.getMasize() }">${valuesize.getSize() }</option>
								</c:forEach>
							</select><br/>
							
							<input type="number" id="soluong" name="soluong" class="form-control" placeholder="soluong"/><br/>
							<button class="btn btn-primary btn-chitiet">Thêm chi tiết</button>
					</div>
				</div>
				
				<div id="chitietsanpham" class="chitietsanpham">
					<span>Chi tiết</span><br/>
						<select name="mausanpham" class="form-control" id="mausanpham">
							<c:forEach var="valuemau" items="${listMauSanPham }">
								<option value="${valuemau.getMamau() }">${valuemau.getTenmau() }</option>
							</c:forEach>
						</select><br/>
							
						<select name="sizesanpham" class="form-control" id="sizesanpham">
							<c:forEach var="valuesize" items="${listSizeSanPham }">
								<option value="${valuesize.getMasize() }">${valuesize.getSize() }</option>
							</c:forEach>
						</select><br/>						
						<input type="number" id="soluong" name="soluong" class="form-control" placeholder="soluong"/><br/>
							<button class="btn btn-primary btn-chitiet">Thêm chi tiết</button>
				</div>
				
				
				<button id="btnthemsanpham" class="btn btn-primary">Thêm sản phẩm</button>
				<button id="btnupdatesanpham" class="btn btn-primary hidden">Sửa sản phẩm</button>
				<button id="btnthoat" class="btn btn-primary hidden">Thoát</button>
			</div>
		</form>
		
		<div class="col-md-7 col-sm-12">
			<table id="table-sanpham" class="table">
				<thead>
					<tr>
						<th>
							<div class="checkbox">
								<label><input id="checkall" type="checkbox" value=""/></label>
							</div>
						</th>
						<th>Tên sản phẩm</th>
						<th>Giá tiền</th>
						<th>Giành cho</th>
						<th>Chỉnh sửa</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="value" items="${listSanPhams }">
						<tr>
							<td>
								<div class="checkbox">
									<label><input class="checkboxsanpham" type="checkbox" value="${value.getMasanpham() }"/></label>
								</div>
							</td>
							<td class="tensp">${value.getTensanpham() }</td>
							<td class="giatien">${value.getGiatien() }</td>
							<td class="gianhcho">${value.getGianhcho() }</td>
							<td class="btn btn-info" id="sua-sanpham" data-masanpham="${value.getMasanpham() }">Sửa</td>
						</tr>
					</c:forEach>
				</tbody>	
			</table>
			<ul class="pagination pagination-sm">
				<c:forEach var="i" begin="1" end="${tongsopage }">
					<c:choose>
						<c:when test="${i==1 }">
							<li class="paging-item active"><a href="#">${i}</a></li>
						</c:when>
						
						<c:otherwise>
							<li class="paging-item"><a href="#">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
			<div style="float: right; margin-top: 13px; margin-right: 57px">
				<button id="xoa-sanpham" class="btn btn-info">Xóa</button>
			</div>
			
		</div>
	</div>
	   
</div>
  <!--//content-inner-->
			<!--/sidebar-menu-->
				<div class="sidebar-menu">
					<header class="logo1">
						<a href="#" class="sidebar-icon"> <span class="fa fa-bars"></span> </a> 
					</header>
						<div style="border-top:1px ridge rgba(255, 255, 255, 0.15)"></div>
                           <div class="menu">
									<ul id="menu" >
										<li><a href="index.html"><i class="fa fa-tachometer"></i> <span>Dashboard</span><div class="clearfix"></div></a></li>
										
										
										 <li id="menu-academico" ><a href="inbox.html"><i class="fa fa-envelope nav_icon"></i><span>Inbox</span><div class="clearfix"></div></a></li>
									<li><a href="gallery.html"><i class="fa fa-picture-o" aria-hidden="true"></i><span>Gallery</span><div class="clearfix"></div></a></li>
									<li id="menu-academico" ><a href="charts.html"><i class="fa fa-bar-chart"></i><span>Charts</span><div class="clearfix"></div></a></li>
									 <li id="menu-academico" ><a href="#"><i class="fa fa-list-ul" aria-hidden="true"></i><span> Short Codes</span> <span class="fa fa-angle-right" style="float: right"></span><div class="clearfix"></div></a>
										   <ul id="menu-academico-sub" >
										   <li id="menu-academico-avaliacoes" ><a href="icons.html">Icons</a></li>
											<li id="menu-academico-avaliacoes" ><a href="typography.html">Typography</a></li>
											<li id="menu-academico-avaliacoes" ><a href="faq.html">Faq</a></li>
										  </ul>
										</li>
									<li id="menu-academico" ><a href="errorpage.html"><i class="fa fa-exclamation-triangle" aria-hidden="true"></i><span>Error Page</span><div class="clearfix"></div></a></li>
									  <li id="menu-academico" ><a href="#"><i class="fa fa-cogs" aria-hidden="true"></i><span> UI Components</span> <span class="fa fa-angle-right" style="float: right"></span><div class="clearfix"></div></a>
										   <ul id="menu-academico-sub" >
										   <li id="menu-academico-avaliacoes" ><a href="button.html">Buttons</a></li>
											<li id="menu-academico-avaliacoes" ><a href="grid.html">Grids</a></li>
										  </ul>
										</li>
									 <li><a href="tabels.html"><i class="fa fa-table"></i>  <span>Tables</span><div class="clearfix"></div></a></li>
									<li><a href="maps.html"><i class="fa fa-map-marker" aria-hidden="true"></i>  <span>Maps</span><div class="clearfix"></div></a></li>
							        <li id="menu-academico" ><a href="#"><i class="fa fa-file-text-o"></i>  <span>Pages</span> <span class="fa fa-angle-right" style="float: right"></span><div class="clearfix"></div></a>
										 <ul id="menu-academico-sub" >
											<li id="menu-academico-boletim" ><a href="calendar.html">Calendar</a></li>
											<li id="menu-academico-avaliacoes" ><a href="signin.html">Sign In</a></li>
											<li id="menu-academico-avaliacoes" ><a href="signup.html">Sign Up</a></li>
											

										  </ul>
									 </li>
									<li><a href="#"><i class="fa fa-check-square-o nav_icon"></i><span>Forms</span> <span class="fa fa-angle-right" style="float: right"></span><div class="clearfix"></div></a>
									  <ul>
										<li><a href="input.html"> Input</a></li>
										<li><a href="validation.html">Validation</a></li>
									</ul>
									</li>
								  </ul>
								</div>
							  </div>
							  <div class="clearfix"></div>		
							</div>
							<script>
							var toggle = true;
										
							$(".sidebar-icon").click(function() {                
							  if (toggle)
							  {
								$(".page-container").addClass("sidebar-collapsed").removeClass("sidebar-collapsed-back");
								$("#menu span").css({"position":"absolute"});
							  }
							  else
							  {
								$(".page-container").removeClass("sidebar-collapsed").addClass("sidebar-collapsed-back");
								setTimeout(function() {
								  $("#menu span").css({"position":"relative"});
								}, 400);
							  }
											
											toggle = !toggle;
										});
							</script>
<!--js -->
<script src='<c:url value="/resource/template/js/jquery.nicescroll.js" />'></script>
<script src='<c:url value="/resource/template/js/scripts.js" />'></script>
<script src='<c:url value="/resource/jquery/custom.js" />'></script>

<!-- Bootstrap Core JavaScript -->
	<script src='<c:url value="/resource/template/js/bootstrap.min.js" />'></script>
   
   <!-- /Bootstrap Core JavaScript -->	   
<!-- morris JavaScript -->	
<script src='<c:url value="/resource/template/js/raphael-min.js" />'></script>
<script src='<c:url value="/resource/template/js/morris.js" />'></script>

<script>
	$(document).ready(function() {
		//BOX BUTTON SHOW AND CLOSE
	   jQuery('.small-graph-box').hover(function() {
		  jQuery(this).find('.box-button').fadeIn('fast');
	   }, function() {
		  jQuery(this).find('.box-button').fadeOut('fast');
	   });
	   jQuery('.small-graph-box .box-close').click(function() {
		  jQuery(this).closest('.small-graph-box').fadeOut(200);
		  return false;
	   });
	   
	    //CHARTS
	    function gd(year, day, month) {
			return new Date(year, month - 1, day).getTime();
		}
		
		graphArea2 = Morris.Area({
			element: 'hero-area',
			padding: 10,
        behaveLikeLine: true,
        gridEnabled: false,
        gridLineColor: '#dddddd',
        axes: true,
        resize: true,
        smooth:true,
        pointSize: 0,
        lineWidth: 0,
        fillOpacity:0.85,
			data: [	
				{period: '2014 Q1', iphone: 2668, ipad: null, itouch: 2649},
				{period: '2014 Q2', iphone: 15780, ipad: 13799, itouch: 12051},
				{period: '2014 Q3', iphone: 12920, ipad: 10975, itouch: 9910},
				{period: '2014 Q4', iphone: 8770, ipad: 6600, itouch: 6695},
				{period: '2015 Q1', iphone: 10820, ipad: 10924, itouch: 12300},
				{period: '2015 Q2', iphone: 9680, ipad: 9010, itouch: 7891},
				{period: '2015 Q3', iphone: 4830, ipad: 3805, itouch: 1598},
				{period: '2015 Q4', iphone: 15083, ipad: 8977, itouch: 5185},
				{period: '2016 Q1', iphone: 10697, ipad: 4470, itouch: 2038},
				{period: '2016 Q2', iphone: 8442, ipad: 5723, itouch: 1801}
			],
			lineColors:['#ff4a43','#a2d200','#22beef'],
			xkey: 'period',
            redraw: true,
            ykeys: ['iphone', 'ipad', 'itouch'],
            labels: ['All Visitors', 'Returning Visitors', 'Unique Visitors'],
			pointSize: 2,
			hideHover: 'auto',
			resize: true
		});
		
	   
	});
	</script>
</body>
</html>