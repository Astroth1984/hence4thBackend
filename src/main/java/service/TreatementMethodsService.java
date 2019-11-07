package service;

import dto.model.CommentDto;

public interface TreatementMethodsService {
	
	 CommentDto replaceEmojis(CommentDto commentDto);
	 CommentDto removeSpecialCaracteres(CommentDto commentDto);
	 CommentDto suppLesAccents(CommentDto commentDto);
	 
}
