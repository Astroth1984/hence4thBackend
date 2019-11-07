package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;

import dto.mapper.CommentMapper;
import dto.model.CommentDto;
import models.Comments;
import repositories.CommentRepository;


@Component
public class TreatementMethodsServiceImp implements TreatementMethodsService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private CommentMapper commentMapper;
	
	public CommentDto replaceEmojis(CommentDto commentDto) {
		
		Optional<Comments> comments = Optional.ofNullable(commentRepository.findById(commentDto.getId()));
        if (comments.isPresent()) {
            Comments commentsModel = comments.get();
            
        }    
		
	}
	 CommentDto removeSpecialCaracteres(CommentDto comments) {
		 
	 }
	 CommentDto suppLesAccents(CommentDto comments) {
		 
	 }

}
