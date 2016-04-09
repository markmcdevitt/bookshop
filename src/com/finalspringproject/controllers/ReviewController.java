package com.finalspringproject.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finalspringproject.entity.Book;
import com.finalspringproject.entity.Review;
import com.finalspringproject.entity.User;
import com.finalspringproject.service.BookService;
import com.finalspringproject.service.ReviewService;
import com.finalspringproject.service.UsersService;

@Controller
public class ReviewController {

	private BookService bookService;
	private ReviewService reviewService;
	private UsersService userService;

	@RequestMapping("/createreview/{title}")
	public String createReview(Model model, @RequestParam(value = "message") String message,
			@RequestParam(value = "rating-input-1") String rating, @PathVariable String title, Principal principal) {

		List<Book> book = bookService.getBook(title);
		User user = userService.getUser(principal.getName());
		
		Book currentBook = book.get(0);
		Review review = new Review(rating, message, user);
		
		List<Review> reviewList = currentBook.getReview();
		reviewList.add(review);
		int userRating = 0;
		for (Review r : reviewList) {
			userRating += Integer.parseInt(r.getRating());
		}
		int numberOfRatings = reviewList.size();
		double averageRating = userRating / numberOfRatings;
		String totalRating = String.valueOf(averageRating);
		
		currentBook.setTotalRating(totalRating);
		currentBook.setReview(reviewList);


		bookService.saveOrUpdate(currentBook);
		book.clear();
		book.add(currentBook);
		

		model.addAttribute("book", book);
		return "book";
	}

	@Autowired
	public void setUserService(UsersService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	@Autowired
	public void setReviewService(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

}
