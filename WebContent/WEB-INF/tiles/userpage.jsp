<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<div class="container">
	<div class="row">
		<div class="col-sm-10">
			<h1>
				<c:out value="${user.username}"></c:out>
				s Profile
			</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-4">
			<ul class="list-group">
				<li class="list-group-item text-muted">Profile</li>
				<li class="list-group-item text-right"><span class="pull-left"><strong>Address</strong></span>
					<c:out value="${user.shippingAddress}"></c:out></li>
				<li class="list-group-item text-right"><span class="pull-left"><strong>Loyalty Points</strong></span>
					<c:out value="${user.loyaltyPoints}"></c:out></li>	
			</ul>
		</div>
		<div class="col-sm-11">
			<ul class="nav nav-tabs" id="myTab">
				<li class="active"><a href="#home" data-toggle="tab">User
						Details</a></li>
				<li><a href="#settings" data-toggle="tab"></a></li>
			</ul>

			<div class="tab-content">
				<div class="tab-pane active" id="home">
					<div class="table-responsive">
						<c:if test="${ empty purchaseHistories}">
							<br>
							<p>This user never bought a book</p>
						</c:if>
						<c:if test="${not empty purchaseHistories}">
							<table class="table table-hover">
								<thead>
									<tr>
										<td><b>Date</b></td>
										<td><b>Quantity</b></td>
										<td><b>Book Title</b></td>
										<td><b>Price</b></td>
									</tr>
								</thead>
								<tbody id="items">
									<c:forEach var="purchaseHistories" items="${purchaseHistories}">
										<c:forEach var="lineItem"
											items="${purchaseHistories.shoppingCart.lineItem}">
											<tr>
												<td><c:out value='${purchaseHistories.date}'></c:out></td>
												<td><c:out value='${lineItem.quantity}'></c:out></td>
												<td><c:out value="${lineItem.book.title}"></c:out></td>
												<td><c:out value="${lineItem.book.price}"></c:out></td>
											</tr>
										</c:forEach>
									</c:forEach>
								</tbody>
							</table>
						</c:if>
						<hr>
						<div class="row">
							<div class="col-md-4 col-md-offset-4 text-center">
								<ul class="pagination" id="myPager"></ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	$.fn.pageMe = function(opts) {
		var $this = this, defaults = {
			perPage : 7,
			showPrevNext : false,
			numbersPerPage : 1,
			hidePageNumbers : false
		}, settings = $.extend(defaults, opts);

		var listElement = $this;
		var perPage = settings.perPage;
		var children = listElement.children();
		var pager = $('.pagination');

		if (typeof settings.childSelector != "undefined") {
			children = listElement.find(settings.childSelector);
		}

		if (typeof settings.pagerSelector != "undefined") {
			pager = $(settings.pagerSelector);
		}

		var numItems = children.size();
		var numPages = Math.ceil(numItems / perPage);

		pager.data("curr", 0);

		if (settings.showPrevNext) {
			$('<li><a href="#" class="prev_link">«</a></li>').appendTo(pager);
		}

		var curr = 0;
		while (numPages > curr && (settings.hidePageNumbers == false)) {
			$('<li><a href="#" class="page_link">' + (curr + 1) + '</a></li>')
					.appendTo(pager);
			curr++;
		}

		if (settings.numbersPerPage > 1) {
			$('.page_link').hide();
			$('.page_link').slice(pager.data("curr"), settings.numbersPerPage)
					.show();
		}

		if (settings.showPrevNext) {
			$('<li><a href="#" class="next_link">»</a></li>').appendTo(pager);
		}

		pager.find('.page_link:first').addClass('active');
		if (numPages <= 1) {
			pager.find('.next_link').hide();
		}
		pager.children().eq(1).addClass("active");

		children.hide();
		children.slice(0, perPage).show();

		pager.find('li .page_link').click(function() {
			var clickedPage = $(this).html().valueOf() - 1;
			goTo(clickedPage, perPage);
			return false;
		});
		pager.find('li .prev_link').click(function() {
			previous();
			return false;
		});
		pager.find('li .next_link').click(function() {
			next();
			return false;
		});

		function previous() {
			var goToPage = parseInt(pager.data("curr")) - 1;
			goTo(goToPage);
		}

		function next() {
			goToPage = parseInt(pager.data("curr")) + 1;
			goTo(goToPage);
		}

		function goTo(page) {
			var startAt = page * perPage, endOn = startAt + perPage;

			children.css('display', 'none').slice(startAt, endOn).show();

			if (page >= 1) {
				pager.find('.prev_link').show();
			} else {
				pager.find('.prev_link').hide();
			}

			if (page < (numPages - 1)) {
				pager.find('.next_link').show();
			} else {
				pager.find('.next_link').hide();
			}

			pager.data("curr", page);

			if (settings.numbersPerPage > 1) {
				$('.page_link').hide();
				$('.page_link').slice(page, settings.numbersPerPage + page)
						.show();
			}

			pager.children().removeClass("active");
			pager.children().eq(page + 1).addClass("active");
		}
	};

	$('#items').pageMe({
		pagerSelector : '#myPager',
		childSelector : 'tr',
		showPrevNext : true,
		hidePageNumbers : false,
		perPage : 5
	});
</script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$.fn.stars = function() {
		return this.each(function(i, e) {
			$(e).html($('<span/>').width($(e).text() * 16));
		});
	};
	$('.stars').stars();
</script>