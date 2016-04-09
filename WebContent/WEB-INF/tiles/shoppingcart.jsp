<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form class="form-horizontal" action="${pageContext.request.contextPath}/shippingaddress" method="post">

		<table class="table table-bordered table-striped">
			<tr>
				<td><b>Quantity</b></td>
				<td><b>Book</b></td>
				<td><b>Price</b></td>
			</tr>
			<c:forEach var="user" items="${user}">
				<c:forEach var="lineItem" items="${user.shoppingCart.lineItem}">
					<tr>
						<td><c:out value='${lineItem.quantity}'></c:out></td>
						<td><c:out value="${lineItem.book.title}"></c:out></td>
						<td><c:out value="${lineItem.book.price}"></c:out></td>
					</tr>
				</c:forEach>
			</c:forEach>
		</table>

		<table>
			<tr>
				<td><b>Total Price</b></td>
			</tr>

			<c:forEach var="user" items="${user}">
				<tr>
					<td><c:out value="${user.shoppingCart.totalCost}"></c:out></td>
				</tr>
			</c:forEach>
		</table>
		<div class="form-group">
			<div class="col-md-12 text-right">
				<button type="submit" class="btn btn-primary btn-lg">Complete Order</button>
			</div>
		</div>
	</form>
</body>
</html>