<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="header.jsp"></jsp:include>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
	<div id="header-chitiet" class="container-fluid">
		<nav class="navbar navbar-default none_nav">
		  <div class="container-fluid">
		    <!-- Brand and toggle get grouped for better mobile display -->
		    <div class="navbar-header">
		      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </button>
		      <a class="navbar-brand" href="#"><img alt="yameshop" src='<c:url value="/resource/Image/icon_yame_shop.png" />' /></a>
		    </div>
		
		    <!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      <ul class="nav navbar-nav navbar-center">
		        
		        <li class="active" ><a href="#">TRANG CHỦ</a></li>
		        <li class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
		          <ul class="dropdown-menu open">
		            <li><a href="#">Action</a></li>
		            <li><a href="#">Another action</a></li>
		            <li><a href="#">Something else here</a></li>
		            <li role="separator" class="divider"></li>
		            <li><a href="#">Separated link</a></li>
		            <li role="separator" class="divider"></li>		
		            <li><a href="#">One more separated link</a></li>
		          </ul>
		        </li>
		        <li><a href="#">DỊCH VỤ</a></li>
		        <li><a href="#">LIÊN HỆ</a></li>
		      </ul>
		      
		      <ul class="nav navbar-nav navbar-right">
		      	<c:choose>
		      		<c:when test="${as!=null}">
		      			<li><a class="circle-avatar" href="http://localhost:8080/minishop/dangnhap"><span>${as }</span></a></li>
		      		</c:when>
		      		
		      		<c:otherwise>
		      			<li><a href="http://localhost:8080/minishop/dangnhap">ĐĂNG NHẬP</a></li>
		      		</c:otherwise>
		      	</c:choose>
		        <li id="giohang"><a href="http://localhost:8080/minishop/giohang"><img alt="icon-xe" src='<c:url value="/resource/Image/icon_google.png" />' />
					<c:choose>
						<c:when test="${soluongsanphamgiohang>0 }">
							<div class="circle-giohang"><span>${soluongsanphamgiohang}</span></div>
						</c:when>
						
						<c:otherwise>
							<div><span>${soluongsanphamgiohang}</span></div>
						</c:otherwise>
					</c:choose>
				
				</a></li>
		        
		      </ul>
		    </div><!-- /.navbar-collapse -->
		  </div><!-- /.container-fluid -->
		</nav>
			
	</div>
	
	<div class="container">
		<div class="row" style="margin-top: 13px">
			<div class="col-sm-2 col-md-2">
				<h3>Danh muc san pham</h3>
				<ul class="mymenu">
					<c:forEach var="value" items="${danhmuc }">
						<li><a href="#">${value.getTendanhmuc() }</a></li>
					</c:forEach>
					
				</ul>
			</div>
			
			<div class="col-sm-8 col-md-8">
				<div class="row">
					<div class="col-sm-4 col-md-4">
						<img style="width: 100%" alt="ada" src='<c:url value="/resource/Image/sanpham/${SanPham.getHinhsanpham() }"/>'/>
					</div>
					
					<div class="col-sm-8 col-md-8">
						<h3 id="tensp" data-masp="${ SanPham.getMasanpham()}">${ SanPham.getTensanpham()}</h3>
						<h4 id="giatien" data-value="${SanPham.getGiatien()}">${SanPham.getGiatien()} VND</h4>
						<table class="table">
							<thead>
								<tr>
									<td><b>Mau san pham</b></td>
									<td><b>Size</b></td>
									<td><b>So luong</b></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="chitietsanpham" items="${SanPham.getChitietsanpham() }">
									<tr>
										<td class="mau" data-mamau="${chitietsanpham.getMausanpham().getMamau()}">${chitietsanpham.getMausanpham().getTenmau() }</td>
										<td class="size" data-masize="${chitietsanpham.getSizesanpham().getMasize()}">${chitietsanpham.getSizesanpham().getSize() }</td>
										<td class="soluong">${chitietsanpham.getSoluong() }</td>
										<td><button data-machitiet="${chitietsanpham.getMachitietsanpham() }" class="btn btn-success btn-giohang">Giỏ hàng</button></td>
									</tr>
									
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				
			</div>
			
			<div class="col-sm-2 col-md-2">
				${SanPham.getMota() }
			</div>
		</div>
		
	</div>
	
	
	
	<div id="footer" class="container-fluid">
		<div class="row">
			<div class="col-sm-4 col-md-4 wow tada">
				<p><span class="title-footer">THÔNG TIN SHOP</span></p>
				<span>Yame là 1 thương hiệu thời trang đầy uy tín, luôn đảm bảo chất lượng tốt nhất cho khách hàng.</span>
			</div>
			<div class="col-sm-4 col-md-4 wow tada">
				<p><span class="title-footer">LIÊN HỆ</span></p>
				<span>43 Nguyễn Trí Thanh, Ba Đình, Hà Nội.</span>
				<span>Email : torihoang1@gmail.com</span>
				<span>0903482094</span>
			</div>
			<div class="col-sm-4 col-md-4 wow tada">
				<p><span class="title-footer">GÓP Ý</span></p>
				<form action="" method="post">
					<input name="tennhanvien" class="material-textinput" style="margin-bottom: 8px" type="text" placeholder="Email"/>
					<textarea name="tuoi" style="margin-bottom: 8px" rows="4" cols="40" placeholder="Nội dung"></textarea>
					<button class="material-primary-bottom">ĐỒNG Ý</button>
				</form>
			</div>
		</div>
	
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>		