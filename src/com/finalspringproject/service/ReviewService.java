package com.finalspringproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalspringproject.dao.ReviewDao;
import com.finalspringproject.entity.Review;

@Service("reviewService")
public class ReviewService {

	private ReviewDao reviewDao;

	@Autowired
	public void setReviewDao(ReviewDao reviewDao) {
		this.reviewDao = reviewDao;
	}

	public void saveOrUpdate(Review review) {
		reviewDao.saveOrUpdate(review);
		
	}
	
	
}
