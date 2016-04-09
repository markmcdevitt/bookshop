<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="well well-sm">
					<form class="form-horizontal"
						action="${pageContext.request.contextPath}/completeorder"
						method="post">
						<fieldset>
							<legend class="text-center">Confirm Details</legend>
							<c:forEach var="user" items="${user}">
								<div class="form-group">
									<label class="col-md-3 control-label">Name</label>
									<div class="col-md-9">
										<input class="form-control" name="name"
											value="${user.username}" type="text" readonly></input><br />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">Address</label>
									<div class="col-md-9">
										<textarea class="form-control"
											name="address" rows="5" cols="5">${user.shippingAddress}</textarea>
										<br />
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-3 control-label">Total Price</label>
									<div class="col-md-9">
										<input class="form-control"
											value="${user.shoppingCart.totalCost}" type="text" readonly/>
									</div>
								</div>
							</c:forEach>
							<div>
								<button type="button" class="btn btn-info btn-lg"
									data-toggle="modal" data-target="#myModal">Open Modal</button>
							</div>
						</fieldset>
						<div class="modal fade" id="myModal" role="dialog">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-body">
										<fieldset>
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<legend>Payment</legend>
											<div class="form-group">
												<label class="col-sm-3 control-label" for="card-holder-name">Name
													on Card</label>
												<div class="col-sm-9">
													<input type="text" class="form-control"
														name="card-holder-name" id="card-holder-name"
														placeholder="Card Holder's Name">
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label" for="card-number">Card
													Number</label>
												<div class="col-sm-9">
													<input type="text" class="form-control" name="card-number"
														id="card-number" placeholder="Debit/Credit Card Number">
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label" for="expiry-month">Expiration
													Date</label>
												<div class="col-sm-9">
													<div class="row">
														<div class="col-xs-3">
															<select class="form-control col-sm-2" name="expiry-month"
																id="expiry-month">
																<option>Month</option>
																<option value="01">Jan (01)</option>
																<option value="02">Feb (02)</option>
																<option value="03">Mar (03)</option>
																<option value="04">Apr (04)</option>
																<option value="05">May (05)</option>
																<option value="06">June (06)</option>
																<option value="07">July (07)</option>
																<option value="08">Aug (08)</option>
																<option value="09">Sep (09)</option>
																<option value="10">Oct (10)</option>
																<option value="11">Nov (11)</option>
																<option value="12">Dec (12)</option>
															</select>
														</div>
														<div class="col-xs-3">
															<select class="form-control" name="expiry-year">
																<option value="16">2016</option>
																<option value="17">2017</option>
																<option value="18">2018</option>
																<option value="19">2019</option>
																<option value="20">2020</option>
																<option value="21">2021</option>
																<option value="22">2022</option>
																<option value="23">2023</option>
															</select>
														</div>
													</div>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label" for="cvv">Card
													CVV</label>
												<div class="col-sm-3">
													<input type="text" class="form-control" name="cvv" id="cvv"
														placeholder="Security Code">
												</div>
											</div>
											<div class="form-group">
												<div class="modal-footer">
													<button type="submit" class="btn btn-primary btn-lg">Submit</button>
												</div>
											</div>
										</fieldset>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>