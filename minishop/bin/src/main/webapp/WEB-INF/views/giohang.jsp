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
		      			<li><a class="circle-avatar" href="dangnhap"><span>${as }</span></a></li>
		      		</c:when>
		      		
		      		<c:otherwise>
		      			<li><a href="dangnhap">ĐĂNG NHẬP</a></li>
		      		</c:otherwise>
		      	</c:choose>
		        <li id="giohang"><a href="#"><img alt="icon-xe" src='<c:url value="/resource/Image/icon_google.png" />' />
					<c:choose>
						<c:when test="${soluongsanphamgiohang>0 }">
							<div class="circle-giohang">${soluongsanphamgiohang}</span></div>
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
		<div class="row">
			<div class="col-md-6 col-sm-12">
				<h3>Danh Sach San Pham Trong Gio Hang</h3>
				<table class="table">
					<thead>
						<tr>
							<td><h5>Ten san pham</h5></td>
							<td><h5>Mau san pham</h5></td>
							<td><h5>Size</h5></td>
							<td><h5>So luong</h5></td>
							<td><h5>Gia tien</h5></td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="value" items="${giohang }">
							<tr>
								<td class="tensp" data-masp="${value.getMasp()}">${value.getTensp()} </td>
								<td class="mau" data-mamau="${value.getMamau()}">${value.getTenmau() }</td>
								<td class="size" data-masize="${value.getMasize()}">${value.getTensize() }</td>
								<td class="soluong"><input class="soluong-giohang" type="number" min="1" value="${value.getSoluong() }"/></td>
								<td class="giatien" data-value="${value.getGiatien() }">${value.getGiatien() } </td>
								<td><button class="btn btn-danger xoa-giohang">Xoa</button></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<h4>Tổng tiền : <span style="color: red" id="tongtien"></span></h4>
			</div>
			<div class="col-md-6 col-sm-12">
				<h3>Thong Tin Nguoi Mua</h3>
				<div class="form-group">
					<form action="" method="post">
						<label for="tenkhachhang">Ten nguoi mua</label>
						<input class="form-control" id="tenkhachhang" name="tenkhachhang"/>
						
						<label for="sodt">Dien thoai lien lac</label>
						<input class="form-control" id="sodt" name="sodt"/>
						
						<div class="radio">
							<label><input checked="" type="radio" name="hinhthucgiaohang" value="Giao hang tan noi"/>Giao hang tan noi</label>
						</div>
						<div class="radio">
							<label><input type="radio" name="hinhthucgiaohang" value="Nhan hang tai cua hang"/>Nhan hang tai cua hang</label>
						</div>
						
						<label for="diachigiaohang">Dia chi nhan hang</label>
						<input class="form-control" id="diachigiaohang" name="diachigiaohang"/>
						
						<label for="ghichu">Comment :</label>
						<textarea class="form-control" rows="4" id="ghichu" name="ghichu"></textarea>
						
						<input type="submit" class="btn btn-primary" value="Đặt hàng"/>
					</form>
					
				</div>
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