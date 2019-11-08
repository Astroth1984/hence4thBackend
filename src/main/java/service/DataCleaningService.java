package service;

import dto.model.CommentDto;

public interface DataCleaningService {
	
	//TF-IDF Method
	CommentDto TFIDF(CommentDto commentDto);
	
	
	//COUNTVECTORIZER Method
	CommentDto countVectorizer(CommentDto commentDto);
	
	

}
