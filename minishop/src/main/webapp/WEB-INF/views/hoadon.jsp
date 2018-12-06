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
	<table class="table">
		<thead>
			<tr>
				<th>Mã hóa đơn</th>
				<th>Tên khách hàng</th>
				<th>Số điện thoại</th>
				<th>Địa chỉ giao hàng</th>
				<th>Tình trạng</th>
				<th>Hình thức giao hàng</th>
				<th>Ghi chú</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="val" items="${listhoadon }">
				<tr>
					<td>${val.getMahoadon() }</td>
					<td>${val.getTenkhachhang() }</td>
					<td>${val.getSodt() }</td>
					<td>${val.getDiachigiaohang() }</td>
					<td>${val.isTinhtrang() }</td>
					<td>${val.getHinhthucgiaohang() }</td>
					<td>${val.getGhichu() }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>		