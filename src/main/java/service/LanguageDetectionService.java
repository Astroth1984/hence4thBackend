package service;

import dto.model.CommentDto;

public interface LanguageDetectionService {
	
	CommentDto LangDetector(CommentDto commentdto);

}
